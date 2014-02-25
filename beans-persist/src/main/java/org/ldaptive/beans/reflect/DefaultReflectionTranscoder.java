/*
  $Id$

  Copyright (C) 2003-2014 Virginia Tech.
  All rights reserved.

  SEE LICENSE FOR MORE INFORMATION

  Author:  Middleware Services
  Email:   middleware@vt.edu
  Version: $Revision$
  Updated: $Date$
*/
package org.ldaptive.beans.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.ldaptive.io.BooleanValueTranscoder;
import org.ldaptive.io.ByteArrayValueTranscoder;
import org.ldaptive.io.CharArrayValueTranscoder;
import org.ldaptive.io.DoubleValueTranscoder;
import org.ldaptive.io.FloatValueTranscoder;
import org.ldaptive.io.IntegerValueTranscoder;
import org.ldaptive.io.LongValueTranscoder;
import org.ldaptive.io.ObjectValueTranscoder;
import org.ldaptive.io.ShortValueTranscoder;
import org.ldaptive.io.StringValueTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default implementation of a reflection transcoder. Determines the correct
 * underlying reflection transcoder by inspecting the class type
 * characteristics.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class DefaultReflectionTranscoder implements ReflectionTranscoder
{

  /** Logger for this class. */
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  /** Transcoder for this type. */
  private final ReflectionTranscoder valueTranscoder;

  /** Set of transcoders support single values. */
  private final Set<SingleValueReflectionTranscoder<?>> singleValueTranscoders =
    new LinkedHashSet<SingleValueReflectionTranscoder<?>>();


  /**
   * Creates a new default reflection transcoder.
   *
   * @param  type  of object to transcode
   */
  public DefaultReflectionTranscoder(final Type type)
  {
    // Order matters due to implicit type conversion
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<String>(
      new StringValueTranscoder()));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Boolean>(
      new BooleanValueTranscoder(true)));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Boolean>(
      new BooleanValueTranscoder()));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Short>(
      new ShortValueTranscoder(true)));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Short>(
      new ShortValueTranscoder()));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Integer>(
      new IntegerValueTranscoder(true)));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Integer>(
      new IntegerValueTranscoder()));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Long>(
      new LongValueTranscoder(true)));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Long>(
      new LongValueTranscoder()));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Float>(
      new FloatValueTranscoder(true)));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Float>(
      new FloatValueTranscoder()));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Double>(
      new DoubleValueTranscoder(true)));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Double>(
      new DoubleValueTranscoder()));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<byte[]>(
      new ByteArrayValueTranscoder()));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<char[]>(
      new CharArrayValueTranscoder()));
    singleValueTranscoders.add(new SingleValueReflectionTranscoder<Object>(
      new ObjectValueTranscoder()));

    if (type instanceof Class) {
      final Class<?> c = (Class<?>) type;
      if (c.isArray()) {
        if (byte[].class == c || char[].class == c) {
          valueTranscoder = getSingleValueReflectionTranscoder(c);
        } else {
          valueTranscoder = new ArrayReflectionTranscoder(
            getSingleValueReflectionTranscoder(c.getComponentType()));
        }
      } else if (Collection.class.isAssignableFrom(c)) {
        valueTranscoder = getCollectionEncoder(c, Object.class);
      } else {
        valueTranscoder = getSingleValueReflectionTranscoder(c);
      }
    } else if (type instanceof ParameterizedType) {
      final ParameterizedType pt = (ParameterizedType) type;
      final Type rawType = pt.getRawType();
      final Type[] typeArgs = pt.getActualTypeArguments();
      if (typeArgs.length != 1) {
        throw new IllegalArgumentException(
          "Unsupported type arguments: " + Arrays.toString(typeArgs));
      }
      final Class<?> rawClass = ReflectionUtils.classFromType(rawType);
      if (typeArgs[0] instanceof GenericArrayType) {
        final GenericArrayType gat = (GenericArrayType) typeArgs[0];
        if (Collection.class.isAssignableFrom(rawClass)) {
          valueTranscoder = getCollectionEncoder(rawClass, gat);
        } else {
          throw new IllegalArgumentException("Unsupported type: " + rawClass);
        }
      } else if (typeArgs[0] instanceof Class) {
        if (Collection.class.isAssignableFrom(rawClass)) {
          valueTranscoder = getCollectionEncoder(rawClass, typeArgs[0]);
        } else {
          throw new IllegalArgumentException("Unsupported type: " + rawClass);
        }
      } else {
        throw new IllegalArgumentException("Unsupported type: " + rawClass);
      }
    } else {
      throw new IllegalArgumentException("Unsupported type: " + type);
    }
  }


  /**
   * Returns the appropriate single value encoder for the supplied type.
   *
   * @param  type  to provide a single value encoder for
   *
   * @return  single value reflection transcoder
   */
  protected SingleValueReflectionTranscoder getSingleValueReflectionTranscoder(
    final Class<?> type)
  {
    for (SingleValueReflectionTranscoder t : singleValueTranscoders) {
      if (t.supports(type)) {
        return t;
      }
    }
    throw new IllegalArgumentException("Unsupported type: " + type);
  }


  /**
   * Returns the appropriate collection encoder for the supplied type.
   *
   * @param  type  to provide a collection encoder for
   * @param  genericType  of the collection
   *
   * @return  reflection transcoder for a collection
   */
  protected ReflectionTranscoder getCollectionEncoder(
    final Class<?> type,
    final Type genericType)
  {
    Class<?> genericClass;
    boolean isGenericArray = false;
    if (genericType instanceof GenericArrayType) {
      final Class<?> c = ReflectionUtils.classFromType(
        ((GenericArrayType) genericType).getGenericComponentType());
      if (Byte.TYPE == c) {
        genericClass = byte[].class;
      } else if (Character.TYPE == c) {
        genericClass = char[].class;
      } else {
        genericClass = c;
        isGenericArray = true;
      }
    } else {
      genericClass = ReflectionUtils.classFromType(genericType);
    }

    ReflectionTranscoder encoder;
    if (type == Collection.class || List.class.isAssignableFrom(type)) {
      if (isGenericArray) {
        encoder = new ListReflectionTranscoder(
          type,
          new ArrayReflectionTranscoder(
            getSingleValueReflectionTranscoder(genericClass)));
      } else {
        encoder = new ListReflectionTranscoder(
          type,
          getSingleValueReflectionTranscoder(genericClass));
      }
    } else if (Set.class.isAssignableFrom(type)) {
      if (isGenericArray) {
        encoder = new SetReflectionTranscoder(
          type,
          new ArrayReflectionTranscoder(
            getSingleValueReflectionTranscoder(genericClass)));
      } else {
        encoder = new SetReflectionTranscoder(
          type,
          getSingleValueReflectionTranscoder(genericClass));
      }
    } else {
      throw new IllegalArgumentException(
        "Unsupported type: " + type + " with generic type: " + genericType);
    }
    return encoder;
  }


  /** {@inheritDoc} */
  @Override
  public Object decodeStringValues(final Collection<String> values)
  {
    return valueTranscoder.decodeStringValues(values);
  }


  /** {@inheritDoc} */
  @Override
  public Object decodeBinaryValues(final Collection<byte[]> values)
  {
    return valueTranscoder.decodeBinaryValues(values);
  }


  /** {@inheritDoc} */
  @Override
  public Collection<String> encodeStringValues(final Object values)
  {
    return valueTranscoder.encodeStringValues(values);
  }


  /** {@inheritDoc} */
  @Override
  public Collection<byte[]> encodeBinaryValues(final Object values)
  {
    return valueTranscoder.encodeBinaryValues(values);
  }


  /** {@inheritDoc} */
  @Override
  public Class<?> getType()
  {
    return valueTranscoder.getType();
  }


  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return String.format(
      "[%s@%d::valueTranscoder=%s]",
      getClass().getName(),
      hashCode(),
      valueTranscoder);
  }
}

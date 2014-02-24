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
package org.ldaptive.io;

/**
 * Decodes and encodes a float for use in an ldap attribute value.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class FloatValueTranscoder
  extends AbstractPrimitiveValueTranscoder<Float>
{


  /** Default constructor. */
  public FloatValueTranscoder() {}


  /**
   * Creates a new float value transcoder.
   *
   * @param  b  whether this transcoder is operating on a primitive
   */
  public FloatValueTranscoder(final boolean b)
  {
    setPrimitive(b);
  }


  /** {@inheritDoc} */
  @Override
  public Float decodeStringValue(final String value)
  {
    return Float.valueOf(value);
  }


  /** {@inheritDoc} */
  @Override
  public Class<Float> getType()
  {
    return isPrimitive() ? float.class : Float.class;
  }
}

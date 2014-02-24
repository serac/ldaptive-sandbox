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

import org.ldaptive.LdapUtils;

/**
 * Base class for primitive value transcoders.
 *
 * @param  <T>  type of object to transcode
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public abstract class AbstractPrimitiveValueTranscoder<T>
  implements ValueTranscoder<T>
{

  /** Whether this transcoder operates on a primitive or an object. */
  private boolean primitive;


  /**
   * Returns whether this transcoder operates on a primitive value.
   *
   * @return  whether this transcoder operates on a primitive value
   */
  public boolean isPrimitive()
  {
    return primitive;
  }


  /**
   * Sets whether this transcoder operates on a primitive value.
   *
   * @param  b  whether this transcoder operates on a primitive value
   */
  public void setPrimitive(final boolean b)
  {
    primitive = b;
  }


  /** {@inheritDoc} */
  @Override
  public String encodeStringValue(final T value)
  {
    return value.toString();
  }


  /** {@inheritDoc} */
  @Override
  public T decodeBinaryValue(final byte[] value)
  {
    return decodeStringValue(LdapUtils.utf8Encode(value));
  }


  /** {@inheritDoc} */
  @Override
  public byte[] encodeBinaryValue(final T value)
  {
    return LdapUtils.utf8Encode(encodeStringValue(value));
  }
}

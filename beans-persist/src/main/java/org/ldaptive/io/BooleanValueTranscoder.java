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
 * Decodes and encodes a boolean for use in an ldap attribute value.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class BooleanValueTranscoder
  extends AbstractPrimitiveValueTranscoder<Boolean>
{


  /** Default constructor. */
  public BooleanValueTranscoder() {}


  /**
   * Creates a new boolean value transcoder.
   *
   * @param  b  whether this transcoder is operating on a primitive
   */
  public BooleanValueTranscoder(final boolean b)
  {
    setPrimitive(b);
  }


  /** {@inheritDoc} */
  @Override
  public Boolean decodeStringValue(final String value)
  {
    return Boolean.valueOf(value);
  }


  /** {@inheritDoc} */
  @Override
  public Class<Boolean> getType()
  {
    return isPrimitive() ? boolean.class : Boolean.class;
  }
}

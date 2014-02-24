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
 * Decodes and encodes a byte array for use in an ldap attribute value.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class ByteArrayValueTranscoder implements ValueTranscoder<byte[]>
{


  /** {@inheritDoc} */
  @Override
  public byte[] decodeStringValue(final String value)
  {
    return LdapUtils.utf8Encode(value);
  }


  /** {@inheritDoc} */
  @Override
  public byte[] decodeBinaryValue(final byte[] value)
  {
    return value;
  }


  /** {@inheritDoc} */
  @Override
  public String encodeStringValue(final byte[] value)
  {
    return LdapUtils.utf8Encode(value);
  }


  /** {@inheritDoc} */
  @Override
  public byte[] encodeBinaryValue(final byte[] value)
  {
    return value;
  }


  /** {@inheritDoc} */
  @Override
  public Class<byte[]> getType()
  {
    return byte[].class;
  }
}

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
 * Decodes and encodes a string for use in an ldap attribute value.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class StringValueTranscoder implements ValueTranscoder<String>
{


  /** {@inheritDoc} */
  @Override
  public String decodeStringValue(final String value)
  {
    return value;
  }


  /** {@inheritDoc} */
  @Override
  public String decodeBinaryValue(final byte[] value)
  {
    return LdapUtils.utf8Encode(value);
  }


  /** {@inheritDoc} */
  @Override
  public String encodeStringValue(final String value)
  {
    return value;
  }


  /** {@inheritDoc} */
  @Override
  public byte[] encodeBinaryValue(final String value)
  {
    return LdapUtils.utf8Encode(value);
  }


  /** {@inheritDoc} */
  @Override
  public Class<String> getType()
  {
    return String.class;
  }
}

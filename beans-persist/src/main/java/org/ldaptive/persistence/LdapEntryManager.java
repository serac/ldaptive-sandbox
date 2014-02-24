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
package org.ldaptive.persistence;

import org.ldaptive.LdapException;
import org.ldaptive.Response;

/**
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public interface LdapEntryManager<T>
{


  /**
   * Searches for the supplied object in an LDAP and returns the object mapped
   * with it's ldap attribute properties set.
   *
   * @param  object  to find
   *
   * @return  mapped object
   *
   * @throws  LdapException  if the object cannot be found
   */
  T find(T object)
    throws LdapException;


  Response<Void> add(T object)
    throws LdapException;


  Response<Void> merge(T object)
    throws LdapException;


  Response<Void> delete(T object)
    throws LdapException;
}

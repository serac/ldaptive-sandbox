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

import org.ldaptive.AddOperation;
import org.ldaptive.AddRequest;
import org.ldaptive.Connection;
import org.ldaptive.ConnectionFactory;
import org.ldaptive.DeleteOperation;
import org.ldaptive.DeleteRequest;
import org.ldaptive.LdapException;
import org.ldaptive.Response;
import org.ldaptive.SearchOperation;
import org.ldaptive.SearchRequest;
import org.ldaptive.SearchResult;
import org.ldaptive.beans.LdapEntryMapper;
import org.ldaptive.ext.MergeOperation;
import org.ldaptive.ext.MergeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class DefaultLdapEntryManager<T> implements LdapEntryManager<T>
{

  /** Logger for this class. */
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  private final LdapEntryMapper<T> ldapEntryMapper;

  private final ConnectionFactory connectionFactory;


  public DefaultLdapEntryManager(
    final LdapEntryMapper<T> mapper,
    final ConnectionFactory factory)
  {
    ldapEntryMapper = mapper;
    connectionFactory = factory;
  }


  protected LdapEntryMapper<T> getLdapEntryMapper()
  {
    return ldapEntryMapper;
  }


  protected ConnectionFactory getConnectionFactory()
  {
    return connectionFactory;
  }


  /** {@inheritDoc} */
  @Override
  public T find(final T object)
    throws LdapException
  {
    final String dn = getLdapEntryMapper().getDn(object);
    final SearchRequest request = SearchRequest.newObjectScopeSearchRequest(dn);
    final Connection conn = getConnectionFactory().getConnection();
    try {
      conn.open();
      final SearchOperation search = new SearchOperation(conn);
      final Response<SearchResult> response = search.execute(request);
      if (response.getResult().size() == 0) {
        throw new IllegalArgumentException(
          String.format(
            "Unable to find ldap entry %s, no entries returned: %s",
            dn,
            response));
      }
      if (response.getResult().size() > 1) {
        throw new IllegalArgumentException(
          String.format(
            "Unable to find ldap entry %s, multiple entries returned: %s",
            dn,
            response));
      }
      getLdapEntryMapper().map(object, response.getResult().getEntry());
    } finally {
      if (conn != null) {
        conn.close();
      }
    }
    return object;
  }


  /** {@inheritDoc} */
  @Override
  public Response<Void> add(final T object)
    throws LdapException
  {
    final org.ldaptive.LdapEntry entry = new org.ldaptive.LdapEntry();
    getLdapEntryMapper().map(object, entry);
    final AddRequest request = new AddRequest(
      entry.getDn(), entry.getAttributes());
    final Connection conn = getConnectionFactory().getConnection();
    try {
      conn.open();
      final AddOperation add = new AddOperation(conn);
      return add.execute(request);
    } finally {
      if (conn != null) {
        conn.close();
      }
    }
  }


  /** {@inheritDoc} */
  @Override
  public Response<Void> merge(final T object)
    throws LdapException
  {
    final org.ldaptive.LdapEntry entry = new org.ldaptive.LdapEntry();
    getLdapEntryMapper().map(object, entry);
    final MergeRequest request = new MergeRequest(entry);
    final Connection conn = getConnectionFactory().getConnection();
    try {
      conn.open();
      final MergeOperation merge = new MergeOperation(conn);
      return merge.execute(request);
    } finally {
      if (conn != null) {
        conn.close();
      }
    }
  }


  /** {@inheritDoc} */
  @Override
  public Response<Void> delete(final T object)
    throws LdapException
  {
    // TODO create delete request from object
    final org.ldaptive.LdapEntry entry = new org.ldaptive.LdapEntry();
    getLdapEntryMapper().map(object, entry);
    final DeleteRequest request = new DeleteRequest(entry.getDn());
    final Connection conn = getConnectionFactory().getConnection();
    try {
      conn.open();
      final DeleteOperation delete = new DeleteOperation(conn);
      return delete.execute(request);
    } finally {
      if (conn != null) {
        conn.close();
      }
    }
  }
}

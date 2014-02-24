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
package org.ldaptive.beans;

import org.ldaptive.LdapEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base implementation of an ldap entry mapper. Uses a
 * {@link ClassDescriptor} for decoding and encoding of objects.
 *
 * @param  <T>  type of object to map
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public abstract class AbstractLdapEntryMapper<T>
  implements LdapEntryMapper<T>
{

  /** Logger for this class. */
  protected final Logger logger = LoggerFactory.getLogger(getClass());


  /**
   * Returns the class descriptor.
   *
   * @param  object  to return the class descriptor for
   *
   * @return  class descriptor
   */
  protected abstract ClassDescriptor getClassDescriptor(final T object);


  /** {@inheritDoc} */
  @Override
  public String getDn(final T object)
  {
    final ClassDescriptor descriptor = getClassDescriptor(object);
    final DnValueMutator accessor = descriptor.getDnValueMutator();
    return accessor.getValue(object);
  }


  /** {@inheritDoc} */
  @Override
  public void map(final T source, final LdapEntry dest)
  {
    logger.debug("map {} to {}", source, dest);
    final ClassDescriptor descriptor = getClassDescriptor(source);
    final DnValueMutator dnMutator = descriptor.getDnValueMutator();
    if (dnMutator != null) {
      dest.setDn(dnMutator.getValue(source));
    }
    for (AttributeValueMutator mutator :
         descriptor.getAttributeValueMutators()) {
      logger.debug("using mutator {}", mutator);
      if (mutator != null) {
        final org.ldaptive.LdapAttribute attr = new org.ldaptive.LdapAttribute(
          mutator.getSortBehavior(), mutator.isBinary());
        attr.setName(mutator.getName());
        if (attr.isBinary()) {
          attr.addBinaryValues(mutator.getBinaryValues(source));
        } else {
          attr.addStringValues(mutator.getStringValues(source));
        }
        dest.addAttribute(attr);
      }
    }
  }


  /** {@inheritDoc} */
  @Override
  public void map(final LdapEntry source, final T dest)
  {
    logger.debug("map {} to {}", source, dest);
    final ClassDescriptor descriptor = getClassDescriptor(dest);
    final DnValueMutator dnMutator = descriptor.getDnValueMutator();
    if (dnMutator != null) {
      dnMutator.setValue(dest, source.getDn());
    }
    for (org.ldaptive.LdapAttribute attr : source.getAttributes()) {
      final AttributeValueMutator mutator = descriptor.getAttributeValueMutator(
        attr.getName());
      logger.debug("using mutator {} for attribute {}", mutator, attr);
      if (mutator != null) {
        if (attr.isBinary()) {
          mutator.setBinaryValues(dest, attr.getBinaryValues());
        } else {
          mutator.setStringValues(dest, attr.getStringValues());
        }
      }
    }
  }
}

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
package org.ldaptive.beans.spring;

import org.ldaptive.beans.AbstractLdapEntryMapper;
import org.ldaptive.beans.ClassDescriptor;

/**
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class SpringLdapEntryMapper<T> extends AbstractLdapEntryMapper<T>
{


  /** {@inheritDoc} */
  @Override
  protected ClassDescriptor getClassDescriptor(final T object)
  {
    final SpringClassDescriptor<T> descriptor = new SpringClassDescriptor<T>(
      object);
    descriptor.initialize(object.getClass());
    return descriptor;
  }
}

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

import java.util.HashMap;
import java.util.Map;
import org.ldaptive.beans.AbstractLdapEntryMapper;
import org.ldaptive.beans.ClassDescriptor;

/**
 * Stores the class descriptors for a specific object in a static map.
 *
 * @param  <T>  type of object to map
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class DefaultLdapEntryMapper<T> extends AbstractLdapEntryMapper<T>
{

  /** Class descriptors for mapping objects. */
  private static final Map<Class, ClassDescriptor> CLASS_DESCRIPTORS =
    new HashMap<Class, ClassDescriptor>();


  /** {@inheritDoc} */
  @Override
  protected ClassDescriptor getClassDescriptor(final T object)
  {
    ClassDescriptor descriptor = null;
    if (object != null) {
      final Class<?> clazz = object.getClass();
      synchronized (CLASS_DESCRIPTORS) {
        if (!CLASS_DESCRIPTORS.containsKey(clazz)) {
          descriptor = new DefaultClassDescriptor();
          descriptor.initialize(clazz);
          CLASS_DESCRIPTORS.put(clazz, descriptor);
        } else {
          descriptor = CLASS_DESCRIPTORS.get(clazz);
        }
      }
    }
    return descriptor;
  }
}

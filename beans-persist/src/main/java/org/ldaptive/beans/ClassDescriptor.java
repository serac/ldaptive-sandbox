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

import java.util.Collection;

/**
 * Describes the attribute mutators and DN accessor for a specific type.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public interface ClassDescriptor
{


  /**
   * Prepare this class descriptor for use.
   *
   * @param  type  of object to describe
   */
  void initialize(Class<?> type);


  /**
   * Returns the DN accessor for this type.
   *
   * @return  dn accessor
   */
  DnValueMutator getDnValueMutator();


  /**
   * Returns the attribute value mutators for this type.
   *
   * @return  value mutators
   */
  Collection<AttributeValueMutator> getAttributeValueMutators();


  /**
   * Returns the attribute value mutator for the attribute with the supplied
   * name.
   *
   * @param  name  of the attribute
   *
   * @return  value mutator
   */
  AttributeValueMutator getAttributeValueMutator(String name);
}

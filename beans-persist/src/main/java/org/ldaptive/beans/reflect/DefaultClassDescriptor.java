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

import org.ldaptive.beans.AbstractClassDescriptor;
import org.ldaptive.beans.Attribute;
import org.ldaptive.beans.AttributeValueMutator;
import org.ldaptive.beans.Entry;

/**
 * Default implementation of a class descriptor. Reads the {@link Entry}
 * annotation and sets the appropriate DN and attribute value mutators.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class DefaultClassDescriptor extends AbstractClassDescriptor
{


  /** {@inheritDoc} */
  @Override
  public void initialize(final Class<?> type)
  {
    // check for entry annotation
    final Entry entryAnnotation = type.getAnnotation(Entry.class);
    if (entryAnnotation != null) {

      // add any method descriptors that match attributes
      final MethodClassDescriptor methodDescriptor =
        new MethodClassDescriptor();
      methodDescriptor.initialize(type);
      if (methodDescriptor.getDnValueMutator() != null) {
        setDnValueMutator(methodDescriptor.getDnValueMutator());
      }
      addAttributeValueMutator(methodDescriptor.getAttributeValueMutators());

      // add any field descriptors that aren't available as method mutators
      final FieldClassDescriptor fieldDescriptor = new FieldClassDescriptor();
      fieldDescriptor.initialize(type);
      if (getDnValueMutator() == null &&
          fieldDescriptor.getDnValueMutator() != null) {
        setDnValueMutator(fieldDescriptor.getDnValueMutator());
      }
      for (AttributeValueMutator mutator :
           fieldDescriptor.getAttributeValueMutators()) {
        if (getAttributeValueMutator(mutator.getName()) == null) {
          addAttributeValueMutator(mutator);
        }
      }

      // add any hard coded attributes that did not have a property declaration
      for (final Attribute attr : entryAnnotation.attributes()) {
        if ("".equals(attr.property())) {
          addAttributeValueMutator(
            new SimpleAttributeValueMutator(
              attr.name(),
              attr.values(),
              attr.binary(),
              attr.sortBehavior()));
        }
      }

      // if no DN mutator has been set, use the value in the annotation
      if (getDnValueMutator() == null) {
        setDnValueMutator(new SimpleDnValueMutator(entryAnnotation.dn()));
      }
    }
  }
}

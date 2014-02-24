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

import java.util.Collection;
import org.ldaptive.SortBehavior;
import org.ldaptive.beans.AbstractClassDescriptor;
import org.ldaptive.beans.Attribute;
import org.ldaptive.beans.AttributeValueMutator;
import org.ldaptive.beans.DnValueMutator;
import org.ldaptive.beans.Entry;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class SpringClassDescriptor<T> extends AbstractClassDescriptor
{

  /** Context for evaluating spring expressions. */
  private final EvaluationContext context;


  /**
   * Creates a new spring class descriptor.
   *
   * @param  object  to describe
   */
  public SpringClassDescriptor(final T object)
  {
    context = new StandardEvaluationContext(object);
  }


  /** {@inheritDoc} */
  @Override
  public void initialize(final Class<?> type)
  {
    // check for entry annotation
    final Entry entryAnnotation = AnnotationUtils.findAnnotation(
      type, Entry.class);
    if (entryAnnotation != null) {
      if (!entryAnnotation.dn().equals("")) {
        setDnValueMutator(new DnValueMutator() {
          @Override
          public String getValue(final Object object)
          {
            final ExpressionParser parser = new SpelExpressionParser();
            final Expression exp = parser.parseExpression(entryAnnotation.dn());
            return exp.getValue(context, object, String.class);
          }

          @Override
          public void setValue(final Object object, final String value) {}
        });
      }
      for (final Attribute attr : entryAnnotation.attributes()) {
        final String expr = attr.property();
        final ExpressionParser parser = new SpelExpressionParser();
        final Expression exp = parser.parseExpression(expr);
        addAttributeValueMutator(new AttributeValueMutator()
        {
          @Override
          public String getName()
          {
            return attr.name();
          }

          @Override
          public boolean isBinary()
          {
            return attr.binary();
          }

          @Override
          public SortBehavior getSortBehavior()
          {
            return attr.sortBehavior();
          }

          @Override
          public Collection<String> getStringValues(final Object object)
          {
            @SuppressWarnings("unchecked")
            final Collection<String> values = (Collection<String>) exp
              .getValue(context, object, Collection.class);
            return values;
          }

          @Override
          public Collection<byte[]> getBinaryValues(final Object object)
          {
            @SuppressWarnings("unchecked")
            final Collection<byte[]> values = (Collection<byte[]>) exp
              .getValue(context, object, Collection.class);
            return values;
          }

          @Override
          public void setStringValues(
            final Object object, final Collection<String> values)
          {
            exp.setValue(context, object, values);
          }

          @Override
          public void setBinaryValues(
            final Object object, final Collection<byte[]> values)
          {
            exp.setValue(context, object, values);
          }
        });
      }
    }
  }
}

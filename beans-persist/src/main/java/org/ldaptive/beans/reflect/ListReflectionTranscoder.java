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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Reflection transcoder for an object that implements {@link List}.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class ListReflectionTranscoder
  extends AbstractCollectionReflectionTranscoder
{


  /**
   * Creates a new list reflection transcoder.
   *
   * @param  c  class that is a list
   * @param  transcoder  to operate on elements of the list
   */
  public ListReflectionTranscoder(
    final Class<?> c,
    final SingleValueReflectionTranscoder<?> transcoder)
  {
    super(c, transcoder);
  }


  /**
   * Creates a new list reflection transcoder.
   *
   * @param  c  class that is a list
   * @param  transcoder  to operate on elements of the list
   */
  public ListReflectionTranscoder(
    final Class<?> c,
    final ArrayReflectionTranscoder transcoder)
  {
    super(c, transcoder);
  }


  /** {@inheritDoc} */
  @Override
  protected <T> Collection<T> createCollection(final Class<T> clazz)
  {
    final Class<?> type = getType();
    List<T> l;
    if (type == Collection.class ||
      type == List.class ||
      type == ArrayList.class) {
      l = new ArrayList<T>();
    } else if (type == LinkedList.class) {
      l = new LinkedList<T>();
    } else {
      throw new IllegalArgumentException("Unsupported type: " + type);
    }
    return l;
  }
}

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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.ldaptive.LdapUtils;
import org.ldaptive.SortBehavior;
import org.ldaptive.beans.Attribute;
import org.ldaptive.beans.Entry;

/**
 * Class for testing beans annotations.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
@Entry(
  dn = "cn=String Entry,ou=people,dc=ldaptive,dc=org",
  attributes = {
    @Attribute(name = "customname1", values = "customvalue1"),
    @Attribute(name = "customname2", values = {"customvalue1", "customvalue2"}),
    @Attribute(name = "type1", property = "type1"),
    @Attribute(name = "type2", property = "type2"),
    @Attribute(name = "stringthree", property = "type3"),
    @Attribute(
      name = "typeArray1",
      property = "typeArray1",
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(
      name = "typeArray2",
      property = "typeArray2",
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(name = "col1", property = "col1"),
    @Attribute(name = "col2", property = "col2"),
    @Attribute(name = "typeCol1", property = "typeCol1"),
    @Attribute(name = "typeCol2", property = "typeCol2"),
    @Attribute(
      name = "typeSet1",
      property = "typeSet1",
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(
      name = "typeSet2",
      property = "typeSet2",
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(
      name = "typeList1",
      property = "typeList1",
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(
      name = "typeList2",
      property = "typeList2",
      sortBehavior = SortBehavior.ORDERED)
    }
)
public class StringCustomObject implements CustomObject
{

  /** hash code seed. */
  private static final int HASH_CODE_SEED = 11;

  // CheckStyle:JavadocVariable OFF
  private String type1;
  private String type2;
  private String type3;
  private String[] typeArray1;
  private String[] typeArray2;
  private Collection col1;
  private Collection col2;
  private Collection<String> typeCol1;
  private Collection<String> typeCol2;
  private Set<String> typeSet1;
  private Set<String> typeSet2;
  private List<String> typeList1;
  private List<String> typeList2;
  // CheckStyle:JavadocVariable ON


  // CheckStyle:JavadocMethod OFF
  // CheckStyle:LeftCurly OFF
  public String getType1() { return type1; }
  public void setType1(final String t) { type1 = t; }
  public void writeType2(final String t) { type2 = t; }
  public String getType3() { return type3; }
  public void setType3(final String t) { type3 = t; }
  public String[] getTypeArray1() { return typeArray1; }
  public void setTypeArray1(final String[] t) { typeArray1 = t; }
  public void writeTypeArray2(final String[] t) { typeArray2 = t; }
  public Collection getCol1() { return col1; }
  public void setCol1(final Collection c) { col1 = c; }
  public void writeCol2(final Collection c) { col2 = c; }
  public Collection<String> getTypeCol1() { return typeCol1; }
  public void setTypeCol1(final Collection<String> c) { typeCol1 = c; }
  public void writeTypeCol2(final Collection<String> c) { typeCol2 = c; }
  public Set<String> getTypeSet1() { return typeSet1; }
  public void setTypeSet1(final Set<String> s) { typeSet1 = s; }
  public void writeTypeSet2(final Set<String> s) { typeSet2 = s; }
  public List<String> getTypeList1() { return typeList1; }
  public void setTypeList1(final List<String> l) { typeList1 = l; }
  public void writeTypeList2(final List<String> l) { typeList2 = l; }
  // CheckStyle:LeftCurly ON
  // CheckStyle:JavadocMethod ON


  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object o)
  {
    return LdapUtils.areEqual(this, o);
  }


  /** {@inheritDoc} */
  @Override
  public int hashCode()
  {
    return
      LdapUtils.computeHashCode(
        HASH_CODE_SEED,
        type1,
        type2,
        type3,
        typeArray1,
        typeArray2,
        col1,
        col2,
        typeCol1,
        typeCol2,
        typeSet1,
        typeSet2,
        typeList1,
        typeList2);
  }


  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return String.format(
      "[%s@%d::" +
      "type1=%s, type2=%s, type3=%s, " +
      "typeArray1=%s, typeArray2=%s, " +
      "col1=%s, col2=%s, " +
      "typeCol1=%s, typeCol2=%s, " +
      "typeSet1=%s, typeSet2=%s, " +
      "typeList1=%s, typeList2=%s]",
      getClass().getSimpleName(),
      hashCode(),
      type1,
      type2,
      type3,
      Arrays.toString(typeArray1),
      Arrays.toString(typeArray2),
      col1,
      col2,
      typeCol1,
      typeCol2,
      typeSet1,
      typeSet2,
      typeList1,
      typeList2);
  }
}

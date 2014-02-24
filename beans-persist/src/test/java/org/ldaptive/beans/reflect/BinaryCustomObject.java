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
 * Class for testing bean annotations.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
@Entry(
  dn = "cn=Binary Entry,ou=people,dc=ldaptive,dc=org",
  attributes = {
    @Attribute(name = "customname1", values = "QEFCQw==", binary = true),
    @Attribute(
      name = "customname2",
      values = {"REVGRw==", "SElQUQ=="},
      binary = true,
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(name = "type1", property = "type1", binary = true),
    @Attribute(name = "type2", property = "type2", binary = true),
    @Attribute(name = "binarythree", property = "type3", binary = true),
    @Attribute(
      name = "typeCol1",
      property = "typeCol1",
      binary = true,
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(
      name = "typeCol2",
      property = "typeCol2",
      binary = true,
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(name = "typeSet1", property = "typeSet1", binary = true),
    @Attribute(name = "typeSet2", property = "typeSet2", binary = true),
    @Attribute(
      name = "typeList1",
      property = "typeList1",
      binary = true,
      sortBehavior = SortBehavior.ORDERED),
    @Attribute(
      name = "typeList2",
      property = "typeList2",
      binary = true,
      sortBehavior = SortBehavior.ORDERED)
    }
)
public class BinaryCustomObject implements CustomObject
{

  /** hash code seed. */
  private static final int HASH_CODE_SEED = 21;

  // CheckStyle:JavadocVariable OFF
  private byte[] type1;
  private byte[] type2;
  private byte[] type3;
  private Collection<byte[]> typeCol1;
  private Collection<byte[]> typeCol2;
  private Set<byte[]> typeSet1;
  private Set<byte[]> typeSet2;
  private List<byte[]> typeList1;
  private List<byte[]> typeList2;
  // CheckStyle:JavadocVariable ON


  // CheckStyle:JavadocMethod OFF
  // CheckStyle:LeftCurly OFF
  public byte[] getType1() { return type1; }
  public void setType1(final byte[] t) { type1 = t; }
  public void writeType2(final byte[] t) { type2 = t; }
  public byte[] getType3() { return type3; }
  public void setType3(final byte[] t) { type3 = t; }
  public Collection<byte[]> getTypeCol1() { return typeCol1; }
  public void setTypeCol1(final Collection<byte[]> c) { typeCol1 = c; }
  public void writeTypeCol2(final Collection<byte[]> c) { typeCol2 = c; }
  public Set<byte[]> getTypeSet1() { return typeSet1; }
  public void setTypeSet1(final Set<byte[]> s) { typeSet1 = s; }
  public void writeTypeSet2(final Set<byte[]> s) { typeSet2 = s; }
  public List<byte[]> getTypeList1() { return typeList1; }
  public void setTypeList1(final List<byte[]> l) { typeList1 = l; }
  public void writeTypeList2(final List<byte[]> l) { typeList2 = l; }
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
      "typeCol1=%s, typeCol2=%s, " +
      "typeSet1=%s, typeSet2=%s, " +
      "typeList1=%s, typeList2=%s]",
      getClass().getSimpleName(),
      hashCode(),
      Arrays.toString(type1),
      Arrays.toString(type2),
      Arrays.toString(type3),
      toString(typeCol1),
      toString(typeCol2),
      toString(typeSet1),
      toString(typeSet2),
      toString(typeList1),
      toString(typeList2));
  }


  /**
   * Returns a string representation of the supplied collection.
   *
   * @param  c  collection to represent as a string
   *
   * @return  collection as a string
   */
  private String toString(final Collection<byte[]> c)
  {
    String s = null;
    if (c != null) {
      s = "";
      for (byte[] t : c) {
        s += Arrays.toString((byte[]) t);
      }
    }
    return s;
  }
}

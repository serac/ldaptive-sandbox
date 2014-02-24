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

import org.ldaptive.LdapAttribute;
import org.ldaptive.LdapEntry;
import org.ldaptive.SortBehavior;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for {@link DefaultLdapEntryMapper}.
 *
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public abstract class AbstractDefaultLdapEntryMapperTest
{

  /** Mapper to use for testing. */
  protected DefaultLdapEntryMapper<CustomObject> mapper =
    new DefaultLdapEntryMapper<CustomObject>();


  /**
   * Creates an ldap entry containing string values.
   *
   * @return  ldap entry
   */
  protected LdapEntry createStringLdapEntry()
  {
    final LdapAttribute typeArray1 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeArray1.setName("typeArray1");
    typeArray1.addStringValue("tav1", "tav2");
    final LdapAttribute typeArray2 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeArray2.setName("typeArray2");
    typeArray2.addStringValue("tav1", "tav2");

    final LdapAttribute col1 = new LdapAttribute(
      SortBehavior.ORDERED);
    col1.setName("col1");
    col1.addStringValue("cv1", "cv2");
    final LdapAttribute col2 = new LdapAttribute(
      SortBehavior.ORDERED);
    col2.setName("col2");
    col2.addStringValue("cv1", "cv2");

    final LdapAttribute typeCol1 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeCol1.setName("typeCol1");
    typeCol1.addStringValue("tcv1", "tcv2");
    final LdapAttribute typeCol2 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeCol2.setName("typeCol2");
    typeCol2.addStringValue("tcv1", "tcv2");

    final LdapAttribute typeSet1 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeSet1.setName("typeSet1");
    typeSet1.addStringValue("tsv1", "tsv2");
    final LdapAttribute typeSet2 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeSet2.setName("typeSet2");
    typeSet2.addStringValue("tsv1", "tsv2");

    final LdapAttribute typeList1 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeList1.setName("typeList1");
    typeList1.addStringValue("tlv1", "tlv2");
    final LdapAttribute typeList2 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeList2.setName("typeList2");
    typeList2.addStringValue("tlv1", "tlv2");

    final LdapEntry entry = new LdapEntry();
    entry.setDn("cn=String Entry,ou=people,dc=ldaptive,dc=org");
    entry.addAttribute(
      new LdapAttribute("customname1", "customvalue1"),
      new LdapAttribute("customname2", "customvalue1", "customvalue2"),
      new LdapAttribute("type1", "tv1"),
      new LdapAttribute("type2", "tv2"),
      new LdapAttribute("stringthree", "tv3"),
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
    return entry;
  }


  /**
   * Creates an ldap entry containing number based string values.
   *
   * @return  ldap entry
   */
  protected LdapEntry createNumberLdapEntry()
  {
    final LdapAttribute typeArray1 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeArray1.setName("typeArray1");
    typeArray1.addStringValue("301", "302");
    final LdapAttribute typeArray2 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeArray2.setName("typeArray2");
    typeArray2.addStringValue("301", "302");

    final LdapAttribute col1 = new LdapAttribute(
      SortBehavior.ORDERED);
    col1.setName("col1");
    col1.addStringValue("401", "402");
    final LdapAttribute col2 = new LdapAttribute(
      SortBehavior.ORDERED);
    col2.setName("col2");
    col2.addStringValue("401", "402");

    final LdapAttribute typeCol1 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeCol1.setName("typeCol1");
    typeCol1.addStringValue("501", "502");
    final LdapAttribute typeCol2 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeCol2.setName("typeCol2");
    typeCol2.addStringValue("501", "502");

    final LdapAttribute typeSet1 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeSet1.setName("typeSet1");
    typeSet1.addStringValue("601", "602");
    final LdapAttribute typeSet2 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeSet2.setName("typeSet2");
    typeSet2.addStringValue("601", "602");

    final LdapAttribute typeList1 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeList1.setName("typeList1");
    typeList1.addStringValue("701", "702");
    final LdapAttribute typeList2 = new LdapAttribute(
      SortBehavior.ORDERED);
    typeList2.setName("typeList2");
    typeList2.addStringValue("701", "702");

    final LdapEntry entry = new LdapEntry();
    entry.setDn("cn=Number Entry,ou=people,dc=ldaptive,dc=org");
    entry.addAttribute(
      new LdapAttribute("type1", "100"),
      new LdapAttribute("type2", "200"),
      new LdapAttribute("numberthree", "300"),
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
    return entry;
  }


  /**
   * Creates an ldap entry containing binary data.
   *
   * @return  ldap entry
   */
  protected LdapEntry createBinaryLdapEntry()
  {
    final LdapAttribute typeCol1 = new LdapAttribute(
      SortBehavior.ORDERED, true);
    typeCol1.setName("typeCol1");
    typeCol1.addBinaryValue(new byte[] {0x20}, new byte[] {0x21});
    final LdapAttribute typeCol2 = new LdapAttribute(
      SortBehavior.ORDERED, true);
    typeCol2.setName("typeCol2");
    typeCol2.addBinaryValue(new byte[] {0x20}, new byte[] {0x21});

    final LdapAttribute typeSet1 = new LdapAttribute(
      SortBehavior.ORDERED, true);
    typeSet1.setName("typeSet1");
    typeSet1.addBinaryValue(new byte[] {0x22}, new byte[] {0x23});
    final LdapAttribute typeSet2 = new LdapAttribute(
      SortBehavior.ORDERED, true);
    typeSet2.setName("typeSet2");
    typeSet2.addBinaryValue(new byte[] {0x22}, new byte[] {0x23});

    final LdapAttribute typeList1 = new LdapAttribute(
      SortBehavior.ORDERED, true);
    typeList1.setName("typeList1");
    typeList1.addBinaryValue(new byte[] {0x24}, new byte[] {0x25});
    final LdapAttribute typeList2 = new LdapAttribute(
      SortBehavior.ORDERED, true);
    typeList2.setName("typeList2");
    typeList2.addBinaryValue(new byte[] {0x24}, new byte[] {0x25});

    final LdapEntry entry = new LdapEntry();
    entry.setDn("cn=Binary Entry,ou=people,dc=ldaptive,dc=org");
    entry.addAttribute(
      new LdapAttribute(
        "customname1",
        new byte[] {0x40, 0x41, 0x42, 0x43}),
      new LdapAttribute(
        "customname2",
        new byte[] {0x44, 0x45, 0x46, 0x47},
        new byte[] {0x48, 0x49, 0x50, 0x51}),
      new LdapAttribute("type1", new byte[] {0x01}),
      new LdapAttribute("type2", new byte[] {0x02}),
      new LdapAttribute("binarythree", new byte[] {0x03}),
      typeCol1,
      typeCol2,
      typeSet1,
      typeSet2,
      typeList1,
      typeList2);
    return entry;
  }


  /**
   * @param  object  initialized with data
   * @param  entry  to compare with mapped entry
   *
   * @throws  Exception  On test failure.
   */
  @Test(groups = {"beans"}, dataProvider = "objects")
  public void mapToLdapEntry(final CustomObject object, final LdapEntry entry)
    throws Exception
  {
    final LdapEntry mapped = new LdapEntry();
    mapper.map(object, mapped);
    Assert.assertEquals(entry, mapped);
  }


  /**
   * @param  object  to compare with mapped object
   * @param  entry  initialized with data
   *
   * @throws  Exception  On test failure.
   */
  @Test(groups = {"beans"}, dataProvider = "objects")
  public void mapToObject(final CustomObject object, final LdapEntry entry)
    throws Exception
  {
    final CustomObject mapped = object.getClass().newInstance();
    mapper.map(entry, mapped);
    Assert.assertEquals(object, mapped);
  }
}

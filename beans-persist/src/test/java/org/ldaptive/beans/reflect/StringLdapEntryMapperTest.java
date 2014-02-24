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
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.DataProvider;

/**
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class StringLdapEntryMapperTest
  extends AbstractDefaultLdapEntryMapperTest
{


  /**
   * Test data for string based object.
   *
   * @return  custom objects
   */
  @DataProvider(name = "objects")
  public Object[][] createCustomObjects()
  {
    final Set<String> s1 = new HashSet<String>();
    s1.add("tsv1");
    s1.add("tsv2");

    final StringCustomObject o1 = new StringCustomObject();
    o1.setType1("tv1");
    o1.writeType2("tv2");
    o1.setType3("tv3");
    o1.setTypeArray1(new String[] {"tav1", "tav2"});
    o1.writeTypeArray2(new String[]{"tav1", "tav2"});
    o1.setCol1(Arrays.asList("cv1", "cv2"));
    o1.writeCol2(Arrays.asList("cv1", "cv2"));
    o1.setTypeCol1(Arrays.asList("tcv1", "tcv2"));
    o1.writeTypeCol2(Arrays.asList("tcv1", "tcv2"));
    o1.setTypeSet1(s1);
    o1.writeTypeSet2(s1);
    o1.setTypeList1(Arrays.asList("tlv1", "tlv2"));
    o1.writeTypeList2(Arrays.asList("tlv1", "tlv2"));

    return new Object[][] {
      new Object[] {o1, createStringLdapEntry(), },
    };
  }
}

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
public class IntegerLdapEntryMapperTest
  extends AbstractDefaultLdapEntryMapperTest
{


  /**
   * Test data for integer based object.
   *
   * @return  custom objects
   */
  @DataProvider(name = "objects")
  public Object[][] createCustomObjects()
  {
    final Set<Integer> s1 = new HashSet<Integer>();
    s1.add(601);
    s1.add(602);

    final IntegerCustomObject o1 = new IntegerCustomObject();
    o1.setIntegerDn("cn=Number Entry,ou=people,dc=ldaptive,dc=org");
    o1.setType1(100);
    o1.writeType2(200);
    o1.setType3(300);
    o1.setTypeArray1(new Integer[]{301, 302});
    o1.writeTypeArray2(new Integer[]{301, 302});
    o1.setCol1(Arrays.asList("401", "402"));
    o1.writeCol2(Arrays.asList("401", "402"));
    o1.setTypeCol1(Arrays.asList(501, 502));
    o1.writeTypeCol2(Arrays.asList(501, 502));
    o1.setTypeSet1(s1);
    o1.writeTypeSet2(s1);
    o1.setTypeList1(Arrays.asList(701, 702));
    o1.writeTypeList2(Arrays.asList(701, 702));

    return new Object[][] {
      new Object[] {o1, createNumberLdapEntry(), },
    };
  }
}

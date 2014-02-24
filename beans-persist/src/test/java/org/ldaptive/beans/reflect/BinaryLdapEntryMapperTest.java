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
import java.util.LinkedHashSet;
import java.util.Set;
import org.testng.annotations.DataProvider;

/**
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class BinaryLdapEntryMapperTest
  extends AbstractDefaultLdapEntryMapperTest
{


  /**
   * Test data for byte[] based object.
   *
   * @return  custom objects
   */
  @DataProvider(name = "objects")
  public Object[][] createCustomObjects()
  {
    final Set<byte[]> s1 = new LinkedHashSet<byte[]>();
    s1.add(new byte[]{0x22});
    s1.add(new byte[]{0x23});

    final BinaryCustomObject o1 = new BinaryCustomObject();
    o1.setType1(new byte[]{0x01});
    o1.writeType2(new byte[]{0x02});
    o1.setType3(new byte[]{0x03});
    o1.setTypeCol1(Arrays.asList(new byte[]{0x20}, new byte[]{0x21}));
    o1.writeTypeCol2(Arrays.asList(new byte[]{0x20}, new byte[]{0x21}));
    o1.setTypeSet1(s1);
    o1.writeTypeSet2(s1);
    o1.setTypeList1(Arrays.asList(new byte[]{0x24}, new byte[]{0x25}));
    o1.writeTypeList2(Arrays.asList(new byte[]{0x24}, new byte[]{0x25}));

    return new Object[][] {
      new Object[] {o1, createBinaryLdapEntry(), },
    };
  }
}

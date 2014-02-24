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
import org.ldaptive.LdapEntry;
import org.testng.annotations.DataProvider;

/**
 * @author  Middleware Services
 * @version  $Revision$ $Date$
 */
public class CharLdapEntryMapperTest extends AbstractDefaultLdapEntryMapperTest
{


  /**
   * Test data for char based object.
   *
   * @return  custom objects
   */
  @DataProvider(name = "objects")
  public Object[][] createCustomObjects()
  {
    final Set<char[]> s1 = new HashSet<char[]>();
    s1.add(new char[]{'t', 's', 'v', '1'});
    s1.add(new char[]{'t', 's', 'v', '2'});

    final CharCustomObject o1 = new CharCustomObject();
    o1.setCustomDn("cn=String Entry,ou=people,dc=ldaptive,dc=org");
    o1.setType1(new char[]{'t', 'v', '1'});
    o1.writeType2(new char[]{'t', 'v', '2'});
    o1.setType3(new char[]{'t', 'v', '3'});
    o1.setTypeCol1(Arrays.asList(new char[]{'t', 'c', 'v', '1'},
                                 new char[]{'t', 'c', 'v', '2'}));
    o1.writeTypeCol2(Arrays.asList(new char[]{'t', 'c', 'v', '1'},
                                   new char[]{'t', 'c', 'v', '2'}));
    o1.setTypeSet1(s1);
    o1.writeTypeSet2(s1);
    o1.setTypeList1(Arrays.asList(new char[]{'t', 'l', 'v', '1'},
                                  new char[]{'t', 'l', 'v', '2'}));
    o1.writeTypeList2(Arrays.asList(new char[]{'t', 'l', 'v', '1'},
                                    new char[]{'t', 'l', 'v', '2'}));

    final LdapEntry entry = createStringLdapEntry();
    entry.removeAttribute("col1");
    entry.removeAttribute("col2");
    entry.removeAttribute("typeArray1");
    entry.removeAttribute("typeArray2");

    return new Object[][] {
      new Object[] {o1, entry, },
    };
  }
}

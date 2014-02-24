# Beans and persistence sandbox

## Packages

### org.ldaptive.beans
Contains the annotations and interfaces to aid in mapping POJOs to the LdapEntry class.

### org.ldaptive.beans.reflect
Reflection based implementation of the LdapEntryMapper. Supports a flat class structure where attribute values map
to primitive types and collections.

### org.ldaptive.beans.spring
Spring based implementation of the LdapEntryMapper. Leverages the spel expression parser to access bean properties on an object.

### org.ldaptive.persistence
LdapEntryManager with default implementation that mirrors a persistence based EntityManager. Combines an LdapEntryMapper with an add/modify/delete operation to provide a persistence API.

## Examples

### Reflect example
```java
@Entry(
  dn = "id",
  attributes = {
    @Attribute(name = "objectClass", values = {"top", "inetOrgPerson"}),
    @Attribute(name = "cn", property = "name"),
    @Attribute(name = "mail", property = "email"),
    @Attribute(name = "eduPersonAffiliation", property = "affiliations")
    }
)
public class MyObject {

  private String id;
  private String name;
  private String email;
  private Set<String> affils;

  public String getId() {return id;}
  public void setId(String s) {id = s;}

  public String getName() {return name;}
  public void setName(String s) {name = s;}

  public String getEmail() {return email;}
  public void setEmail(String s) {email = s;}
  
  public Set<String> getAffiliations() {return affils;}
  public vlid setAffiliations(Set<String> s) {affils = s;}
}
```
```java
LdapEntry entry = ... // retrieve entry from ldap
DefaultLdapEntryMapper mapper = new DefaultLdapEntryMapper();
MyObject object = new MyObject();
mapper.map(entry, object); // object now has it's properties set
```

## Object Mapping APIs
* J2EE Entity Manager: http://docs.oracle.com/javaee/6/api/javax/persistence/EntityManager.html
* Dover: http://dozer.sourceforge.net/documentation/gettingstarted.html
* Morph: http://morph.sourceforge.net/reference/html/transformers.html#transformers-converters
* ModelMapper: http://modelmapper.org/getting-started/
* EZMorph: http://ezmorph.sourceforge.net/howto.html

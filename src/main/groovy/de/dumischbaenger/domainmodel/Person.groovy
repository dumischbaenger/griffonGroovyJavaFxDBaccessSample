package de.dumischbaenger.domainmodel

import javax.persistence.Access
import javax.persistence.AccessType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Transient
import javax.print.attribute.standard.MediaSize.Other

import groovy.transform.EqualsAndHashCode
import javafx.beans.property.IntegerProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

@EqualsAndHashCode
@Entity
@Access(AccessType.PROPERTY)  
class Person {
  @Transient
  private final IntegerProperty idProperty = new SimpleIntegerProperty()
  @Transient
  private final StringProperty nameProperty=new SimpleStringProperty()
  @Transient
  private final IntegerProperty ageProperty=new SimpleIntegerProperty()
  @Transient
  private final ObjectProperty genderProperty=new SimpleObjectProperty()

  @Id
  @GeneratedValue(generator = "person_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize=2,initialValue=1)
  public Integer getId() {
    return idProperty.getValue()
  }
  public void setId(Integer id) {
    this.idProperty.setValue(id)
  }
  
  public String getName() {
    return nameProperty.getValue()
  }
  public void setName(String v) {
    nameProperty.setValue(v)
  }

  public Integer getAge() {
    return ageProperty.getValue()
  }
  public void setAge(Integer v) {
    ageProperty.setValue(v)
  }

  public Integer getGender() {
    return genderProperty.getValue().id
  }
  public void setGender(Integer v) {
    genderProperty.setValue(Gender.genders[v])
  }

  
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idProperty == null) ? 0 : idProperty.get());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this.is(obj))
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Person other = (Person) obj;
    if (idProperty == null) {
      if (other.idProperty != null)
        return false;
    } else if (idProperty.get() != other.idProperty.get())
      return false;
    return true;
  }
  String toString() { "$id $name : age $age, gender $gender" }
}

package de.dumischbaenger.domainmodel

import javax.persistence.Access
import javax.persistence.AccessType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Transient

import groovy.transform.EqualsAndHashCode
import javafx.beans.property.IntegerProperty
import javafx.beans.property.LongProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

@EqualsAndHashCode
@Entity
@Access(AccessType.PROPERTY)  
class Person {
  @Transient
  private final LongProperty idProperty = new SimpleLongProperty()
  @Transient
  private final StringProperty nameProperty=new SimpleStringProperty()
  @Transient
  private final IntegerProperty ageProperty=new SimpleIntegerProperty()
  @Transient
  private final IntegerProperty genderPropery=new SimpleIntegerProperty()

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return idProperty.getValue()
  }
  public void setId(Long id) {
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
    return ageProperty.getValue()
  }
  public void setGender(Integer v) {
    ageProperty.setValue(v)
  }

  String toString() { "$id $name : age $age, gender $gender" }
}

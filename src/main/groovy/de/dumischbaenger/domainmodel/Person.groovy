package de.dumischbaenger.domainmodel

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Person {
  Integer id=1
  String name="name"
  Integer age=12
  Integer gender=1
  
  String toString() { "$id $name : age $age, gender $gender" }
}

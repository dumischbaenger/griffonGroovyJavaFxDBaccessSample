package de.dumischbaenger.domainmodel

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Person {
  Integer id
  String name
  Integer age
  Integer gender
  
  String toString() { "$id $name : age $age, gender $gender" }
}

package de.dumischbaenger.domainmodel

import java.util.Map

import javax.annotation.Nonnull

class Gender {
  @Nonnull
  public static final Map genders=[
    1:new Gender(1, "male"),
    2:new Gender(2, "female")
  ]

  Integer id
  String name
  public Gender(Integer _id, String _name) {
    id=_id
    name=_name
  }
}

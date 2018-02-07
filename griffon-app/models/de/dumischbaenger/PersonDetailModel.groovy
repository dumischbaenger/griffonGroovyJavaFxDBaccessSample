package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel

import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonModel
import griffon.metadata.ArtifactProviderFor
import griffon.transform.FXObservable

class PersonModel {
  @FXObservable
  Integer id
  @FXObservable
  String name
  @FXObservable
  Integer age
  @FXObservable
  Gender gender
  
  Map genders
  
  public PersonModel(Map _genders) {
    genders=_genders
  }
  
  private Person person
  void adapt(Person p) {
    Map prop=p.getProperties()
    prop=prop.findAll{it -> !(it.key in ['class', 'gender'])}
    prop.each{ k, v -> this."$k" = v }
    gender=genders[p.gender]
  }
}

class Gender {
  Integer id
  String name
  public Gender(Integer _id, String _name) {
    id=_id
    name=_name
  }
}

@ArtifactProviderFor(GriffonModel)
class PersonDetailModel extends AbstractGriffonModel {
    
    @Nonnull
    final Map genders=[
      1:new Gender(1, "male"),
      2:new Gender(2, "female")
    ]

    @Nonnull
    final PersonModel personModel=new PersonModel(genders);    
    

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
      println("PersonDetailModel mvcGroupInit")
    }
    
    public void setPerson(Person p) {
      personModel.adapt(p)
    }

}
package de.dumischbaenger

import java.util.Map

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel

import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonModel
import griffon.metadata.ArtifactProviderFor
import griffon.transform.FXObservable
import groovyx.javafx.beans.FXBindable

@FXObservable
class PersonModel {
  Integer id
  String name
  Integer age
  Integer gender
  
  private Person person
  void adapt(Person p) {
    Map prop=p.getProperties()
    prop=prop.findAll{it -> it.key != "class"}
    prop.each{ k, v -> this."$k" = v }
  }
}


@ArtifactProviderFor(GriffonModel)
class PersonDetailModel extends AbstractGriffonModel {
    @Nonnull
    final PersonModel personModel=new PersonModel();    

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
      println("PersonDetailModel mvcGroupInit")
    }
    
    public void setPerson(Person p) {
      personModel.adapt(p)
    }

}
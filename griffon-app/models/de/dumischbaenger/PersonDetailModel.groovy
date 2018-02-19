package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel

import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonModel
import griffon.metadata.ArtifactProviderFor
import griffon.transform.FXObservable


@ArtifactProviderFor(GriffonModel)
class PersonDetailModel extends AbstractGriffonModel {
    
    @Nonnull
    Person person=null  
    

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
      println("PersonDetailModel mvcGroupInit")
    }
    
    public void setPerson(Person p) {
      person=p
    }

}
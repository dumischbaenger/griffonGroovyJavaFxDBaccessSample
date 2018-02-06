package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonController
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading

@ArtifactProviderFor(GriffonController)
class PersonDetailController extends AbstractGriffonController {
    @MVCMember @Nonnull
    PersonDetailModel model
    
    @MVCMember @Nonnull
    PersonDetailView view

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void showPerson(Person p) {
      println("PersonDetailController showPerson")
      model.setPerson(p)
      
//      println("bind da: $view.personGender.getSelectionModel().getSelectedItem()")
    }
}
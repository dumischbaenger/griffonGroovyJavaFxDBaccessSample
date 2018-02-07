package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading

@ArtifactProviderFor(GriffonController)
class PersonDetailController extends AbstractGriffonController {
    @MVCMember @Nonnull
    PersonDetailModel model
    
    @MVCMember @Nonnull
    PersonDetailView view

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void save() {
      println("PersonDetailController save")
      
      println("bin da: " + model.personModel.gender.name)
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void showPerson(Person p) {
      println("PersonDetailController showPerson")
      model.setPerson(p)
      
//      println("bind da: $view.personGender.getSelectionModel().getSelectedItem()")
//      def sel=view.personGender.selectionModel.selectedItem
//      println("bind da: ${sel?.name}")
      
    }
}
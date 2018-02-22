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

    @javax.inject.Inject
    private PersonService personService
    
    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void createNew() {
      log.info("PersonDetailController createNew")
      
      Person p=personService.newPerson()
      showPerson(p)
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void save() {
      log.info("PersonDetailController save")
      
      personService.savePerson(model.person)
      
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void showPerson(Person p) {
      log.info("PersonDetailController showPerson")
      model.setPerson(p)
      view.showPerson(p)
      
//      println("bind da: $view.personGender.getSelectionModel().getSelectedItem()")
//      def sel=view.personGender.selectionModel.selectedItem
//      println("bind da: ${sel?.name}")
      
    }
}
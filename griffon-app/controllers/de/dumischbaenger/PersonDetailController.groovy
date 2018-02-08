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
      
      Person p=new Person();
      p.id=0
      p.name="enter name"
      p.age=5
      p.gender=1
      showPerson(p)
      
//      println("bin da: " + model.personModel.gender.name)
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void save() {
      log.info("PersonDetailController save")
      
//      println("bin da: " + model.personModel.gender.name)
      
//     new File('persistence.txt').withWriter('utf-8') {
//        writer -> writer.writeLine 'Hello World'
//     }
      def res=personService.getPersonName(1)
      
      println(res)
      
      
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void showPerson(Person p) {
      log.info("PersonDetailController showPerson")
      model.setPerson(p)
      
//      println("bind da: $view.personGender.getSelectionModel().getSelectedItem()")
//      def sel=view.personGender.selectionModel.selectedItem
//      println("bind da: ${sel?.name}")
      
    }
}
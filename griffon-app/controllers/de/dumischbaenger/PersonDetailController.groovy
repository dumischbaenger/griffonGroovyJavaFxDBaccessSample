package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

import de.dumischbaenger.domainmodel.Person
import griffon.core.RunnableWithArgs
import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonController)
class PersonDetailController extends AbstractGriffonController {
  @MVCMember @Nonnull
  PersonDetailModel model

  @MVCMember @Nonnull
  PersonDetailView view

  @javax.inject.Inject
  private PersonService personService

  void mvcGroupInit(Map<String, Object> args) {
    log.info("PersonDetailController mvcGroupInit")
    application.eventRouter.addEventListener([
      personDetailsShow: {
        log.info("PersonListController consuming event personSearchFinished")
        Person person=it[0]
        showPerson(person)
      } as RunnableWithArgs
    ])
  }

  @ControllerAction
  void createNew() {
    log.info("PersonDetailController createNew")

    Person p=personService.newPerson()
    showPerson(p)

    application.eventRouter.publishEvent('personCreated', [p])
  }

  @ControllerAction
  void remove() {
    log.info("PersonDetailController remove")

//    showPerson(new Person())
    personService.removePerson(model.person)
    
    application.eventRouter.publishEvent('personRemoved', [model.person])
  }

  @ControllerAction
  void save() {
    log.info("PersonDetailController save")

    personService.savePerson(model.person)
  }

  void showPerson(Person p) {
    log.info("PersonDetailController showPerson")
    view.showPerson(p)
    model.setPerson(p)

    //      println("bind da: $view.personGender.getSelectionModel().getSelectedItem()")
    //      def sel=view.personGender.selectionModel.selectedItem
    //      println("bind da: ${sel?.name}")

  }
}
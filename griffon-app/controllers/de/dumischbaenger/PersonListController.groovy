package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

import de.dumischbaenger.domainmodel.Person
import griffon.core.RunnableWithArgs
import griffon.core.artifact.GriffonController
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.collections.FXCollections
import javafx.collections.ObservableList

@ArtifactProviderFor(GriffonController)
class PersonListController extends AbstractGriffonController{
    @MVCMember @Nonnull
    PersonListModel model

    @MVCMember @Nonnull
    PersonListView view
 
    void mvcGroupInit(Map<String, Object> args) {
      log.info("PersonListController mvcGroupInit")
      application.eventRouter.addEventListener([
          personSearchFinished: {
            log.info("PersonListController consuming event personSearchFinished")
            List persons=it[0]
            showPersons(persons)
          } as RunnableWithArgs,
          personCreated: {
            log.info("PersonListController consuming event personCreated")
            Person person=it[0]
            showAdditionalPersons(person)
          } as RunnableWithArgs
      ])
  }

    void showPersons(List persons) {
      log.info("PersonListController showPersons")
      model.personsObervable = FXCollections.observableArrayList()
      model.personsObervable.addAll(persons)
      view.table.setItems(model.personsObervable)
    }
    void showAdditionalPersons(Person person) {
      log.info("PersonListController showAdditionalPersons")
      model.personsObervable.add(person)
    }
    void showPersonsDetails(Person person) {
      log.info("PersonListController showPersonsDetails")
      application.eventRouter.publishEvent('personDetailsShow', [person])
    }
}
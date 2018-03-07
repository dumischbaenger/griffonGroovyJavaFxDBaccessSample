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
          } as RunnableWithArgs
      ])
  }

    void showPersons(List persons) {
      log.info("PersonListController showPersons")
      ObservableList<Person> personData = FXCollections.observableArrayList()
      personData.addAll(persons)
      view.table.setItems(personData)
    }
}
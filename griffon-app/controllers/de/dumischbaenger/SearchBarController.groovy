package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonController)
class SearchBarController extends AbstractGriffonController {
    @MVCMember @Nonnull
    SearchBarModel model
    
    @javax.inject.Inject
    private PersonService personService

    @ControllerAction
    void search() {
      log.info("SearchBarController search")
      List persons=personService.searchPersons([name:model.nameFilter])
      application.eventRouter.publishEvent('personSearchFinished', [persons])
      
//      //BD getPersonListController directly an and send it list of persons
//      PersonListController c = application.mvcGroupManager.findGroup('personList').getController()
//      c.showPersons(persons)
    }
}
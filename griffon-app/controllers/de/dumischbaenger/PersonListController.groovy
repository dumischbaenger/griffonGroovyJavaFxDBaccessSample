package de.dumischbaenger

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading
import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

@ArtifactProviderFor(GriffonController)
class PersonListController extends AbstractGriffonController{
    @MVCMember @Nonnull
    PersonListModel model

    void showPersons(List persons) {
      log.info("PersonListController showPersons")
    }
}
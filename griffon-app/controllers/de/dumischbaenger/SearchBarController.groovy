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
class SearchBarController extends AbstractGriffonController {
    @MVCMember @Nonnull
    SearchBarModel model

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void search() {
      println("SearchBarController search")
      
      PersonDetailController personDetailController=application.mvcGroupManager.controllers["personDetail"]
      Person p=new Person();
      p.id=3
      p.name="ttt"
      p.age=5
      p.gender=1
      personDetailController.showPerson(p)
    }
}
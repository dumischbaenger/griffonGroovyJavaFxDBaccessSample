package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading

@Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
@ArtifactProviderFor(GriffonController)
class LoginController extends AbstractGriffonController {
    @MVCMember @Nonnull
    LoginModel model
    
    @MVCMember @Nonnull
    LoginView view
  

    @ControllerAction
    void click() {
        int count = model.clickCount.toInteger()
        model.clickCount = String.valueOf(count + 1)
    }
    
    void show() {
      view.show()
    }
}
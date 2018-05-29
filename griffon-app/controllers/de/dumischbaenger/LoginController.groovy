package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.fxml.FXML
import javafx.scene.control.TextField

@ArtifactProviderFor(GriffonController)
class LoginController extends AbstractGriffonController {
    @MVCMember @Nonnull
    LoginModel model
    
    @MVCMember @Nonnull
    LoginView view

    @javax.inject.Inject
    DbLoginService dbLoginService;
  
    void startLogin() {
      view.show()
    }
    
    @ControllerAction
    void login() {
      dbLoginService.login(model.user, model.pwd)
    }
}
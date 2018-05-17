package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.stage.Modality
import javafx.stage.Stage

@ArtifactProviderFor(GriffonView)
class LoginView extends AbstractJavaFXGriffonView {
  @MVCMember @Nonnull
  FactoryBuilderSupport builder
  @MVCMember @Nonnull
  LoginController controller
  @MVCMember @Nonnull
  LoginModel model

  @MVCMember
  MainView parentView


  void initUIxxx() {
    log.info("LoginView initUI")

    Stage window=builder.application(title: application.configuration['application.title'],
    sizeToScene: true, centerOnScreen: true, name: 'login') {
      scene(fill: WHITE, width: 200, height: 60) {
        g = gridPane {
          label(id: 'clickLabel', row: 0, column: 0,
          text: bind(model.clickCountProperty()))
          button(row: 1, column: 0, prefWidth: 200,
          griffonActionId: 'click')
        }
        connectActions(g, controller)
        connectMessageSource(g)
      }
    }
    window.initModality(Modality.APPLICATION_MODAL)
    Stage mainWindow=application.windowManager.findWindow('mainWindow')
    window.initOwner(mainWindow)
  }

  void initUI() {
    Stage loginWindow = (Stage) getApplication()
        .createApplicationContainer(Collections.<String, Object>emptyMap());
    loginWindow.setTitle(getApplication().getMessageSource()
        .getMessage('login.title', "Login"))
//    loginWindow.setWidth(00);
//    loginWindow.setHeight(400);

    GridPane node = loadFromFXML()
    Scene scene = new Scene(node);

    loginWindow.setScene(scene);
    getApplication().getWindowManager().attach(mvcGroup.mvcId, loginWindow);
    loginWindow.initModality(Modality.APPLICATION_MODAL)
    Stage mainWindow=application.windowManager.findWindow('mainFrame')
    loginWindow.initOwner(mainWindow)

    
    connectActions(node, controller);
    connectMessageSource(node);
  }
}
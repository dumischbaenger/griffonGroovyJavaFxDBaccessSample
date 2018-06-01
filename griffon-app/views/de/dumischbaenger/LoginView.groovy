package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.beans.binding.Bindings
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.stage.WindowEvent

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
  
  @FXML
  ComboBox database
  @FXML
  TextField user
  @FXML
  TextField pwd
  
  void initUI() {
    Stage loginWindow = (Stage) getApplication()
        .createApplicationContainer(Collections.<String, Object>emptyMap());
    loginWindow.setTitle(getApplication().getMessageSource()
        .getMessage('login.title', "Login"))
    loginWindow.setWidth(300);
    loginWindow.setHeight(150);
//    loginWindow.initStyle(StageStyle.UNDECORATED);

    //BD disable close button in login window decoration     
    loginWindow.setOnCloseRequest{
      WindowEvent e->
      e.consume()
    };

    GridPane node = loadFromFXML()
    Scene scene = new Scene(node);

    loginWindow.setScene(scene);
    getApplication().getWindowManager().attach(mvcGroup.mvcId, loginWindow);
    loginWindow.initModality(Modality.APPLICATION_MODAL)
    Stage mainWindow=application.windowManager.findWindow('mainFrame')
    loginWindow.initOwner(mainWindow)

    
    connectActions(node, controller);
    connectMessageSource(node);
    
    Bindings.bindBidirectional(user.textProperty(), model.userProperty)
    Bindings.bindBidirectional(pwd.textProperty(), model.pwdProperty)
    
  }
  
  void show() {
    application.windowManager.show("login")
  }
}
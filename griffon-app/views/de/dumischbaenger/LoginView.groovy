package de.dumischbaenger

import java.util.List

import javax.annotation.Nonnull
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView
import org.w3c.dom.Document
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.FXObservable
import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.NodeChild
import groovy.util.slurpersupport.NodeChildren
import javafx.beans.binding.Bindings
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.stage.Modality
import javafx.stage.Stage
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
    loginWindow.setOnCloseRequest{ WindowEvent e->
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
    Bindings.bindBidirectional(database.itemsProperty(), model.persistenceUnitsProperty)
    Bindings.bindBidirectional(database.valueProperty(), model.persistenceUnitProperty)
    if(!model.persistenceUnits.empty){
      database.getSelectionModel().select(0)
    }
  }

  void show() {
    application.windowManager.show("login")
  }
}
package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.stage.Stage

@ArtifactProviderFor(GriffonView)
class MainView extends AbstractJavaFXGriffonView {
  @MVCMember @Nonnull
  MainController controller
  @MVCMember @Nonnull
  MainModel model

  @FXML
  VBox vbox
  
  Stage mainWindow

  @Override
  public void mvcGroupInit(@Nonnull Map<String, Object> args) {
    log.info("GriffonGroovyJavaFxDBaccessSampleView mvcGroupInit")
    
    createMVCGroup("searchBar");
    createMVCGroup("personList");
    createMVCGroup("personDetail");
  }

  
  void initUI() {
    log.info("GriffonGroovyJavaFxDBaccessSampleView initUI")
    
    
    mainWindow = (Stage) getApplication()
        .createApplicationContainer(Collections.<String, Object>emptyMap());
    mainWindow.setTitle(getApplication().getMessageSource()
        .getMessage('application.title', "GriffonGroovyJavaFxDBaccessSample"))
    mainWindow.setWidth(600);
    mainWindow.setHeight(400);

    VBox node = loadFromFXML()
    Scene scene = new Scene(node);

    mainWindow.setScene(scene);
    getApplication().getWindowManager().attach(mvcGroup.mvcId, mainWindow);
    connectActions(node, controller);
    connectMessageSource(node);
  }
}
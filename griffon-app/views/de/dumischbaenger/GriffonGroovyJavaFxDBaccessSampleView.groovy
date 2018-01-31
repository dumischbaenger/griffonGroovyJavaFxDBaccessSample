package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.stage.Stage

@ArtifactProviderFor(GriffonView)
class GriffonGroovyJavaFxDBaccessSampleView extends AbstractJavaFXGriffonView {
  @MVCMember @Nonnull
  GriffonGroovyJavaFxDBaccessSampleController controller
  @MVCMember @Nonnull
  GriffonGroovyJavaFxDBaccessSampleModel model

  @FXML 
  TextField countText
  
  void initUI() {
    Stage stage = (Stage) getApplication()
        .createApplicationContainer(Collections.<String, Object>emptyMap());
    stage.setTitle(getApplication().getMessageSource()
                .getMessage('application.title', "GriffonGroovyJavaFxDBaccessSample"))
    stage.setWidth(400);
    stage.setHeight(120);

    def node = loadFromFXML()
    Scene scene = new Scene(node);

    stage.setScene(scene);
    getApplication().getWindowManager().attach("mainWindow", stage);
    connectActions(node, controller);
    connectMessageSource(node);
    
    model.clickCount().bindBidirectional(countText.textProperty());
  }
}
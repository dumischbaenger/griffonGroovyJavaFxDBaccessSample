package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.scene.Group
import javafx.scene.Scene
import javafx.stage.Stage

@ArtifactProviderFor(GriffonView)
class GriffonGroovyJavaFxDBaccessSampleView extends AbstractJavaFXGriffonView {
  @MVCMember @Nonnull
  GriffonGroovyJavaFxDBaccessSampleController controller
  @MVCMember @Nonnull
  GriffonGroovyJavaFxDBaccessSampleModel model

  void initUI() {
    Stage stage = (Stage) getApplication()
        .createApplicationContainer(Collections.<String, Object>emptyMap());
    stage.setTitle("bla");
    stage.setWidth(400);
    stage.setHeight(120);

    def node = loadFromFXML()
    Scene scene = new Scene(node);

    stage.setScene(scene);
    getApplication().getWindowManager().attach("mainWindow", stage);
    connectActions(node, controller);
    connectMessageSource(node);  
  }
}
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
  FactoryBuilderSupport builder
  @MVCMember @Nonnull
  GriffonGroovyJavaFxDBaccessSampleController controller
  @MVCMember @Nonnull
  GriffonGroovyJavaFxDBaccessSampleModel model

  void initUI() {
    //        builder.application(title: application.configuration['application.title'],
    //            sizeToScene: true, centerOnScreen: true, name: 'mainWindow') {
    //            scene(fill: WHITE, width: 200, height: 60) {
    //                g = gridPane {
    //                    label(id: 'clickLabel', row: 0, column: 0,
    //                          text: bind(model.clickCountProperty()))
    //                    button(row: 1, column: 0, prefWidth: 200,
    //                           griffonActionId: 'click')
    //                }
    //                connectActions(g, controller)
    //                connectMessageSource(g)
    //            }
    //        }

    Stage stage = (Stage) getApplication()
        .createApplicationContainer(Collections.<String, Object>emptyMap());
    stage.setTitle("bla");
    stage.setWidth(400);
    stage.setHeight(120);

    def node = loadFromFXML()
    Scene scene = new Scene(node);
//    scene.getRoot().getChildren().addAll(node);
    

    stage.setScene(scene);
    getApplication().getWindowManager().attach("mainWindow", stage);
  }
}
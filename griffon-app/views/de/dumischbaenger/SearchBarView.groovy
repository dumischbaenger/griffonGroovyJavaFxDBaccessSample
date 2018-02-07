package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.ButtonBar
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color

@ArtifactProviderFor(GriffonView)
class SearchBarView extends AbstractJavaFXGriffonView {
    @MVCMember @Nonnull
    SearchBarController controller
    @MVCMember @Nonnull
    SearchBarModel model
    
    @MVCMember
    GriffonGroovyJavaFxDBaccessSampleView parentView
    
    @FXML
    ButtonBar searchbar

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
      log.info("searchBar mvcGroupInit")
    }
  
    void initUI() {
      log.info("searchBar initUI")
      
      loadFromFXML()
      searchbar.setBackground(new Background(new BackgroundFill(Color
        .rgb(17, 119, 255), CornerRadii.EMPTY, Insets.EMPTY)));
      connectActions(searchbar, controller);
      connectMessageSource(searchbar);
  
      parentView.vbox.getChildren().add(searchbar)
    }
}
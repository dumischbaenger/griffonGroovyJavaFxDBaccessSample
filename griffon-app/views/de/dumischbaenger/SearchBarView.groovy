package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.beans.property.SimpleStringProperty
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.layout.HBox

@ArtifactProviderFor(GriffonView)
class SearchBarView extends AbstractJavaFXGriffonView {
    @MVCMember @Nonnull
    SearchBarController controller
    @MVCMember @Nonnull
    SearchBarModel model
    
    @MVCMember
    MainView parentView
    
    @FXML
    HBox searchbar
    
    @FXML
    TextField searchName

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
      log.info("searchBar mvcGroupInit")
    }
  
    void initUI() {
      log.info("searchBar initUI")
      
      loadFromFXML()
//      searchbar.setBackground(new Background(new BackgroundFill(Color
//        .rgb(17, 119, 255), CornerRadii.EMPTY, Insets.EMPTY)));
      connectActions(searchbar, controller);
      connectMessageSource(searchbar);
      
//      model.nameFilter.textProperty.
//      personId.textProperty().unbind()
//      searchName.textProperty().bind(model.nameFilterProperty)
      SimpleStringProperty nameFilterProp=model.nameFilterProperty
      nameFilterProp.bind(searchName.textProperty())
      
  
      parentView.vbox.getChildren().add(searchbar)
    }
}
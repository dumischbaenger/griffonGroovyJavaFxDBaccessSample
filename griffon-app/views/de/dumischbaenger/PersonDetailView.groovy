package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.beans.binding.Bindings
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.util.StringConverter
import javafx.util.converter.NumberStringConverter

@ArtifactProviderFor(GriffonView)
class PersonDetailView extends AbstractJavaFXGriffonView {
  @MVCMember @Nonnull
  PersonDetailController controller
  @MVCMember @Nonnull
  PersonDetailModel model


  @MVCMember
  GriffonGroovyJavaFxDBaccessSampleView parentView

  @FXML
  AnchorPane personDetail
  @FXML
  TextField personId
  @FXML
  TextField personName
  @FXML
  TextField personAge
  @FXML
  ComboBox personGender


  @Override
  public void mvcGroupInit(@Nonnull Map<String, Object> args) {
    println("PersonDetailView mvcGroupInit")
  }


  void initUI() {
    println("PersonDetailView initUI")
    loadFromFXML()
    //      personDetail.setBackground(new Background(new BackgroundFill(Color
    //        .rgb(17, 119, 255), CornerRadii.EMPTY, Insets.EMPTY)));
    connectActions(personDetail, controller);
    connectMessageSource(personDetail);

    parentView.vbox.getChildren().add(personDetail)
    ObservableList<Gender> genders = FXCollections.observableArrayList();
    genders.addAll(model.genders.values());


    personGender.setItems(genders);
    personGender.setConverter(new StringConverter<Gender>() {
      public String toString(Gender g) {
        return "$g.name ($g.id)"
      }
      public Gender fromString(String string) {
         combo.getItems().stream().filter({
           gender -> gender.getName().equals(string).findFirst().orElse(null)
         })
      }
    })

    //      model.personModel.name().bindBidirectional(personName.textProperty());
    //      model.personModel.age().bindBidirectional(personAge.textProperty(), new NumberStringConverter());
    //      personAge.textProperty().bindBidirectional(model.personModel.age,new NumberStringConverter())
    Bindings.bindBidirectional(personId.textProperty(), model.personModel.id(), new NumberStringConverter());
    Bindings.bindBidirectional(personName.textProperty(), model.personModel.name())
    Bindings.bindBidirectional(personAge.textProperty(), model.personModel.age(), new NumberStringConverter());
    def xxx=personGender.valueProperty()
    println("")
    Bindings.bindBidirectional(personGender.valueProperty(), model.personModel.gender());
    
  }
}
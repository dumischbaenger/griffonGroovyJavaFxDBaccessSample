package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import de.dumischbaenger.domainmodel.Gender
import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.javafx.beans.binding.UIThreadAwareBindings
import griffon.javafx.collections.GriffonFXCollections
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
  MainView parentView

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
    log.info("PersonDetailView mvcGroupInit")
  }


  void initUI() {
    log.info("PersonDetailView initUI")
    
    loadFromFXML()
    //      personDetail.setBackground(new Background(new BackgroundFill(Color
    //        .rgb(17, 119, 255), CornerRadii.EMPTY, Insets.EMPTY)));
    connectActions(personDetail, controller);
    connectMessageSource(personDetail);

    parentView.vbox.getChildren().add(personDetail)
    ObservableList<Gender> genders = FXCollections.observableArrayList();
    genders.addAll(Gender.genders.values());
    //BD tried this to avoid "Not on FX application thread" error but didn't help
//    ObservableList<Gender> gendersThreadAware = GriffonFXCollections.uiThreadAwareObservableList(genders)


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

  }
  
  void showPerson(Person p) {
    log.info("PersonDetailView showPerson")
    
    if(model.person!=null) {
      log.info("unbind from: $model.person")
      personId.textProperty().unbind()
      Bindings.unbindBidirectional(personName.textProperty(), model.person.nameProperty)
      Bindings.unbindBidirectional(personAge.textProperty(), model.person.ageProperty)
      Bindings.unbindBidirectional(personGender.valueProperty(), model.person.genderProperty)
    }
    
    if(p!=null) {
      log.info("bind to: $p")
      personId.textProperty().bind(p.idProperty.asString())
      Bindings.bindBidirectional(personName.textProperty(), p.nameProperty)
      Bindings.bindBidirectional(personAge.textProperty(), p.ageProperty, new NumberStringConverter())
      //BD this call must run inside UI thread to avoid "Not on FX application thread" error
      runInsideUISync{
        Bindings.bindBidirectional(personGender.valueProperty(), p.genderProperty)
      }
//      BD tried this to avoid "Not on FX application thread" error but didn't help
//      UIThreadAwareBindings.uiThreadAwareBind(personGender.valueProperty(), p.genderProperty)
    }
  }
}
package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.fxml.FXML
import javafx.scene.control.ScrollPane
import javafx.scene.layout.AnchorPane

@ArtifactProviderFor(GriffonView)
class PersonListView extends AbstractJavaFXGriffonView {
    @MVCMember @Nonnull
    FactoryBuilderSupport builder
    @MVCMember @Nonnull
    PersonListController controller
    @MVCMember @Nonnull
    PersonListModel model

    @MVCMember
    GriffonGroovyJavaFxDBaccessSampleView parentView

    @FXML
    AnchorPane rootAnchor  

    void initUI() {
    log.info("PersonDetailView initUI")
    
    loadFromFXML()

    parentView.vbox.getChildren().add(rootAnchor)
//    VBox.setVgrow(scrollPane, Priority.ALWAYS);
    
//    connectActions(personDetail, controller);
//    connectMessageSource(personDetail);
//
//    parentView.vbox.getChildren().add(personDetail)
//    ObservableList<Gender> genders = FXCollections.observableArrayList();
//    genders.addAll(Gender.genders.values());
//
//
//    personGender.setItems(genders);
//    personGender.setConverter(new StringConverter<Gender>() {
//      public String toString(Gender g) {
//        return "$g.name ($g.id)"
//      }
//      public Gender fromString(String string) {
//         combo.getItems().stream().filter({
//           gender -> gender.getName().equals(string).findFirst().orElse(null)
//         })
//      }
//    })

   }
}
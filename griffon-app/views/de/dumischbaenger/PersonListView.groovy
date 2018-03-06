package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import de.dumischbaenger.domainmodel.Gender
import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.collections.FXCollections
import javafx.util.StringConverter

@ArtifactProviderFor(GriffonView)
class PersonListView extends AbstractJavaFXGriffonView {
    @MVCMember @Nonnull
    FactoryBuilderSupport builder
    @MVCMember @Nonnull
    PersonListController controller
    @MVCMember @Nonnull
    PersonListModel model

    void initUI() {
    log.info("PersonDetailView initUI")
    
    loadFromFXML()

    connectActions(personDetail, controller);
    connectMessageSource(personDetail);

    parentView.vbox.getChildren().add(personDetail)
    ObservableList<Gender> genders = FXCollections.observableArrayList();
    genders.addAll(Gender.genders.values());


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
}
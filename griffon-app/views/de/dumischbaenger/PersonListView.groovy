package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
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
    
    @FXML
    TableView table
    @FXML
    TableColumn columnId
    @FXML
    TableColumn columnName
    @FXML
    TableColumn columnAge

    void initUI() {
    log.info("PersonDetailView initUI")
    
    loadFromFXML()

    parentView.vbox.getChildren().add(rootAnchor)
    connectMessageSource(rootAnchor);
    
    columnId.setCellValueFactory{
      personObserver -> 
      Person p=personObserver.value
      p.idProperty
    }
    columnName.setCellValueFactory{
      personObserver ->
      Person p=personObserver.value
      p.nameProperty
    }
    columnAge.setCellValueFactory{
      personObserver ->
      Person p=personObserver.value
      p.ageProperty
    }

//    connectActions(personDetail, controller);

   }
}
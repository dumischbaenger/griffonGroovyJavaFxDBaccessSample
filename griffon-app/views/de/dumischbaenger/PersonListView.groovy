package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.beans.property.IntegerProperty
import javafx.beans.value.ChangeListener
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TableColumn.CellEditEvent
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.layout.AnchorPane
import javafx.util.StringConverter

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
    
//    initUIReadOnly()
    initUIReadWrite()
  }

  void initUIReadWrite() {
    log.info("PersonDetailView initUIReadWrite")

    loadFromFXML()
    
    table.setEditable(true)

    parentView.vbox.getChildren().add(rootAnchor)
    connectMessageSource(rootAnchor);

    columnId.setCellValueFactory{ personObserver ->
      Person p=personObserver.value
      p.idProperty
    }
    columnId.setCellFactory(
      TextFieldTableCell.forTableColumn(
        new StringConverter<Integer>() {
          public String toString(Integer i) {
            return Integer.toString(i)
          }
          public Integer fromString(String intAsString) {
            return Integer.parseInt(intAsString)
          }
        }
      )
    )
    columnName.setCellValueFactory{ personObserver ->
      Person p=personObserver.value
      p.nameProperty
    }
    columnName.setCellFactory(TextFieldTableCell.forTableColumn())
    columnName.setOnEditCommit(
      new EventHandler<CellEditEvent<Person, String>>() {
        public void handle(CellEditEvent<Person, String> t) {
            ((Person) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setName(t.getNewValue());
        }
      }
    )
    
    columnAge.setCellValueFactory{ personObserver ->
      Person p=personObserver.value
      p.ageProperty
    }
    columnAge.setCellFactory(
      TextFieldTableCell.forTableColumn(
        new StringConverter<Integer>() {
          public String toString(Integer i) {
            return Integer.toString(i)
          }
          public Integer fromString(String intAsString) {
            return Integer.parseInt(intAsString)
          }
        }
      )
    )

    // Listen for selection changes and show the person details when changed.
    table.getSelectionModel().selectedItemProperty().addListener({ observable, oldValue, newValue ->
      controller.showPersonsDetails(newValue)
    } as ChangeListener);

    //    connectActions(personDetail, controller);

  }

  void initUIReadOnly() {
    log.info("PersonDetailView initUIReadOnly")
  
    loadFromFXML()
  
    parentView.vbox.getChildren().add(rootAnchor)
    connectMessageSource(rootAnchor);
  
    columnId.setCellValueFactory{ personObserver ->
      Person p=personObserver.value
      p.idProperty
    }
    columnName.setCellValueFactory{ personObserver ->
      Person p=personObserver.value
      p.nameProperty
    }
    columnAge.setCellValueFactory{ personObserver ->
      Person p=personObserver.value
      p.ageProperty
    }
  
    // Listen for selection changes and show the person details when changed.
    table.getSelectionModel().selectedItemProperty().addListener({ observable, oldValue, newValue ->
      controller.showPersonsDetails(newValue)
    } as ChangeListener);
  
    //    connectActions(personDetail, controller);
  
  }
}
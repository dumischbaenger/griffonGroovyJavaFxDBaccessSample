package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import de.dumischbaenger.domainmodel.Gender
import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.ComboBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TableColumn.CellEditEvent
import javafx.scene.control.cell.ComboBoxTableCell
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
  MainView parentView

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
  @FXML
  TableColumn columnGender
  @FXML
  TableColumn columnGenderCombo

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

    String maleText=getApplication().getMessageSource().getMessage('gender.male')
    String femaleText=getApplication().getMessageSource().getMessage('gender.female')
    
    columnGender.setCellValueFactory{ personObserver ->
      Person p=personObserver.value
      p.genderProperty
    }
    columnGender.setCellFactory(
      TextFieldTableCell.forTableColumn(
        new StringConverter<Gender>() {
          public String toString(Gender g) {
            String result="";
            if(g!=null) {
              result=(g.id==1) ? maleText : femaleText
            }
            return result
          }
          public Gender fromString(String genderAsStrinig) {
            Gender result=Gender.genders[1]
            switch(genderAsStrinig) {
              case "1": case maleText:
              result=Gender.genders[1]
              break
              case "2": case femaleText:
              result=Gender.genders[2]
              break
            }
            return result
          }
        }
      )
    )

    ObservableList<Gender> genders = FXCollections.observableArrayList(
      Gender.genders.values().toArray()
    );

    columnGenderCombo.setCellValueFactory{ personObserver ->
      Person p=personObserver.value
      p.genderProperty
    }
    columnGenderCombo.setCellFactory(
      ComboBoxTableCell.forTableColumn(
        new StringConverter<Gender>() {
          public String toString(Gender g) {
            String result="";
            if(g!=null) {
              result=(g.id==1) ? maleText : femaleText
            }
            return result
          }
          public Gender fromString(String genderAsStrinig) {
            Gender result=Gender.genders[1]
            switch(genderAsStrinig) {
              case maleText:
              result=Gender.genders[1]
              case femaleText:
              result=Gender.genders[2]
            }
            return result
          }
        },
      genders)
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
package de.dumischbaenger

import javax.annotation.Nonnull
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory

import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView
import org.w3c.dom.Document
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList

import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.beans.binding.Bindings
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.WindowEvent

@ArtifactProviderFor(GriffonView)
class LoginView extends AbstractJavaFXGriffonView {
  @MVCMember @Nonnull
  FactoryBuilderSupport builder
  @MVCMember @Nonnull
  LoginController controller
  @MVCMember @Nonnull
  LoginModel model

  @MVCMember
  MainView parentView

  @FXML
  ComboBox database
  @FXML
  TextField user
  @FXML
  TextField pwd

  void initUI() {
    Stage loginWindow = (Stage) getApplication()
        .createApplicationContainer(Collections.<String, Object>emptyMap());
    loginWindow.setTitle(getApplication().getMessageSource()
        .getMessage('login.title', "Login"))
    loginWindow.setWidth(300);
    loginWindow.setHeight(150);
    //    loginWindow.initStyle(StageStyle.UNDECORATED);

    //BD disable close button in login window decoration
    loginWindow.setOnCloseRequest{ WindowEvent e->
      e.consume()
    };

    GridPane node = loadFromFXML()
    Scene scene = new Scene(node);

    loginWindow.setScene(scene);
    getApplication().getWindowManager().attach(mvcGroup.mvcId, loginWindow);
    loginWindow.initModality(Modality.APPLICATION_MODAL)
    Stage mainWindow=application.windowManager.findWindow('mainFrame')
    loginWindow.initOwner(mainWindow)

    List persistenceUnits=persistenceUnits


    connectActions(node, controller);
    connectMessageSource(node);

    Bindings.bindBidirectional(user.textProperty(), model.userProperty)
    Bindings.bindBidirectional(pwd.textProperty(), model.pwdProperty)

  }

  private List getPersistenceUnits() {

    List pUnits=[]

    DocumentBuilderFactory builderFactory=DocumentBuilderFactory.newInstance();
    builderFactory.setValidating(false);
    builderFactory.setNamespaceAware(false);
    builderFactory.setCoalescing(true);
    builderFactory.setIgnoringComments(true);
    builderFactory.setExpandEntityReferences(true);
    DocumentBuilder documentBuilder;
    Document xmlTree=null;


    //URL url=getClass().getResource("/googleFeedConf.xml");
    String filename="/META-INF/persistence.xml";
    URL url=getClass().getResource(filename);
    try {
      documentBuilder = builderFactory.newDocumentBuilder();

      xmlTree=documentBuilder.parse(url.openStream());
    } catch (Exception e) {
      log.error("$filename could not be parsed")
      application.shutdown()
    }

    //BD XPath - Expression aufsetzen
    XPathFactory xpf=XPathFactory.newInstance();
    XPath xPath=xpf.newXPath();
    XPathExpression expression=null;
    try {
      String filter="/persistence/persistence-unit";
      log.debug("xpath filter: " + filter);
      expression=xPath.compile(filter);
    } catch (XPathExpressionException e) {
      log.error("xpath expression could not be compiled", e);
      application.shutdown()
    }

    //BD Abfrage mit Hilfe der Xpath Expression
    try {
      NodeList nodeList=(NodeList)expression.evaluate(xmlTree, XPathConstants.NODESET);
      for(int i=0;i<nodeList.getLength();i++){
        Node n=nodeList.item(i);
        NamedNodeMap attributes=n.getAttributes();
        String name=attributes.getNamedItem("name").getNodeValue();
        pUnits.add(name)
      }
    } catch (XPathExpressionException e) {
      log.error("xpath expression not valid", e);
      System.exit(1);
    }

    pUnits
  }

  void show() {
    application.windowManager.show("login")
  }
}
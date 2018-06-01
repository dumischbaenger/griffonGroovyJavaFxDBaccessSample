package de.dumischbaenger

import javax.annotation.Nonnull
import javax.swing.JComboBox
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController
import org.w3c.dom.Document
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import javafx.stage.Stage

@ArtifactProviderFor(GriffonController)
class LoginController extends AbstractGriffonController {
  @MVCMember @Nonnull
  LoginModel model

  @MVCMember @Nonnull
  LoginView view

  @javax.inject.Inject
  DbLoginService dbLoginService;

  void startLogin() {
    view.show()
  }

  private Map getPersistenceUnits() {

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

    database=new JComboBox<String>();

    //BD Abfrage mit Hilfe der Xpath Expression
    try {
      NodeList nodeList=(NodeList)expression.evaluate(xmlTree, XPathConstants.NODESET);
      for(int i=0;i<nodeList.getLength();i++){
        Node n=nodeList.item(i);
        NamedNodeMap attributes=n.getAttributes();
        String name=attributes.getNamedItem("name").getNodeValue();
        database.addItem(name);
      }
    } catch (XPathExpressionException e) {
      LOGGER.fatal("xpath expression not valid", e);
      System.exit(1);
    }

  }

  @ControllerAction
  void login() {
    log.info("LoginController login")

    dbLoginService.login(model.user, model.pwd)

    //      application.mvcGroupManager.destroyMVCGroup(mvcGroup.mvcId)
    //      log.info("MVCGroup: $mvcGroup.mvcId")

    runInsideUISync {
      Stage window=application.windowManager.findWindow(mvcGroup.mvcId)
      window.hide()
    }

    destroyMVCGroup(mvcGroup.mvcId)
  }

  @ControllerAction
  void cancel() {
    log.info("LoginController cancel")

    getApplication().shutdown()
  }
}
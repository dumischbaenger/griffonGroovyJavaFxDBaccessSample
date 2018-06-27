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


  @ControllerAction
  void login() {
    log.info("LoginController login")

    dbLoginService.login(model.user, model.pwd, model.persistenceUnit)

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
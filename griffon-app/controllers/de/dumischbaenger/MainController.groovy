package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

import griffon.core.GriffonApplication
import griffon.core.artifact.GriffonController
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonController)
class MainController extends AbstractGriffonController{
  @MVCMember @Nonnull
  MainModel model
  
  @Override
  public void mvcGroupInit(@Nonnull Map<String, Object> args) {
    log.info("MainController mvcGroupInit")
    createMVC("login")
    
  }
  
  void onReadyStart(GriffonApplication application) {
    log.info("MainController onReadyStart")

    println application.windowManager.getWindowNames()

    application.windowManager.show('mainWindow')
    application.windowManager.show('login')
  }

}
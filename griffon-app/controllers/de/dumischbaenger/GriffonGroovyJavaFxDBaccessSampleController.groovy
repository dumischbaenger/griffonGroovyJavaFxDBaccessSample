package de.dumischbaenger

import java.util.Map

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

import griffon.core.artifact.GriffonController
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonController)
class GriffonGroovyJavaFxDBaccessSampleController extends AbstractGriffonController{
  @MVCMember @Nonnull
  GriffonGroovyJavaFxDBaccessSampleModel model
  
  @Override
  public void mvcGroupInit(@Nonnull Map<String, Object> args) {
    log.info("GriffonGroovyJavaFxDBaccessSampleController mvcGroupInit")
//    application.windowManager.show('loginWindow')
    createMVC("login")
    
    println application.windowManager.getWindowNames()

    application.windowManager.show('mainWindow')
    
//    System.sleep(5000)
    
    application.windowManager.show('login')
  }

}
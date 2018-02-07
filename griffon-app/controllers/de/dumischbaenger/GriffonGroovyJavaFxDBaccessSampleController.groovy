package de.dumischbaenger

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading
import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController

@ArtifactProviderFor(GriffonController)
class GriffonGroovyJavaFxDBaccessSampleController extends AbstractGriffonController{
  @MVCMember @Nonnull
  GriffonGroovyJavaFxDBaccessSampleModel model

  @ControllerAction
  @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
  void click() {
    log.info("GriffonGroovyJavaFxDBaccessSampleController click")
    
    String helper=model.clickCount ? model.clickCount : "0"
    int count = 0
    try {
      count=helper.toInteger()
    } catch (Exception e) {
    }
    model.clickCount = String.valueOf(count + 1)
  }
}
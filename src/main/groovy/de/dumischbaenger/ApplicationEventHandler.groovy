package de.dumischbaenger

import java.util.Map

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import griffon.core.GriffonApplication
import griffon.core.event.EventHandler
import griffon.exceptions.GriffonViewInitializationException
import javafx.application.Platform

class ApplicationEventHandler implements EventHandler {
  Logger log=LoggerFactory.getLogger(this.getClass())
  void onUncaughtGriffonViewInitializationException(GriffonViewInitializationException x) {
    Platform.exit()
  }

  void onBootstrapStart(GriffonApplication application) {
    log.info("ApplicationEventHandler onBootstrapStart")

//    println application.startupArgs
  }

  void onJpaConnectStart(String persistenceUnitName, Map<String, Object> config) {
    log.info("ApplicationEventHandler onJpaConnectStart - not jet connected to $persistenceUnitName $config")
    Map factory=config['factory']
    if(System.properties.get("griffonGroovyJavaFxDBaccessSample.user")) {
      factory['hibernate.connection.username']=System.properties.get("griffonGroovyJavaFxDBaccessSample.user")
    }
    if(System.properties.get("griffonGroovyJavaFxDBaccessSample.password")) {
      factory['hibernate.connection.password']=System.properties.get("griffonGroovyJavaFxDBaccessSample.password")
    }
  }

  void onJpaDisconnectEnd(String persistenceUnitName, Map<String, Object> config) {
    log.info("ApplicationEventHandler onJpaDisconnectEnd - disconnected from $persistenceUnitName $config")
  }
}
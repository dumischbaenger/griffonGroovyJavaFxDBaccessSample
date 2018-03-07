package de.dumischbaenger

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

    println application.startupArgs
  }

  void onJpaDisconnectEnd(String persistenceUnitName, Map<String, Object> config) {
    log.info("ApplicationEventHandler onJpaDisconnectEnd - disconnected from $persistenceUnitName $config")
  }
}
package de.dumischbaenger

import griffon.core.GriffonApplication
import griffon.core.event.EventHandler
import griffon.exceptions.GriffonViewInitializationException
import javafx.application.Platform

class ApplicationEventHandler implements EventHandler {
    void onUncaughtGriffonViewInitializationException(GriffonViewInitializationException x) {
        Platform.exit()
    }
	
	void onBootstrapStart(GriffonApplication application) {
		println("I am here")
		println application.startupArgs
	}

  void onJpaDisconnectEnd(String persistenceUnitName, Map<String, Object> config) {
//    log.info()
//    log.info("event onJpaDisconnectEnd $persistenceUnitName $config")
    
    println("event onJpaDisconnectEnd $persistenceUnitName $config")
  }
}
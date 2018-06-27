package de.dumischbaenger

import javax.annotation.Nonnull

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel

import griffon.core.artifact.GriffonModel
import griffon.metadata.ArtifactProviderFor
import griffon.transform.FXObservable
import groovy.util.slurpersupport.GPathResult

@ArtifactProviderFor(GriffonModel)
class LoginModel extends AbstractGriffonModel {
    
    @FXObservable
    String user
    @FXObservable
    String pwd
    @FXObservable
    List persistenceUnits=[]
    @FXObservable
    String persistenceUnit=""
    
    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
      log.info("LoginModel mvcGroupInit")
      
      List pUnits=[]
      
        String filename="/META-INF/persistence.xml"
        GPathResult persistence
        try {
          String text=getClass().getResource(filename).text
          persistence=new XmlSlurper(false,false).parseText(text)
        } catch (Exception e) {
          log.error("$filename could not be parsed")
          application.shutdown()
        }
      
        pUnits=persistence.'persistence-unit'.@name*.text()
        
        persistenceUnits.addAll(pUnits)
    
    }

}
package de.dumischbaenger

import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService

import de.dumischbaenger.domainmodel.Person
import griffon.core.artifact.GriffonService
import griffon.metadata.ArtifactProviderFor
import griffon.plugins.jpa.EntityManagerHandler

@ArtifactProviderFor(GriffonService.class)
class DbLoginService extends AbstractGriffonService {

  @Inject
  private EntityManagerHandler entityManagerHandler
  
  boolean login(String user, String pwd) {
    log.info("DbLoginService login: User $user")
    
    //BD attention: I put username and password into the system properties 
    //BD to access it later in ApplicationEventHandler.onJpaConnectStart() method
    if(user) {
      System.properties.put("griffonGroovyJavaFxDBaccessSample.user",user)
    }
    if(pwd) {
      System.properties.put("griffonGroovyJavaFxDBaccessSample.password",pwd)
    }

    List persons=[]
    entityManagerHandler.withEntityManager("exampledb"){ 
      String persistenceUnitName, EntityManager entityManager ->
      persons=entityManager.createQuery('select p from Person p where 1=2').getResultList().collect()
    }

    true
  }
  
}

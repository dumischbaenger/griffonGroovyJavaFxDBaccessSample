package de.dumischbaenger

import javax.inject.Inject
import javax.persistence.EntityManager

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService

import griffon.core.artifact.GriffonService
import griffon.metadata.ArtifactProviderFor
import griffon.plugins.jpa.EntityManagerHandler

@ArtifactProviderFor(GriffonService.class)
class PersonService extends AbstractGriffonService {

  @Inject
  private EntityManagerHandler entityManagerHandler

  String getPersonName(int id) {
       entityManagerHandler.withEntityManager("exampledb"){ 
         String persistenceUnitName, EntityManager entityManager ->
         println("persistenceUnitName: $persistenceUnitName")
//         entityManager.createQuery('select p from Person p').singleResult?.name ?: null
         entityManager.createQuery('select p from TestPerson p').singleResult?.summary ?: null
       }
  }
}

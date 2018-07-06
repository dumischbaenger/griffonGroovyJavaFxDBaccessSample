package de.dumischbaenger

import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.EntityTransaction

import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService
import org.codehaus.groovy.ast.stmt.SwitchStatement

import de.dumischbaenger.domainmodel.Person
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
      log.info("persistenceUnitName: $persistenceUnitName")
//               entityManager.createQuery('select p from Person p').singleResult?.name ?: null
      entityManager.createQuery('select p from Person p').singleResult?.name ?: null
    }
  }
  List searchPersons(Map searchCriteria) {
    log.info("search criteria: $searchCriteria")
    List persons=[]
    entityManagerHandler.withEntityManager("exampledb"){ 
      String persistenceUnitName, EntityManager entityManager ->
      Map x=searchCriteria
      String query="select p from Person p where 1=1 "
      searchCriteria.each{
        key, value ->
        if(value) {
          switch (value) {
            case [String]:
              query+= " $key like '${value}%'"
              break
            default:
              query+=""
          }
        } 
      }
      persons=entityManager.createQuery(query).getResultList().collect()
    }
    
    log.info("personen: $persons")

    persons
  }
  
  Person newPerson() {
    log.info("new person")
    Person p=new Person();
    p.name="enter name"
    p.age=99
    p.gender=1

    p    
  }
  
  Person savePerson(Person p) {
    log.info("save person: $p")
    entityManagerHandler.withEntityManager("exampledb"){
      String persistenceUnitName, EntityManager entityManager ->
      EntityTransaction tx=entityManager.getTransaction()
      tx.begin()
      entityManager.persist(p)
      tx.commit()
    }

    p    
  }
  void removePerson(Person p) {
    log.info("removePerson person: $p")

    if(p!=null) {
      entityManagerHandler.withEntityManager("exampledb"){
        String persistenceUnitName, EntityManager entityManager ->
        EntityTransaction tx=entityManager.getTransaction()
        tx.begin()
        entityManager.remove(p)
        tx.commit()
      }
    }
  
    p    
  }
}

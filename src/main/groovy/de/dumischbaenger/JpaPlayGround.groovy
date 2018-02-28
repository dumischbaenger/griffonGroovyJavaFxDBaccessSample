package de.dumischbaenger

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

import de.dumischbaenger.domainmodel.Person

class JpaPlayGround {

  static main(args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("exampledb");
    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();
    
    Person p1 = new Person();
    p1.age=13
    p1.name="p1"
    p1.gender=1
    em.persist(p1);

    Person p2 = new Person();
    p2.age=13
    p2.name="p2"
    p2.gender=1
    em.persist(p2);
    
    if(p1.equals(p2)) {
      println("p1 and p2 are equal")
    }

    println("hashCode: " + p1.hashCode())
    
    em.getTransaction().commit();

    emf.close();
    
  }

}

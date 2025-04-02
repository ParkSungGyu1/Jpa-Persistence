package com.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaTestController {

    @GetMapping("/practice1")
    public void practice1() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        Members member = new Members();
        member.setId(103L);
        member.setTitle("테스트맨");

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        System.out.println("BEFORE");
        em.persist(member);
        System.out.println("AFTER");

        transaction.commit();
        em.close();
        emf.close();
    }

    @GetMapping("/practice2")
    public void practice2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        Members member = new Members();
        member.setId(1001L);
        member.setTitle("회원");

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(member);
        Members member1 = em.find(Members.class, 103L);

        System.out.println("조회 결과 : " + member1.getTitle());

        transaction.commit();
        em.close();
        emf.close();
    }

    @GetMapping("/practice3")
    public void practice3() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Members member1 = em.find(Members.class, 103L);
        Members member2 = em.find(Members.class, 103L);

        System.out.println("비교 결과 : " + (member1 == member2));

        transaction.commit();
        em.close();
        emf.close();
    }

    @GetMapping("/practice4")
    public void practice4() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Members member1 = new Members();
        member1.setId(10000L);
        member1.setTitle("회원님1");

        Members member2 = new Members();
        member2.setId(10001L);
        member2.setTitle("회원님2");

        em.persist(member1);
        em.persist(member2);

        System.out.println("=============================");

        transaction.commit();
        em.close();
        emf.close();
    }

    @GetMapping("/practice5")
    public void practice5() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Members member1 = em.find(Members.class, 103L);
        member1.setTitle("바뀔까");

        transaction.commit();
        em.close();
        emf.close();
    }
}

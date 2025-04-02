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
        // JPA 기능을 가지고 있다 (영속성 컨텍스트)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // 비영속 상태
        Members member = new Members();
        member.setId(103L);
        member.setTitle("테스트맨");

        // transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        System.out.println("BEFORE"); // before 찍힘
        em.persist(member); // insert 날라감
        System.out.println("AFTER"); // after 찍힘

        transaction.commit();
        em.close();
        emf.close();
    }

    /**
     * 1차 캐시
     */
    @GetMapping("/practice2")
    public void practice2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        Members member = new Members();
        member.setId(1001L);
        member.setTitle("회원");

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //1차 캐시에 저장됨
        em.persist(member); // insert

        //104번 멤버 조회
        Members member1 = em.find(Members.class, 1001L); // select

        System.out.println("조회 결과 : " + member1.getTitle());

        transaction.commit();
        em.close();
        emf.close();
    }


    /**
     * 영속성 엔티티의 동일성 보장
     */
    @GetMapping("/practice3")
    public void practice3() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        //105번 멤버 조회
        Members member1 = em.find(Members.class, 105L);
        //105번 멤버 조회
        Members member2 = em.find(Members.class, 105L);

        System.out.println("비교 결과 : " + (member1 == member2));

        transaction.commit();
        em.close();
        emf.close();
    }


    /**
     * 트랜잭션을 지원하는 쓰기지연
     */
    @GetMapping("/practice4")
    public void practice4() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Members member1 = new Members();
        member1.setId(100002L);
        member1.setTitle("회원님1");

        Members member2 = new Members();
        member2.setId(100011L);
        member2.setTitle("회원님2");


        // 이 때 Insert 쿼리를 보내게 될까?
        em.persist(member1);
        em.persist(member2);

        System.out.println("=============================");

        transaction.commit();
        em.close();
        emf.close();
    }


    /**
     * 변경 감지 Dirty Checking
     */
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

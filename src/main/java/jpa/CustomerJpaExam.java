package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entity.Customer;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "customer-exam"); // EntityManager 를 얻어오기 위한 Factory 설정
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // Transaction 을 가져온다

        tx.begin();
        try { // persist, commit 과정에서 예외가 발생하면 rollback

            for(int i = 0; i < 100; i++) {
                Customer customer = new Customer();
                customer.setName("Jin");
                customer.setRegisterDate(System.currentTimeMillis());
                em.persist(customer); // 시퀀스 값을 가져온다
                // 현재는 allocationSize 옵션이 1이어서 반복 횟수만큼 시퀀스 조회 쿼리문이 발생됨
                // (allocationSize 옵션을 기본값으로 두면 대부분의 상황에서 좋은 성능)
            }

            System.out.println("before commit");

            tx.commit(); // insert
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}

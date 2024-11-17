package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entity.Customer;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam"); // EntityManager 를 얻어오기 위한 Factory 설정
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // Transaction 을 가져온다

        tx.begin();
        try { // persist, commit 과정에서 예외가 발생하면 rollback
            Customer customer = new Customer("ID0002", "kim");
            em.persist(customer); // insert 실행되지 않고 1차 캐시에 저장된다
            em.persist(customer);

            tx.commit(); // insert 실행된다
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}

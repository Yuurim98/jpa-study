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

            Customer customer = new Customer("ID0004", "Hong"); // 순수 자바 객체 - 비영속 상태
            em.persist(customer); // 영속 객체 - 영속 상태 / find()를 통해 객체를 데이터베이스에서 가져와서 컨텍스트에 올리면 해당 객체도 영속 상태가 된다
            em.flush();
            // commit 시점에 자동으로 flush 되지만 명시적으로 flush() 를 호출하면 commit 처리되지 않아도 insert 쿼리 발생
            // 즉 현재 실행하는 자바 프로그램에서는 insert가 발생하지만 아래 코드에서 commit 처리는 하지 않았기 때문에 H2에서는 반영이 되지 않는다
            // 다른 트랜잭션을 가지고 있음


            // tx.commit(); // insert 실행된다
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}

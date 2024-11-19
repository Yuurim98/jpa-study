package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import jpa.entity.Customer;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "customer-exam"); // EntityManager 를 얻어오기 위한 Factory 설정
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // Transaction 을 가져온다

        tx.begin();
        try { // persist, commit 과정에서 예외가 발생하면 rollback

            Customer customer = new Customer("ID0004", "Lee"); // 순수 자바 객체 - 비영속 상태
            em.persist(customer); // 영속 객체 - 영속 상태 / find()를 통해 객체를 데이터베이스에서 가져와서 컨텍스트에 올리면 해당 객체도 영속 상태가 된다

            Query query = em.createQuery("SELECT c FROM Customer c", Customer.class); // JPQL 쿼리 생성
            // JPQL 을 날릴 때 flush 가 선행되기 때문에 해당 트랜잭션에서는 DB에 반영이 된 상태

            List<Customer> customers = query.getResultList();
            System.out.println(customers.toString());

            //tx.commit(); 현재는 commit 하지 않음 -> 최종 DB 트랜잭션은 commit 하지 않았기 때문에 H2에서 바라볼 때는 반영되지 않음
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}

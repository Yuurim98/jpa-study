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

            Customer customer = new Customer("ID0005", "Jin"); // 순수 자바 객체 - 비영속 상태
            em.persist(customer); // 영속 객체 - 영속 상태 / find()를 통해 객체를 데이터베이스에서 가져와서 컨텍스트에 올리면 해당 객체도 영속 상태가 된다
            em.detach(customer); // 준 영속 상태

            Customer foundCustomer = em.find(Customer.class, "ID0005");
            System.out.println(foundCustomer.toString());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}

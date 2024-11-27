package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entity.Major;
import jpa.entity.Student;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "customer-exam"); // EntityManager 를 얻어오기 위한 Factory 설정
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction(); // Transaction 을 가져온다

        tx.begin();
        try {

            // 전공 생성
            Major major = new Major("Computer Science", "Engineering");
            em.persist(major);

            // 학생 생성
            Student student = new Student("kim", "3");
            student.setMajor(major);
            em.persist(student);

            em.flush();
            em.clear();

            // 학생의 전공 찾기
            Student foundStudent = em.find(Student.class, 1);
            System.out.println(foundStudent); // 학생 검색 시 major 에 대한 정보 함께 포함

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }

}

package studentdatabasejpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class StudentTable {
        
    public static void insertStudent(Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static List<Student> findAllStudents() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        List<Student> studentList = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();
        em.close();
        return studentList;
    }

    public static void updateStudent(Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, student.getId());
        fromDb.setName(student.getName());
        fromDb.setGpa(student.getGpa());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static Student findStudentById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    public static List<Student> findStudentByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Student.findByName");
        query.setParameter("name", name);
        List<Student> studentList = (List<Student>) query.getResultList();
        em.close();
        return studentList;
    }

    public static void removeStudent(Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, student.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }      
    }
}

package studentdatabasejpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StudentDatabaseJPA {

    public static void main(String[] args) {
        Student student1 = new Student(1, "John", 3.8);
        Student student2 = new Student(2, "Marry", 4.0);
        StudentTable.insertStudent(student1);
        StudentTable.insertStudent(student2);

        List<Student> students = StudentTable.findAllStudents();
        printAllStudents(students);
    }

    public static void printAllStudents(List<Student> studentList) {
        for (Student student : studentList) {
            System.out.print(student.getId() + " ");
            System.out.print(student.getName() + " ");
            System.out.println(student.getGpa() + " ");
        }
    }
    
    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
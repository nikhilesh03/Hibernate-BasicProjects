package org.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Course;
import org.model.Student;
import org.util.HibernateUtil;

import java.util.Set;

public class StudentDao {
    public void saveStudent(Student student) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.persist(student);
        tx.commit();
    }

    public void enrollStudents(int studentId, Set<Course> courses) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Student student = session.getReference(Student.class, studentId);
        if (student != null) {
            for (Course course : courses) {
                Course c = session.getReference(Course.class, course.getId());
                if (c != null) {
                    student.getCourses().add(c);
                    c.getStudents().add(student);
                }
            }
        }

        session.persist(student);
        tx.commit();
    }

    public void updateStudents(int studentId, String newStudentName, String newStudentEmail) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        Student updateStudent = session.getReference(Student.class, studentId);
        if (updateStudent != null) {
            updateStudent.setName(newStudentName);
            updateStudent.setEmail(newStudentEmail);
            session.persist(updateStudent);
        }
        tx.commit();
    }

    public void removeStudents(int studentId) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.remove(session.getReference(Student.class, studentId));
        tx.commit();
    }
}

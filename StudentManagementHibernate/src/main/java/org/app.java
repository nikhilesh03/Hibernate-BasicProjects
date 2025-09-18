package org;

import org.dao.StudentDao;
import org.model.Course;
import org.model.Student;

import java.util.Set;

public class app {
    public static void main(String[] args) {
        Student student = new Student();
        student.setEmail("nj@gmail.com");
        student.setName("nj");

        StudentDao studentDao = new StudentDao();
        studentDao.saveStudent(student);
        System.out.println("Student saved successfully");

        Course java = new Course();
        java.setId(1);
        Course sql = new Course();
        sql.setId(2);

        studentDao.enrollStudents(student.getId(), Set.of(java, sql));
        studentDao.updateStudents(1, "nikki", "nikki@gmail.com");
        studentDao.removeStudents(6);
    }
}

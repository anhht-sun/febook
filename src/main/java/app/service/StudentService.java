package app.service;

import java.util.List;

import app.model.Student;

public interface StudentService extends BaseService<Integer, Student> {
	boolean deleteStudent(Integer id);

	List<Student> searchStudents(String query);

	List<Student> loadStudents();
}

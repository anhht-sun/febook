package app.dao;

import java.util.List;

import app.model.Student;

public interface StudentDAO extends BaseDAO<Integer, Student> {

	List<Student> search(String query);

	List<Student> loadStudents();

}

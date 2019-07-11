package app.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import app.dao.GenericDAO;
import app.dao.StudentDAO;
import app.model.Student;

public class StudentDAOImpl extends GenericDAO<Integer, Student> implements StudentDAO {
	private static final Logger logger = Logger.getLogger(StudentDAOImpl.class);

	public StudentDAOImpl() {
		super(Student.class);
	}

	public StudentDAOImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}	

	@Override
	public List<Student> search(String query) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Student> cr = builder.createQuery(Student.class);
		Root<Student> root = cr.from(Student.class);
		cr.select(root);

		Predicate search = builder.or(builder.like(root.get("name"), "%" + query + "%"), builder.like(root.get("email"), "%" + query + "%"));

		cr.where(search);
		return getSession().createQuery(cr).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> loadStudents() {
		return getSession().createQuery("from Student").getResultList();
	}

}

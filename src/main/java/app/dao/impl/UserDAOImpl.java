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
import app.dao.UserDAO;
import app.model.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		super(User.class);
	}

	public UserDAOImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}	

	@Override
	public List<User> search(String query) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<User> cr = builder.createQuery(User.class);
		Root<User> root = cr.from(User.class);
		cr.select(root);

		Predicate search = builder.or(builder.like(root.get("name"), "%" + query + "%"), builder.like(root.get("email"), "%" + query + "%"));

		cr.where(search);
		return getSession().createQuery(cr).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadUsers() {
		return getSession().createQuery("from User").getResultList();
	}

}

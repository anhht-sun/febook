package app.service.impl;

import java.io.Serializable;

import java.util.List;

import org.apache.log4j.Logger;

import app.model.User;
import app.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public User saveOrUpdate(User entity) {
		try {
			return getUserDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean deleteUser(Integer id) {
		try {
			User user = getUserDAO().findById(id);
			return delete(user);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	

	@Override
	public List<User> searchUsers(String query) {
		try {
			return getUserDAO().search(query);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> loadUsers() {
		try {
			return getUserDAO().loadUsers();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findById(Serializable key) {
		try {
			return getUserDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(User entity) {
		try {
			getUserDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

}

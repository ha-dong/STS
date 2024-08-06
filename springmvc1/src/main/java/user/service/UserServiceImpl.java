package user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.Dao.UserDao;
import user.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> listUser() throws Exception {
		return userDao.listUser();
	}

	@Override
	public User getUser(int userId) throws Exception {
		return userDao.getUser(userId);
	}

	@Override
	public int insertUser(User user) throws Exception {
		return userDao.insertUser(user);
	}

	@Override
	public int updateUser(User userId) throws Exception {
		return userDao.updateUser(userId);
	}

	@Override
	public int deleteUser(User userId) throws Exception {
		return userDao.deleteUser(userId);
	}

}

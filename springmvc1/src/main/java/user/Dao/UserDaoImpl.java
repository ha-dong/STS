package user.Dao;

import java.util.List;

import user.domain.User;

public class UserDaoImpl implements UserDao {

	@Override
	public List<User> listUser() throws Exception {
		System.out.println("listUserȣ���");
		return null;
	}

	@Override
	public User getUser(int id) throws Exception {
		System.out.println("getUserȣ���");
		return null;
	}

	@Override
	public int insertUser(User user) throws Exception {
		System.out.println("insertUserȣ���");
		return 0;
	}

	@Override
	public int updateUser(User user) throws Exception {
		System.out.println("updateUserȣ���");
		return 0;
	}

	@Override
	public int deleteUser(User userId) throws Exception {
		System.out.println("deleteUserȣ���");
		return 0;
	}

}

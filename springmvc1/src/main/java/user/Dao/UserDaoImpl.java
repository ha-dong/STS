package user.Dao;

import java.util.List;

import user.domain.User;

public class UserDaoImpl implements UserDao {

	@Override
	public List<User> listUser() throws Exception {
		System.out.println("listUser»£√‚µ ");
		return null;
	}

	@Override
	public User getUser(int id) throws Exception {
		System.out.println("getUser»£√‚µ ");
		return null;
	}

	@Override
	public int insertUser(User user) throws Exception {
		System.out.println("insertUser»£√‚µ ");
		return 0;
	}

	@Override
	public int updateUser(User user) throws Exception {
		System.out.println("updateUser»£√‚µ ");
		return 0;
	}

	@Override
	public int deleteUser(User userId) throws Exception {
		System.out.println("deleteUser»£√‚µ ");
		return 0;
	}

}

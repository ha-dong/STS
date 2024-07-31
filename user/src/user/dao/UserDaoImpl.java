package user.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import user.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private UserDao userDao;

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUser(int userNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(int userno) {
		// TODO Auto-generated method stub
		return 0;
	}

}

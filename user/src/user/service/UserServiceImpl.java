package user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import user.domain.User;

@Service("UserService")
public class UserServiceImpl implements UserService {

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

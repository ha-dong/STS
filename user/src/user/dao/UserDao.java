package user.dao;

import java.util.List;


import user.domain.User;


public interface UserDao {
	
	public abstract int insertUser(User user);
	
	public abstract List<User> getUserList();
	
	public abstract User getUser(int userNo);
	
	public abstract int updateUser(User user);
	
	public abstract int deleteUser(int userno);
}

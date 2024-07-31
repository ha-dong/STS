package user.domain;

public class User {
	private int userNo;
	private String userId;
	private String name;
	private String gender;
	private String city;
	
	public User() {		
	}

	public User(int userNo, String userId, String name, String gender, String city) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.name = name;
		this.gender = gender;
		this.city = city;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", name=" + name + ", gender=" + gender + ", city="
				+ city + "]";
	}

}

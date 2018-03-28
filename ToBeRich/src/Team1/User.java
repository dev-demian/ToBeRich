package Team1;

public class User {
	private String id;
	private String pwd;
	private String name;
	private String sex;
	private String email;
	
	public User(String id, String pwd, String name, String sex, String email) {
		
		this.setId(id);
		this.setPwd(pwd);;
		this.setName(name);
		this.setSex(sex);
		this.setEmail(email);
		
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		
	
	
}

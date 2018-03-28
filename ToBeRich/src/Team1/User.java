package Team1;
import java.io.Serializable;

public class User  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; // 회원 아이디	
	private String pwd; // 회원 비밀번호
	private String name; // 회원 이름
	private String sex; // 회원 성별
	private String email; // 회원 이메일
	
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
		
	public String print_member(){
		String member = "id:"+this.getId()+"pwd:"+this.getPwd()+"name:"+this.getName()+"sex:"+this.getSex()+"Email:"+this.getEmail();
		System.out.println(member);
		return member;
	}
	
	
}

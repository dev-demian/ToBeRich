package Team1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class DBManager {
	File Userdb;
	private Map<String,User> userinfo;
	
	public DBManager() throws ClassNotFoundException, IOException{
		// 연결됨
		this.Userdb = new File("DB","user.txt");
			
		if(this.Userdb.exists()){
			try{
				System.out.println("파일이 이미 있어서 파일을 불러옵니다");
				FileInputStream in = new FileInputStream(Userdb);
				BufferedInputStream bufferin = new BufferedInputStream (in);
				ObjectInputStream datain = new ObjectInputStream(bufferin);
				userinfo  = (Map<String,User>) datain.readObject();
				datain.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else{
			System.out.println("파일이 없어 티폴트 값을 넣은 파일을 생성합니다");
			
			User defalt_user = new User("guak908","asdfasdf","곽재훈","남자","guak908@hanmail.net");
			Map<String,User> defalt_map = new HashMap<String,User>();
			defalt_map.put("guak908", defalt_user);
			try{
				FileOutputStream dbout = new FileOutputStream(Userdb);
				BufferedOutputStream dbbufferout = new BufferedOutputStream(dbout);
				ObjectOutputStream dbdataout = new ObjectOutputStream(dbbufferout);
				dbdataout.writeObject(defalt_map);
				dbdataout.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	

	public void signup(String fid, User user){
		// 회원가입	
		boolean singupflg = true;  // true면 회원가입 완료 
		
		for(Map.Entry<String,User> m : userinfo.entrySet()){
		// 아이디 일치 검사 일치시 singupflg == false;
			if(m.getKey().equals(fid)){
				System.out.print("중복!! ");
				singupflg = false;
			}
		}
		
		if(singupflg == true){
			System.out.println("회원가입 진행 ");
			Map<String,User> signupinfo = new HashMap<String, User>();
			signupinfo.put(fid, user);
			Map<String, User> mergeinfo = new HashMap<String, User>();
			mergeinfo.putAll(userinfo);
			mergeinfo.putAll(signupinfo);
			try{
				FileOutputStream out = new FileOutputStream(Userdb);
				BufferedOutputStream bufferout = new BufferedOutputStream(out);
				ObjectOutputStream dataout = new ObjectOutputStream(bufferout);
				dataout.writeObject(mergeinfo);
				dataout.flush();
				dataout.close();
				System.out.println("회원가입 완료");
			}catch(IOException e){
				System.out.print("DB 회원가입 연동 실패");
			}		
		}else{
			System.out.print("이미 일치하는 아이디가 있습니다!!");
		}
	}
	
	public User login(String id , String pwd){
		int i = 0;
		int user = 0;
		boolean pass=false;
		User target_user= null;
		for(Map.Entry<String,User> m : userinfo.entrySet()){
			if(m.getKey().equals(id)&&m.getValue().getPwd().equals(pwd)){
				i+=1;
				target_user =m.getValue();
				pass=true;
			}
			user++;
		}
			
		if(i ==1){
			System.out.println("로그인 성공");
			pass=true;
			
		}else if(i ==0)
			System.out.println("로그인 실패");
			
		else {
			System.out.println("로그인 오류 발생(중복 ID 발생)");
			
		}
		System.out.println("총회원 : "+user+"명");
		
		if(pass==true){
			return target_user;
		}else
		return null;
				
	}
	
	public void ShowUser(){
		for (Map.Entry<String, User> entry : userinfo.entrySet()) {
		    System.out.println(entry.getKey() + ":" + entry.getValue().print_member());
		}
	}
	
}
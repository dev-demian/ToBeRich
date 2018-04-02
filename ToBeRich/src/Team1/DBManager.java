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
import java.util.Map.Entry;

public class DBManager {
	File Userdb;
	File Savingsdb;
	
	private Map<String,User> userinfo;//Map<아이디,사용자class>
	private Map<String,Savings> savingsinfo;//Map<적금명,적금class>
	
	
	public DBManager() throws ClassNotFoundException, IOException{
		// 연결됨
		this.Userdb = new File("DB","user.txt");// 나중에 상대 경로로 바꿔주기
		this.Savingsdb = new File("DB","savings.txt");
		/////////////////////////////////////////////////////////////////////////////////////////////
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
		/////////////////////////////////////////////////////////////////////////////////////////////
		if(this.Savingsdb.exists()){
			try{
				System.out.println("적금파일이 이미 있어서 파일을 불러옵니다");
				FileInputStream sin = new FileInputStream(Savingsdb);
				BufferedInputStream sbufferin = new BufferedInputStream (sin);
				ObjectInputStream sdatain = new ObjectInputStream(sbufferin);
				savingsinfo  = (Map<String,Savings>) sdatain.readObject();
				sdatain.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("적금파일이 없어 티폴트 값을 넣은 파일을 생성합니다");
			
			Savings defalt_saving = new Savings("신나라사랑적금","신한은행","12개월","100000","단리","4.4","5,4","가산금리 최고 연 1.0% 금여이체 실적 보유시 0.7%");
			Map<String,Savings> defalt_smap = new HashMap<String,Savings>();
			defalt_smap.put("신나라사랑적금", defalt_saving);
			try{
				FileOutputStream sdbout = new FileOutputStream(Savingsdb);
				BufferedOutputStream sdbbufferout = new BufferedOutputStream(sdbout);
				ObjectOutputStream sdbdataout = new ObjectOutputStream(sdbbufferout);
				sdbdataout.writeObject(defalt_smap);
				sdbdataout.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
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
	
	////////////////////////////////////////////////////////////////////////////////////////////
	public void savings_save(String sname, Savings save){
		// 회원가입	
		boolean singupflg = true;  // true면 적금저장 완료 
		
		for(Map.Entry<String,Savings> m : savingsinfo.entrySet()){
		// 아이디 일치 검사 일치시 singupflg == false;
			if(m.getKey().equals(sname)){
				System.out.print("중복!! ");
				singupflg = false;
			}
		}
		
		if(singupflg == true){
			System.out.println("적금등록 진행 ");
			Map<String,Savings> signupinfo = new HashMap<String, Savings>();
			signupinfo.put(sname, save);
			Map<String, Savings> mergeinfo = new HashMap<String, Savings>();
			mergeinfo.putAll(savingsinfo);
			mergeinfo.putAll(signupinfo);
			try{
				FileOutputStream sout = new FileOutputStream(Savingsdb);
				BufferedOutputStream sbufferout = new BufferedOutputStream(sout);
				ObjectOutputStream sdataout = new ObjectOutputStream(sbufferout);
				sdataout.writeObject(mergeinfo);
				sdataout.flush();
				sdataout.close();
				System.out.println("적금등록 완료");
			}catch(IOException e){
				System.out.print("DB 적금등록 연동 실패");
			}		
		}else{
			System.out.print("이미 일치하는적금명이 있습니다!!");
		}
	}
	
	
	
	public void ShowUser(){
		for (Map.Entry<String, User> entry : userinfo.entrySet()) {
		    System.out.println(entry.getKey() + ":" + entry.getValue().print_member());
		}
	}
	public void ShowSavings(){
		for (Map.Entry<String, Savings> entry : savingsinfo.entrySet()) {
		    System.out.println(entry.getKey() + ":" + entry.getValue().print_member());
		}
	}
	
	public Object[][] CallTable(){
		int x =0;
		int y =9;// 선택  상품명 은행명 기간 최납금 이자적용방식 기본금리 우대금리 우대적용
		
		for(Entry<String, Savings> m : savingsinfo.entrySet()){//2차원 배열을 X Y를 측정하기 위한 foreach문
			x++;
		}
		
		//table에 넣어주기 
		Object[][] data = new Object[x][y];
		int data_x=0;
		for(Entry<String, Savings> m : savingsinfo.entrySet()){//data에 값을 넣어주기 위한 foreach문
			
			data[data_x][0] = false;
			data[data_x][1] = m.getValue().getName();
			data[data_x][2] = m.getValue().getBank();
			data[data_x][3] = m.getValue().getTerm();
			data[data_x][4] = m.getValue().getMaxsave();
			data[data_x][5] = m.getValue().getInterests_calculation();
			data[data_x][6] = m.getValue().getBasic_interest();
			data[data_x][7] = m.getValue().getUpgrade_interest();
			data[data_x][8] = false;
			
			data_x++;
			
		}
		return data;
		
	}
	
}
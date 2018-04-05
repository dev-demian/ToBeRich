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
import java.net.UnknownHostException;
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
     
         try{
        	 FileClient app_u = new FileClient("127.0.0.1",8888);
        	 userinfo = (Map<String,User>)app_u.call_request("user");
        	 try{
        	    	FileOutputStream out = new FileOutputStream(Userdb);
        			BufferedOutputStream bufferout = new BufferedOutputStream (out);
        			ObjectOutputStream dataout = new ObjectOutputStream(bufferout);
        			dataout.writeObject(userinfo);
        			dataout.flush();
        			System.out.println(userinfo);
        	    }catch(Exception e){
        	    	e.printStackTrace();
        	    }
         }catch(Exception e){
            e.printStackTrace();
           
         }
         
      
         
      
      /////////////////////////////////////////////////////////////////////////////////////////////
         try{
        	 FileClient app_s = new FileClient("127.0.0.1",8888);
        	 savingsinfo = (Map<String,Savings>)app_s.call_request("savings");
        	 try{
        	    	FileOutputStream out = new FileOutputStream(Savingsdb);
        			BufferedOutputStream bufferout = new BufferedOutputStream (out);
        			ObjectOutputStream dataout = new ObjectOutputStream(bufferout);
        			dataout.writeObject(savingsinfo);
        			dataout.flush();
        			System.out.println(savingsinfo);
        	    }catch(Exception e){
        	    	e.printStackTrace();
        	    }
            
         }catch(Exception e){
            e.printStackTrace();
            
         }
      /////////////////////////////////////////////////////////////////////////////////////////////
      
      
      
   }
   

   public void signup(String fid, User user) throws UnknownHostException, IOException{
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
         try{//저장된 Userinfo를 다시 불러와서 
           
       			FileInputStream in = new FileInputStream(Userdb);
       			BufferedInputStream bufferin = new BufferedInputStream (in);
       			ObjectInputStream datain = new ObjectInputStream(bufferin);
       			userinfo  = (Map<String,User>)datain.readObject();
           	
           }catch(Exception e){
           	e.printStackTrace();
           }
         Pakage data = new Pakage("user",userinfo);
         FileClient app_u = new FileClient("127.0.0.1",8888);
         app_u.save_request(data); 
          
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
   public void savings_save(String sname, Savings save) throws UnknownHostException, IOException{
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
         
         try{//저장된 Savingsinfo를 다시 불러와서 
             
    			FileInputStream in = new FileInputStream(Savingsdb);
    			BufferedInputStream bufferin = new BufferedInputStream (in);
    			ObjectInputStream datain = new ObjectInputStream(bufferin);
    			savingsinfo  = (Map<String,Savings>)datain.readObject();
        	
        }catch(Exception e){
        	e.printStackTrace();
        }
         Pakage data = new Pakage("savings",savingsinfo);
         FileClient app_s = new FileClient("127.0.0.1",8888);
         app_s.save_request(data); 
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
   


   public boolean overLap(String id) {
      boolean IDcheck = false;
      if(userinfo.containsKey(id))
         System.out.println("이미 가입된 ID입니다.");
      else {
         System.out.println("가입가능한 ID입니다.");
         IDcheck = true;
      }
      
      return IDcheck;
   }
   
}
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
   
   private Map<String,User> userinfo;//Map<���̵�,�����class>
   private Map<String,Savings> savingsinfo;//Map<���ݸ�,����class>
   
   
   public DBManager() throws ClassNotFoundException, IOException{
      // �����
      this.Userdb = new File("DB","user.txt");// ���߿� ��� ��η� �ٲ��ֱ�
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
      // ȸ������   
      boolean singupflg = true;  // true�� ȸ������ �Ϸ� 
      
      for(Map.Entry<String,User> m : userinfo.entrySet()){
      // ���̵� ��ġ �˻� ��ġ�� singupflg == false;
         if(m.getKey().equals(fid)){
            System.out.print("�ߺ�!! ");
            singupflg = false;
         }
      }
      
      if(singupflg == true){
         System.out.println("ȸ������ ���� ");
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
            System.out.println("ȸ������ �Ϸ�");
         }catch(IOException e){
            System.out.print("DB ȸ������ ���� ����");
         }      
         try{//����� Userinfo�� �ٽ� �ҷ��ͼ� 
           
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
         System.out.print("�̹� ��ġ�ϴ� ���̵� �ֽ��ϴ�!!");
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
         System.out.println("�α��� ����");
         pass=true;
         
      }else if(i ==0)
         System.out.println("�α��� ����");
         
      else {
         System.out.println("�α��� ���� �߻�(�ߺ� ID �߻�)");
         
      }
      System.out.println("��ȸ�� : "+user+"��");
      
      if(pass==true){
         return target_user;
      }else
      return null;
            
   }
   
   ////////////////////////////////////////////////////////////////////////////////////////////
   public void savings_save(String sname, Savings save) throws UnknownHostException, IOException{
      // ȸ������   
      boolean singupflg = true;  // true�� �������� �Ϸ� 
      
      for(Map.Entry<String,Savings> m : savingsinfo.entrySet()){
      // ���̵� ��ġ �˻� ��ġ�� singupflg == false;
         if(m.getKey().equals(sname)){
            System.out.print("�ߺ�!! ");
            singupflg = false;
         }
      }
      
      if(singupflg == true){
         System.out.println("���ݵ�� ���� ");
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
            System.out.println("���ݵ�� �Ϸ�");
         }catch(IOException e){
            System.out.print("DB ���ݵ�� ���� ����");
         }
         
         try{//����� Savingsinfo�� �ٽ� �ҷ��ͼ� 
             
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
         System.out.print("�̹� ��ġ�ϴ����ݸ��� �ֽ��ϴ�!!");
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
      int y =9;// ����  ��ǰ�� ����� �Ⱓ �ֳ��� ���������� �⺻�ݸ� ���ݸ� �������
      
      for(Entry<String, Savings> m : savingsinfo.entrySet()){//2���� �迭�� X Y�� �����ϱ� ���� foreach��
         x++;
      }
      
      //table�� �־��ֱ� 
      Object[][] data = new Object[x][y];
      
      int data_x=0;
      for(Entry<String, Savings> m : savingsinfo.entrySet()){//data�� ���� �־��ֱ� ���� foreach��
         
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
         System.out.println("�̹� ���Ե� ID�Դϴ�.");
      else {
         System.out.println("���԰����� ID�Դϴ�.");
         IDcheck = true;
      }
      
      return IDcheck;
   }
   
}
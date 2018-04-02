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
	
	private Map<String,User> userinfo;//Map<���̵�,�����class>
	private Map<String,Savings> savingsinfo;//Map<���ݸ�,����class>
	
	
	public DBManager() throws ClassNotFoundException, IOException{
		// �����
		this.Userdb = new File("DB","user.txt");// ���߿� ��� ��η� �ٲ��ֱ�
		this.Savingsdb = new File("DB","savings.txt");
		/////////////////////////////////////////////////////////////////////////////////////////////
		if(this.Userdb.exists()){
			try{
				System.out.println("������ �̹� �־ ������ �ҷ��ɴϴ�");
				FileInputStream in = new FileInputStream(Userdb);
				BufferedInputStream bufferin = new BufferedInputStream (in);
				ObjectInputStream datain = new ObjectInputStream(bufferin);
				userinfo  = (Map<String,User>) datain.readObject();
				datain.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else{
			System.out.println("������ ���� Ƽ��Ʈ ���� ���� ������ �����մϴ�");
			
			User defalt_user = new User("guak908","asdfasdf","������","����","guak908@hanmail.net");
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
				System.out.println("���������� �̹� �־ ������ �ҷ��ɴϴ�");
				FileInputStream sin = new FileInputStream(Savingsdb);
				BufferedInputStream sbufferin = new BufferedInputStream (sin);
				ObjectInputStream sdatain = new ObjectInputStream(sbufferin);
				savingsinfo  = (Map<String,Savings>) sdatain.readObject();
				sdatain.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("���������� ���� Ƽ��Ʈ ���� ���� ������ �����մϴ�");
			
			Savings defalt_saving = new Savings("�ų���������","��������","12����","100000","�ܸ�","4.4","5,4","����ݸ� �ְ� �� 1.0% �ݿ���ü ���� ������ 0.7%");
			Map<String,Savings> defalt_smap = new HashMap<String,Savings>();
			defalt_smap.put("�ų���������", defalt_saving);
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
	public void savings_save(String sname, Savings save){
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
	
}
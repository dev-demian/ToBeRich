//�Խ��� �����ε带 �ϱ� ���� ����ϴ� Ŭ����
package Team1;

import java.io.*;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Map.Entry;

public class BoardControl {
   private File target = new File("DB","board.txt"); //DS DB���� ���
   //DS �Խ��ǿ� ��ȣ�� Map key�� ����Ѵ�
   private Map<Integer,List<Object>> map = new HashMap<>();
   
   private Map<Integer,List<Object>> table_map = new HashMap<>();
   //DS �Խ��ǿ� ��ȣ�� ������ ������ List�� �����Ѵ�
   private List<Object> new_list = new ArrayList();
   private String title; //DS ����
   private String password; //DS �� �ۼ� �� ��ι�ȣ
   private String img; //DS ��� ��ũ���� �̹��� ���� ���
   private String text; //DS ���� ����
   private int number; //DS �Խ��ǿ� �Խù� ��� �� �ֽ� ��ȣ ����
   private List<String> comment =new ArrayList();// ��� �����ϱ� ���� ����
   
   public BoardControl() throws UnknownHostException, IOException {
	// TODO Auto-generated constructor stub
	  System.out.println("�⺻ ������ ȣ�� �ֽ� �Խ��� ������ �����ɴϴ� ");
	  
	  FileClient app_b = new FileClient("127.0.0.1",8888);
	  this.map = (Map<Integer,List<Object>>)app_b.call_request("board");
	  try{
	    	FileOutputStream out = new FileOutputStream(target);
			BufferedOutputStream bufferout = new BufferedOutputStream (out);
			ObjectOutputStream dataout = new ObjectOutputStream(bufferout);
			dataout.writeObject(this.map);
			dataout.flush();
			System.out.println(this.map);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
   }
   
   //DS ���� ��ü ������ �ش� �ʵ忡 ���� �� ����Ʈ�� �����Ѵ�  DB�ű� ������ ȣ�� 
   public void allSet(String title,String password,File file, String text,String userid) { 
	 //0���� 1 ��� 2 ���ϰ�� 3 �ؽ�Ʈ����� 4 ���̵� 5��¥ 6 ��ȸ�� 7 ���
	   //DS �ʵ忡 ����
      this.title = title;
      this.password = password;
      this.text = text;
      
      //DS �ʵ带 ����Ʈ�� ����
      new_list.add(this.title);
      new_list.add(this.password);
      new_list.add(file);
      new_list.add(this.text);
      new_list.add(userid); //DS �Խù��� �ø� ������� �̸��� �޾Ƽ� ����(ȫ�浿���� �켱 ������) ##����� ���� DB�� ���� �ʿ�##
      //DS ���� ��¥�� ���ϰ� list�� ������
      String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
      new_list.add(date); //DS ���� ��¥�� �ð��� ������
      new_list.add(0); //DS �ش� �Խù� ��ȸ���� list�� ������(�ʱⰪ�� 0���� ������)
      this.comment.add("����Ʈ ���");
      new_list.add(this.comment);//ó������ ���������� ��۵�Ͻ� �ϳ��ϳ� ä�������� 
      
      try {
          mapInput();
       } catch (Exception e1) {
          System.out.println("BoardConrol�� ������ ���� �� ����");
       }
       // DS ���� �� map�� DB�� ����
       try {
          mapOutput();
       } catch (Exception e1) {
          System.out.println("BoardConrol�� ������ ���� �� ����");
       }
       //�ش� �̹����� ��������
   }
   
   //DS �Խù� Ȯ����(Board_main)�� �� ������ ��ȸ���� 1�� �����ϱ� ���� �޼ҵ��̸� �ֽ� ���� map,�Խù� ��ȣ�� ���� �޴´�
   public void viewAdd( int number){
	   try{
			  ObjectInputStream in = new ObjectInputStream(
					  new BufferedInputStream(new FileInputStream(target)));
			  //DS DB�� �о� �� �����Ͱ� �����ϸ� ���� �ȴ�
			  map = (Map<Integer,List<Object>>)in.readObject();
			  in.close();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	   
	   this.number = number;
	   
	   //DS �Խù� ��ȣ�� �ش��ϴ� list�� �̾� Ŭ���� ������ list�� ����
	   this.new_list = map.get(number);
	   //DS ������ ��ȸ������ 1������ ���� Ŭ���� list�� �����Ѵ�
	   new_list.set(6, (int)(new_list.get(6))+1);
	   //DS ���� �� list�� map�� ����
	   map.put(number, new_list);
	   //DS ���� �� map�� DB�� ����
	   this.mapOutput();
	   System.out.println(new_list);
   }
   
   //DS Board_main(�Խù� Ȯ�� ��)���� ���� ��ư�� ������ �� DB�ֽ� ����map,�Խù� ��ȣ,���� �� ����,���� �����͸� ���� �޴� �޼ҵ�
   public void updateSet(int number,String title,String text) {
	   
	   try{
			  ObjectInputStream in = new ObjectInputStream(
					  new BufferedInputStream(new FileInputStream(target)));
			  //DS DB�� �о� �� �����Ͱ� �����ϸ� ���� �ȴ�
			  map = (Map<Integer,List<Object>>)in.readObject();
			  in.close();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	   
	   //�ش� �Խù� ��ȣ�� ���� �޾� �ʵ忡 ����
	   this.number = number;
	   //�ֽ� ���� �� map�� ���� �޾� �����Ѵ�
	   
	   //�޼ҵ� �������� ����ϴ� list�� ����
	   List<Object> list = new ArrayList<>();
	   //�޼ҵ� ���� list�� Ŭ���� ���� map�� �ش� key��(�Խ��� ��ȣ)�� value(list<object>)�� �ִ´� 
	   list = map.get(number);
	   //����� ������ ���� �����͸� ���� �޾� �޼ҵ� ���� list�� �����Ѵ�
	   list.set(0, title);
	   list.set(3, text);
	   //������ �ð��� ���ؼ� �޼ҵ� ���� list�� �����Ѵ�
	   String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
	   list.set(5, date);
	   //Ŭ���� ���� list�� �޼ҵ� ���� list�� �ִ´�
	   this.new_list = list;
	   //Ŭ���� ���� map�� key��(�Խù� ��ȣ)�� �ش��ϴ� map value�� list�� �������Ѵ�
	   map.put(number, list);
	   //���� �� map�� DB�� �����Ѵ�
	   this.mapOutput();
   }
   
   //DS Board_main(Ȯ��â)���� ���� ��ư�� ������ ȣ�� �Ǵ� �޼ҵ��̸� ���� �޴� �����ʹ� �ֽ� ���� map,�Խù� ��ȣ,������ �̹��� ����̴�
   public void delete(int number,File imgPath) {
	   //Ŭ���� ���� map�� ����
	   try{
			  ObjectInputStream in = new ObjectInputStream(
					  new BufferedInputStream(new FileInputStream(target)));
			  //DS DB�� �о� �� �����Ͱ� �����ϸ� ���� �ȴ�
			  map = (Map<Integer,List<Object>>)in.readObject();
			  in.close();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	   //Ŭ���� ���� ������ ����
	   this.number = number;
	   //���� ���� �̹��� ����� �̹��� ������ ����
	   imgPath.delete();
	   //�ֽ� ���� �� map���� �ش� key���� �ش��ϴ� map���� ����
	   map.remove(number);
	   //�۾��� map�� DB�� ����
	   this.mapOutput();
   }
   
   //DB���� �����͸� �ҷ��� map�� ���� �� �Խù� ��ȣ�� ���� ū ���ڷ� �����ϴ� �޼ҵ�
   public void mapInput() {
       try {
          //DS �о���� ��Ʈ�� ���� DB�� �о� �� �����Ͱ� ������ ���� �߻� catch������� ����
          ObjectInputStream in = new ObjectInputStream(
          new BufferedInputStream(new FileInputStream(target)));
          //DS DB�� �о� �� �����Ͱ� �����ϸ� ���� �ȴ�
          map = (Map<Integer,List<Object>>)in.readObject();
          //DS DB���� �ҷ� map�� ������ key�� �߿� ���� ū ���� ���� 1�� ���Ѵ�(�Խ��ǿ� �Խù� ���� ��ȣ ������ ����)
          for(int key:map.keySet()) {
             number = key;
             if(key > number)
                number = key;
          }
          number++;
          map.put(number, new_list);
       }catch(Exception e) {
          //DS DB���� �ҷ� �� �����Ͱ� ������ ����
          map.put(1, new_list);
          e.printStackTrace();
       }
   }
   //DS ���� �� map�� DB�� �����ϴ� �޼ҵ�
   public void mapOutput() {
      try {
         ObjectOutputStream out = new ObjectOutputStream(
         new BufferedOutputStream(new FileOutputStream(target)));
         
         out.writeObject(map);
         out.flush();
         
         Pakage data = new Pakage("board",map);
         FileClient app_b = new FileClient("127.0.0.1",8888);
         app_b.save_request(data);
         
      }catch(Exception e) {
         System.out.println("DB�� �����͸� ���� �� ����");
         e.printStackTrace();
      }
      //System.out.println(map); //DS �ֽ� ���� �� DB�� ������ ���� Ȯ�� �� �� ����
   }

   public Object[][] calltable() {
	// TODO Auto-generated method stub
	   //DS �о���� ��Ʈ�� ���� DB�� �о� �� �����Ͱ� ������ ���� �߻� catch������� ����
       //���ų� 
	   Object[][] Table = null;
//	   �ְų� 
	  if(target.exists()){
		  System.out.println("������ ������");
		  Table = null;
		  try{
			  ObjectInputStream in = new ObjectInputStream(
					  new BufferedInputStream(new FileInputStream(target)));
			  //DS DB�� �о� �� �����Ͱ� �����ϸ� ���� �ȴ�
			  table_map = (Map<Integer,List<Object>>)in.readObject();
			  in.close();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
		  int x_Idx =0;
		  for(Entry<Integer, List<Object>> m : table_map.entrySet()){
				x_Idx++;
		  }
		  Table = new Object[x_Idx][5];
		  
		  int Index=0 ;
		  for(Entry<Integer, List<Object>> m : table_map.entrySet()){
			  Table[Index][0] = m.getKey();//integer
			  Table[Index][1] = m.getValue().get(0);//string ����
			  Table[Index][2] = m.getValue().get(4);//string �̸�
			  Table[Index][3] = m.getValue().get(5);//String ��¥
			  Table[Index][4] = m.getValue().get(6);//Integer ��ȸ
			  
			  Index++;
		  }
		  System.out.println("�������� Table��:");
		  for(int i=0;i<Table.length;i++){// �ߵ��� Ȯ�� 
			  for(int j=0;j<Table[i].length;j++){
				  System.out.println(Table[i][j]);
			  }
			  System.out.println();
		  }
	  }else{
		  System.out.println("������ �������� �ʾ� ���� �� ���ΰ�ħ �Ͻÿ�");
		  Table = null;
	  }
	
	
      
	return Table;  //null��ȯ�� Text�� ���� ;
}
   
   
   public void addcomment(int number,String comment){
	   Map<Integer,List<Object>> called_map = new HashMap<>();
	   String new_comment  = comment;
	   if(target.exists()){
			  System.out.println("������ ������");
			  try{
				  ObjectInputStream in = new ObjectInputStream(
						  new BufferedInputStream(new FileInputStream(target)));
				  //DS DB�� �о� �� �����Ͱ� �����ϸ� ���� �ȴ�
				  called_map = (Map<Integer,List<Object>>)in.readObject();// DB�� ���� �ӽ� �ʿ� ����
				  in.close();
				  List<Object> temp_list = new ArrayList<>();			  // �ӽ� list�� value�� ����
				  List<String> temp_comment ;							  // ��� ���� �ӽ÷� ������ �����ѵ� ���� �߰��� ����� �־��ְ� 
				  temp_list = called_map.get(number);
				  temp_comment = (List<String>)temp_list.get(7);
				  temp_comment.add(new_comment);
				  temp_list.set(7, temp_comment);						  // �ӽ� value���� �ٲ� ��� ������ ��ü��Ų�� 
				  called_map.put(number, temp_list);					  // �ٲ� value������ �ٽ� �־��� 
				  this.map = called_map;								  // ���ŵ� ���� ������ 
				  this.mapOutput();
				  
			  }
			  catch(Exception e){
				  e.printStackTrace();
			  }
	   }else{
			  System.out.println("������ �������� �ʾ� ���� �� ���ΰ�ħ �Ͻÿ�");
			 
		  }
   }
   
}
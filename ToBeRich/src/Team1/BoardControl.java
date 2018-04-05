//게시판 새업로드를 하기 위해 사용하는 클래스
package Team1;

import java.io.*;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Map.Entry;

public class BoardControl {
   private File target = new File("DB","board.txt"); //DS DB파일 경로
   //DS 게시판에 번호를 Map key로 사용한다
   private Map<Integer,List<Object>> map = new HashMap<>();
   
   private Map<Integer,List<Object>> table_map = new HashMap<>();
   //DS 게시판에 번호를 제외한 정보를 List로 저장한다
   private List<Object> new_list = new ArrayList();
   private String title; //DS 제목
   private String password; //DS 글 작성 시 비민번호
   private String img; //DS 결과 스크린샷 이미지 저장 경로
   private String text; //DS 본문 내용
   private int number; //DS 게시판에 게시물 등록 시 최신 번호 생성
   private List<String> comment =new ArrayList();// 댓글 저장하기 위한 변수
   
   public BoardControl() throws UnknownHostException, IOException {
	// TODO Auto-generated constructor stub
	  System.out.println("기본 생성자 호출 최신 게시판 정보를 가져옵니다 ");
	  
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
   
   //DS 받은 전체 정보를 해당 필드에 저장 후 리스트에 저장한다  DB신규 생성시 호출 
   public void allSet(String title,String password,File file, String text,String userid) { 
	 //0제목 1 비번 2 파일경로 3 텍스트에어리어 4 아이디 5날짜 6 조회수 7 댓글
	   //DS 필드에 저장
      this.title = title;
      this.password = password;
      this.text = text;
      
      //DS 필드를 리스트에 저장
      new_list.add(this.title);
      new_list.add(this.password);
      new_list.add(file);
      new_list.add(this.text);
      new_list.add(userid); //DS 게시물을 올린 사용자의 이름을 받아서 저장(홍길동으로 우선 저장함) ##사용자 정보 DB와 연동 필요##
      //DS 현재 날짜를 구하고 list에 저장함
      String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
      new_list.add(date); //DS 현재 날짜와 시간을 저장함
      new_list.add(0); //DS 해당 게시물 조회수를 list에 저장함(초기값을 0으로 설정함)
      this.comment.add("디폴트 댓글");
      new_list.add(this.comment);//처음에는 공백이지만 댓글등록시 하나하나 채워나갈것 
      
      try {
          mapInput();
       } catch (Exception e1) {
          System.out.println("BoardConrol에 데이터 전송 중 오류");
       }
       // DS 갱신 된 map을 DB에 갱신
       try {
          mapOutput();
       } catch (Exception e1) {
          System.out.println("BoardConrol에 데이터 전송 중 오류");
       }
       //해당 이미지도 보내야한
   }
   
   //DS 게시물 확인폼(Board_main)이 뜰 때마다 조회수를 1씩 증가하기 위한 메소드이며 최신 갱신 map,게시물 번호를 전달 받는다
   public void viewAdd( int number){
	   try{
			  ObjectInputStream in = new ObjectInputStream(
					  new BufferedInputStream(new FileInputStream(target)));
			  //DS DB에 읽어 올 데이터가 존재하면 실행 된다
			  map = (Map<Integer,List<Object>>)in.readObject();
			  in.close();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	   
	   this.number = number;
	   
	   //DS 게시물 번호에 해당하는 list를 뽑아 클래스 범위의 list에 저장
	   this.new_list = map.get(number);
	   //DS 마지막 조회수에서 1증가한 값을 클래스 list에 저장한다
	   new_list.set(6, (int)(new_list.get(6))+1);
	   //DS 변경 된 list를 map에 갱신
	   map.put(number, new_list);
	   //DS 갱신 된 map을 DB에 저장
	   this.mapOutput();
	   System.out.println(new_list);
   }
   
   //DS Board_main(게시물 확인 폼)에서 수정 버튼을 눌렀을 때 DB최신 갱신map,게시물 번호,수정 된 제목,내용 데이터를 전달 받는 메소드
   public void updateSet(int number,String title,String text) {
	   
	   try{
			  ObjectInputStream in = new ObjectInputStream(
					  new BufferedInputStream(new FileInputStream(target)));
			  //DS DB에 읽어 올 데이터가 존재하면 실행 된다
			  map = (Map<Integer,List<Object>>)in.readObject();
			  in.close();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	   
	   //해당 게시물 번호를 전달 받아 필드에 저장
	   this.number = number;
	   //최신 갱신 된 map을 전달 받아 저장한다
	   
	   //메소드 내에서만 사용하는 list를 생성
	   List<Object> list = new ArrayList<>();
	   //메소드 범위 list에 클래스 범위 map의 해당 key값(게시판 번호)의 value(list<object>)를 넣는다 
	   list = map.get(number);
	   //제목과 내용의 변경 데이터를 전달 받아 메소드 범위 list에 저장한다
	   list.set(0, title);
	   list.set(3, text);
	   //수정한 시간을 구해서 메소드 범위 list에 저장한다
	   String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
	   list.set(5, date);
	   //클래스 범위 list에 메소드 범위 list를 넣는다
	   this.new_list = list;
	   //클래스 범위 map에 key값(게시물 번호)에 해당하는 map value의 list를 재저장한다
	   map.put(number, list);
	   //변경 된 map을 DB에 저장한다
	   this.mapOutput();
   }
   
   //DS Board_main(확인창)에서 삭제 버튼을 누르면 호출 되는 메소드이며 전달 받는 데이터는 최신 갱신 map,게시물 번호,삭제할 이미지 경로이다
   public void delete(int number,File imgPath) {
	   //클래스 범위 map에 저장
	   try{
			  ObjectInputStream in = new ObjectInputStream(
					  new BufferedInputStream(new FileInputStream(target)));
			  //DS DB에 읽어 올 데이터가 존재하면 실행 된다
			  map = (Map<Integer,List<Object>>)in.readObject();
			  in.close();
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	   //클래스 범위 변수에 저장
	   this.number = number;
	   //전달 받은 이미지 경로의 이미지 파일을 삭제
	   imgPath.delete();
	   //최신 갱신 된 map에서 해당 key값에 해당하는 map내용 삭제
	   map.remove(number);
	   //작업한 map을 DB에 저장
	   this.mapOutput();
   }
   
   //DB에서 데이터를 불러와 map에 갱신 후 게시물 번호를 가장 큰 숫자로 갱신하는 메소드
   public void mapInput() {
       try {
          //DS 읽어오는 스트림 생성 DB에 읽어 올 데이터가 없으면 오류 발생 catch블록으로 간다
          ObjectInputStream in = new ObjectInputStream(
          new BufferedInputStream(new FileInputStream(target)));
          //DS DB에 읽어 올 데이터가 존재하면 실행 된다
          map = (Map<Integer,List<Object>>)in.readObject();
          //DS DB에서 불러 map에 갱신한 key값 중에 가장 큰 값을 구해 1을 더한다(게시판에 게시물 생성 번호 갱신을 위함)
          for(int key:map.keySet()) {
             number = key;
             if(key > number)
                number = key;
          }
          number++;
          map.put(number, new_list);
       }catch(Exception e) {
          //DS DB에서 불러 올 데이터가 있으면 실행
          map.put(1, new_list);
          e.printStackTrace();
       }
   }
   //DS 갱신 된 map을 DB에 갱신하는 메소드
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
         System.out.println("DB에 데이터를 저장 중 오류");
         e.printStackTrace();
      }
      //System.out.println(map); //DS 최신 갱신 된 DB의 데이터 값을 확인 할 수 있음
   }

   public Object[][] calltable() {
	// TODO Auto-generated method stub
	   //DS 읽어오는 스트림 생성 DB에 읽어 올 데이터가 없으면 오류 발생 catch블록으로 간다
       //없거나 
	   Object[][] Table = null;
//	   있거나 
	  if(target.exists()){
		  System.out.println("파일이 존재함");
		  Table = null;
		  try{
			  ObjectInputStream in = new ObjectInputStream(
					  new BufferedInputStream(new FileInputStream(target)));
			  //DS DB에 읽어 올 데이터가 존재하면 실행 된다
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
			  Table[Index][1] = m.getValue().get(0);//string 제목
			  Table[Index][2] = m.getValue().get(4);//string 이름
			  Table[Index][3] = m.getValue().get(5);//String 날짜
			  Table[Index][4] = m.getValue().get(6);//Integer 조회
			  
			  Index++;
		  }
		  System.out.println("리턴해줄 Table값:");
		  for(int i=0;i<Table.length;i++){// 잘들어갔나 확인 
			  for(int j=0;j<Table[i].length;j++){
				  System.out.println(Table[i][j]);
			  }
			  System.out.println();
		  }
	  }else{
		  System.out.println("파일이 존재하지 않아 생성 후 새로고침 하시오");
		  Table = null;
	  }
	
	
      
	return Table;  //null반환시 Text가 없다 ;
}
   
   
   public void addcomment(int number,String comment){
	   Map<Integer,List<Object>> called_map = new HashMap<>();
	   String new_comment  = comment;
	   if(target.exists()){
			  System.out.println("파일이 존재함");
			  try{
				  ObjectInputStream in = new ObjectInputStream(
						  new BufferedInputStream(new FileInputStream(target)));
				  //DS DB에 읽어 올 데이터가 존재하면 실행 된다
				  called_map = (Map<Integer,List<Object>>)in.readObject();// DB맵 복사 임시 맵에 삽입
				  in.close();
				  List<Object> temp_list = new ArrayList<>();			  // 임시 list에 value값 삽입
				  List<String> temp_comment ;							  // 댓글 변수 임시로 선언후 저장한뒤 새로 추가할 댓글을 넣어주고 
				  temp_list = called_map.get(number);
				  temp_comment = (List<String>)temp_list.get(7);
				  temp_comment.add(new_comment);
				  temp_list.set(7, temp_comment);						  // 임시 value값에 바꾼 댓글 변수로 교체시킨다 
				  called_map.put(number, temp_list);					  // 바뀐 value값으로 다시 넣어줌 
				  this.map = called_map;								  // 갱신된 맵을 덮어씌우기 
				  this.mapOutput();
				  
			  }
			  catch(Exception e){
				  e.printStackTrace();
			  }
	   }else{
			  System.out.println("파일이 존재하지 않아 생성 후 새로고침 하시오");
			 
		  }
   }
   
}
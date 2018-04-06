package Team1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FileClient extends JFrame 
{
	static Map<String,User> userinfo ;
	private int port  ;
	private String host ;
	private Socket socket ;
	private Pakage data;
	private File ip_add=new File("property","ip_add.txt");
	
	public static void main(String[] args) throws UnknownHostException, IOException {
//    
//		System.out.println("welcome client");
//		File Userdb = new File("DB","user.txt");
////		try{
////    
////			FileInputStream in = new FileInputStream(Userdb);
////			BufferedInputStream bufferin = new BufferedInputStream (in);
////			ObjectInputStream datain = new ObjectInputStream(bufferin);
////			userinfo  = (Map<String,User>)datain.readObject();
////    	
////    }catch(Exception e){
////    	e.printStackTrace();
////    }
//
//    Pakage data = new Pakage("user",userinfo);
//    FileClient app = new FileClient("127.0.0.1",8888);
////    app.save_request(data);  
//    Map<String,User> result_Map = (Map<String,User>)app.call_request("user");  // user savings board images중 1  
//    try{
//    	
//        
//		FileOutputStream out = new FileOutputStream(Userdb);
//		BufferedOutputStream bufferout = new BufferedOutputStream (out);
//		ObjectOutputStream dataout = new ObjectOutputStream(bufferout);
//		dataout.writeObject(result_Map);
//		dataout.flush();
//		System.out.println(result_Map);
//    }catch(Exception e){
//    	e.printStackTrace();
//    }
//    
    
    
  }
	Object result;
	String default_ip ="127.0.0.1";

// initialize chatServer and set up GUI
    public FileClient( ) throws UnknownHostException, IOException{// host , port ,구분자, 파일 
    	this.port = 8888;
    	
    	try{
    			
    		FileInputStream fis = new FileInputStream(ip_add); 
    		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fis));

    		while( (host = bufferReader.readLine()) != null ){
    			// str에 txt파일의 한 라인을 읽어온다
    			host += host; 
    		}





        	
        }catch(Exception e){
        	e.printStackTrace();
        	System.out.println("ip 가져오기 실패 파일이 없습니다");
        	
        	FileOutputStream fout = new FileOutputStream(ip_add);
        	byte[] contentInBytes = default_ip.getBytes();
        	fout.write(contentInBytes);
        	fout.flush();
        	fout.close();
        	System.out.println("Done");
        }
    	
    	this.socket = new Socket(host, port);
    	
    	//전송 메소드 
	}
   
   /////////////////////////////////////////////////////////////////////////////////
    public void save_request(Pakage data){  // 저장 요청 (map or image 보내기) 구분자, map or image
    	this.data = data;
    	
    	
    	if(data.getType().equals("user")){
    		 try{
    			 ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
    			 os.writeObject(data);
    			 System.out.println("보내고 대기중 ");
    			 ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
     			String str = (String)is.readObject();
     			if(str.equals("요청완료")){
     				System.out.println("소켓 종료");
     				socket.close();
     			}
    			 
    		 }catch(Exception e){
    			 e.printStackTrace();
    			 System.out.println("유저맵 보내기 오류");
    		 }
    	}else if(data.getType().equals("board")){
    		try{
    			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
    			os.writeObject(data);
    			System.out.println("보내고 대기중 ");
    			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
    			String str = (String)is.readObject();
    			if(str.equals("요청완료")){
    				System.out.println("소켓 종료");
     				socket.close();
    			}
   			 	
   		 }catch(Exception e){
   			e.printStackTrace();
			 System.out.println("게시판맵 보내기 오류");

   		 }
    	}else if(data.getType().equals("savings")){
    		try{
    			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
    			os.writeObject(data);
    			System.out.println("보내고 대기중 ");
    			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
    			String str = (String)is.readObject();
    			if(str.equals("요청완료")){
    				System.out.println("소켓 종료");
     				socket.close();
    			}   			 
   		 }catch(Exception e){
   			e.printStackTrace();
			 System.out.println("저금상품맵 보내기 오류");

   		 }
    	}else{//이미지
    		try{
    			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
    			os.writeObject(data);
    			ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
    			String str = (String)is.readObject();
    			if(str.equals("요청완료")){
    				System.out.println("소켓 종료");
     				socket.close();
    			}   			 
   		 }catch(Exception e){
   			 e.printStackTrace();
			 System.out.println("이미지 보내기 오류");

   		 }
    	}
    }
    
    public Object call_request(String type){  // 자료 요청 (map or image 요청)
    	
    	String reuest_targetName;
    	if(type.equals("user")){
    		reuest_targetName ="user";
    	}else if(type.equals("board")){
    		reuest_targetName ="board";
    	}else if(type.equals("savings")){
    		reuest_targetName ="savings";
    	}else{//이미지
    		reuest_targetName =type;
    	}
    	//보내고 받기 
    	 try{
    		 
    		 ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
    		 os.writeObject(reuest_targetName);
    		 System.out.println("보내고 대기중 ");
    		 
    		 ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
    		 Object temp_obj =  is.readObject();
    		 
    		
    		 
    		 if(type.equals("user")){
    			 result = (Map<String,User>)temp_obj;
    		 }else if(type.equals("board")){
    			 result = (Map<Integer,List<Object>>)temp_obj;
    		 }else if(type.equals("savings")){
    			 result = (Map<String,Savings>)temp_obj;
    		 }else{//이미지
    			 result = (ImageIcon)temp_obj;
    		 }
    		 System.out.println("소켓 종료합니다.");
    		 socket.close();
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 result=null;
			 System.out.println("자료 요청오류로 null반환 ");

    	 }
    	
    	 //받은게 무엇인지 판별해야한다 
    	
		return result;
		
    }
   /////////////////////////////////////////////////////////////////////////////////
}
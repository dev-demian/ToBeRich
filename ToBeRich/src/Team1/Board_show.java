package Team1;


import java.awt.Font;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

class Board_show extends JFrame{//단순 확인창 button클릭시 수정 삭제로 넘어감 
   //DS 선언식으로 모아 둔 변수 생성 위치들 변경함
   JPanel mainPanel = new JPanel();
   JLabel lblNewLabel = new JLabel("제목");
   JTextField textField = new JTextField();
   JLabel label = new JLabel("번호");
   JTextField textField_1 = new JTextField();
   JLabel label_1 = new JLabel("날짜");
   JTextField textField_2 = new JTextField();
   //DS 이미지를 넣을 라벨 변수 생성
   JLabel imgLabel;
   JTextArea textArea_1 = new JTextArea();
   JTextArea TA_print_comment = new JTextArea();
   JButton edit_button = new JButton("\uC218\uC815/\uC0AD\uC81C");
   //DS DB경로 생성
   private File target = new File("DB","board.txt");
   private Image img = null;
   //DS 이미지 경로가 저장 될 변수 생성
   private File imgPath;
   //DS 게시판 번호가 저장 되는 변수
   private int number;
   //DS 클릭한 게시물의 map데이터를 가져와 갱신할 map,list생성
   private Map<Integer,List<Object>> map = new HashMap<>();
   private List<Object> list = new ArrayList();
   String userid;//해당 유저만 수정 삭제폼을 불러오기위한 구분자
   ImageIcon ic;
   private final JTextField TF_Comment = new JTextField();
   private final JButton add_commnet_btn = new JButton("\uB4F1\uB85D");
   private String printed_comment ;
   
   
   //main에 하던 설정들을 생성자에서 진행
   public Board_show(int number,String userid) {
	   //DS 생성자에서 게시물 번호를 전달 받아 number변수에 저장한다
	  this.number = number;
	  this.userid= userid;
	  //DS DB에서 전체 map데이터를 불러와서 number에 해당하는 map데이터만을 list에 갱신한다
	  try{
		  //DS DB에서 읽어오는 스트림 생성
		  ObjectInputStream mapInput = new ObjectInputStream(
		  new BufferedInputStream(new FileInputStream(target)));
		  //DS DB에서 전체 map 정보를 불러와 현재 map에 갱신
		  map = (Map<Integer,List<Object>>)mapInput.readObject();
		  //DS 현재 map에서 number(게시물 번호)에 해당하는 key값의 value만을 현재 list에 저장
		  list = map.get(number);
		  //System.out.println(list.get(1)); //DS DB에서 가져온 해당 게시물 내용을 확인할 수 있다
	  }catch(Exception e) {
		  System.out.println("DB에서 데이터를 가져올 때 오류");
	  }
	  
	  //DS 생성자를 통해 게시물 확일 폼이 뜰 때마다 조회수가 1씩 증가하는 메소드 호출
	  BoardControl bc = new BoardControl();
	  bc.viewAdd(map,number);
	  
	  //DS 생성자에서 list의 2인덱스 데이터를 받아 이미지 경로를 저장하고 
	  //이미지 경로를 Image타입으로 변환해 저장한다
	  try {
	    	imgPath = (File)list.get(2);
			img = ImageIO.read(imgPath);
		} catch (IOException e) {
			System.out.println("이미지 파일이 없습니다.");
		}
	  //DS Image타입의 변수를 ImageIcon으로 변환 후 상위 클래스 타입인 JLabel에 삽입한다
	  imgLabel = new JLabel(new ImageIcon(img));
	  
	   
	  
	  
      this.display();//화면 구성 관련 처리
      this.event();//이벤트 관련 처리
      this.menu();//메뉴 관련 처리
      
      this.setTitle("게시판 내용 확인");
      this.setSize(1000, 600);
      //this.setLocation(100, 100);
      //위치를 운영체제가 결정하도록 한다
      this.setLocationByPlatform(true);
      this.setResizable(false);
      this.setVisible(true);
   }

   private void display() {
      this.setContentPane(mainPanel);
      mainPanel.setLayout(null);
      
     
      lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
      lblNewLabel.setBounds(12, 10, 28, 21);
      mainPanel.add(lblNewLabel);
      
      
      label.setFont(new Font("굴림", Font.PLAIN, 14));
      label.setBounds(254, 10, 28, 21);
      mainPanel.add(label);
      
      
      label_1.setFont(new Font("굴림", Font.PLAIN, 14));
      label_1.setBounds(488, 10, 28, 21);
      mainPanel.add(label_1);
      textField.setEditable(false);
      
      
      textField.setBounds(47, 11, 145, 26);
      mainPanel.add(textField);
      //DS list의 0인덱스(제목)를 필드에 채움
      textField.setText((String)list.get(0));
      textField_1.setEditable(false);
      
      
      textField_1.setColumns(10);
      textField_1.setBounds(294, 11, 145, 26);
      mainPanel.add(textField_1);
      //DS Main_Form에서 받은 게시판 번호(number)를 필드에 채움
      textField_1.setText(String.valueOf(number));
      textField_2.setEditable(false);
      
      textField_2.setColumns(10);
      textField_2.setBounds(535, 11, 167, 26);
      mainPanel.add(textField_2);
      //DS list의 5인덱스(날짜)를 필드에 채움
      textField_2.setText((String)list.get(5));
      
      //DS 이미 생성자에서 만든 이미지를 담고 있는 imgLabel변수를 폼의 위치에 설정한다
      imgLabel.setBounds(12, 41, 481, 320);
      mainPanel.add(imgLabel);
      textArea_1.setEditable(false);
      
      
      textArea_1.setBounds(505, 41, 197, 320);
      mainPanel.add(textArea_1);
      textArea_1.setText((String)list.get(3));
      
      JScrollPane scroll = new JScrollPane(textArea_1);
      scroll.setBounds(505, 41, 197, 320);
      mainPanel.add(scroll);
      
      TA_print_comment.setBackground(SystemColor.control);
      TA_print_comment.setEditable(false);
      
      List<Object>temp_O = map.get(number);
      List<String> temp_S = (List<String>)temp_O.get(7);
      for(int i=temp_S.size()-1;i>=0;i--){
//    	  System.out.println("댓글 합치는중");
//    	  System.out.println(temp_S.get(i));
    	  printed_comment +=  temp_S.get(i)+"      \n"; 
      }
      //null들어가는거 제거
      System.out.println(printed_comment);
      //댓글처럼 달리게 보이도록 출력하기 
      TA_print_comment.setText(printed_comment);
      
      TA_print_comment.setBounds(12, 371, 690, 130);
      mainPanel.add(TA_print_comment);
      
      JScrollPane scroll_1 = new JScrollPane(TA_print_comment);
      scroll_1.setBounds(12, 410, 688, 143);
      mainPanel.add(scroll_1);
      
      
      edit_button.setBounds(876, 508, 97, 45);
      mainPanel.add(edit_button);
     
      ic  = new ImageIcon("property/googlead.png");
      JLabel ad_sense = new JLabel();
      ad_sense.setIcon(ic);
      ad_sense.setBounds(716, 42, 257, 457);
      
      mainPanel.add(ad_sense);
      TF_Comment.setColumns(10);
      TF_Comment.setBounds(12, 374, 599, 24);
      
      mainPanel.add(TF_Comment);
      add_commnet_btn.setBounds(613, 373, 89, 27);
      
      mainPanel.add(add_commnet_btn);
   }

   private void event() {
      //setDefaultCloseOperation(EXIT_ON_CLOSE);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
      //DS 삭제 버튼을 눌렀을 때 이벤트 설정
      edit_button.addActionListener(e->{
    	  System.out.println(list.get(4).toString() + userid +"를비교한다" );
    	  if(list.get(4).toString().equals(userid)){
    		  Board_main bm = new Board_main(number);
    	  }else
    		  JOptionPane.showMessageDialog(this, "해당 게시물의 등록자가 아닙니다", "수정불가", JOptionPane.INFORMATION_MESSAGE);
      });
      
      add_commnet_btn.addActionListener(e->{
    	  try{
    		  //정규식 아무것도 치지 않았다면 다이얼로그 띄우고
    		  BoardControl Bc = new BoardControl();
    		  
    		  long time = System.currentTimeMillis(); 
    		  SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    		  String str_comment_time = dayTime.format(new Date(time));


    		  String str = userid+" ( "+str_comment_time+" )" + "    --    " + TF_Comment.getText() ;
    		  Bc.addcomment(number, str);
    	  }catch(Exception err){
//    		  String commnet_str = TF_Comment.getText();
    		  err.printStackTrace();
    	  }
      });
      
   }

   private void menu() {
      
   }



//   public static void main(String[] args) {
//      /*
////스킨 설치
//      try {
//         UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      */
//      //창을 더이상 직접 만들지 않고 확장시킨 클래스의 인스턴스를 생성
//	   Board_main window = new Board_main();
//      
//   }
}
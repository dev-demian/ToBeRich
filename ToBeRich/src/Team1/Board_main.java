package Team1;


import java.awt.Font;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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

class Board_main extends JFrame{
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
   JTextArea textArea_2 = new JTextArea();
   JButton btnNewButton = new JButton("수정");
   JButton button = new JButton("삭제");
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
   ImageIcon ic;
   private JTextField TF_add_comment;
   
   //main에 하던 설정들을 생성자에서 진행
   public Board_main(int number) {
	   //DS 생성자에서 게시물 번호를 전달 받아 number변수에 저장한다
	  this.number = number;
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
//	  bc.viewAdd(map,number);
	  
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
      
      
      textArea_1.setBounds(505, 41, 197, 320);
      mainPanel.add(textArea_1);
      textArea_1.setText((String)list.get(3));
      
      JScrollPane scroll = new JScrollPane(textArea_1);
      scroll.setBounds(505, 41, 197, 320);
      mainPanel.add(scroll);
      textArea_2.setEditable(false);
      
      
      textArea_2.setBounds(12, 371, 690, 130);
      mainPanel.add(textArea_2);
      
      JScrollPane scroll_1 = new JScrollPane(textArea_2);
      scroll_1.setBounds(12, 406, 690, 147);
      mainPanel.add(scroll_1);
      //textArea_3.setText("광고"); //광고 내용이 담길 공간
      
      btnNewButton.setBounds(760, 511, 97, 45);
      mainPanel.add(btnNewButton);
      
      
      button.setBounds(883, 511, 97, 45);
      mainPanel.add(button);
      ic  = new ImageIcon("property/googlead.png");
      JLabel ad_sense = new JLabel();
      ad_sense.setIcon(ic);
      ad_sense.setBounds(716, 42, 257, 457);
      mainPanel.add(ad_sense);
      
      TF_add_comment = new JTextField();
      TF_add_comment.setEditable(false);
      TF_add_comment.setBounds(12, 374, 599, 24);
      mainPanel.add(TF_add_comment);
      TF_add_comment.setColumns(10);
      
      JButton btnNewButton_1 = new JButton("\uB4F1\uB85D");
      btnNewButton_1.setEnabled(false);
      btnNewButton_1.setBounds(613, 373, 89, 27);
      mainPanel.add(btnNewButton_1);
   }

   private void event() {
      //setDefaultCloseOperation(EXIT_ON_CLOSE);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      //setDefaultCloseOperation(HIDE_ON_CLOSE);
      //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//JFrame 기본 이벤트 방지
      
      //DS 수정 버튼을 눌렀을 때 이벤트 설정
      btnNewButton.addActionListener(e->{
    	  //DS 비번 입력창을 띄운다
    	  String pw = JOptionPane.showInputDialog(null,"게시물 비번을 입력해주세요");
    	  //DS DB에 저장 되어 있는 비번과 입력 받은 비번이 일치하면 수정 완료 메시지창 띄우고 해당 폼 닫기
    	  if(list.get(1).equals(pw)) {
    		  //DS BoardControl 객체 생성
    		  BoardControl bc = new BoardControl();
    		  //DS BoardControl 내의 updateSet(수정기능) 메소드를 호출하며 field와 area에 입력한 데이터를 전달한다
    		  bc.updateSet(map,number,textField.getText(),textArea_1.getText());
    		  //DS 완료 확인창을 띄운다
    		  JOptionPane.showMessageDialog(null,"게시물 수정 완료");
    		  //DS 해당 폼을 종료한다
    		  dispose();
          //DS 비번 입력창에서 취소 버튼을 누르면 비번 입력창 닫기
    	  }else if(pw == null) {
    		  //DS 아무른 이벤트 없음
    	  //DS 입력한 비번이 불일치하면 오류 메시지창을 띄운다
    	  }else if(list.get(1) != (pw)) {
    		  JOptionPane.showMessageDialog(null, "오류", "오류", JOptionPane.ERROR_MESSAGE);
    	  }
      });
      
      //DS 삭제 버튼을 눌렀을 때 이벤트 설정
      button.addActionListener(e->{
    	  //DS 비번 입력창을 띄운다
    	  String pw = JOptionPane.showInputDialog(null,"게시물 비번을 입력해주세요");
    	  //DS DB에 저장 되어 있는 비번과 입력 받은 비번이 일치하면 수정 완료 메시지창 띄우고 해당 폼 닫기
    	  if(list.get(1).equals(pw)) {
    		//DS BoardControl 객체 생성
    		BoardControl bc = new BoardControl();
    		//DS BoardControl 내의 delete(게시물 삭제 기능) 메소드 호출이며 전달 데이터로는 최신 갱신 map,게시물 번호,해당 이미지 경로가 있다
    		bc.delete(map,number,imgPath);
    		//DS 완료 확인창을 띄운다
    		JOptionPane.showMessageDialog(null,"게시물 삭제 완료");
  		  	//DS 해당 폼을 종료한다
  		  	dispose();
    	  }else if(pw == null) {
    		  //DS 아무른 이벤트 없음
    	  //DS 입력한 비번이 불일치하면 오류 메시지창을 띄운다
    	  }else if(list.get(1) != (pw)) {
    		  JOptionPane.showMessageDialog(null, "오류", "오류", JOptionPane.ERROR_MESSAGE);
    	  }
      });
      
   }

   private void menu() {
      
   }
}
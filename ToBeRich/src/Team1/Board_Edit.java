package Team1;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Board_Edit extends JDialog {
   // DS 선언 변수 위치 변경함
   JPanel mainPanel = new JPanel();
   //제목
   JLabel lblNewLabel = new JLabel("제목");
   JTextField textField = new JTextField();
   //비번
   JLabel lblNewLabel_1 = new JLabel("비번");
   JTextField textField_1 = new JTextField();
   //그냥보여주는 텍스트
   JLabel label = new JLabel("이미지");
   JTextField textField_2 = new JTextField("캡쳐한이미지가 저장되었습니다.");
   //내용
   JLabel label_1 = new JLabel("내용");
   JTextArea textArea = new JTextArea();
   JScrollPane scroll = new JScrollPane();
   JButton btnNewButton = new JButton("확인");
   JButton btnNewButton_1 = new JButton("취소");
   //데이터베이스에 저장되는 이미지
   private File file;
   String userid;
   
   BufferedImage send_img;//매개변수로 보낼 이미지
   String img_name ; //파일명으로 줄 랜덤값
   
   
   // main에 하던 설정들을 생성자에서 진행
   public Board_Edit(File file,String userid,BufferedImage send_img,String img_name) {
	  this.userid=userid;
      this.file = file;
      this.send_img  = send_img;
      this.img_name = img_name;
      this.display();// 화면 구성 관련 처리
      this.event();// 이벤트 관련 처리
      this.menu();// 메뉴 관련 처리

      this.setTitle("게시판 새업로드/수정");
      this.setSize(1000, 600);
      // this.setLocation(100, 100);
      // 위치를 운영체제가 결정하도록 한다
      this.setLocationByPlatform(true);
      this.setResizable(false);
      this.setVisible(true);
   }
   private void display() {
      this.setContentPane(mainPanel);
//      Simulation_Result result = new Simulation_Result();
//      textField_1.setText(result.getFile());
      
      //이미지경로 입력 제한했습니다.
      textField_2.setEditable(false);
      
      mainPanel.setLayout(null);

      lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
      lblNewLabel.setBounds(12, 10, 36, 27);
      mainPanel.add(lblNewLabel);

      lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
      lblNewLabel_1.setBounds(12, 47, 36, 27);
      mainPanel.add(lblNewLabel_1);

      label.setFont(new Font("굴림", Font.PLAIN, 14));
      label.setBounds(12, 84, 52, 27);
      mainPanel.add(label);

      label_1.setFont(new Font("굴림", Font.PLAIN, 14));
      label_1.setBounds(12, 121, 36, 27);
      mainPanel.add(label_1);

      textField.setBounds(60, 13, 600, 24);
      mainPanel.add(textField);
      // DS setColumns 줄 제거함

      // DS setColumns 줄 제거함
      textField_1.setBounds(60, 50, 600, 24);
      mainPanel.add(textField_2);
      
      textField_2.setBounds(60, 87, 600, 24);
      mainPanel.add(textField_1);


      scroll.setBounds(60, 121, 850, 371);
      mainPanel.add(scroll);

      scroll.setViewportView(textArea);

      btnNewButton.setBounds(352, 502, 109, 38);
      mainPanel.add(btnNewButton);

      btnNewButton_1.setBounds(573, 502, 109, 38);
      mainPanel.add(btnNewButton_1);

   }

   private void event() {
      // setDefaultCloseOperation(EXIT_ON_CLOSE);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      // setDefaultCloseOperation(HIDE_ON_CLOSE);
      // setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//JFrame 기본 이벤트 방지

      // DS 확인을 클릭 했을 때 텍스트필드,에어리어에 있는 내용의 값을 가지고 BoardControl 클래스에 setAll 매소드로 보낸다
      btnNewButton.addActionListener(e -> {
         
    	  try{
        	 BoardControl bc = new BoardControl();
        	 bc.allSet(textField.getText(), textField_1.getText(),file, textArea.getText(),userid);
        	 
        	 
        	 ImageIcon imageIcon = new ImageIcon(send_img);//서버로 보내기위한 변환
        	 Pakage data = new Pakage(img_name,imageIcon);  //이름   버퍼드 이미지 
           try{
          	 FileClient FC = new FileClient("127.0.0.1",8888);
          	 FC.save_request(data);
          	 
           }catch(Exception err3){
          	 err3.printStackTrace();
           }
        	 
         }catch(Exception err){
        	 err.printStackTrace();
         }
         dispose();
      });

      btnNewButton_1.addActionListener(e -> {
    	  //해당 파일 삭제하기 
    	  file.delete();
    	  
    	  
         dispose();
      });
   }

   private void menu() {

   }
}

// public class Create {
// public static void main(String[] args) {
// /*
//// 스킨 설치
// try {
// UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
// } catch (Exception e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// */
// //창을 더이상 직접 만들지 않고 확장시킨 클래스의 인스턴스를 생성
// Board_Edit window = new Board_Edit();
//
// }
// }
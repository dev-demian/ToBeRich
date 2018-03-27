package Team1;
//
//
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.PasswordView;

class Board_Edit extends JFrame{
   JPanel mainPanel = new JPanel();
   JLabel label = new JLabel("이미지");
   JTextField textField_2 = new JTextField();
   JTextField textField_3 = new JTextField();
   JScrollPane scroll = new JScrollPane();
   JButton btnNewButton_1 = new JButton("취소");
   JTextArea textArea = new JTextArea();
   JButton btnNewButton = new JButton("확인");
   JTextField textField = new JTextField();
   JLabel lblNewLabel = new JLabel("제목");
   JLabel lblNewLabel_1 = new JLabel("비번");
   JLabel label_1 = new JLabel("내용");

   //main에 하던 설정들을 생성자에서 진행
   public Board_Edit() {
      this.display();//화면 구성 관련 처리
      this.event();//이벤트 관련 처리
      this.menu();//메뉴 관련 처리
      
      this.setTitle("게시판 새업로드/수정");
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
      //textField.setColumns(10);
      
      //textField_2.setColumns(10);
      textField_2.setBounds(60, 87, 600, 24);
      mainPanel.add(textField_2);
      
      textField_3.setBounds(60, 50, 600, 24);
      mainPanel.add(textField_3);
      
      scroll.setBounds(60, 121, 850, 371);
      mainPanel.add(scroll);
      
      scroll.setViewportView(textArea);
      
      btnNewButton.setBounds(352, 502, 109, 38);
      mainPanel.add(btnNewButton);
      
      btnNewButton_1.setBounds(573, 502, 109, 38);
      mainPanel.add(btnNewButton_1);
   
   }

   private void event() {
      //setDefaultCloseOperation(EXIT_ON_CLOSE);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      //setDefaultCloseOperation(HIDE_ON_CLOSE);
      //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//JFrame 기본 이벤트 방지
   }

   private void menu() {
      
   }
}

//public class Create {
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
//      Board_Edit window = new Board_Edit();
//      
//   }
//}




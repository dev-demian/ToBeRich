package Team1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

class LogIn extends JFrame{
   
   private JPanel mainPanel = new JPanel();
   private JTextField id_textFiled = new JTextField();
   private JPasswordField pw_Filed = new JPasswordField();
   private JLabel id_label = new JLabel("ID");
   private JLabel pw_label = new JLabel("PW");
   private JLabel title_label = new JLabel("1\uC5B5 \uB9CC\uB4E4\uAE30 \uD504\uB85C\uC81D\uD2B8");
   private JButton Login_button = new JButton("LOGIN");
   private JButton join_button = new JButton("회원가입");

   //private JButton find_button = new JButton("아이디 / 비밀번호 찾기");
   
   
   //main에 하던 설정들을 생성자에서 진행
   public LogIn() {
      this.display();//화면 구성 관련 처리
      this.event();//이벤트 관련 처리
      this.menu();//메뉴 관련 처리
      
      this.setTitle("1억 만들기 프로젝트");
      this.setLocationByPlatform(true);
      this.setResizable(false);
      this.setVisible(true);
   }

   private void display() {
      //mainPanel을 기본 패널로 설정
         this.setContentPane(mainPanel);
         setBounds(100, 100, 354, 425);
         mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(mainPanel);
         mainPanel.setLayout(null);
         
         //타이틀 라벨
         title_label.setFont(new Font("함초롬바탕",Font.BOLD , 30));
         title_label.setHorizontalAlignment(SwingConstants.CENTER);
         title_label.setBounds(12, 22, 316, 85);
         mainPanel.add(title_label);
         
         //아이디 라벨
         id_label.setFont(new Font("함초롬바탕", Font.BOLD, 29));
         id_label.setHorizontalAlignment(SwingConstants.RIGHT);
         id_label.setBounds(0, 137, 78, 53);
         mainPanel.add(id_label);
         
         //패스워드 라벨
         pw_label.setFont(new Font("함초롬바탕", Font.BOLD, 29));
         pw_label.setHorizontalAlignment(SwingConstants.RIGHT);
         pw_label.setBounds(2, 190, 78, 53);
         mainPanel.add(pw_label);
         
         //TextFiled
         id_textFiled.setFont(new Font("Verdana", Font.BOLD, 20));
         id_textFiled.setBounds(90, 146, 174, 37);
         mainPanel.add(id_textFiled);
         
         pw_Filed.setBounds(90, 200, 174, 37);
         mainPanel.add(pw_Filed);
         
         //버튼 등록
         Login_button.setBounds(115, 255, 126, 31);
         mainPanel.add(Login_button);
         join_button.setBounds(125, 296, 103, 23);;
         mainPanel.add(join_button);
         

      
      
   }

   private void event() {
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//창 종료
      Login_button.addActionListener(e->{
         Main_Form callmain = new Main_Form();
      });
      join_button.addActionListener(e->{
         JoinPage join = new JoinPage();
      });
     
      
      
   }

   private void menu() {
      
   }
}

public class LoginPage {
   public static void main(String[] args) {
      LogIn window = new LogIn();
   }
}
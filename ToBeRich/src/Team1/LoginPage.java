package Team1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
   
   //로그인 아이디 , 비밀번호 입력란
   private JTextField id_textFiled = new JTextField();
   private JPasswordField pw_Filed = new JPasswordField();
   
   //아이디 , 비밀번호 라벨
   private JLabel id_label = new JLabel("ID");
   private JLabel pw_label = new JLabel("PW");
   private JLabel title_label = new JLabel("1\uC5B5 \uB9CC\uB4E4\uAE30 \uD504\uB85C\uC81D\uD2B8");
   //로그인 , 회원 가입 버튼
   private JButton Login_button = new JButton("LOGIN");
   private JButton join_button = new JButton("회원가입");
   private String id_regex ="^[A-Za-z]{1}[A-Za-z0-9]{3,19}$"; //시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 3 ~ 19자 이하
   private String pwd_regex ="[a-z0-9]{6,15}";
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
    	 
    	Pattern id_pattern = Pattern.compile(id_regex);  //아이디 조건 정규식
    	Matcher id_match = id_pattern.matcher(id_textFiled.getText());
    	
    	Pattern pwd_pattern = Pattern.compile(pwd_regex); // 비밀번호 조건 정규식
    	char[] temp_pwd = pw_Filed.getPassword();
    	String change="";
    	for(int i=0; i<pw_Filed.getPassword().length;i++) {
    		change += Character.toString(temp_pwd[i]);
    	}
    	
 //   	System.out.println(change);
    		
     	Matcher pwd_match = pwd_pattern.matcher(change);
     	
//    	System.out.println(id_match.matches());
//    	System.out.println(pwd_match.matches());
    	
    	if(id_match.matches()&&pwd_match.matches()) {
    	  Main_Form callmain = new Main_Form();
    	  dispose();
    	}
    	 
    	
    		
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
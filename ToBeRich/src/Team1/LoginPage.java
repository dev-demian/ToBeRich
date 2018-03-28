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
   
   //�α��� ���̵� , ��й�ȣ �Է¶�
   private JTextField id_textFiled = new JTextField();
   private JPasswordField pw_Filed = new JPasswordField();
   
   //���̵� , ��й�ȣ ��
   private JLabel id_label = new JLabel("ID");
   private JLabel pw_label = new JLabel("PW");
   private JLabel title_label = new JLabel("1\uC5B5 \uB9CC\uB4E4\uAE30 \uD504\uB85C\uC81D\uD2B8");
   //�α��� , ȸ�� ���� ��ư
   private JButton Login_button = new JButton("LOGIN");
   private JButton join_button = new JButton("ȸ������");
   private String id_regex ="^[A-Za-z]{1}[A-Za-z0-9]{3,19}$"; //������ �������θ�, '_'�� ������ Ư������ �ȵǸ� ����, ����, '_'���θ� �̷���� 3 ~ 19�� ����
   private String pwd_regex ="[a-z0-9]{6,15}";
   //private JButton find_button = new JButton("���̵� / ��й�ȣ ã��");
   
   
   //main�� �ϴ� �������� �����ڿ��� ����
   public LogIn() {
      this.display();//ȭ�� ���� ���� ó��
      this.event();//�̺�Ʈ ���� ó��
      this.menu();//�޴� ���� ó��
      
      this.setTitle("1�� ����� ������Ʈ");
      this.setLocationByPlatform(true);
      this.setResizable(false);
      this.setVisible(true);
   }

   private void display() {
      //mainPanel�� �⺻ �гη� ����
         this.setContentPane(mainPanel);
         setBounds(100, 100, 354, 425);
         mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
         setContentPane(mainPanel);
         mainPanel.setLayout(null);
         
         //Ÿ��Ʋ ��
         title_label.setFont(new Font("���ʷҹ���",Font.BOLD , 30));
         title_label.setHorizontalAlignment(SwingConstants.CENTER);
         title_label.setBounds(12, 22, 316, 85);
         mainPanel.add(title_label);
         
         //���̵� ��
         id_label.setFont(new Font("���ʷҹ���", Font.BOLD, 29));
         id_label.setHorizontalAlignment(SwingConstants.RIGHT);
         id_label.setBounds(0, 137, 78, 53);
         mainPanel.add(id_label);
         
         //�н����� ��
         pw_label.setFont(new Font("���ʷҹ���", Font.BOLD, 29));
         pw_label.setHorizontalAlignment(SwingConstants.RIGHT);
         pw_label.setBounds(2, 190, 78, 53);
         mainPanel.add(pw_label);
         
         //TextFiled
         id_textFiled.setFont(new Font("Verdana", Font.BOLD, 20));
         id_textFiled.setBounds(90, 146, 174, 37);
         mainPanel.add(id_textFiled);
         
         pw_Filed.setBounds(90, 200, 174, 37);
         mainPanel.add(pw_Filed);
         
         //��ư ���
         Login_button.setBounds(115, 255, 126, 31);
         mainPanel.add(Login_button);
         join_button.setBounds(125, 296, 103, 23);;
         mainPanel.add(join_button);
         
         	
         	
         	
   }		
   			
   private void event() {
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//â ����
     Login_button.addActionListener(e->{
    	 
    	Pattern id_pattern = Pattern.compile(id_regex);  //���̵� ���� ���Խ�
    	Matcher id_match = id_pattern.matcher(id_textFiled.getText());
    	
    	Pattern pwd_pattern = Pattern.compile(pwd_regex); // ��й�ȣ ���� ���Խ�
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
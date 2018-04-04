package Team1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Team1.DBManager;
import Team1.User;

public class JoinPage extends JFrame {
   private JTextField textField;
   private JTextField join_title;
   private JPanel panel_1;
   private JTextField pw_textfiled;
   private JTextField id_textfield;
   private JTextField name_textfield;
   private JTextField pw_textfiled_1;
   private JTextField sex_textfield;
   private JTextField email;
   private JPanel panel_2;
   private JTextField name;
   private JTextField email_textfield;
   private JPanel panel_3;
   private JPanel panel_4;
   private JButton join_button;
   private JButton cancel_button;
   private JPasswordField pw_textfield;
   private JPasswordField pw_textfield_1;
   private JPanel panel_5;
   private JTextField ID;
   private JRadioButton sexcheck_man;
   private JRadioButton sexcheck_woman;
   private JButton overlap_id;
   private boolean IDcheck = false;
   private boolean Overlap_check = false;
//   /**
//    * Launch the application.
//    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               JoinPage frame = new JoinPage();
//               frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the frame.
    */
   public JoinPage() {
      this.display();
      this.event();
      this.menu();
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 354, 425);
      this.setTitle("1�� �����");
      this.setLocationByPlatform(true);
      this.setResizable(false);
      this.setVisible(true);
   }

   private void menu() {
      
   }

   private void event() {
      overlap_id.addActionListener(e->{                              // �ߺ�Ȯ�� ��ư. 
         
       Pattern pattern = Pattern.compile("^[A-Za-z]{1}[A-Za-z0-9]{3,19}$");   //���̵� ���� 
         Matcher match = pattern.matcher(ID.getText());   
         
         if(match.matches()){      //���̵� ���ǹ�
            System.out.println("����");
            String id = ID.getText();
            
            try {
            	DBManager dbm = new DBManager();
            	IDcheck = dbm.overLap(id);
//          dispose();
            } catch (Exception err) {
            	err.printStackTrace();
            }
            
            if(IDcheck) {
            	JOptionPane.showMessageDialog(this, "���԰����� ���̵� �Դϴ�", "�ߺ�Ȯ��", JOptionPane.INFORMATION_MESSAGE);
            	Overlap_check = true;
            }else {
            	JOptionPane.showMessageDialog(this, "�̹� ���Ե� �ִ� ���̵� �Դϴ�.", "�ߺ�Ȯ��", JOptionPane.INFORMATION_MESSAGE);
            }
         }else{
        	 JOptionPane.showMessageDialog(this, "��� ������ ID���� �Է��ϼ���(����,���� 3���̻�)", "���̵� Ȯ��", JOptionPane.INFORMATION_MESSAGE);
        	 System.out.println("s");
         }
      });
      
      join_button.addActionListener(e->{                                 //���Թ�ư
       
       Pattern pwd_pattern = Pattern.compile("[a-z0-9]{6,15}"); // ��й�ȣ ���� ���Խ�
         char[] jtemp_pwd = pw_textfield.getPassword();
         String changestr="";
         for(int i=0; i<pw_textfield.getPassword().length;i++) {
            changestr += Character.toString(jtemp_pwd[i]);
         }
         Matcher pwd_match = pwd_pattern.matcher(changestr);
         
       if(pwd_match.matches()){                     //�н����� ���Խ� Ȯ��
          System.out.println("��й�ȣ ���� ����");
       }else
          System.out.println("��й�ȣ ������");
       
        char[] pwd = pw_textfield.getPassword();
         char[] pwd1 = pw_textfield.getPassword();
//         System.out.println(pwd);
//         System.out.println(pwd1);
         
         if(Arrays.equals(pwd, pwd1)) {      //��й�ȣ Ȯ��
            System.out.println("�����ص� ��");
         }
         
         Pattern pattern1 = Pattern.compile("^[��-�R]{2,4}$");                  //�̸�����
         Matcher match1 = pattern1.matcher(name.getText());
         if(match1.matches()) {
            System.out.println("�̸�����");
         }
         else
            System.out.println("�̸��Ұ�");
         
         if(sexcheck_man.isSelected())                        //��������
            System.out.println("����");
         else if(sexcheck_woman.isSelected())
            System.out.println("����");
         else
            System.out.println("üũ�ؾ���");
//         
         Pattern pattern2 = Pattern.compile("^[_ a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$");
         Matcher match2 = pattern2.matcher(email_textfield.getText());
         if(match2.matches()) 
            System.out.println("�̸��ϰ���");
         
         else
            System.out.println("�̸��ϺҰ�");
         
         if(!(Overlap_check)) {
            JOptionPane.showMessageDialog(this, "�ߺ�Ȯ���� ���ּ���.", "�ߺ�Ȯ��", JOptionPane.INFORMATION_MESSAGE);
         }
        
         
         if(IDcheck && pwd_match.matches()&&Arrays.equals(pwd, pwd1)&&match1.matches()&&(sexcheck_man.isSelected()||sexcheck_woman.isSelected())&&match2.matches()){
            
            String fid = ID.getText(); 
            char[] temp_pwd = pw_textfield.getPassword();
             String change="";
             for(int i=0; i<pw_textfield.getPassword().length;i++) {
                change += Character.toString(temp_pwd[i]);
             }
             String fpwd = change;
            String fname = name.getText();
            String fsex = null;
             if(sexcheck_man.isSelected())                       
                  fsex = "����";
               else if(sexcheck_woman.isSelected())
                  fsex = "����";
               else
                 fsex = null;
            
            String femail = email_textfield.getText();
             
            User user = new User(fid,fpwd,fname,fsex,femail);
//            System.out.println(fid+fpwd+fname+fsex+femail);
//            System.out.println(user.getId()+user.getName()+user.getEmail()+user.getPwd()+user.getSex());
            
            try {
               DBManager DBM = new DBManager();
              DBM.signup(fid,user);//ù���Խ� �̰� �ּ�ó���ϰ� 
              dispose();
              
           } catch (Exception e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
           }
          }else
             System.out.println("���� �������� �ʾҽ��ϴ� �ٽ� �Է����ּ���");
      
      });

     
      
      cancel_button.addActionListener(e->{
         //dispose
         try {
           DBManager DBM = new DBManager();
           DBM.ShowUser();
           dispose();
        } catch (Exception e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
        }
      });
      
      
   }

   private void display() {
      JPanel panel = new JPanel();
      getContentPane().add(panel, BorderLayout.NORTH);
      panel.setLayout(new GridLayout(2, 1, 0, 0));
      
      panel_1 = new JPanel();
      getContentPane().add(panel_1, BorderLayout.WEST);
      panel_1.setLayout(new GridLayout(6, 1, 0, 0));
      
      panel_2 = new JPanel();
      getContentPane().add(panel_2, BorderLayout.CENTER);
      panel_2.setLayout(new GridLayout(6, 1, 0, 0));

      textField = new JTextField();
      textField.setFont(new Font("����", Font.PLAIN, 40));
      
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(textField);
      textField.setColumns(10);
      
      join_title = new JTextField();
      join_title.setFont(new Font("����", Font.PLAIN, 40));
      join_title.setHorizontalAlignment(SwingConstants.CENTER);
      join_title.setText("\uD68C\uC6D0\uAC00\uC785");
      panel.add(join_title);
      join_title.setColumns(10);
      
      
      id_textfield = new JTextField();
      id_textfield.setHorizontalAlignment(SwingConstants.CENTER);
      id_textfield.setText("\uC544\uC774\uB514");
      panel_1.add(id_textfield);
      id_textfield.setColumns(10);
      
      pw_textfiled = new JTextField();
      pw_textfiled.setText("\uBE44\uBC00\uBC88\uD638");
      pw_textfiled.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(pw_textfiled);
      pw_textfiled.setColumns(10);
      
      pw_textfiled_1 = new JTextField();
      pw_textfiled_1.setText("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
      pw_textfiled_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(pw_textfiled_1);
      pw_textfiled_1.setColumns(10);
      
      name_textfield = new JTextField();
      name_textfield.setHorizontalAlignment(SwingConstants.CENTER);
      name_textfield.setText("\uC774\uB984");
      panel_1.add(name_textfield);
      name_textfield.setColumns(10);
      
      sex_textfield = new JTextField();
      sex_textfield.setHorizontalAlignment(SwingConstants.CENTER);
      sex_textfield.setText("\uC131\uBCC4");
      panel_1.add(sex_textfield);
      sex_textfield.setColumns(10);
      
      email = new JTextField();
      email.setHorizontalAlignment(SwingConstants.CENTER);
      email.setText("\uC774\uBA54\uC77C");
      panel_1.add(email);
      email.setColumns(10);
      
      panel_5 = new JPanel();
      panel_2.add(panel_5);
      panel_5.setLayout(new GridLayout(1, 2, 0, 0));
      
      ID = new JTextField();
      panel_5.add(ID);
      ID.setColumns(10);
      
      overlap_id = new JButton("�ߺ�Ȯ��");
      overlap_id.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {}   });
      panel_5.add(overlap_id);
      
      pw_textfield = new JPasswordField();
      pw_textfield.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(pw_textfield);
      
      pw_textfield_1 = new JPasswordField();
      pw_textfield_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(pw_textfield_1);
      
      name = new JTextField();
      name.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(name);
      name.setColumns(10);
      
      panel_3 = new JPanel();
      panel_2.add(panel_3);
      panel_3.setLayout(new GridLayout(0, 2, 0, 0));
      
      sexcheck_man = new JRadioButton("��");
      sexcheck_man.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(sexcheck_man);
      
      sexcheck_woman = new JRadioButton("��");
      sexcheck_woman.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(sexcheck_woman);
      
      ButtonGroup group = new ButtonGroup();   //��ư �׷���
      group.add(sexcheck_man);
      group.add(sexcheck_woman);
      
      email_textfield = new JTextField();
      panel_2.add(email_textfield);
      email_textfield.setColumns(10);
      
      panel_4 = new JPanel();
      getContentPane().add(panel_4, BorderLayout.SOUTH);
      panel_4.setLayout(new GridLayout(1, 2, 0, 0));
      
      join_button = new JButton("\uAC00\uC785");
      panel_4.add(join_button);
      
      cancel_button = new JButton("\uCDE8\uC18C");
      panel_4.add(cancel_button);
      
      
   }

}
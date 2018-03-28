package Team1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 354, 425);
      this.setTitle("1¾ï ¸¸µé±â");
      this.setLocationByPlatform(true);
      this.setResizable(false);
      this.setVisible(true);
   }

   private void menu() {
      
   }

   private void event() {
//      btnNewButton_2.addActionListener(e->{                              // Áßº¹È®ÀÎ ¹öÆ°. 
//         Pattern pattern = Pattern.compile("^[A-Za-z]{1}[A-Za-z0-9]{3,19}$");   //¾ÆÀÌµð Á¶°Ç 
//         Matcher match = pattern.matcher(ID.getText());   
//         
//         if(match.matches()){      //¾ÆÀÌµð Á¶°Ç¹®
//            System.out.println("¸ÂÀ½");
//            
//         }else
//            System.out.println("s");
//      });
//      
//      btnNewButton.addActionListener(e->{                                 //°¡ÀÔ¹öÆ°
//         char[] pwd = passwordField.getPassword();
//         char[] pwd1 = passwordconfirm.getPassword();
////         System.out.println(pwd);
////         System.out.println(pwd1);
//         if(Arrays.equals(pwd, pwd1)) {      //ºñ¹Ð¹øÈ£ È®ÀÎ
//            System.out.println("ÀúÀåÇØµµ µÊ");
//         }
//         
//         Pattern pattern1 = Pattern.compile("^[°¡-ÆR]{2,4}$");                  //ÀÌ¸§Á¶°Ç
//         Matcher match1 = pattern1.matcher(name.getText());
//         if(match1.matches()) {
//            System.out.println("ÀÌ¸§°¡´É");
//         }
//         else
//            System.out.println("ÀÌ¸§ºÒ°¡");
//         
//         if(rdbtnNewRadioButton.getText() == "³²")                        //¼ºº°Á¶°Ç
//            System.out.println("³²ÀÚ");
//         else
//            System.out.println("¿©ÀÚ");
//         
//         Pattern pattern2 = Pattern.compile("^[_ a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$");
//         Matcher match2 = pattern2.matcher(email.getText());
//         if(match2.matches()) 
//            System.out.println("ÀÌ¸ÞÀÏ°¡´É");
//         
//         else
//            System.out.println("ÀÌ¸ÞÀÏºÒ°¡");
//         
//      });

      join_button.addActionListener(e->{
         //ÀúÀå
      });
      
      cancel_button.addActionListener(e->{
         //dispose
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
      textField.setFont(new Font("±¼¸²", Font.PLAIN, 40));
      
      textField.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(textField);
      textField.setColumns(10);
      
      join_title = new JTextField();
      join_title.setFont(new Font("±¼¸²", Font.PLAIN, 40));
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
      
      overlap_id = new JButton("Áßº¹È®ÀÎ");
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
      
      sexcheck_man = new JRadioButton("³²");
      sexcheck_man.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(sexcheck_man);
      
      sexcheck_woman = new JRadioButton("¿©");
      sexcheck_woman.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(sexcheck_woman);
      
      ButtonGroup group = new ButtonGroup();   //¹öÆ° ±×·çÇÎ
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
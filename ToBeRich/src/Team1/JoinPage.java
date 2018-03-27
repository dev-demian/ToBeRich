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
   private JTextField textField_1;
   private JPanel panel_1;
   private JTextField textField_2;
   private JTextField textField_3;
   private JTextField textField_4;
   private JTextField textField_5;
   private JTextField textField_6;
   private JTextField textField_7;
   private JPanel panel_2;
   private JTextField name;
   private JTextField email;
   private JPanel panel_3;
   private JRadioButton rdbtnNewRadioButton;
   private JRadioButton rdbtnNewRadioButton_1;
   private JPanel panel_4;
   private JButton btnNewButton;
   private JButton btnNewButton_1;
   private JPasswordField passwordField;
   private JPasswordField passwordconfirm;
   private JPanel panel_5;
   private JTextField ID;
   private JButton btnNewButton_2;

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

      btnNewButton.addActionListener(e->{
         //ÀúÀå
      });
      
      btnNewButton_1.addActionListener(e->{
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
      
      textField_1 = new JTextField();
      textField_1.setFont(new Font("±¼¸²", Font.PLAIN, 40));
      textField_1.setHorizontalAlignment(SwingConstants.CENTER);
      textField_1.setText("\uD68C\uC6D0\uAC00\uC785");
      panel.add(textField_1);
      textField_1.setColumns(10);
      
      
      textField_3 = new JTextField();
      textField_3.setHorizontalAlignment(SwingConstants.CENTER);
      textField_3.setText("\uC544\uC774\uB514");
      panel_1.add(textField_3);
      textField_3.setColumns(10);
      
      textField_2 = new JTextField();
      textField_2.setText("\uBE44\uBC00\uBC88\uD638");
      textField_2.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(textField_2);
      textField_2.setColumns(10);
      
      textField_5 = new JTextField();
      textField_5.setText("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
      textField_5.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(textField_5);
      textField_5.setColumns(10);
      
      textField_4 = new JTextField();
      textField_4.setHorizontalAlignment(SwingConstants.CENTER);
      textField_4.setText("\uC774\uB984");
      panel_1.add(textField_4);
      textField_4.setColumns(10);
      
      textField_6 = new JTextField();
      textField_6.setHorizontalAlignment(SwingConstants.CENTER);
      textField_6.setText("\uC131\uBCC4");
      panel_1.add(textField_6);
      textField_6.setColumns(10);
      
      textField_7 = new JTextField();
      textField_7.setHorizontalAlignment(SwingConstants.CENTER);
      textField_7.setText("\uC774\uBA54\uC77C");
      panel_1.add(textField_7);
      textField_7.setColumns(10);
      
      panel_5 = new JPanel();
      panel_2.add(panel_5);
      panel_5.setLayout(new GridLayout(1, 2, 0, 0));
      
      ID = new JTextField();
      panel_5.add(ID);
      ID.setColumns(10);
      
      btnNewButton_2 = new JButton("Áßº¹È®ÀÎ");
      btnNewButton_2.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {}   });
      panel_5.add(btnNewButton_2);
      
      passwordField = new JPasswordField();
      passwordField.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(passwordField);
      
      passwordconfirm = new JPasswordField();
      passwordconfirm.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(passwordconfirm);
      
      name = new JTextField();
      name.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(name);
      name.setColumns(10);
      
      panel_3 = new JPanel();
      panel_2.add(panel_3);
      panel_3.setLayout(new GridLayout(0, 2, 0, 0));
      
      rdbtnNewRadioButton = new JRadioButton("³²");
      rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(rdbtnNewRadioButton);
      
      rdbtnNewRadioButton_1 = new JRadioButton("¿©");
      rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(rdbtnNewRadioButton_1);
      
      ButtonGroup group = new ButtonGroup();   //¹öÆ° ±×·çÇÎ
      group.add(rdbtnNewRadioButton);
      group.add(rdbtnNewRadioButton_1);
      
      email = new JTextField();
      panel_2.add(email);
      email.setColumns(10);
      
      panel_4 = new JPanel();
      getContentPane().add(panel_4, BorderLayout.SOUTH);
      panel_4.setLayout(new GridLayout(1, 2, 0, 0));
      
      btnNewButton = new JButton("\uAC00\uC785");
      panel_4.add(btnNewButton);
      
      btnNewButton_1 = new JButton("\uCDE8\uC18C");
      panel_4.add(btnNewButton_1);
      
      
   }

}
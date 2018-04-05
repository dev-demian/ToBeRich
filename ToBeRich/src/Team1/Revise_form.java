package Team1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Revise_form extends JFrame {//Á¤º¸ ¼öÁ¤ Æû
   private JPanel panel_1;
   private JPanel panel_2;
   private JTextField name;
   private JTextField email_textfield;
   private JPanel panel_3;
   private JPanel panel_4;
   private JButton revice_button;
   private JButton cancel_button;
   private JPasswordField pw_textfield;
   private JPasswordField pw_textfield_1;
   private JPanel panel_5;
   private JTextField ID;
   private JRadioButton sexcheck_man;
   private JRadioButton sexcheck_woman;
   private JButton overlap_id;

   private Map<String, User> map = new HashMap<>();

   private User user;

   private File target = new File("DB", "user.txt"); // DS DBÆÄÀÏ °æ·Î
   private JLabel lblNewLabel_1;
   private JLabel lblNewLabel_2;
   private JLabel lblNewLabel_3;
   private JLabel lblNewLabel_4;
   private JLabel lblNewLabel_5;
   private JLabel lblNewLabel_6;
   private JLabel lblNewLabel;

   // /**
   // * Launch the application.
   // */
   // public static void main(String[] args) {
   // EventQueue.invokeLater(new Runnable() {
   // public void run() {
   // try {
   // Revise_form frame = new Revise_form();
   // frame.setVisible(true);
   // } catch (Exception e) {
   // e.printStackTrace();
   // }
   // }
   // });
   // }

   /**
    * Create the frame.
    */
   public Revise_form(User user) {
      this.user = user;
      this.display();
      this.event();
      this.menu();
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 354, 425);
      this.setTitle("1¾ï ¸¸µé±â");
      this.setLocationByPlatform(true);
      this.setResizable(false);
      this.setVisible(true);
   }

   private void menu() {

   }

   private void event() {

      revice_button.addActionListener(e -> { // DS ¼öÁ¤¹öÆ°

         Pattern pwd_pattern = Pattern.compile("[a-z0-9]{6,15}"); // ºñ¹Ð¹øÈ£ Á¶°Ç Á¤±Ô½Ä
         char[] jtemp_pwd = pw_textfield.getPassword();
         String changestr = "";
         for (int i = 0; i < pw_textfield.getPassword().length; i++) {
            changestr += Character.toString(jtemp_pwd[i]);
         }
         Matcher pwd_match = pwd_pattern.matcher(changestr);

         if (pwd_match.matches()) { // ÆÐ½º¿öµå Á¤±Ô½Ä È®ÀÎ
            System.out.println("ºñ¹Ð¹øÈ£ ÀúÀå °¡´É");
         } else
            System.out.println("ºñ¹Ð¹øÈ£ ºÎÀûÇÕ");

         char[] pwd = pw_textfield.getPassword();
         char[] pwd1 = pw_textfield.getPassword();
         // System.out.println(pwd);
         // System.out.println(pwd1);

         if (Arrays.equals(pwd, pwd1)) { // ºñ¹Ð¹øÈ£ È®ÀÎ
            System.out.println("ÀúÀåÇØµµ µÊ");
         }

         /*
          * Pattern pattern1 = Pattern.compile("^[°¡-ÆR]{2,4}$"); //ÀÌ¸§Á¶°Ç Matcher match1 =
          * pattern1.matcher(name.getText()); if(match1.matches()) {
          * System.out.println("ÀÌ¸§°¡´É"); } else System.out.println("ÀÌ¸§ºÒ°¡");
          * 
          * if(sexcheck_man.isSelected()) //¼ºº°Á¶°Ç System.out.println("³²ÀÚ"); else
          * if(sexcheck_woman.isSelected()) System.out.println("¿©ÀÚ"); else
          * System.out.println("Ã¼Å©ÇØ¾ßÇÔ"); //
          * 
          */
         Pattern pattern2 = Pattern.compile("^[_ a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$");
         Matcher match2 = pattern2.matcher(email_textfield.getText());
         if (match2.matches())
            System.out.println("ÀÌ¸ÞÀÏ°¡´É");

         else
            System.out.println("ÀÌ¸ÞÀÏºÒ°¡");

         // ºñ¹Ð¹øÈ£ Á¤±Ô½ÄÀÌ ¸Â´ÂÁö //ºñ¹Ð¹øÈ£,ºñ¹Ð¹øÈ£ È®ÀÎÀÌ ¸Â´ÂÁö
         if (pwd_match.matches() && Arrays.equals(pwd, pwd1) && match2.matches()) {

            // ºñ¹Ð¹øÈ£ ÀÔ·Â ¶õ¿¡ ÀÖ´Â Á¡À» StringÀ¸·Î º¯È¯ÇÏ´Â ÄÚµå
            char[] temp_pwd = pw_textfield.getPassword();
            String change = "";
            for (int i = 0; i < pw_textfield.getPassword().length; i++) {
               change += Character.toString(temp_pwd[i]);
            }
            String fpwd = change;

            String femail = email_textfield.getText();
            
            dispose();
            
            try {
               
               FileClient app_u = new FileClient("127.0.0.1",8888);
               map = (Map<String,User>)app_u.call_request("user");
               
               ObjectOutputStream out = new ObjectOutputStream(
               new BufferedOutputStream(new FileOutputStream(target)));
               
               user.setPwd(fpwd);
               user.setEmail(femail);
               map.put(user.getId(), user);
               out.writeObject(map);
               out.flush();
               
               Pakage data = new Pakage("user",map);
               FileClient app_user = new FileClient("127.0.0.1",8888);
               app_user.save_request(data); 
               
               System.out.println("DB °»½Å ¿Ï·á");
               JOptionPane.showMessageDialog(null,"¼öÁ¤ ¿Ï·á");
            } catch (Exception e1) {
               System.out.println("user.txt DB¿¡¼­ µ¥ÀÌÅÍ¸¦ ³Ö´Ù°¡ ¿À·ù");
            }
         }else {
            JOptionPane.showMessageDialog(null,"ºñ¹Ð¹øÈ£,ÀÌ¸ÞÀÏ Çü½Ä¿¡ ¸ÂÁö ¾Ê½À´Ï´Ù.");
         }
         

      });

      cancel_button.addActionListener(e -> {
         // dispose
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
      JPanel Revice_title = new JPanel();
      getContentPane().add(Revice_title, BorderLayout.NORTH);
      Revice_title.setLayout(new GridLayout(2, 1, 0, 0));
      
      lblNewLabel = new JLabel("\uD68C\uC6D0\uC815\uBCF4 \uC218\uC815");
      lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 40));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      Revice_title.add(lblNewLabel);

      panel_1 = new JPanel();
      getContentPane().add(panel_1, BorderLayout.WEST);
      panel_1.setLayout(new GridLayout(6, 1, 0, 0));

      panel_2 = new JPanel();
      getContentPane().add(panel_2, BorderLayout.CENTER);
      panel_2.setLayout(new GridLayout(6, 1, 0, 0));

      lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(lblNewLabel_1);

      lblNewLabel_2 = new JLabel("\uC0C8 \uBE44\uBC00\uBC88\uD638");
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(lblNewLabel_2);

      lblNewLabel_3 = new JLabel("\uC0C8 \uBE44\uBC00\uBC88\uD638 \uD655\uC778");
      lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(lblNewLabel_3);

      lblNewLabel_4 = new JLabel("\uC774\uB984");
      lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(lblNewLabel_4);

      lblNewLabel_5 = new JLabel("\uC131\uBCC4");
      lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(lblNewLabel_5);

      lblNewLabel_6 = new JLabel("\uC774\uBA54\uC77C");
      lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(lblNewLabel_6);

      panel_5 = new JPanel();
      panel_2.add(panel_5);
      panel_5.setLayout(new GridLayout(1, 2, 0, 0));

      ID = new JTextField();
      ID.setEditable(false);
      ID.setEnabled(false);
      panel_5.add(ID);
      ID.setColumns(10);
      ID.setText(user.getId());

      overlap_id = new JButton("Áßº¹È®ÀÎ");
      overlap_id.setEnabled(false);
      overlap_id.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      panel_5.add(overlap_id);

      pw_textfield = new JPasswordField();
      pw_textfield.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(pw_textfield);

      pw_textfield_1 = new JPasswordField();
      pw_textfield_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(pw_textfield_1);

      name = new JTextField();
      name.setEnabled(false);
      name.setEditable(false);
      name.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(name);
      name.setColumns(10);
      name.setText(user.getName());

      panel_3 = new JPanel();
      panel_2.add(panel_3);
      panel_3.setLayout(new GridLayout(0, 2, 0, 0));

      sexcheck_man = new JRadioButton("³²");
      sexcheck_man.setEnabled(false);
      sexcheck_man.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(sexcheck_man);

      sexcheck_woman = new JRadioButton("¿©");
      sexcheck_woman.setEnabled(false);
      sexcheck_woman.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(sexcheck_woman);

      ButtonGroup group = new ButtonGroup(); // ¹öÆ° ±×·çÇÎ
      group.add(sexcheck_man);
      group.add(sexcheck_woman);

      email_textfield = new JTextField();
      panel_2.add(email_textfield);
      email_textfield.setColumns(10);
      email_textfield.setText(user.getEmail());

      panel_4 = new JPanel();
      getContentPane().add(panel_4, BorderLayout.SOUTH);
      panel_4.setLayout(new GridLayout(1, 2, 0, 0));

      revice_button = new JButton("¼öÁ¤");
      panel_4.add(revice_button);

      cancel_button = new JButton("Ãë¼Ò");
      panel_4.add(cancel_button);

   }

}
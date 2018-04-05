package Team1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.UIManager;

public class Simulation_Result extends JFrame {

   private JPanel contentPane;
   JButton btnNewButton_1 = new JButton("\uB3CC\uC544\uAC00\uAE30");
   JButton btnNewButton = new JButton("\uACF5\uC720\uC544\uC774\uCF58");
   JPanel panel = new JPanel();
   JLabel lblNewLabel = new JLabel("1\uC5B5 \uB9CC\uB4E4\uAE30 \uC2DC\uBBAC\uB808\uC774\uC158 \uACB0\uACFC");
   JLabel lblNewLabel_1 = new JLabel("         ");
   JPanel panel_1 = new JPanel();
   JPanel panel_3 = new JPanel();
   JLabel lblNewLabel_3 = new JLabel("\uC801\uC6A9 \uC0C1\uD488(\uC774\uC728)");
   JLabel lblNewLabel_4 = new JLabel("\uCD1D \uAE30\uAC04");
   JLabel lblNewLabel_6 = new JLabel("\uD658\uAE09 \uC608\uC815\uC77C");
   JPanel panel_4 = new JPanel();
   JPanel panel_2 = new JPanel();
   JLabel L_month = new JLabel("\uAC1C\uC6D4");
   JLabel L_date = new JLabel("\uB0A0\uC9DC");
   
   
   BufferedImage send_img;//매개변수로 보낼 이미지
   int random ; //파일명으로 줄 랜덤값
   String userid;
   File file;//스샷
   String[] resultstr;
   private final JTextArea TA_used_savings = new JTextArea();
   private final JLabel L_user_name = new JLabel(" ");
   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               Simulation_Result frame = new Simulation_Result();
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
   public Simulation_Result(String[] resultstr,String userid) {
	  this.resultstr = resultstr;
	  this.userid=userid;
	  String temp_str = this.resultstr[0].substring(4);
	  String str = temp_str.substring(0, temp_str.length()-1);
	  System.out.println(str);
	  String result_srt;
	  String ktypeWhere = "";             //ktypeWhere는 공백상태
	  
	    String[] array = str.split(",");     //콤마 구분자로 배열에 ktype저장
	    int index =1;
	    for(String cha : array){      //배열 갯수만큼 포문이 돌아간다.
	 
	    	TA_used_savings.append("     "+index+"등 : "+cha+" \n");
	    	index++;
	    }
	    
//	  TA_used_savings.setText();
	  L_month.setText(Integer.toString((Integer.parseInt(this.resultstr[1])*12))+" 개월");
	  Cul_date Cd = new Cul_date(Integer.parseInt(this.resultstr[1])*12);
	  L_date.setHorizontalAlignment(SwingConstants.CENTER);
	  
	  L_date.setText(Cd.returndate());
	  L_user_name.setText(userid+"님  ");
	  
	  
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 480, 320);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      setVisible(true);
      event();
      display();
      
   } 
   private void display() {
	     
         contentPane.add(panel, BorderLayout.NORTH);
         
         panel.add(L_user_name);
         
         panel.add(lblNewLabel);
         
         panel.add(lblNewLabel_1);
         panel.add(btnNewButton);
         
         contentPane.add(panel_1, BorderLayout.CENTER);
         panel_1.setLayout(null);
         panel_3.setBounds(0, 0, 137, 189);
         
         panel_1.add(panel_3);
         panel_3.setLayout(null);
         lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_3.setBounds(0, 12, 137, 75);
         
         panel_3.add(lblNewLabel_3);
         lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_4.setBounds(0, 99, 137, 42);
         
         panel_3.add(lblNewLabel_4);
         lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_6.setBounds(0, 141, 137, 47);
         
         panel_3.add(lblNewLabel_6);
         panel_4.setBounds(136, 0, 316, 189);
         
         panel_1.add(panel_4);
         panel_4.setLayout(null);
         
         JScrollPane scrollPane = new JScrollPane();
         scrollPane.setBounds(0, 0, 315, 99);
         panel_4.add(scrollPane);
         TA_used_savings.setBackground(UIManager.getColor("CheckBox.background"));
         TA_used_savings.setEditable(false);
         
         scrollPane.setViewportView(TA_used_savings);
         
         L_month.setHorizontalAlignment(SwingConstants.CENTER);
         L_month.setBounds(0, 100, 315, 41);
         panel_4.add(L_month);
         L_date.setBounds(0, 141, 315, 48);
         panel_4.add(L_date);
         
         contentPane.add(panel_2, BorderLayout.SOUTH);
                 
         panel_2.add(btnNewButton_1);
      }
   
   private void event() {
	   
	   
      btnNewButton.addActionListener(e->{
    	 
    	 try {
             //파일명 렌던으로
             random = (int)(Math.random()*99999);
             Robot r = new Robot();
             Rectangle rect = new Rectangle(this.getX(), this.getY(), 480, 320);// 좌표가 창의위치를 따라간다.
             BufferedImage img = r.createScreenCapture(rect);
             file = new File("images", "save"+"_"+random+".png");
             ImageIO.write(img, "png", file);  //이때 저장이 됨 
             
             
             System.out.println(file.getAbsolutePath()+"의패스로 서버에도 저장하러 ㄱㄱ");
             send_img = ImageIO.read(file);
             

             
             
          } catch (Exception err2) {
             err2.printStackTrace();
          }
    	  
         Board_Edit BE = new Board_Edit(file,userid,send_img,Integer.toString(random));
      });
      btnNewButton_1.addActionListener(e->{
    	  dispose();
      });
   }
}



//
//btnNewButton_1.addActionListener(new ActionListener() {
//    public void actionPerformed(ActionEvent e) {
//    }
// });
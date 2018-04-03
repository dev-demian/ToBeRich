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
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Simulation_Result extends JFrame {

   private JPanel contentPane;
   JButton btnNewButton_2 = new JButton("\uBA54\uC778\uC73C\uB85C");
   JButton btnNewButton_1 = new JButton("\uB3CC\uC544\uAC00\uAE30");
   JButton btnNewButton = new JButton("\uACF5\uC720\uC544\uC774\uCF58");
   JPanel panel = new JPanel();
   JLabel lblNewLabel = new JLabel("1\uC5B5 \uB9CC\uB4E4\uAE30 \uC2DC\uBBAC\uB808\uC774\uC158 \uACB0\uACFC");
   JLabel lblNewLabel_1 = new JLabel("         ");
   JPanel panel_1 = new JPanel();
   JPanel panel_3 = new JPanel();
   JLabel lblNewLabel_3 = new JLabel("\uC0C1\uD488");
   JLabel lblNewLabel_4 = new JLabel("\uCD1D \uAE30\uAC04");
   JLabel lblNewLabel_6 = new JLabel("\uD658\uAE09 \uC608\uC815\uC77C");
   JPanel panel_4 = new JPanel();
   JPanel panel_2 = new JPanel();
   JLabel lblNewLabel_2 = new JLabel("                                ");
   JLabel L_month = new JLabel("\uAC1C\uC6D4");
   JLabel L_date = new JLabel("\uB0A0\uC9DC");
   
   
   String userid;
   File file;//스샷
   String[] resultstr;
   private final JTextArea TA_used_savings = new JTextArea();
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
	 
	    	TA_used_savings.append(index+"등 : "+cha+" \n");
	    	index++;
	    }


	
//	  TA_used_savings.setText();
	  L_month.setText(Integer.toString((Integer.parseInt(this.resultstr[1])*12))+" 개월");
	  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
         
         panel.add(lblNewLabel);
         
         panel.add(lblNewLabel_1);
         panel.add(btnNewButton);
         
         contentPane.add(panel_1, BorderLayout.CENTER);
         panel_1.setLayout(null);
         panel_3.setBounds(0, 0, 137, 189);
         
         panel_1.add(panel_3);
         panel_3.setLayout(null);
         lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel_3.setBounds(0, 12, 137, 47);
         
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
         TA_used_savings.setBackground(Color.WHITE);
         TA_used_savings.setEditable(false);
         
         scrollPane.setViewportView(TA_used_savings);
         
         L_month.setHorizontalAlignment(SwingConstants.CENTER);
         L_month.setBounds(120, 111, 62, 18);
         panel_4.add(L_month);
         
         L_date.setHorizontalAlignment(SwingConstants.CENTER);
         L_date.setBounds(120, 159, 62, 18);
         panel_4.add(L_date);
         
         contentPane.add(panel_2, BorderLayout.SOUTH);
                 
         panel_2.add(btnNewButton_1);
         
         panel_2.add(lblNewLabel_2);
         
         
         panel_2.add(btnNewButton_2);
      }
   
   private void event() {
	   
	   
      btnNewButton.addActionListener(e->{
    	 
    	 try {
             //파일명 렌던으로
             int random = (int)(Math.random()*99999);
             Robot r = new Robot();
             Rectangle rect = new Rectangle(this.getX(), this.getY(), 480, 320);// 좌표가 창의위치를 따라간다.
             BufferedImage img = r.createScreenCapture(rect);
             file = new File("images", "save"+"_"+random+".png");
             ImageIO.write(img, "png", file);         
          } catch (Exception err) {
             err.printStackTrace();
          }
    	  
         Board_Edit BE = new Board_Edit(file,userid);
      });
   }
}



//
//btnNewButton_1.addActionListener(new ActionListener() {
//    public void actionPerformed(ActionEvent e) {
//    }
// });
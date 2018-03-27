package Team1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.JButton;

public class Simulation_Form extends JFrame {

   private JPanel contentPane;
   JPanel panel = new JPanel();
   JLabel lblNewLabel = new JLabel("\uC2DC\uBBAC\uB808\uC774\uC158 Form");
   JPanel panel_1 = new JPanel();
   JList list = new JList();
   JPanel panel_2 = new JPanel();
   JLabel lblNewLabel_1 = new JLabel("\uC6D4 \uB0A9\uC785\uAE08 :  ");
   JEditorPane editorPane = new JEditorPane();
   JLabel label = new JLabel("                   ");
   JButton button = new JButton("\uACC4\uC0B0\uD558\uAE30");
   
   
   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               Simulation_Form frame = new Simulation_Form();
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
   public Simulation_Form() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 800, 600);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      setVisible(true);
      this.event();
      this.display();
      
      
      
   }
   private void display() {
         contentPane.add(panel, BorderLayout.NORTH);
         
         panel.add(lblNewLabel);
         
         contentPane.add(panel_1, BorderLayout.CENTER);
         
         panel_1.add(list);
         
         contentPane.add(panel_2, BorderLayout.SOUTH);
         
         panel_2.add(lblNewLabel_1);
         
         panel_2.add(editorPane);
         
         panel_2.add(label);
         
         
         panel_2.add(button);
   } 
   
   private void event() {
      button.addActionListener(e->{
      Simulation_Result SRcall = new Simulation_Result();   
      });
   } 

}
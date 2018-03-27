package Team1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
   JLabel lblNewLabel_5 = new JLabel("\uC774\uC728");
   JLabel lblNewLabel_4 = new JLabel("\uCD1D \uAE30\uAC04");
   JLabel lblNewLabel_6 = new JLabel("\uB0A0\uC9DC");
   JPanel panel_4 = new JPanel();
   JTextArea textArea = new JTextArea();
   JFormattedTextField formattedTextField = new JFormattedTextField();
   JFormattedTextField formattedTextField_1 = new JFormattedTextField();
   JFormattedTextField formattedTextField_2 = new JFormattedTextField();
   JPanel panel_2 = new JPanel();
   JLabel lblNewLabel_2 = new JLabel("                                ");

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
   public Simulation_Result() {
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
         panel_1.setLayout(new GridLayout(1, 0, 0, 0));
         
         panel_1.add(panel_3);
         panel_3.setLayout(new GridLayout(4, 1, 0, 0));
         
         panel_3.add(lblNewLabel_3);
         
         panel_3.add(lblNewLabel_5);
         
         panel_3.add(lblNewLabel_4);
         
         panel_3.add(lblNewLabel_6);
         
         panel_1.add(panel_4);
         panel_4.setLayout(new GridLayout(4, 0, 0, 0));
         
         panel_4.add(textArea);
         
         panel_4.add(formattedTextField);
         
         panel_4.add(formattedTextField_1);
         
         panel_4.add(formattedTextField_2);
         
         contentPane.add(panel_2, BorderLayout.SOUTH);
                 
         panel_2.add(btnNewButton_1);
         
         panel_2.add(lblNewLabel_2);
         
         
         panel_2.add(btnNewButton_2);
      }
   
   private void event() {
      btnNewButton.addActionListener(e->{
         Board_Edit BE = new Board_Edit();
      });
   }

}



//
//btnNewButton_1.addActionListener(new ActionListener() {
//    public void actionPerformed(ActionEvent e) {
//    }
// });
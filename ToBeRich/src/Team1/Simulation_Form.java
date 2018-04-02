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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Simulation_Form extends JFrame {

   private JPanel contentPane;
   JPanel panel = new JPanel();
   JLabel lblNewLabel = new JLabel("\uC2DC\uBBAC\uB808\uC774\uC158 Form");
   JPanel panel_2 = new JPanel();
   JLabel lblNewLabel_1 = new JLabel("\uC6D4 \uB0A9\uC785\uAE08 :  ");
   JEditorPane editorPane = new JEditorPane();
   JLabel label = new JLabel("                   ");
   JButton button = new JButton("\uACC4\uC0B0\uD558\uAE30");
   private final JScrollPane scrollPane = new JScrollPane();
   private final JTable table = new JTable();
   
   Object[][] data;
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
      setBounds(100, 100, 1000, 750);
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
         
         contentPane.add(scrollPane, BorderLayout.CENTER);
         //DBM 을사용해 data 불러오기
         try{
        	 DBManager DBM = new DBManager();
        	 data = DBM.CallTable();
        	 System.out.println("데이터 사이즈 : "+data.length);
        	 
         }catch(Exception e){
        	 e.printStackTrace();
         }
         String[] c_name = new String[] {
          		"\uC0C1\uD488\uC120\uD0DD", "        \uC801  \uAE08  \uC0C1  \uD488  \uBA85", "\uC740\uD589\uBA85", "\uAE30\uAC04(\uC6D4)", "\uCD5C\uB300\uB0A9\uC785\uAC00\uB2A5\uAE08\uC561", "\uC774\uC790\uC801\uC6A9\uBC29\uC2DD", "\uAE30\uBCF8\uAE08\uB9AC", "\uC6B0\uB300\uAE08\uB9AC", "\uC6B0\uB300\uC801\uC6A9"
          	};//컬럽명을 담당하는 string[]
         
         //custom Tabel클래스 
         CustomTable CT = new CustomTable(data,c_name);
         table.setModel(CT);
         table.getColumnModel().getColumn(1).setPreferredWidth(201);
         table.getColumnModel().getColumn(3).setPreferredWidth(76);
         table.getColumnModel().getColumn(4).setPreferredWidth(126);
         table.getColumnModel().getColumn(5).setPreferredWidth(112);
         
         scrollPane.setViewportView(table);
         
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
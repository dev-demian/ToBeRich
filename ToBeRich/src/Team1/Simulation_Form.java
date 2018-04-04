package Team1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JScrollPane;

public class Simulation_Form extends JFrame {

   private JPanel contentPane;
   JPanel panel = new JPanel();
   JLabel lblNewLabel = new JLabel("\uC2DC\uBBAC\uB808\uC774\uC158 Form");
   JPanel panel_2 = new JPanel();
   JLabel lblNewLabel_1 = new JLabel	("\uC6D4 \uB0A9\uC785\uAE08 :  ");
   JEditorPane Total_money = new JEditorPane();
   JLabel label = new JLabel("                   ");
   JButton button = new JButton("\uACC4\uC0B0\uD558\uAE30");
   private final JScrollPane scrollPane = new JScrollPane();
   private final JTable table = new JTable();
   String userid;
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
   public Simulation_Form(String userid) {
	   this.userid=userid;
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setBounds(100, 100, 1000, 750);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      setVisible(true);
      this.display();
      this.event();
      
      
     
   }
   private void display() {
         contentPane.add(panel, BorderLayout.NORTH);
         
         panel.add(lblNewLabel);
         
         contentPane.add(scrollPane, BorderLayout.CENTER);
         //DBM ������� data �ҷ�����
         try{
        	 DBManager DBM = new DBManager();
        	 data = DBM.CallTable();
        	 System.out.println("������ ������ : "+data.length);
        	 
         }catch(Exception e){
        	 e.printStackTrace();
         }
         
         String[] c_name = new String[] {
          		"\uC0C1\uD488\uC120\uD0DD", "        \uC801  \uAE08  \uC0C1  \uD488  \uBA85", "\uC740\uD589\uBA85", "\uAE30\uAC04(\uC6D4)", "\uCD5C\uB300\uB0A9\uC785\uAC00\uB2A5\uAE08\uC561", "\uC774\uC790\uC801\uC6A9\uBC29\uC2DD", "\uAE30\uBCF8\uAE08\uB9AC", "\uC6B0\uB300\uAE08\uB9AC", "\uC6B0\uB300\uC801\uC6A9"
          	};//�÷����� ����ϴ� string[]
         
         //custom TabelŬ���� (DefaultTableModel����ϱ� ���� �ϴ� ����)
         //CustomTable CT = new CustomTable(data,c_name);
         
         DefaultTableModel model = new DefaultTableModel(data,new String[] {
          		"\uC0C1\uD488\uC120\uD0DD", "        \uC801  \uAE08  \uC0C1  \uD488  \uBA85", "\uC740\uD589\uBA85", "\uAE30\uAC04(\uC6D4)", "\uCD5C\uB300\uB0A9\uC785\uAC00\uB2A5\uAE08\uC561", "\uC774\uC790\uC801\uC6A9\uBC29\uC2DD", "\uAE30\uBCF8\uAE08\uB9AC", "\uC6B0\uB300\uAE08\uB9AC", "\uC6B0\uB300\uC801\uC6A9"
          	}
          ){
        	 @Override
         	public Class<?> getColumnClass(int columnIndex) {//�� Ŭ������ ������ ���� �������༭ Checkbox�� �ѷ��� (0����, 8��°)
         		// TODO Auto-generated method stub
         		 switch(columnIndex){
         		 case 0: return Boolean.class;
         		 case 1: return String.class;
         		 case 2: return String.class;
         		 case 3: return String.class;
         		 case 4: return String.class;
         		 case 5: return String.class;
         		 case 6: return String.class;
         		 case 7: return String.class;
         		 case 8: return Boolean.class;
         		 
         		 }
         		return Object.class;
         	}
         };
          
         table.setModel(model);
         table.getColumnModel().getColumn(1).setPreferredWidth(201);
         table.getColumnModel().getColumn(3).setPreferredWidth(76);
         table.getColumnModel().getColumn(4).setPreferredWidth(126);
         table.getColumnModel().getColumn(5).setPreferredWidth(112);
         
         scrollPane.setViewportView(table);
         //Ŭ�� ������ ����
         model.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				Object obj =  model.getDataVector().get(e.getLastRow());
				Vector row = (Vector)obj;
				System.out.println(row.get(0));// Ŭ���� �ش� ���� 1��° ��(���� ���)(���õǾ����� �ƴ���)
				System.out.println(row.get(8));// Ŭ���� �ش� ���� 7��° ��(������� ���)(��� ����Ǵ���  �ƴ���)
				
			}
		});
         
         
         contentPane.add(panel_2, BorderLayout.SOUTH);
         
         panel_2.add(lblNewLabel_1);
         
         panel_2.add(Total_money);
         
         panel_2.add(label);
         
         
         panel_2.add(button);
   } 
   
   private void event() {
	   button.addActionListener(e->{  // if������ �ش� Text�ʵ忡 ���� �־�� �Ѱܾߵ�  �� �ƹ��͵� üũ �ȵ������� üũ�϶�� ������ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		   //Ŭ���� �ش� ���̺��� �ܾ�ͼ� ���ο� ������ �� �����ھƾ��� true �� �о�ͼ� 
		   int t_row = table.getRowCount();
		   int t_column = table.getColumnCount();
		   int val_row=0;
		   
		   for (int j = 0; j  < t_row; j++) {			// �տ��� True�� 
			   if((Boolean)table.getValueAt(j,0)){
				   val_row++;							// ���õ� ������ŭval�� row�ε��� ī����
//				   for (int i = 0; i  < t_column; i++) {
//			           System.out.println(table.getValueAt(j, i));  //���̺� ��ü ���
//			       }
			   }
		   }
//		   System.out.println(val_row);
		   Object[][] val = new Object[val_row][t_column];
		   int val_x = 0;
		   for (int j = 0; j  < t_row; j++) {			
			   if((Boolean)table.getValueAt(j,0)){
				   for (int i = 0; i  < t_column; i++) {
//			           System.out.println(table.getValueAt(j, i)); // ���õ� ���̺� �����͸� ���
					   val[val_x][i]=table.getValueAt(j, i);
			       }
				   val_x++;
			   }
		   }
		   if(val.length==0){
			   JOptionPane.showMessageDialog(this, "������ �������� �ʾ� �ܼ� �������� ����մϴ�.", "�����Ұ�", JOptionPane.INFORMATION_MESSAGE);
			   
		   }
//		  for(int i=0;i<val_x;i++){							// ���� ������ ����غ��� 
//			  for(int j=0;j<t_column;j++){
//				  System.out.println(val[i][j]);
//				  
//			  }
//		  }
//		   System.out.println(val.length);					// ���� �������� ����Ȯ�� 
		   
		   //���⿡ ����ϴ°� Cul Ŭ���� �־��༭ ��� ������ ���ϰ� �޾Ƽ� �Ű������� �־��� 
//		   
//		   int total_money =  Integer.parseInt(Total_money.getText().toString());
//		   System.out.println(total_money);
//		   Savings_Calculator SC = new Savings_Calculator(val,total_money);
//		   SC.sort();
//		   String[] resultstr = SC.Calculate();
//		   Simulation_Result SRcall = new Simulation_Result(resultstr,userid);   
      
		   Pattern pattern = Pattern.compile("[1-9][0-9]{3,9}");
	         Matcher match = pattern.matcher(Total_money.getText());
	         if(match.matches()){
	            int total_money =  Integer.parseInt(Total_money.getText().toString());
	            System.out.println(total_money);
	            Savings_Calculator SC = new Savings_Calculator(val,total_money);
	            SC.sort();
	            String[] resultstr = SC.Calculate();
	            Simulation_Result SRcall = new Simulation_Result(resultstr,userid);   
	         }
	         else
	           JOptionPane.showMessageDialog(this, "1000������ 1�� ������ �ݾ׸� ����", "�����Ұ�", JOptionPane.INFORMATION_MESSAGE);
	   
	   });
	   
	   
	   
	   
	   
   } 
   
  


}
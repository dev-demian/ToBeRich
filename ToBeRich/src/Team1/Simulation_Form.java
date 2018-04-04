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
         
         //custom Tabel클래스 (DefaultTableModel사용하기 위해 일단 보류)
         //CustomTable CT = new CustomTable(data,c_name);
         
         DefaultTableModel model = new DefaultTableModel(data,new String[] {
          		"\uC0C1\uD488\uC120\uD0DD", "        \uC801  \uAE08  \uC0C1  \uD488  \uBA85", "\uC740\uD589\uBA85", "\uAE30\uAC04(\uC6D4)", "\uCD5C\uB300\uB0A9\uC785\uAC00\uB2A5\uAE08\uC561", "\uC774\uC790\uC801\uC6A9\uBC29\uC2DD", "\uAE30\uBCF8\uAE08\uB9AC", "\uC6B0\uB300\uAE08\uB9AC", "\uC6B0\uB300\uC801\uC6A9"
          	}
          ){
        	 @Override
         	public Class<?> getColumnClass(int columnIndex) {//이 클래스로 들어오는 값을 지정해줘서 Checkbox를 뿌려줌 (0번쨰, 8번째)
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
         //클릭 리스너 구현
         model.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				Object obj =  model.getDataVector().get(e.getLastRow());
				Vector row = (Vector)obj;
				System.out.println(row.get(0));// 클릭시 해당 열의 1번째 값(선택 출력)(선택되었는지 아닌지)
				System.out.println(row.get(8));// 클릭시 해당 열의 7번째 값(우대적용 출력)(우대 적용되는지  아닌지)
				
			}
		});
         
         
         contentPane.add(panel_2, BorderLayout.SOUTH);
         
         panel_2.add(lblNewLabel_1);
         
         panel_2.add(Total_money);
         
         panel_2.add(label);
         
         
         panel_2.add(button);
   } 
   
   private void event() {
	   button.addActionListener(e->{  // if문으로 해당 Text필드에 값이 있어야 넘겨야됨  또 아무것도 체크 안되있을시 체크하라고 떠야함 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		   //클릭시 해당 테이블을 긁어와서 새로운 변수에 다 때려박아야함 true 만 읽어와서 
		   int t_row = table.getRowCount();
		   int t_column = table.getColumnCount();
		   int val_row=0;
		   
		   for (int j = 0; j  < t_row; j++) {			// 앞에가 True면 
			   if((Boolean)table.getValueAt(j,0)){
				   val_row++;							// 선택된 개수만큼val의 row인덱스 카운팅
//				   for (int i = 0; i  < t_column; i++) {
//			           System.out.println(table.getValueAt(j, i));  //테이블 전체 출력
//			       }
			   }
		   }
//		   System.out.println(val_row);
		   Object[][] val = new Object[val_row][t_column];
		   int val_x = 0;
		   for (int j = 0; j  < t_row; j++) {			
			   if((Boolean)table.getValueAt(j,0)){
				   for (int i = 0; i  < t_column; i++) {
//			           System.out.println(table.getValueAt(j, i)); // 선택된 테이블 데이터만 출력
					   val[val_x][i]=table.getValueAt(j, i);
			       }
				   val_x++;
			   }
		   }
		   if(val.length==0){
			   JOptionPane.showMessageDialog(this, "적금을 선택하지 않아 단순 저금으로 계산합니다.", "수정불가", JOptionPane.INFORMATION_MESSAGE);
			   
		   }
//		  for(int i=0;i<val_x;i++){							// 보낼 데이터 출력해보기 
//			  for(int j=0;j<t_column;j++){
//				  System.out.println(val[i][j]);
//				  
//			  }
//		  }
//		   System.out.println(val.length);					// 보낼 데이터의 길이확인 
		   
		   //여기에 계산하는거 Cul 클래스 넣어줘서 계산 끝내고 리턴값 받아서 매개변수로 넣어줌 
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
	           JOptionPane.showMessageDialog(this, "1000원에서 1억 사이의 금액만 가능", "수정불가", JOptionPane.INFORMATION_MESSAGE);
	   
	   });
	   
	   
	   
	   
	   
   } 
   
  


}
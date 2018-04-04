package Team1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

class Main_Form extends JFrame {
//   ��ġ�� �������(������Ʈ)�� ����ʵ�� ������ �� ���
   
//   �������� Component�� Frame�� ���� ��ġ�߾��µ� �̷��� ����ȿ���� ��������
//   Panel�� ���� Component�� ��ġ�� �� �ֵ��� ������ �� �ִ�(ContentPane)
   private JPanel mainPanel = new JPanel();
   
   private String name = "����Ʈ";
   private JLabel user = new JLabel(name + "��", JLabel.CENTER);
   private JButton simulation = new JButton("�ùķ��̼�");
   private JButton userInformation = new JButton("��������");
   private JButton logout = new JButton("�α׾ƿ�");
   private JLabel title = new JLabel("< �Խ��� >");
   private Font font = new Font("", Font.PLAIN, 30);
   private final JTable table = new JTable();
   User thisuser;
   ImageIcon ic;
  
   
   
   
   
   //main�� �ϴ� �������� �����ڿ��� ����
   public Main_Form( User target_user) {
	  this.thisuser = target_user;
	  user.setText(this.thisuser.getName()+" ��");
	  this.display();//ȭ�� ���� ���� ó��
      this.event();//�̺�Ʈ ���� ó��
      this.menu();//�޴� ���� ó��
      
      this.setTitle("GUI�׽�Ʈ");
      this.setSize(1000, 600);
//      ��ġ�� �ü���� �����ϵ��� ����
      this.setLocationByPlatform(true);
//      ��ܺκ��� ������ �ʵ��� ����
//      this.setUndecorated(true);
      this.setResizable(false);
      this.setVisible(true);
   }
   private void display() {
      //mainPanel�� �⺻ �гη� ����
      this.setContentPane(mainPanel);
      //��� ������Ʈ�� �߰��� mainPanel�� ����
      mainPanel.setLayout(null);
      
      user.setBounds(28,160,106,50);
      userInformation.setBounds(40, 220, 106, 50);
      logout.setBounds(40, 310, 106, 50);
      simulation.setBounds(40,400,106,50);
      title.setBounds(190, 18, 770, 40);
      //��Ʈ
      title.setFont(font);
      //������ 
      mainPanel.setBackground(Color.WHITE);
      mainPanel.add(user);
      mainPanel.add(simulation);
      mainPanel.add(userInformation);
      mainPanel.add(logout);
      mainPanel.add(title);
//      BoardControl BC�ҷ��� Object[][] �޾ƿ���  data
//      Object[][] data = BC.calltable();
     
      BoardControl BC = new BoardControl();
      Object[][] data = BC.calltable();
      
      String[] header =  new String[] {
        		"\uBC88\uD638", "\uC81C\uBAA9", "\uC791\uC131\uC790", "\uB0A0\uC790", "\uC870\uD68C\uC218"
        	};
      //S : ���̺�
      DefaultTableModel model = new DefaultTableModel(data, header) {
         @Override
        public Class<?> getColumnClass(int columnIndex) {
        	// TODO Auto-generated method stub
        	 switch(columnIndex){
        	 case 0: return Integer.class;
        	 case 1: return String.class;
        	 case 2: return String.class;
        	 case 3: return String.class;//��¥
        	 case 4: return Integer.class;
     		 
     		 
     		 }
     		return Object.class;
        }
      };
     
      
     
      table.setModel(model);
      
      
      JScrollPane pane = new JScrollPane(table);
     
      
      pane.getViewport().setBackground(Color.WHITE);
      //��ũ�Ѽ���
      pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      mainPanel.add(pane);
      pane.setBounds(190, 68, 770, 469);
      if(this.thisuser.getSex().equals("����")){		// ���� ���� ������ ���� 
     	   ic  = new ImageIcon("property/male.png");
     	  
       }else{
     	   ic  = new ImageIcon("property/female.png");
       }
     
      JLabel iconimg = new JLabel();
      iconimg.setIcon(ic);
      iconimg.setBounds(28, 32, 130, 128);
      mainPanel.add(iconimg);
      //���̺� ����
      DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
      renderer.setHorizontalAlignment(SwingConstants.CENTER);
      
      //���̺� width�� ����
     
//      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      table.getColumnModel().getColumn(0).setPreferredWidth(34);
      table.getColumnModel().getColumn(1).setPreferredWidth(400);
      table.getColumnModel().getColumn(2).setPreferredWidth(100);
      table.getColumnModel().getColumn(3).setPreferredWidth(130);
      table.getColumnModel().getColumn(4).setPreferredWidth(88);
      //���̺� �̵��Ұ�
//      table.getTableHeader().setReorderingAllowed(false);
      //���̺� ũ�� ���� �Ұ�
//      table.getTableHeader().setResizingAllowed(false);
      //���콺�̺�Ʈ
      //F : ���̺�
   }
   private void event() {
//      this.setDefaultCloseOperation(EXIT_ON_CLOSE);//���α׷� ����
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//â ����
//      this.setDefaultCloseOperation(HIDE_ON_CLOSE);//â ����
//      ���� �͵��� �� ���� ��� Ŀ���� �̺�Ʈ ����
//      this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//�⺻ �̺�Ʈ ����
      
      simulation.addActionListener(e->{
         //�ùķ��̼� �� ȣ�� ����� ���� �־��ֱ� 
    	 String userid = thisuser.getId();
         Simulation_Form callSF = new Simulation_Form(userid);
      });
      
      
//      //���콺Ŭ�� �̺�Ʈ
//      class MouseListener extends MouseAdapter {
//          @Override
//          public void mouseClicked(MouseEvent e) {
//             if (e.getButton() == 1) {
//               int row = table.getSelectedRow();
//               int col = table.getSelectedColumn();
//               System.out.println(row + "��");
//             } //Ŭ��
//   //          if (e.getClickCount() == 2) { } // ����Ŭ��
//   //          if (e.getButton() == 3) { } // ������ Ŭ��
//          }
//      }
     
      table.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
             int row = table.getSelectedRow();
             int col = table.getSelectedColumn();
             int rowCount = table.getSelectedRowCount();
//             System.out.println(table.getValueAt(row, 0));
             
//             int colCount = table.getColumnCount();
//             for(int i = 0; i < colCount; i++) {
                 //System.out.println(table.getValueAt(row, col));
             	//�Խù��� Ŭ������ �� �Խù� ��ȣ�� Board_main�� �����ϰ� Board_main���� ���
                 Board_show bs = new Board_show(Integer.parseInt(table.getValueAt(row, 0).toString()),thisuser.getId().toString());
//             }
             //����������
//             System.out.println(table.getValueAt(row, col));
          }
       });
      
      //�α׾ƿ�
      logout.addActionListener(event -> {
         dispose();
         this.thisuser = null;
         LogIn LP = new LogIn();
      });
      
   }
   private void menu() {}
}



















//public class Main_Form {                           
//   public static void main(String[] args) {
//      //â�� ���̻� ���� ������ �ʰ� Ȯ���Ų Ŭ������ �ν��Ͻ��� ����
//      Main_Form window = new Main_Form();
//   }
//}

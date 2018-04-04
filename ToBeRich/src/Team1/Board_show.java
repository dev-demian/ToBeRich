package Team1;


import java.awt.Font;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

class Board_show extends JFrame{//�ܼ� Ȯ��â buttonŬ���� ���� ������ �Ѿ 
   //DS ��������� ��� �� ���� ���� ��ġ�� ������
   JPanel mainPanel = new JPanel();
   JLabel lblNewLabel = new JLabel("����");
   JTextField textField = new JTextField();
   JLabel label = new JLabel("��ȣ");
   JTextField textField_1 = new JTextField();
   JLabel label_1 = new JLabel("��¥");
   JTextField textField_2 = new JTextField();
   //DS �̹����� ���� �� ���� ����
   JLabel imgLabel;
   JTextArea textArea_1 = new JTextArea();
   JTextArea TA_print_comment = new JTextArea();
   JButton edit_button = new JButton("\uC218\uC815/\uC0AD\uC81C");
   //DS DB��� ����
   private File target = new File("DB","board.txt");
   private Image img = null;
   //DS �̹��� ��ΰ� ���� �� ���� ����
   private File imgPath;
   //DS �Խ��� ��ȣ�� ���� �Ǵ� ����
   private int number;
   //DS Ŭ���� �Խù��� map�����͸� ������ ������ map,list����
   private Map<Integer,List<Object>> map = new HashMap<>();
   private List<Object> list = new ArrayList();
   String userid;//�ش� ������ ���� �������� �ҷ��������� ������
   ImageIcon ic;
   private final JTextField TF_Comment = new JTextField();
   private final JButton add_commnet_btn = new JButton("\uB4F1\uB85D");
   private String printed_comment ;
   
   
   //main�� �ϴ� �������� �����ڿ��� ����
   public Board_show(int number,String userid) {
	   //DS �����ڿ��� �Խù� ��ȣ�� ���� �޾� number������ �����Ѵ�
	  this.number = number;
	  this.userid= userid;
	  //DS DB���� ��ü map�����͸� �ҷ��ͼ� number�� �ش��ϴ� map�����͸��� list�� �����Ѵ�
	  try{
		  //DS DB���� �о���� ��Ʈ�� ����
		  ObjectInputStream mapInput = new ObjectInputStream(
		  new BufferedInputStream(new FileInputStream(target)));
		  //DS DB���� ��ü map ������ �ҷ��� ���� map�� ����
		  map = (Map<Integer,List<Object>>)mapInput.readObject();
		  //DS ���� map���� number(�Խù� ��ȣ)�� �ش��ϴ� key���� value���� ���� list�� ����
		  list = map.get(number);
		  //System.out.println(list.get(1)); //DS DB���� ������ �ش� �Խù� ������ Ȯ���� �� �ִ�
	  }catch(Exception e) {
		  System.out.println("DB���� �����͸� ������ �� ����");
	  }
	  
	  //DS �����ڸ� ���� �Խù� Ȯ�� ���� �� ������ ��ȸ���� 1�� �����ϴ� �޼ҵ� ȣ��
	  BoardControl bc = new BoardControl();
	  bc.viewAdd(map,number);
	  
	  //DS �����ڿ��� list�� 2�ε��� �����͸� �޾� �̹��� ��θ� �����ϰ� 
	  //�̹��� ��θ� ImageŸ������ ��ȯ�� �����Ѵ�
	  try {
	    	imgPath = (File)list.get(2);
			img = ImageIO.read(imgPath);
		} catch (IOException e) {
			System.out.println("�̹��� ������ �����ϴ�.");
		}
	  //DS ImageŸ���� ������ ImageIcon���� ��ȯ �� ���� Ŭ���� Ÿ���� JLabel�� �����Ѵ�
	  imgLabel = new JLabel(new ImageIcon(img));
	  
	   
	  
	  
      this.display();//ȭ�� ���� ���� ó��
      this.event();//�̺�Ʈ ���� ó��
      this.menu();//�޴� ���� ó��
      
      this.setTitle("�Խ��� ���� Ȯ��");
      this.setSize(1000, 600);
      //this.setLocation(100, 100);
      //��ġ�� �ü���� �����ϵ��� �Ѵ�
      this.setLocationByPlatform(true);
      this.setResizable(false);
      this.setVisible(true);
   }

   private void display() {
      this.setContentPane(mainPanel);
      mainPanel.setLayout(null);
      
     
      lblNewLabel.setFont(new Font("����", Font.PLAIN, 14));
      lblNewLabel.setBounds(12, 10, 28, 21);
      mainPanel.add(lblNewLabel);
      
      
      label.setFont(new Font("����", Font.PLAIN, 14));
      label.setBounds(254, 10, 28, 21);
      mainPanel.add(label);
      
      
      label_1.setFont(new Font("����", Font.PLAIN, 14));
      label_1.setBounds(488, 10, 28, 21);
      mainPanel.add(label_1);
      textField.setEditable(false);
      
      
      textField.setBounds(47, 11, 145, 26);
      mainPanel.add(textField);
      //DS list�� 0�ε���(����)�� �ʵ忡 ä��
      textField.setText((String)list.get(0));
      textField_1.setEditable(false);
      
      
      textField_1.setColumns(10);
      textField_1.setBounds(294, 11, 145, 26);
      mainPanel.add(textField_1);
      //DS Main_Form���� ���� �Խ��� ��ȣ(number)�� �ʵ忡 ä��
      textField_1.setText(String.valueOf(number));
      textField_2.setEditable(false);
      
      textField_2.setColumns(10);
      textField_2.setBounds(535, 11, 167, 26);
      mainPanel.add(textField_2);
      //DS list�� 5�ε���(��¥)�� �ʵ忡 ä��
      textField_2.setText((String)list.get(5));
      
      //DS �̹� �����ڿ��� ���� �̹����� ��� �ִ� imgLabel������ ���� ��ġ�� �����Ѵ�
      imgLabel.setBounds(12, 41, 481, 320);
      mainPanel.add(imgLabel);
      textArea_1.setEditable(false);
      
      
      textArea_1.setBounds(505, 41, 197, 320);
      mainPanel.add(textArea_1);
      textArea_1.setText((String)list.get(3));
      
      JScrollPane scroll = new JScrollPane(textArea_1);
      scroll.setBounds(505, 41, 197, 320);
      mainPanel.add(scroll);
      
      TA_print_comment.setBackground(SystemColor.control);
      TA_print_comment.setEditable(false);
      
      List<Object>temp_O = map.get(number);
      List<String> temp_S = (List<String>)temp_O.get(7);
      for(int i=temp_S.size()-1;i>=0;i--){
//    	  System.out.println("��� ��ġ����");
//    	  System.out.println(temp_S.get(i));
    	  printed_comment +=  temp_S.get(i)+"      \n"; 
      }
      //null���°� ����
      System.out.println(printed_comment);
      //���ó�� �޸��� ���̵��� ����ϱ� 
      TA_print_comment.setText(printed_comment);
      
      TA_print_comment.setBounds(12, 371, 690, 130);
      mainPanel.add(TA_print_comment);
      
      JScrollPane scroll_1 = new JScrollPane(TA_print_comment);
      scroll_1.setBounds(12, 410, 688, 143);
      mainPanel.add(scroll_1);
      
      
      edit_button.setBounds(876, 508, 97, 45);
      mainPanel.add(edit_button);
     
      ic  = new ImageIcon("property/googlead.png");
      JLabel ad_sense = new JLabel();
      ad_sense.setIcon(ic);
      ad_sense.setBounds(716, 42, 257, 457);
      
      mainPanel.add(ad_sense);
      TF_Comment.setColumns(10);
      TF_Comment.setBounds(12, 374, 599, 24);
      
      mainPanel.add(TF_Comment);
      add_commnet_btn.setBounds(613, 373, 89, 27);
      
      mainPanel.add(add_commnet_btn);
   }

   private void event() {
      //setDefaultCloseOperation(EXIT_ON_CLOSE);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
      //DS ���� ��ư�� ������ �� �̺�Ʈ ����
      edit_button.addActionListener(e->{
    	  System.out.println(list.get(4).toString() + userid +"�����Ѵ�" );
    	  if(list.get(4).toString().equals(userid)){
    		  Board_main bm = new Board_main(number);
    	  }else
    		  JOptionPane.showMessageDialog(this, "�ش� �Խù��� ����ڰ� �ƴմϴ�", "�����Ұ�", JOptionPane.INFORMATION_MESSAGE);
      });
      
      add_commnet_btn.addActionListener(e->{
    	  try{
    		  //���Խ� �ƹ��͵� ġ�� �ʾҴٸ� ���̾�α� ����
    		  BoardControl Bc = new BoardControl();
    		  
    		  long time = System.currentTimeMillis(); 
    		  SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    		  String str_comment_time = dayTime.format(new Date(time));


    		  String str = userid+" ( "+str_comment_time+" )" + "    --    " + TF_Comment.getText() ;
    		  Bc.addcomment(number, str);
    	  }catch(Exception err){
//    		  String commnet_str = TF_Comment.getText();
    		  err.printStackTrace();
    	  }
      });
      
   }

   private void menu() {
      
   }



//   public static void main(String[] args) {
//      /*
////��Ų ��ġ
//      try {
//         UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      */
//      //â�� ���̻� ���� ������ �ʰ� Ȯ���Ų Ŭ������ �ν��Ͻ��� ����
//	   Board_main window = new Board_main();
//      
//   }
}
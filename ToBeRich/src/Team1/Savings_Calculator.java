package Team1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Savings_Calculator {
	public Object[][] val ;
	public Object[][] sortval;
	public int total_money;
	public static final int Goal_money  = 100000000;			//1�︸���ϱ� 1��
	
	
	
	public Savings_Calculator(Object[][] val,int total_money) {
		this.val = val;
		this.total_money = total_money;
	}
	//2���� �迭�� ����ũ������� ���� (�ش� �迭�� 6�� 7�� �ε����� Double�� ����ȯ�� ���� )
	public void sort(){
		
		sortval = this.val;
		
		for(int i =0;i<sortval.length;i++){
			for(int j=0;j<sortval[i].length;j++){
				System.out.println(sortval[i][j]+"\t");
			}
			System.out.println();
		}
		//������ 
		Object[] column = new Object[sortval.length];
		for(int i=0;i<column.length;i++){
			column[i] = (Boolean)sortval[i][8]?sortval[i][7]:sortval[i][6];  // true�Ͻ� ���ݸ� false�Ͻ� �⺻�ݸ�
			System.out.println("column["+i+"] =" + column[i]);
		}
		//�� ����
		for (int i=0; i<sortval.length;i++){
			int minIdx = i;
			for(int j = i+1; j<column.length; j++){
				double v1 = Double.parseDouble(column[minIdx].toString());
				double v2 = Double.parseDouble(column[j].toString());
				if(v1 < v2){
					Object tmp = column[minIdx];
					column[minIdx] = column[j];
					column[j] = tmp;
					
					Object[] tmpArr = sortval[minIdx];
					sortval[minIdx] = sortval[j];
					sortval[j] = tmpArr;
					
				}
			}
		}
		System.out.println(Arrays.toString(column));// ��� ������ ���ĉ���� Ȯ�� 
		
		
		for(int i =0;i<sortval.length;i++){
			for(int j=0;j<sortval[i].length;j++){
				System.out.println(sortval[i][j]+"\t");
			}
			System.out.println();
		}
		
		
	}
	// �ش� ���ĵȰ��� �ִ��ѵ���� �� totalmoney�� ������ ������ ���� ���� ���� ���� �� ���Ա����� ��밡���� ���ݸ� ���� �迭�� ���� 
	public String[] Calculate(){//2���� ���� ������  x�� ���ϱ� 2������� (������ ��ǰ���� �ִ� ���԰��ɱݾ��� ������ �� ���Աݺ��� ū���(while���) vs �������(�׿� ���� �ڵ� ��������))
		
		String[] result = new String[2];//0:����� ���� ��ǰ���� String���� ��� ���� 1: �ش� ��ǰ���� �ɸ� ��� 
		int money_month = total_money;
		int selected_max =0;
		for(int i=0;i<sortval.length;i++){
			selected_max += Integer.parseInt(sortval[i][4].toString());
		}
		
		
		
		if(selected_max>money_month){// ������ ��ǰ�� �� ���Ա��� �����ݿ��� �ݾ׺��� ũ�� ������ ���� ���� ���� ���� ��ǰ�� ������ ���ֱ� ������ 
			System.out.println("������ ��ǰ���� �ִ� ���� ���� �ݾ��� �����Կ����ݺ���  �� Ŀ�� ������ ���� ���� ��ǰ�� �ִ��ѵ��� �������� ���մϴ�.");
			int goal = Goal_money;
			int x_Idx =0;
			int index=0;
			
			int left_money =0; //������ ��ǰ�� ����� �ݾ�(���ݻ����� �� �޼��ѱݾ�)
			//x�ε����� left_money ���ϱ�
			while(true){// ����� 
				left_money= money_month;
				money_month -= Integer.parseInt(sortval[index][4].toString());
				index++;
				
				if(money_month<0){
					x_Idx =index;
					break;
				}
			}
			//use val�� �ش��ϴ� ���ݱ��� �ֱ� 
			String[][] use_val = new String[x_Idx][4];//���� val y- 0: ���ݸ� 1: ���� �ݾ� (�ִ��ѵ�)�������� left_money�� �������ݾ� 2: ���� ���� 3:����ȯ�ޱݾ�
			
			for(int i=0;i<use_val.length;i++){//�������� Max���Ա��� ä���� �������� if������ ������ ���� �ܸ� ������ ������ �س��� ���� ������ �ܸ� ��ǰ�� ���ͼ� �ܸ��� ����
				
				if(i<use_val.length-1){
					use_val[i][0] = sortval[i][1].toString();
					use_val[i][1] = sortval[i][4].toString();
					use_val[i][2] = (Boolean)sortval[i][8]?sortval[i][7].toString():sortval[i][6].toString();
					use_val[i][3] = dan(use_val[i][1],use_val[i][2]);
//				use_val[i][3] = sortval[i][5].toString().equals("�ܸ�")?dan(use_val[i][1],use_val[i][2]):bok(use_val[i][1],use_val[i][2]);//�޼ҵ� ���� ;
				}else{
					use_val[i][0] = sortval[i][1].toString();
					use_val[i][1] = Integer.toString(left_money);
					use_val[i][2] = (Boolean)sortval[i][8]?sortval[i][7].toString():sortval[i][6].toString();
					use_val[i][3] = dan(use_val[i][1],use_val[i][2]);
//				use_val[i][3] = sortval[i][5].toString().equals("�ܸ�")?dan(use_val[i][1],use_val[i][2]):bok(use_val[i][1],use_val[i][2]);//�޼ҵ� ���� ;
				}
				
			}
			
			for(int i=0;i<use_val.length;i++){//���
				for(int j=0;j<use_val[i].length;j++){
					System.out.println(use_val[i][j]);
				}
			}
			//1�� - ���� 
			int CNT =0; //CNT = ��    
			
			while(true){
				for(int i=0;i<use_val.length;i++){//���
					goal -= Integer.parseInt(use_val[i][3]);
				}
				CNT++;
				if(goal<0){
					System.out.println("�� "+CNT+"���� �ɸ��ϴ�.");
					break;
				}
			}
			for(int i=0;i<use_val.length;i++){
				result[0] += use_val[i][0]+",";
			}
			result[1]= Integer.toString(CNT);
			
			System.out.println(result[0] +"��ǰ���� ");
			System.out.println(result[1] +"�� ���� �����ؾ� �˴ϴ� ");
			
			
		}else{// ������ ��ǰ�� �� ���Ա��� �����ݿ��� �ݾ׺��� ������ (sortval�� ���� ������ �ʿ䰡 ����)
			System.out.println("�����Ա��� �� Ŀ�� �׿� �ݾ��� �Ϲ� �������� �����˴ϴ�");
			int goal = Goal_money;			
			int justsave = 0;//�׳� ������ �� 
			
			for(int i=0;i<sortval.length;i++){
				money_month -= Integer.parseInt(sortval[i][4].toString());
			}
			justsave = money_month; 
			System.out.println("�Ϲ� ���ݱݾ� :"+justsave+"��");
			String[][] use_val = new String[sortval.length][4];//���� �� y- 0: ���ݸ� 1: ���� �ݾ� (�ִ��ѵ�)�������� left_money�� �������ݾ� 2: ���� ���� 3:����ȯ�ޱݾ�
			
			for(int i=0;i<use_val.length;i++){//���� �ٸ��� ��� �÷��� ��ǰ�� Max���ݰ��ɱݾ����� ���Ǿ� �ϱ⋚���� if������� �ʿ䰡 ����
						
					use_val[i][0] = sortval[i][1].toString();
					use_val[i][1] = sortval[i][4].toString();
					use_val[i][2] = (Boolean)sortval[i][8]?sortval[i][7].toString():sortval[i][6].toString();
					use_val[i][3] = dan(use_val[i][1],use_val[i][2]);
//				use_val[i][3] = sortval[i][5].toString().equals("�ܸ�")?dan(use_val[i][1],use_val[i][2]):bok(use_val[i][1],use_val[i][2]);//�޼ҵ� ���� ;

			}
			
			for(int i=0;i<use_val.length;i++){ //����غ���
				for(int j=0;j<use_val[i].length;j++){
					System.out.println(use_val[i][j]);
				}
			}
			//1�� - (�� max�� + �Ϲ����ݱݾ�)
			
			int CNT =0; //CNT = ��    
			
			while(true){
				for(int i=0;i<use_val.length;i++){//���
					goal -= Integer.parseInt(use_val[i][3]);
				}
				goal -= justsave*12;  // �⺻ ���ݵ� ���� ����� �Ѵ� 
				
				CNT++;
				if(goal<0){
					System.out.println("�� "+CNT+"���� �ɸ��ϴ�.");
					break;
				}
			}
			for(int i=0;i<use_val.length;i++){
				result[0] +=  use_val[i][0]+",";
			}
			result[0]+= "+ �� "+justsave+"���� ����";
			result[1]= Integer.toString(CNT);
			
			System.out.println(result[0] );
			System.out.println(result[1] +"�� ���� �����ؾ� �˴ϴ� ");
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		return result; //result[0] = ����� ���� ��ǰ , result[1]=�ش� ��ǰ���� �ɸ� �Ⱓ,  
		
	}
	
	
	//�ܸ��޼ҵ�(12������ ���� ȯ�ޱ��� )���ϰ� String
	public String dan(String investment ,String interest ){
		String Returnd_AMT =null;
		System.out.println("�ܸ� ����");
		 int money = Integer.parseInt(investment);
	     double before_Tax_interest = Double.parseDouble(interest);
	     int term = 12;
	     double realInterest = before_Tax_interest / 100*(term+1) / 24;   //���� ������
	     double afterTax = realInterest *(1-0.154);   //���� ������
	     int total = (int)(money *term +((money*term)*afterTax));      //�ܸ�

	     Returnd_AMT = Integer.toString(total);
		return Returnd_AMT;
		
	}
//	//�����޼ҵ帮�ϰ� String  ���� ��ǰ�� ���� ���ٺ��� �ϴ� ���� ������ �س��� 
//	public String bok(String investment ,String interest){
//		String Returnb_AMT =null;
//		System.out.println("���� ����");
//		int money = Integer.parseInt(investment);
//	    double before_Tax_interest = Double.parseDouble(interest);
//	    int term = 12;
//	    double inter = before_Tax_interest/12/100;               // ���� ������
//	    double bokri = money*(1+inter)*(Math.pow(1+inter, term)-1)/inter; 
//	    DecimalFormat f  = new DecimalFormat("###############.##");
//	    String decimal = f.format(bokri);
//	    Returnb_AMT = decimal;
//		return Returnb_AMT;
//		
//	}
	
	 
	
	
	
}

package Team1;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Cul_date {
	
	int total_month;
	public Cul_date(int total_month){
		//���� ��¥
		this.total_month = total_month;
	}
	
	public String returndate(){
		
		  int addMonth = this.total_month;//�߰� ������
		  
		  String result = null;
		  SimpleDateFormat sdformat = new SimpleDateFormat("yyyy �� MM ��"); 
		  Date date = new Date();
		  
		  // Ķ������������
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.add(Calendar.MONTH, addMonth); //������ ���ϱ�
		  result = sdformat.format(cal.getTime());  
		  System.out.println(result);
		
		return result;
		
	}
	
	
}

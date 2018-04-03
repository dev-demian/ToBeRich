package Team1;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Cul_date {
	
	int total_month;
	public Cul_date(int total_month){
		//현재 날짜
		this.total_month = total_month;
	}
	
	public String returndate(){
		
		  int addMonth = this.total_month;//추가 개월수
		  
		  String result = null;
		  SimpleDateFormat sdformat = new SimpleDateFormat("yyyy 년 MM 월"); 
		  Date date = new Date();
		  
		  // 캘린더가져오기
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.add(Calendar.MONTH, addMonth); //개월수 더하기
		  result = sdformat.format(cal.getTime());  
		  System.out.println(result);
		
		return result;
		
	}
	
	
}

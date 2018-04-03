package Team1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Savings_Calculator {
	public Object[][] val ;
	public Object[][] sortval;
	public int total_money;
	public static final int Goal_money  = 100000000;			//1억만들기니까 1억
	
	
	
	public Savings_Calculator(Object[][] val,int total_money) {
		this.val = val;
		this.total_money = total_money;
	}
	//2차원 배열을 이율크기순으로 정렬 (해당 배열의 6번 7번 인덱스를 Double로 형변환후 저장 )
	public void sort(){
		
		sortval = this.val;
		
		for(int i =0;i<sortval.length;i++){
			for(int j=0;j<sortval[i].length;j++){
				System.out.println(sortval[i][j]+"\t");
			}
			System.out.println();
		}
		//선추출 
		Object[] column = new Object[sortval.length];
		for(int i=0;i<column.length;i++){
			column[i] = (Boolean)sortval[i][8]?sortval[i][7]:sortval[i][6];  // true일시 우대금리 false일시 기본금리
			System.out.println("column["+i+"] =" + column[i]);
		}
		//후 정렬
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
		System.out.println(Arrays.toString(column));// 어느 순서로 정렬됬는지 확인 
		
		
		for(int i =0;i<sortval.length;i++){
			for(int j=0;j<sortval[i].length;j++){
				System.out.println(sortval[i][j]+"\t");
			}
			System.out.println();
		}
		
		
	}
	// 해당 정렬된값의 최대한도대로 월 totalmoney를 나눠서 실제로 사용될 값만 따로 만듬 월 납입금으로 사용가능한 적금만 따로 배열을 구성 
	public String[] Calculate(){//2가지 모드로 구동됨  x값 구하기 2가지경우 (선택한 상품들의 최대 납입가능금액의 총합이 월 납입금보다 큰경우(while사용) vs 작은경우(잉여 돈은 자동 저축으로))
		
		String[] result = new String[2];//0:결과값 사용된 상품들을 String으로 묶어서 저장 1: 해당 상품으로 걸린 년수 
		int money_month = total_money;
		int selected_max =0;
		for(int i=0;i<sortval.length;i++){
			selected_max += Integer.parseInt(sortval[i][4].toString());
		}
		
		
		
		if(selected_max>money_month){// 선택한 상품의 총 납입금이 월적금예정 금액보다 크면 이율이 비교적 제일 적은 적금 상품은 적금을 못넣기 떄문에 
			System.out.println("선택한 상품들의 최대 적립 가능 금액이 월납입예정금보다  더 커서 이율이 비교적 낮은 상품은 최대한도로 적금하지 못합니다.");
			int goal = Goal_money;
			int x_Idx =0;
			int index=0;
			
			int left_money =0; //마지막 상품이 사용할 금액(적금상한을 못 달성한금액)
			//x인덱스와 left_money 구하기
			while(true){// 사용한 
				left_money= money_month;
				money_month -= Integer.parseInt(sortval[index][4].toString());
				index++;
				
				if(money_month<0){
					x_Idx =index;
					break;
				}
			}
			//use val에 해당하는 적금까지 넣기 
			String[][] use_val = new String[x_Idx][4];//사용될 val y- 0: 적금명 1: 적금 금액 (최대한도)마지막엔 left_money로 나머지금액 2: 적용 이율 3:만기환급금액
			
			for(int i=0;i<use_val.length;i++){//마지막은 Max납입금을 채우지 못함으로 if문으로 나눈다 복리 단리 나눠서 구현은 해놨고 요즘 적금은 단리 상품만 나와서 단리로 적용
				
				if(i<use_val.length-1){
					use_val[i][0] = sortval[i][1].toString();
					use_val[i][1] = sortval[i][4].toString();
					use_val[i][2] = (Boolean)sortval[i][8]?sortval[i][7].toString():sortval[i][6].toString();
					use_val[i][3] = dan(use_val[i][1],use_val[i][2]);
//				use_val[i][3] = sortval[i][5].toString().equals("단리")?dan(use_val[i][1],use_val[i][2]):bok(use_val[i][1],use_val[i][2]);//메소드 쓰기 ;
				}else{
					use_val[i][0] = sortval[i][1].toString();
					use_val[i][1] = Integer.toString(left_money);
					use_val[i][2] = (Boolean)sortval[i][8]?sortval[i][7].toString():sortval[i][6].toString();
					use_val[i][3] = dan(use_val[i][1],use_val[i][2]);
//				use_val[i][3] = sortval[i][5].toString().equals("단리")?dan(use_val[i][1],use_val[i][2]):bok(use_val[i][1],use_val[i][2]);//메소드 쓰기 ;
				}
				
			}
			
			for(int i=0;i<use_val.length;i++){//출력
				for(int j=0;j<use_val[i].length;j++){
					System.out.println(use_val[i][j]);
				}
			}
			//1억 - 시작 
			int CNT =0; //CNT = 년    
			
			while(true){
				for(int i=0;i<use_val.length;i++){//출력
					goal -= Integer.parseInt(use_val[i][3]);
				}
				CNT++;
				if(goal<0){
					System.out.println("총 "+CNT+"년이 걸립니다.");
					break;
				}
			}
			for(int i=0;i<use_val.length;i++){
				result[0] += use_val[i][0]+",";
			}
			result[1]= Integer.toString(CNT);
			
			System.out.println(result[0] +"상품으로 ");
			System.out.println(result[1] +"년 동안 적금해야 됩니다 ");
			
			
		}else{// 선택한 상품의 총 납입금이 월적금예정 금액보다 작으면 (sortval을 따로 편집할 필요가 없음)
			System.out.println("월납입금이 더 커서 잉여 금액이 일반 저금으로 변동됩니다");
			int goal = Goal_money;			
			int justsave = 0;//그냥 저금할 돈 
			
			for(int i=0;i<sortval.length;i++){
				money_month -= Integer.parseInt(sortval[i][4].toString());
			}
			justsave = money_month; 
			System.out.println("일반 저금금액 :"+justsave+"원");
			String[][] use_val = new String[sortval.length][4];//사용될 값 y- 0: 적금명 1: 적금 금액 (최대한도)마지막엔 left_money로 나머지금액 2: 적용 이율 3:만기환급금액
			
			for(int i=0;i<use_val.length;i++){//위와 다르게 모든 컬럼의 상품이 Max적금가능금액으로 계산되야 하기떄문에 if문사용할 필요가 없다
						
					use_val[i][0] = sortval[i][1].toString();
					use_val[i][1] = sortval[i][4].toString();
					use_val[i][2] = (Boolean)sortval[i][8]?sortval[i][7].toString():sortval[i][6].toString();
					use_val[i][3] = dan(use_val[i][1],use_val[i][2]);
//				use_val[i][3] = sortval[i][5].toString().equals("단리")?dan(use_val[i][1],use_val[i][2]):bok(use_val[i][1],use_val[i][2]);//메소드 쓰기 ;

			}
			
			for(int i=0;i<use_val.length;i++){ //출력해보기
				for(int j=0;j<use_val[i].length;j++){
					System.out.println(use_val[i][j]);
				}
			}
			//1억 - (각 max값 + 일반저금금액)
			
			int CNT =0; //CNT = 년    
			
			while(true){
				for(int i=0;i<use_val.length;i++){//출력
					goal -= Integer.parseInt(use_val[i][3]);
				}
				goal -= justsave*12;  // 기본 저금도 따로 빼줘야 한다 
				
				CNT++;
				if(goal<0){
					System.out.println("총 "+CNT+"년이 걸립니다.");
					break;
				}
			}
			for(int i=0;i<use_val.length;i++){
				result[0] +=  use_val[i][0]+",";
			}
			result[0]+= "+ 월 "+justsave+"원의 저금";
			result[1]= Integer.toString(CNT);
			
			System.out.println(result[0] );
			System.out.println(result[1] +"년 동안 적금해야 됩니다 ");
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		return result; //result[0] = 결과값 사용된 상품 , result[1]=해당 상품으로 걸린 기간,  
		
	}
	
	
	//단리메소드(12개월당 만기 환급금이 )리턴값 String
	public String dan(String investment ,String interest ){
		String Returnd_AMT =null;
		System.out.println("단리 적용");
		 int money = Integer.parseInt(investment);
	     double before_Tax_interest = Double.parseDouble(interest);
	     int term = 12;
	     double realInterest = before_Tax_interest / 100*(term+1) / 24;   //실제 이자율
	     double afterTax = realInterest *(1-0.154);   //세후 이자율
	     int total = (int)(money *term +((money*term)*afterTax));      //단리

	     Returnd_AMT = Integer.toString(total);
		return Returnd_AMT;
		
	}
//	//복리메소드리턴값 String  복리 상품이 거의 없다보니 일단 보류 구현은 해놓음 
//	public String bok(String investment ,String interest){
//		String Returnb_AMT =null;
//		System.out.println("복리 적용");
//		int money = Integer.parseInt(investment);
//	    double before_Tax_interest = Double.parseDouble(interest);
//	    int term = 12;
//	    double inter = before_Tax_interest/12/100;               // 복리 이자율
//	    double bokri = money*(1+inter)*(Math.pow(1+inter, term)-1)/inter; 
//	    DecimalFormat f  = new DecimalFormat("###############.##");
//	    String decimal = f.format(bokri);
//	    Returnb_AMT = decimal;
//		return Returnb_AMT;
//		
//	}
	
	 
	
	
	
}

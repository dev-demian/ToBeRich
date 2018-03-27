package Team1;


public class Project_Main {
   private static boolean islogin = false;   //true : 로그인창 생략하고 바로 메인  false : 로그인창 호출
   
   public static void main(String[] args) {

      
      if(islogin == false) {
         LogIn window = new LogIn();
      }else
      {
         Main_Form window = new Main_Form();//매개변수로 로그인 정보 넘겨주는거 구형해야함 
         //Main_Window window = new Main_Window(사용자,이름,.....);
      }


   }
}
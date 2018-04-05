package Team1;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Pakage implements Serializable{//구분자 string(무조건 user,savings,board,images중 1) / Object map or img
	private String type;
	private Map<String,User> user_map;
	private Map<String,Savings> savings_map;
	private Map<Integer,List<Object>> board_map;
	private BufferedImage img;
	
	
		
	public Pakage(String type,Object map_or_img){//
		this.setType(type);
		if(type.equals("user")){
			this.setUser_map((Map<String,User>)map_or_img);
		}else if(type.equals("savings")){
			this.setSavings_map((Map<String,Savings>)map_or_img);
		}else if(type.equals("board")){
			this.setBoard_map((Map<Integer,List<Object>>)map_or_img);
		}else{//이미지
			this.setImg((BufferedImage)map_or_img);
		}
		
		
		
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Map<String,Savings> getSavings_map() {
		return savings_map;
	}



	public void setSavings_map(Map<String,Savings> savings_map) {
		this.savings_map = savings_map;
	}



	public Map<String,User> getUser_map() {
		return user_map;
	}



	public void setUser_map(Map<String,User> user_map) {
		this.user_map = user_map;
	}



	public BufferedImage getImg() {
		return img;
	}



	public void setImg(BufferedImage img) {
		this.img = img;
	}



	public Map<Integer,List<Object>> getBoard_map() {
		return board_map;
	}



	public void setBoard_map(Map<Integer,List<Object>> board_map) {
		this.board_map = board_map;
	}
	
	
}

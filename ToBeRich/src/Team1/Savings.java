package Team1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Savings implements Serializable{

	private String name;//적금상품명
	private String bank;//은행명
	private String term;//적금기간(12개월)
	private String maxsave;//최대 납입가능 금액  (없을시 0)
	private String interests_calculation; //이자 계산 방식 
	private String basic_interest;//기본금리
	private String upgrade_interest;//우대금리
	private String upgrade_rate;//우대 조건 (월납입금이 upgrade_rate 보다 커야한다 )
	
	
	
	
	public Savings(String name, String bank, String term, String maxsave, String interests_calculation, String basic_interest,
			String upgrade_interest, String upgrade_rate) {
		super();
		this.setName(name);
		this.setBank(bank);
		this.setTerm(term);
		this.setMaxsave(maxsave);
		this.setInterests_calculation(interests_calculation);
		this.setBasic_interest(basic_interest);
		this.setUpgrade_interest(upgrade_interest);
		this.setUpgrade_rate(upgrade_rate);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getMaxsave() {
		return maxsave;
	}
	public void setMaxsave(String maxsave) {
		this.maxsave = maxsave;
	}
	public String getInterests_calculation() {
		return interests_calculation;
	}
	public void setInterests_calculation(String interests_calculation) {
		this.interests_calculation = interests_calculation;
	}
	public String getBasic_interest() {
		return basic_interest;
	}
	public void setBasic_interest(String basic_interest) {
		this.basic_interest = basic_interest;
	}
	public String getUpgrade_interest() {
		return upgrade_interest;
	}
	public void setUpgrade_interest(String upgrade_interest) {
		this.upgrade_interest = upgrade_interest;
	}
	public String getUpgrade_rate() {
		return upgrade_rate;
	}
	public void setUpgrade_rate(String upgrade_rate) {
		this.upgrade_rate = upgrade_rate;
	}
	
	public List<String> getList(){
		List<String> list = new ArrayList<String>();
		list.add(name);
		list.add(bank);
		list.add(term);
		list.add(maxsave);
		list.add(interests_calculation);
		list.add(basic_interest);
		list.add(upgrade_interest);
		
		return list;
		
	}
	public String print_member(){
		String member = "적금명:"+this.getName()+"은행명:"+this.getBank()+"적금기간:"+this.getTerm()+"최대납입금:"+this.getMaxsave()+"이자계산 방식:"+this.getInterests_calculation();
		System.out.println(member);
		return member;
	}
	

}

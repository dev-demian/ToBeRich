package Team1;

public class Savings {

	private String name;//���ݸ�
	private String bank;//�����
	private int term;//���ݱⰣ(12����)
	private int maxsave;//�ִ� ���԰��� �ݾ�  (������ 0)
	private boolean interests_calculation; //���� ��� ��� (true:�ܸ� , false:����) 
	private int basic_interest;//�⺻�ݸ�
	private int upgrade_interest;//���ݸ�
	private String upgrade_rate;//��� ���� (�����Ա��� upgrade_rate ���� Ŀ���Ѵ� )
	
	
	
	
	public Savings(String name, String bank, int term, int maxsave, boolean interests_calculation, int basic_interest,
			int upgrade_interest, String upgrade_rate) {
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
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getMaxsave() {
		return maxsave;
	}
	public void setMaxsave(int maxsave) {
		this.maxsave = maxsave;
	}
	public boolean isInterests_calculation() {
		return interests_calculation;
	}
	public void setInterests_calculation(boolean interests_calculation) {
		this.interests_calculation = interests_calculation;
	}
	public int getBasic_interest() {
		return basic_interest;
	}
	public void setBasic_interest(int basic_interest) {
		this.basic_interest = basic_interest;
	}
	public int getUpgrade_interest() {
		return upgrade_interest;
	}
	public void setUpgrade_interest(int upgrade_interest) {
		this.upgrade_interest = upgrade_interest;
	}
	public String getUpgrade_rate() {
		return upgrade_rate;
	}
	public void setUpgrade_rate(String upgrade_rate) {
		this.upgrade_rate = upgrade_rate;
	}
	
	
	

}

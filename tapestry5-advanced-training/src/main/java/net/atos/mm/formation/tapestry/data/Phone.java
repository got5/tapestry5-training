package net.atos.mm.formation.tapestry.data;

public class Phone {
	
	private String number;
	
	private PhoneType type;
	
	public Phone() {
		super();
	}
	
	public Phone(String number, PhoneType type) {
		super();
		this.number = number;
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}
	
	
}

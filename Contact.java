package model;

public class Contact {
	private int id;
	private String name;
	private Long phone;
	
	public Contact(int id,String name,Long phone) {
		this.id=id;
		this.name=name;
		this.phone=phone;
	}
	public Contact(String name,Long phone) {
		
		this.name=name;
		this.phone=phone;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getPhone() {
		return phone;
	}
	
}

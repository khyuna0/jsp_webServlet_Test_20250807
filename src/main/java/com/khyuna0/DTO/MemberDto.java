package com.khyuna0.DTO;

public class MemberDto {
	private String id;
	private String mname;
	private int mage;
	private String mdate;
	
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String id, String mname, int mage, String mdate) {
		super();
		this.id = id;
		this.mname = mname;
		this.mage = mage;
		this.mdate = mdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getMage() {
		return mage;
	}

	public void setMage(int mage) {
		this.mage = mage;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	
	
}

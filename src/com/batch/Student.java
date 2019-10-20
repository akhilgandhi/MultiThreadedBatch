package com.batch;

public class Student {

	private String rollno;
	private String name;
	private char section;
	private String dept;
	private String address;
	private boolean isHostel;
	private int batchYear;
	
	public Student(String rollno, String name, char section, String dept, String address, boolean isHostel,
			int batchYear) {
		super();
		this.rollno = rollno;
		this.name = name;
		this.section = section;
		this.dept = dept;
		this.address = address;
		this.isHostel = isHostel;
		this.batchYear = batchYear;
	}
	
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSection() {
		return section;
	}
	public void setSection(char section) {
		this.section = section;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isHostel() {
		return isHostel;
	}
	public void setHostel(boolean isHostel) {
		this.isHostel = isHostel;
	}
	public int getBatchYear() {
		return batchYear;
	}
	public void setBatchYear(int batchYear) {
		this.batchYear = batchYear;
	}

	@Override
	public String toString() {
		return "rollno=" + rollno + ", name=" + name + ", section=" + section + ", dept=" + dept + ", address="
				+ address + ", isHostel=" + isHostel + ", batchYear=" + batchYear + "\n";
	}
	
}

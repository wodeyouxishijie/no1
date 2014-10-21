package com.doorcii.ad.utils.export;

import java.util.Date;

public class Student {
	
	private String stId;
	
	private String name;
	
	private int age;
	
	private boolean sex;
	
	private Date birthday;
	

	public Student(String sId, String name, int age, boolean sex, Date birthday) {
		this.stId = sId;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.birthday = birthday;
	}

	public String getStId() {
		return stId;
	}


	public void setStId(String stId) {
		this.stId = stId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}

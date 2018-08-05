package pojo;

import java.io.Serializable;

public class User implements Serializable{
	private Integer id;
	private String uname;
	private int age;
	
	public User() {
		System.out.println("user constructed....");
	}

	public User(Integer id, String uname, int age) {
		super();
		this.id = id;
		this.uname = uname;
		this.age = age;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2;

/**
 *
 * @author P.T.B
 */
public class User {
	private String name;
	private String phoneNumber;
	private Status st;
	public User(String name, String phoneNumber, Status st) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.st = st;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Status getSt() {
		return st;
	}
	public void setSt(Status st) {
		this.st = st;
	}
	public User(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.st=Status.INIT;
	}
	public User() {
		this.st=Status.INIT;
	}
	
}

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
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Process {
	//check key user entered
	public boolean processAuthen(Message m) {
		String keyValue="topica";
		for(Content c: m.getListTVL()) {
			if(c.getTags()==0) {
				if(c.getValue().equalsIgnoreCase(keyValue)) {
					return true;
				}
			}
		}
		return false;
	}
	//create message from server to response 
	public void createResponseMessage(short cmdCode,DataOutputStream dos,Message request,String resultCode) {
		Convert c=new Convert();
		ArrayList<Content> list=new ArrayList<Content>();
		for(Content content: request.getListTVL()) {
			if(content.getTags()==1) {
				list.add(content);
			}
		}
		list.add(new Content((short) 3, resultCode));
		Message response=new Message(cmdCode,list);
		byte[] data;
		try {
			data = c.convertMessageToByteArray(response); //convert message to byte array to send
	        dos.writeInt(data.length);
	        dos.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//create message from server to response if user request username
	public void createResponseMessage(short cmdCode,DataOutputStream dos,Message request,String resultCode,String userName) {
		Convert c=new Convert();
		ArrayList<Content> list=new ArrayList<Content>();
		for(Content content: request.getListTVL()) {
			if(content.getTags()==1) {
				list.add(content);
			}
		}
		list.add(new Content((short) 3, resultCode));
		list.add(new Content((short) 2, userName));
		Message response=new Message(cmdCode,list);
		byte[] data;
		try {
			data = c.convertMessageToByteArray(response);//convert message to byte array to send
	        dos.writeInt(data.length);
	        dos.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//create message error from server to response
	public void createErrMessage(DataOutputStream dos) {
		Convert c=new Convert();
		Content con=new Content((short)3,"NA");
		Message m= new Message((short)4,con);
		byte[] data;
		try {
			data = c.convertMessageToByteArray(m);
	        dos.writeInt(data.length);
	        dos.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	//add property user
	public User addUser(Message m, User user) {
		for(Content cont: m.getListTVL()) {
			if(cont.getTags()==2) {
				user.setName(cont.getValue());
			}
			if(cont.getTags()==1) {
				user.setPhoneNumber(cont.getValue());
			}
		}
		return user;
	}

}

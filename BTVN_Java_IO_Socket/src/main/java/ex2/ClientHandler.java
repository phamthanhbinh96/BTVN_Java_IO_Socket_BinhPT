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

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;



public class ClientHandler extends Thread{
	private DataInputStream dis;
	private DataOutputStream dos;
	private Socket socket;
	private Status st;
	private User user;
	public ClientHandler(DataInputStream dis, DataOutputStream dos, Socket socket) {
		super();
		this.dis = dis;
		this.dos = dos;
		this.socket = socket;
		this.st=Status.INIT;
		this.user=new User();
	}
	@Override
	public void run() {
		while(true) {
			try {
				Process process=new Process();
				Convert c=new Convert();
				String regex="098[2-9][0-9]{6}";
				int length=dis.readInt();
			    byte[] messageByte = new byte[length];
			    dis.readFully(messageByte, 0, messageByte.length);
			    
			    Message m = c.convertByteArrayToMessage(messageByte);
			    boolean match=false;
			    Short cmdCode=m.getCmdCode();
			    for(Content cont: m.getListTVL()) {
			    	if(cont.getTags()==1) {
			    		if(cont.getValue().matches(regex)) {
			    			match=true;
			    			break;
			    		}else {
			    			process.createErrMessage(dos);
			    		}
			    	}
			    }
			    if(match) {
				    switch(cmdCode) {
				    case 0: {
			    	   	if(process.processAuthen(m)) {
			    	   		process.createResponseMessage((short)0,dos, m,"OK");
					    	this.st=Status.READY;
				    	}else {
				    		process.createErrMessage(dos);
				    	}
			    	   	break;
					 }
				    case 1: {
				    	if(this.st==Status.READY) {
				    		this.user=process.addUser(m, this.user);
				    		process.createResponseMessage((short)1,dos, m, "OK");
					    	
					    }else {
					    	process.createResponseMessage((short)1,dos, m, "NOK");
					    }
				    	break;
				    }
				    case 2: {
				    	if(this.st==Status.READY) {
				    		process.createResponseMessage((short)2,dos, m, "OK");
				    		this.st=Status.SELECT;
					    }else {
					    	process.createResponseMessage((short)2,dos, m, "NOK");
					    }
				    	break;
				    }
				    case 3: {
				    	if(this.st==Status.SELECT) {
				    		process.createResponseMessage((short)3,dos, m, "OK", user.getName());
				    	}else {
				    		process.createResponseMessage((short)3,dos, m, "NOK");
				    	}
				    	break;
				    }
				    default: break;
				    }
			    }
			    dos.flush();
			    
			} catch (EOFException e) {
				System.out.println("EOF occurs");
			} catch (SocketException e) {
                if (e.toString().contains("Socket closed") || e.toString().contains("Connection reset")
                        || e.toString().contains("Broken pipe")) {
                } else {
                    stop();
                }	
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
		}

	}

}

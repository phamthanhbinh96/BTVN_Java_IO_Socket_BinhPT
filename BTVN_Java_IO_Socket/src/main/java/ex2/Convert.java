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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Convert {
	//convert tag to short to save in data
	public short convertTagToShort(String s) {
		switch(s) {
		case "Key": return 0;
		case "PhoneNumber" : return 1;
		case "Name" : return 2;
		case "ResultCode" : return 3;
		default : return -1;
		}
	}
	//convert short from data to tag to show
	public String convertShortToTag(Short s) {
		switch(s) {
		case 0: return "Key";
		case 1: return "PhoneNumber"; 
		case 2: return "Name";
		case 3: return "ResultCode";
		default: return "Error";
		}
	}
	//convert cmdCode to short to save in data
	public short convertCmdCodeToShort(String s) {
		switch(s) {
		case "AUTHEN": return 0;
		case "INSERT" : return 1;
		case "COMMIT" : return 2;
		case "SELECT" : return 3;
		default : return 4;
		}
	}
	//convert short from data to cmdCode to show
	public String convertShortToCmdCode(Short s) {
		switch(s) {
		case 0: return "AUTHEN";
		case 1: return "INSERT";
		case 2: return "COMMIT";
		case 3: return "SELECT";
		default : return "ERROR";

		}
	}
	//convert message to byte array
	public byte[] convertMessageToByteArray(Message m) throws IOException {
		ByteArrayOutputStream bos= new ByteArrayOutputStream();
		bos.write(ByteBuffer.allocate(4).putInt(m.getLength()).array());
		bos.write(ByteBuffer.allocate(2).putShort(m.getCmdCode()).array());
		bos.write(ByteBuffer.allocate(2).putShort(m.getVersion()).array());
		for(Content c: m.getListTVL()) {
			bos.write(ByteBuffer.allocate(2).putShort(c.getTags()).array());
			bos.write(ByteBuffer.allocate(2).putShort(c.getLength()).array());
			bos.write(c.getValue().getBytes(StandardCharsets.UTF_8));
		}
		return bos.toByteArray();
	}
	public int convertByteArrayToInt(byte[] b,int off) {
		return ByteBuffer.wrap(Arrays.copyOfRange(b, off,off+4)).getInt();
	}
	public short convertByteArrayToShort(byte[] b,int off) {
		return ByteBuffer.wrap(Arrays.copyOfRange(b, off,off+2)).getShort();
	}
	public String convertByteArrayToString(byte[] b,int off,int len) {
		
		return new String(Arrays.copyOfRange(b, off, off+len),StandardCharsets.UTF_8);
	}
	public Message convertByteArrayToMessage(byte[] messageByte) {
		Convert c= new Convert();
		List<Content> list=new ArrayList<Content>();
		int lengthOfMessage=c.convertByteArrayToInt(messageByte, 0);
	    short cmdCode=c.convertByteArrayToShort(messageByte, 4);
	    short version=c.convertByteArrayToShort(messageByte, 6);
	    int currentByte=8;
	    while(currentByte<lengthOfMessage) {
	    	short tag=c.convertByteArrayToShort(messageByte, currentByte);
	    	short len=c.convertByteArrayToShort(messageByte, currentByte+2);
	    	String value=c.convertByteArrayToString(messageByte, currentByte+4, len);
	    	currentByte+=4+len;
	    	list.add(new Content(tag,value));
	    }
	    return new Message(lengthOfMessage,cmdCode,version,list);
	}
}

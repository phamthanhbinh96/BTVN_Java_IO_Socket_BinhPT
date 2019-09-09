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
public class Content {
	private short tags;
	private short length;
	private String value;
	public short getTags() {
		return tags;
	}
	public void setTags(short tags) {
		this.tags = tags;
	}
	public short getLength() {
		return length;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Content(short tags, String value) {
		super();
		this.tags = tags;
		this.length =  (short) value.length();
		this.value = value;
	}
	@Override
	public String toString() {
		return "Content [tags=" + tags + ", length=" + length + ", value=" + value + "]";
	}
	
	
	
}

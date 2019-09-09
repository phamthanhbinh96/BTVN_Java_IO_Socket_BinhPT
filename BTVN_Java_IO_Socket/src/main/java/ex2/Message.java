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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable{
   
    private static final long serialVersionUID = 1L;
    private short cmdCode;
    private List<Content> listTVL;
    private int length;
    private short version=0;
    public Message(short cmdCode, List<Content> listTVL) {
        super();
        this.cmdCode = cmdCode;
        this.listTVL = listTVL;
        int len=8;
        for(Content c: listTVL) {
            len+=4+c.getLength();
        }
        this.length=len;
    }
    public Message(short cmdCode, Content c) {
        super();
        List<Content> list= new ArrayList<Content>();
        list.add(c);
        this.cmdCode = cmdCode;
        this.listTVL = list;
        this.length=8+c.getLength();
    }

    public Message( int length, short cmdCode, short version, List<Content> listTVL) {
        super();
        this.cmdCode = cmdCode;
        this.listTVL = listTVL;
        this.length = length;
        this.version = version;
    }
    public short getVersion() {
        return version;
    }

    public int getLength() {
        return length;
    }
    public short getCmdCode() {
        return cmdCode;
    }
    public void setCmdCode(short cmdCode) {
        this.cmdCode = cmdCode;
    }
    public List<Content> getListTVL() {
        return listTVL;
    }
    public void setListTVL(List<Content> listTVL) {
        this.listTVL = listTVL;
    }
	 
}

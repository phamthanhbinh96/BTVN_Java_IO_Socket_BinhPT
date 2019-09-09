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
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args)  {
    	Convert c=new Convert();
        Socket socket;
        int port=9669;
        try {
            socket = new Socket("localhost", port);
            System.out.println("Connected!");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            Scanner input = new Scanner(System.in);
            boolean ans=true;
            while(ans) {
                System.out.println("---Request---");
                List<Content> tvl=new ArrayList<Content>();
                String str= input.nextLine();
                String[] temp=str.split(" ");
                short cmdCode=c.convertCmdCodeToShort(temp[0]);
                if(cmdCode==4) {
                    System.out.println("Message Format is incorrect. (cmdCode+[Tag + value])");
                    continue;
                }
                for(int i=1;i<temp.length;i+=2) {

                    tvl.add(new Content(c.convertTagToShort(temp[i]),temp[i+1]));
                }
                Message request=new Message(cmdCode,tvl);

                byte[] data=c.convertMessageToByteArray(request);

                dos.writeInt(data.length);
                dos.write(data);
                System.out.println("---Response---");
                int length=dis.readInt();
                byte[] messageByte = new byte[length];
                dis.readFully(messageByte, 0, messageByte.length);
                Message response=c.convertByteArrayToMessage(messageByte); 
                System.out.print(c.convertShortToCmdCode(response.getCmdCode())+" ");
                for(Content cont: response.getListTVL()) {
                    System.out.print(c.convertShortToTag(cont.getTags())+" " + cont.getValue()+"\n");
                }
                System.out.println("Do you want to continue ?(y/n)");
                String a=input.next();
                input.nextLine();
                if(a.equalsIgnoreCase("y")) {
                        ans=true;
                }else {
                        ans=false;
                }

            }
            input.close();
            dos.close();
            dis.close();
            socket.close();
        } catch (UnknownHostException e) {
                System.out.println("Host is invalid");
                e.printStackTrace();
        } catch (IOException e) {
                System.out.println("Accept failed!");
                e.printStackTrace();
        }      
    }
}

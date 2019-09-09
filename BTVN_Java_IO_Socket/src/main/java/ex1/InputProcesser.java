/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

/**
 *
 * @author P.T.B
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputProcesser {
	
    
    private static final String directory  = System.getProperty("user.dir");
    private static final String fileNameInput = directory  + "\\src\\main\\java\\file\\input1.txt";

    public List<Product> readInput(){
        List<Product> listP=new ArrayList<Product>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        String line=null;

        try {
            FileReader fileReader = new FileReader(fileNameInput);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            try {
                //read each line in file input1.txt
                while((line = bufferedReader.readLine()) != null) {
                    String[] content=line.split(",");
                    dateFormat.setLenient(false); 
                    //check date valid
                    Date date=null;
                    int year=Integer.parseInt(content[4]);
                    try {
                        date=dateFormat.parse(content[3]);
                    }
                    catch (ParseException e) {
                        System.out.println("Invalid date");
                        continue;
                    }
                    //add to list product
                    listP.add(new Product(content[0],content[1],content[2],date,year));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }   
        } catch (FileNotFoundException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listP;
    }
    //method to standardize string
    public String standardizeString(String str) {
        str=str.trim();
        str=str.replaceAll("\\s+" , " ");//remove all multi space
        String temp[] = str.split(" ");
        str = ""; 
        //convert the first character of string to uppercase and other is lowercase 
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1).toLowerCase();
            if (i < temp.length - 1) 
            str += " ";
        }
        return str;
    }
    //method to find popular substring
    public List<String> findPopularSubstring(String str) {
        String temp[] = str.split(" ");
        int count[] = new int[temp.length];
        for(int i=0;i<temp.length;i++) {
            count[i]=0;
            for(int j=0;j<=i;j++) {
                if(temp[j].equals(temp[i])) {
                    count[i]++;
                }
            }
        }
        int max=count[0];
        for(int i=0;i<count.length;i++) {
            if(max<count[i]) {
                max=count[i];
            }
        }
        List<String> listStr=new ArrayList<String>();
        for(int i=0;i<count.length;i++) {
            if(count[i]==max) {
                listStr.add(temp[i]);
            }
        }
        return listStr;
    }
	
}
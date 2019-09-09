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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Ex1_3 {

	public static void main(String[] args) {
            // TODO Auto-generated method stub
            InputProcesser input=new InputProcesser();
            OutputProcesser output=new OutputProcesser();
            List<Product> listP=input.readInput();
            SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
            Date start=new Date();
            Date end=new Date();
            try {
                start= dateFormat.parse("31/10/2018");
                end=dateFormat.parse("31/10/2019");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Iterator<Product> itr=listP.iterator();
            //get all product has code contain "TOPICA"
            while(itr.hasNext()) {
                Product p = itr.next();
                if(!p.getCode().contains("TOPICA")||p.getInputDate().after(end)||p.getInputDate().before(start)) {
                itr.remove();

                }
            }
            //process standardize field owner of product
            for(Product p : listP) {
                p.setOwner(input.standardizeString(p.getOwner()));
            }
            //sort list product by inputDate
            //if two product has same inputDate, sort by warrantyYear
            Collections.sort(listP, new Comparator<Product>() {
                public int compare(Product p1, Product p2) {
                    int year1=p1.getWarrantyYear();
                    int year2=p2.getWarrantyYear();
                    Date date1=p1.getInputDate();
                    Date date2=p2.getInputDate();
                    if(date1.equals(date2)) {
                        return year1>year2 ? 1 : -1;
                    }
                    else {
                        return date1.after(date2) ? -1 : 1;
                    }
                }
            });
            //write data to output1.txt
            output.writeOutputFile(listP);

	}

}
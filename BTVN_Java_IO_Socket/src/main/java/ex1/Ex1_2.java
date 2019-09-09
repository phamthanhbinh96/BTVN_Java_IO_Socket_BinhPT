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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ex1_2 {

    public static void main(String[] args) {
        InputProcesser input=new InputProcesser();
        OutputProcesser output=new OutputProcesser();
        List<Product> listP=input.readInput();
        //process standardize field owner of product
        for(Product p : listP) {
            p.setOwner(input.standardizeString(p.getOwner()));
        }
        //sort by warrantyYear
        Collections.sort(listP, new Comparator<Product>() { 
            public int compare(Product p1, Product p2) {
                return p1.getWarrantyYear() > p2.getWarrantyYear() ? -1 : 1;
            }
        });
        //write data  to output1.txt
        output.writeOutputFile(listP);


    }
}

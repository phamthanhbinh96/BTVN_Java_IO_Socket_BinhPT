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

public class Ex1_1 {
    private static final String directory  = System.getProperty("user.dir");
    private static final String output1 = directory  + "\\src\\main\\java\\file\\output1.txt";
    public static void main(String[] args) {
        InputProcesser input=new InputProcesser();
        OutputProcesser output=new OutputProcesser();
        List<Product> listP=input.readInput();
        //sort list product by warrantyYear
        Collections.sort(listP, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p1.getWarrantyYear() > p2.getWarrantyYear() ? 1 : -1;
            }
        });
        //write to output1.txt
        output.writeOutputFile(listP);

    }
}
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
import java.util.List;

public class Ex1_4 {
	public static void main(String[] args) {
		InputProcesser input=new InputProcesser();
		OutputProcesser output=new OutputProcesser();
		List<Product> listP=input.readInput();
		String str="";
		//process standardize field owner of product
		for(Product p : listP) {
                    p.setOwner(input.standardizeString(p.getOwner()));
                    //add all field owner of list product to a string
                    str+=p.getOwner()+" ";
		}
		//find name popular
		List<String> listStr=input.findPopularSubstring(str);
		String stringOutput="";
		for(String st : listStr) {
                    stringOutput+=st+"\n";
		}
		//write name popular to output1.txt
		output.writeOutputFile(stringOutput);
	}
}
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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputProcesser {
    private static final String directory  = System.getProperty("user.dir");
    private static final String fileNameOutput = directory  + "\\src\\main\\java\\file\\output1.txt";
    //write list product to file output1.txt
    public void writeOutputFile(List<Product> listP) {
        String fileNameOutput="src/output1.txt";

        try {
            FileWriter writer = new FileWriter(fileNameOutput,true);
            BufferedWriter buffer = new BufferedWriter(writer);
            for(Product p: listP) {
                    buffer.write(p.toString());
                    buffer.newLine();
            }
            buffer.write("###");
            buffer.newLine();
            System.out.println("success");
            buffer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // write a tring to file output1.txt
    public void writeOutputFile(String str) {
        String fileNameOutput="src/output1.txt";

        try {
            FileWriter writer = new FileWriter(fileNameOutput,true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(str);
            buffer.newLine();

            buffer.write("###");
            buffer.newLine();
            System.out.println("success");
            buffer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

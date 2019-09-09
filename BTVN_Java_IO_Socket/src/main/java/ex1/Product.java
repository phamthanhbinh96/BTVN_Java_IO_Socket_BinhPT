/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author P.T.B
 */
public class Product {
    private String code;
    private String name;
    private String owner;
    private Date inputDate;
    private int warrantyYear;

    private static SimpleDateFormat sdf = null;
    private static String dateFormat = "dd/MM/yyyy";
    
    public Product(){
	sdf = new SimpleDateFormat(dateFormat);
    }
    //constructor
    public Product(String code, String name, String owner, Date inputDate, int warrantyYear) {
        this.code = code;
        this.name = name;
        this.owner = owner;
        this.inputDate = inputDate;
        this.warrantyYear = warrantyYear;
    }
    
    //getter setter
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getInputDate() {
        return inputDate;
    }
    
    public String getInputDateFormat()
    {
        return sdf.format(inputDate);
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public int getWarrantyYear() {
        return warrantyYear;
    }

    public void setWarrantyYear(int warrantyYear) {
        this.warrantyYear = warrantyYear;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    public static void setSdf(SimpleDateFormat sdf) {
        Product.sdf = sdf;
    }

    public static String getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(String dateFormat) {
        Product.dateFormat = dateFormat;
    }
    
    
    @Override
    public String toString() {
        return "Product{" + "code=" + code + ", name=" + name + ", owner=" + owner + ", inputDate=" + inputDate + ", warrantyYear=" + warrantyYear + '}';
    }

   
    
    
    
    
}

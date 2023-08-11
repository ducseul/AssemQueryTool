/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemquerytool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ducnm62
 */
public class Utils {

    public static boolean isDigit(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception ex) {

        }
        try {
            Long.parseLong(str);
            return true;
        } catch (Exception ex) {

        }
        return false;
    }
    
    public static boolean isDate(String str){
        // Pattern to match the given date format Wed Aug 09 10:06:30 ICT 2023
        String pattern = "EEE MMM dd HH:mm:ss z yyyy";  
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            Date parsedDate = dateFormat.parse(str);
            System.out.println("Parsed Date: " + parsedDate);
            return true;
        } catch (ParseException e) {
        }
        return false;
    }
    
    public static Date parseDate(String str){
        // Pattern to match the given date format Wed Aug 09 10:06:30 ICT 2023
        String pattern = "EEE MMM dd HH:mm:ss z yyyy";  
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            Date parsedDate = dateFormat.parse(str);
            System.out.println("Parsed Date: " + parsedDate);
            return parsedDate;
        } catch (ParseException e) {
        }
        return null;
    } 
}

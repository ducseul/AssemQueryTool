/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.awt.Color;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author bug
 */
public class Constrain {

    public interface MESSAGE{
        String notCompleteSQL = "There are still questionable (?) in query, do you want to matching manually ?";
        String camelWarning = "WARNING: camelCase Reversing could lead to SQL mis-structure!";
        String noQueryWarning = "Nothing to build!";
        String copySuccess = "Output has been copied to Clipboard!";
        String[] listKeyword = {"CREATE", "PRIMARY", "KEY", "INSERT", "VALUES", "INTO", "SELECT", "FROM",
        "ALTER", "ADD", "DISTINCT", "UPDATE", "SET", "DELETE", "TRUNCATE",
        "AS", "ORDER", "BY", "ASC", "DESC", "BETWEEN", "WHERE", "AND", "OR",
        "NOT", "LIMIT", "IS", "NULL", "DROP", "COLUMN", "DATABASE", "TABLE",
        "GROUP", "HAVING", "IN", "ON", "JOIN", "UNION", "ALL", "EXISTS", "LIKE", "CASE", "LEFT", "RIGHT"};
    }    
}

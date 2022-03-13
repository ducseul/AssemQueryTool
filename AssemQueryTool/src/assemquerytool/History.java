/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemquerytool;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Ducnm62
 */
public class History {
    private static HashMap<Date, QueryBlock> history;

    private History() {
    }
    
    public static HashMap<Date, QueryBlock> getInstance(){
        if(history ==null){
            history = new HashMap<>();
        }
        return history;
    }
}

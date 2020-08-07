/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import com.github.vertical_blank.sqlformatter.SqlFormatter;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author bug
 */
public class Utilities {

    public String assembleQuery(String query, ArrayList<String> listParam) {
        String output = query;
        StringTokenizer st = new StringTokenizer(query, "?");
        if (listParam.size() > 1) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            while (st.hasMoreTokens()) {
                sb.append(st.nextToken() + " ");
                sb.append(listParam.get(index) + " ");
                index++;
            }
            output = sb.toString();
        } else {
            output += ";";
        }
//        System.out.println(sb.toString());
        return output;

    }

    public void copyToClipboard(String sql) {
        StringSelection stringSelection = new StringSelection(SqlFormatter.format(sql));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(null, Constrain.MESSAGE.copySuccess);
    }

    public ArrayList<String> analysParam(String param) {
        ArrayList<String> listParam = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(param, "\n");
        while (st.hasMoreTokens()) {
            String current = st.nextToken();
            if (current.contains("Long") || current.contains("Integer")) {
                StringTokenizer st2 = new StringTokenizer(current, " ");
                st2.nextToken();
                st2.nextToken();
                st2.nextToken();
                listParam.add(st2.nextToken());
            } else if (current.contains("Date")) {
                StringTokenizer st2 = new StringTokenizer(current, " ");
                st2.nextToken();
                st2.nextToken();
                st2.nextToken();
                StringBuilder sb = new StringBuilder();
                st2.nextToken();
                String tmp2 = st2.nextToken() + "/" + st2.nextToken();
                st2.nextToken();
                st2.nextToken();
                tmp2 += "/" + st2.nextToken();
                st2 = new StringTokenizer(tmp2, "\"");
                String tmp = "TO_DATE('" + st2.nextToken() + "','MON-DD-YYYY')";
                sb.append(tmp + " ");
                listParam.add(sb.toString());
            } else if (current.contains("Timestamp")) {
                StringTokenizer st2 = new StringTokenizer(current, " ");
                st2.nextToken();
                st2.nextToken();
                st2.nextToken();
                String _hours = st2.nextToken();
                String _minutes = st2.nextToken();

                StringTokenizer hours = new StringTokenizer(_hours, "\"");
                StringTokenizer minutes = new StringTokenizer(_minutes, "\"");
                //TO_DATE('1998-DEC-25 17:30','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN')
                String output = "TO_DATE('" + hours.nextToken() + " "
                        + minutes.nextToken().substring(0, 8) + "', 'YYYY-MM-DD HH24:MI:SS')";
                listParam.add(output);
//                listParam.add("TO_DATE('" + hours.nextToken() + "', 'yyyy-mm-dd')");
            } else {
                StringTokenizer st2 = new StringTokenizer(current, " ");
                st2.nextToken();
                st2.nextToken();
                String tmp = st2.nextToken();
                st2 = new StringTokenizer(tmp, "\"");
                listParam.add("'" + st2.nextToken() + "'");
            }
        }
        listParam.add(";");
        return listParam;
    }

    public int countParam(String hmm) {
        System.out.println(hmm);
        StringTokenizer st = new StringTokenizer(hmm, "?");
        if (hmm.endsWith("?")) {
            return st.countTokens();
        } else {
            return st.countTokens() - 1;
        }
    }
}

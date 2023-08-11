/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemquerytool.GUI;

import assemquerytool.entity.History;
import assemquerytool.entity.QueryBlock;
import assemquerytool.Utils;
import com.github.vertical_blank.sqlformatter.SqlFormatter;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author bug
 */
public class Application extends javax.swing.JFrame {

    private final String BUILD_VERSION = "4.2.2 - Updated 08-08-2023";
    private Boolean historyFlag = false;

    static String[] arr = {"CREATE", "PRIMARY", "KEY", "INSERT", "VALUES", "INTO", "SELECT", "FROM",
        "ALTER", "ADD", "DISTINCT", "UPDATE", "SET", "DELETE", "TRUNCATE",
        "AS", "ORDER", "BY", "ASC", "DESC", "BETWEEN", "WHERE", "AND", "OR",
        "NOT", "LIMIT", "IS", "NULL", "DROP", "COLUMN", "DATABASE", "TABLE",
        "GROUP", "HAVING", "IN", "ON", "JOIN", "UNION", "ALL", "EXISTS", "LIKE", "CASE", "LEFT", "RIGHT"};

    static Set<String> keyword;
    private static HashMap<String, String> lstNamedParameter = null;

    /**
     * Creates new form Application
     */
    public Application() {
        keyword = new HashSet<>(Arrays.asList(arr));
        initComponents();
        txtParam.setName("txtParam");
        txtQuery.setName("txtQuery");
        txtWarning.setVisible(false);
        txtVersion.setText("Build: " + BUILD_VERSION);

        Font font = txtGitRepo.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        txtGitRepo.setFont(font.deriveFont(attributes));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        this.addComponentListener(new ComponentAdapter() { //force only resize able by horizontal

            @Override
            public void componentResized(ComponentEvent e) {
                setSize(new Dimension(1070, getHeight()));
                super.componentResized(e);
            }

        });

        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtParam = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuery = new javax.swing.JTextArea();
        btnBuild = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        isCamelRev = new javax.swing.JCheckBox();
        txtWarning = new javax.swing.JLabel();
        txtVersion = new javax.swing.JLabel();
        btnHistory = new javax.swing.JButton();
        txtGitRepo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AssemQuerryTool @ducnm62");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));

        txtParam.setColumns(20);
        txtParam.setRows(5);
        txtParam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtParamMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtParam);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Query Sentence"));

        txtQuery.setColumns(20);
        txtQuery.setRows(5);
        txtQuery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtQueryMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtQuery);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );

        btnBuild.setText("Build Querry");
        btnBuild.setToolTipText("Design for Intelliji debugging format");
        btnBuild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuildActionPerformed(evt);
            }
        });

        btnClear.setForeground(new java.awt.Color(255, 0, 51));
        btnClear.setText("Clear All");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        isCamelRev.setText("'camelCase' Reversing");
        isCamelRev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isCamelRevActionPerformed(evt);
            }
        });

        txtWarning.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        txtWarning.setForeground(new java.awt.Color(255, 0, 51));
        txtWarning.setText("jLabel1");

        txtVersion.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        txtVersion.setForeground(new java.awt.Color(153, 153, 153));
        txtVersion.setText("jLabel1");

        btnHistory.setText("History");
        btnHistory.setEnabled(false);
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        txtGitRepo.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtGitRepo.setForeground(new java.awt.Color(102, 102, 255));
        txtGitRepo.setText("@Github repo");
        txtGitRepo.setToolTipText("Open to checkin the github repository");
        txtGitRepo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGitRepoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtVersion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(isCamelRev)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuild, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHistory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGitRepo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(104, 104, 104))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuild)
                    .addComponent(btnClear)
                    .addComponent(isCamelRev)
                    .addComponent(txtVersion)
                    .addComponent(btnHistory)
                    .addComponent(txtGitRepo))
                .addGap(5, 5, 5)
                .addComponent(txtWarning, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuildActionPerformed
        // TODO add your handling code here:
        String param = txtParam.getText();
        String query = txtQuery.getText();

        // Pre-processing the params
        if (query.toLowerCase().startsWith("SQLQueryImpl(".toLowerCase())) {
            query = query.substring("SQLQueryImpl(".length(), query.length() - 1);
        }

        if (query.toLowerCase().startsWith("\"")) {
            query = query.substring(1, query.length() - 1);
        }

        if (query == null || query.equals("")) {
            JOptionPane.showMessageDialog(null, "Nothing to build!");
            return;
        }

        // Pre-format the query
        StringTokenizer st = new StringTokenizer(query, " ");
        StringBuilder filter = new StringBuilder();
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            if (isCamelRev.isSelected()) {
                if (!isSqlKeywords(str) && !str.contains(":")) {
                    StringBuilder sb = new StringBuilder(str);
                    for (int i = 1; i < str.length(); i++) {
                        if (Character.isUpperCase(str.charAt(i))
                                && Character.isLowerCase(str.charAt(i - 1))) {
                            sb.insert(i, "_");
                            str = sb.toString();
                        }
                    }
                }
            }
            filter.append(isSqlKeywords(str) ? str.toUpperCase() + " " : str + " ");
        }
        try {
            query = SqlFormatter.format(filter.toString());
        } catch (java.lang.NoClassDefFoundError er) {
            txtWarning.setText("Oops! Please place sql-formatter-1.0.1.jar inside lib folder for formatting feature.");
        }

        // Create history record
        txtQuery.setText(query);
        QueryBlock queryBlock = new QueryBlock()
                .setParams(txtParam.getText())
                .setQuery(query)
                .setTimestamp(new Date());
        History.getInstance().put(queryBlock.getTimestamp(), queryBlock);

        // Assem the params to query
        ArrayList<String> listParam = analysParam(param);
//        listParam.stream().forEach(System.out::println);
        String output = assembleQuery(query, listParam);
        if (lstNamedParameter != null) {
            output = assemNamedParameters(output);
        }

        StringSelection stringSelection = new StringSelection(output);
        try {
            // Format further using sql-formatter-1.0.1.jar
            stringSelection = new StringSelection(SqlFormatter.format(output));
        } catch (java.lang.NoClassDefFoundError er) {
            txtWarning.setText("Oops! Please place sql-formatter-1.0.1.jar inside lib folder for formatting feature.");
            txtWarning.setVisible(true);
        }
        
        // Copy to clipboard
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        if (historyFlag == false) {
            historyFlag = true;
            btnHistory.setEnabled(true);
        }
        JOptionPane.showMessageDialog(null, "Output has been copied to Clipboard!");
    }//GEN-LAST:event_btnBuildActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtParam.setText("");
        txtQuery.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void isCamelRevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isCamelRevActionPerformed
        // TODO add your handling code here:
        if (isCamelRev.isSelected()) {
            txtWarning.setText("WARNING: camelCase Reversing could lead to SQL mis-structure!");
            txtWarning.setVisible(true);
        } else {
            txtWarning.setVisible(false);
        }
    }//GEN-LAST:event_isCamelRevActionPerformed

    /**
     * Paste clipboard into text area user right click on
     */
    private void txtParamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtParamMousePressed
        doPasteClipBoard(evt);
    }//GEN-LAST:event_txtParamMousePressed

    private void txtQueryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtQueryMousePressed
        // TODO add your handling code here:
        doPasteClipBoard(evt);
    }//GEN-LAST:event_txtQueryMousePressed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        // TODO add your handling code here:
        HistorySelector historySelector = new HistorySelector(this, true);
        historySelector.setVisible(true);
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void txtGitRepoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGitRepoMouseClicked
        try {
            // TODO add your handling code here:
            openWebpage(new URL("https://github.com/ducseul/AssemQueryTool.git"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtGitRepoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Application().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuild;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHistory;
    private javax.swing.JCheckBox isCamelRev;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel txtGitRepo;
    private javax.swing.JTextArea txtParam;
    private javax.swing.JTextArea txtQuery;
    private javax.swing.JLabel txtVersion;
    private javax.swing.JLabel txtWarning;
    // End of variables declaration//GEN-END:variables
    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String assembleQuery(String query, ArrayList<String> listParam) {
        String output = query;
        try {
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
                if (!output.endsWith(";")) {
                    output += ";";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        System.out.println(sb.toString());
        return output;

    }

    private static ArrayList<String> analysParam(String param) {
        ArrayList<String> listParam = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(param, "\n");
        while (st.hasMoreTokens()) {
            String current = st.nextToken();
            if (current.contains("Long") || current.contains("Integer") || current.contains("Double")) {
                try {
                    System.out.println("Parsing number token: " + current);
                    StringTokenizer st2 = new StringTokenizer(current, " ");
                    st2.nextToken();
                    st2.nextToken();
                    st2.nextToken();
                    listParam.add(st2.nextToken());
                } catch (Exception ex) {
                    System.out.println("Parsing number token fail: " + current);
                }
            } else if (current.contains("Date")) {
                try {
                    StringTokenizer st2 = new StringTokenizer(current, " ");
                    st2.nextToken();
                    st2.nextToken();
                    st2.nextToken();
                    StringBuilder sb = new StringBuilder();
                    String nextToken = st2.nextToken();
                    if (st2.hasMoreTokens()) {
                        String tmp2 = st2.nextToken() + "/" + st2.nextToken();
                        st2.nextToken();
                        st2.nextToken();
                        tmp2 += "/" + st2.nextToken();
                        st2 = new StringTokenizer(tmp2, "\"");
                        String tmp = st2.nextToken();
                        if (tmp.matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2}")) {
                            tmp = "TO_DATE('" + tmp + "','YYYY-MM-DD')";
                            sb.append(tmp + " ");
                            listParam.add(sb.toString());
                        } else if (tmp.matches("[A-Z][a-z]{2}[/][0-9]{2}[/][0-9]{4}")) {
                            tmp = "TO_DATE('" + tmp + "','Mon/DD/YYYY')";
                            sb.append(tmp + " ");
                            listParam.add(sb.toString());
                        } else {
                            tmp = "TO_DATE('" + st2.nextToken() + "','YYYY-MM-DD')";
                            sb.append(tmp + " ");
                            listParam.add(sb.toString());
                        }
                    } else {
                        if (nextToken.split("\"")[1].matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2}")) {
                            String tmp = "TO_DATE('" + nextToken.split("\"")[1] + "','YYYY-MM-DD')";
                            sb.append(tmp + " ");
                            listParam.add(sb.toString());
                        } else if (nextToken.split("\"")[1].matches("[A-Z][a-z]{2}[/][0-9]{2}[/][0-9]{4}")) {
                            String tmp = "TO_DATE('" + nextToken.split("\"")[1] + "','Mon/DD/YYYY')";
                            sb.append(tmp + " ");
                            listParam.add(sb.toString());
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                String output = "TO_DATE('" + hours.nextToken() + " " + minutes.nextToken().substring(0, 8) + "', 'YYYY-MM-DD HH24:MI:SS')";
                listParam.add(output);
                //listParam.add("TO_DATE('" + hours.nextToken() + "', 'yyyy-mm-dd')");
            } else if (current.contains("Constants")) {
                try {
                    System.out.println("Parsing constant token: " + current);
                    StringTokenizer st2 = new StringTokenizer(current, " ");
                    st2.nextToken();
                    st2.nextToken();
                    st2.nextToken();
                    String output = st2.nextToken();
                    listParam.add("'" + output.substring(1, output.length() - 1) + "'");
                } catch (Exception ex) {
                    System.out.println("Can't parse constant: " + current);
                }
            } else if (current.contains("TypedValue@")) {
                if (lstNamedParameter == null) {
                    lstNamedParameter = new HashMap<>();
                }
                String[] data = current.split("->");
                try {
                    lstNamedParameter.put(data[0].replace("\"", ""), data[1].split("} ")[1].replace("\"", "").trim());
                } catch (Exception ignore) {

                }
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

    private static boolean isSqlKeywords(String param) {
        return keyword.contains(param.toUpperCase().trim());
    }

    private void doPasteClipBoard(MouseEvent evt) {
        try {
            // TODO add your handling code here:
            boolean isRightClick = SwingUtilities.isRightMouseButton(evt);
            if (isRightClick) {
                Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                String clipBoard = c.getData(DataFlavor.stringFlavor) + "";
                String name = ((JTextArea) evt.getSource()).getName();
                if ("txtParam".equals(name)) {
                    txtParam.setText(clipBoard);
                } else if ("txtQuery".equals(name)) {
                    txtQuery.setText(clipBoard);

                }
            }
//            System.out.println("Source: "+((JTextArea)evt.getSource()).getName());
        } catch (UnsupportedFlavorException | IOException ex) {
            Logger.getLogger(Application.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void callBackOnSelected(QueryBlock queryBlock) {
        this.txtParam.setText(queryBlock.getParams());
        this.txtQuery.setText(queryBlock.getQuery());
    }

    private String assemNamedParameters(String output) {
        Set<String> lstSet = lstNamedParameter.keySet();
        for (String keyword : lstSet) {
            String value = lstNamedParameter.get(keyword).trim();
            if (Utils.isDigit(value)) {
                output = output.replaceAll(":" + keyword.trim(), value);
            } else if (Utils.isDate(value)) {
                Date date = Utils.parseDate(value);
                SimpleDateFormat oracleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String formatedDate = oracleDateFormat.format(date);
                String tmp = "TO_DATE('" + formatedDate + "','YYYY/MM/DD HH:MI:SS')";
                output = output.replaceAll(":" + keyword.trim(), tmp);
            } else {
                output = output.replaceAll(":" + keyword.trim(), String.format("'%s'", value));
            }
        }
        lstSet.clear();
        return output;
    }
}

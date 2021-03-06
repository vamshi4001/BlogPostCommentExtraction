/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Algorithm.java
 *
 * Created on Sep 8, 2010, 12:20:54 PM
 */

/**
 *
 * @author Vamshi Krishna
 */

import java.io.IOException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.examples.getTags;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
class ProxyAuthenticator extends Authenticator {
     private String user, password;
     public ProxyAuthenticator(String user, String password) {
         this.user = user;
         this.password = password;
     }
    @Override
     protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(user, password.toCharArray());
     }
 }
public class Algorithm extends java.awt.Frame {

    /** Creates new form Algorithm */
    public Algorithm() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        url = new javax.swing.JTextField();
        submit = new javax.swing.JButton();
        respost_contain = new javax.swing.JScrollPane();
        respost = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        comments = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jLabel1.setText("Enter Url");

        submit.setText("Get Post");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        respost.setColumns(20);
        respost.setLineWrap(true);
        respost.setRows(5);
        respost_contain.setViewportView(respost);

        comments.setColumns(20);
        comments.setLineWrap(true);
        comments.setRows(5);
        jScrollPane1.setViewportView(comments);

        jLabel2.setText("Blog Post");

        jLabel3.setText("Comments");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(respost_contain, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                            .addComponent(url, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(submit))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(url, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submit))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(respost_contain, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        String link = url.getText();
        String com="";
        getTags gp=new getTags();
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Algorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document doc = null;
        try {
            doc = Jsoup.parse(url, 3 * 1000);
        } catch (IOException ex) {
            Logger.getLogger(Algorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        String text="";
            //If the provided url is a blogspot.com post
            if(gp.isBlogger(link,doc))//If the given url is of blogspot.com
            {
                Elements body=gp.getPostBlogspot(doc);
                respost.setText(body.first().text());
                try {
                    com = gp.getComments(url.toString());
                } catch (Exception ex) {
                    Logger.getLogger(Algorithm.class.getName()).log(Level.SEVERE, null, ex);
                }
                comments.setText(com);
            }//end of blogspot.com
            else{//A generic blog
                try {
                    text = gp.perform(link);
                } catch (Exception ex) {
                    Logger.getLogger(Algorithm.class.getName()).log(Level.SEVERE, null, ex);
                }
                respost.setText(text);
            }
    }//GEN-LAST:event_submitActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Proxy Settings
                //Authenticator.setDefault(new ProxyAuthenticator("iiitb", "iiitb"));
                System.setProperty("http.proxyHost", "192.168.3.254");
                System.setProperty("http.proxyPort", "8080");
                new Algorithm().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea comments;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea respost;
    private javax.swing.JScrollPane respost_contain;
    private javax.swing.JButton submit;
    private javax.swing.JTextField url;
    // End of variables declaration//GEN-END:variables

}

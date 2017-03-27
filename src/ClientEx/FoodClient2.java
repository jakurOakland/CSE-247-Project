/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientEx;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author elijahhunt93
 */
public class FoodClient2 extends javax.swing.JFrame {
private static Socket clientSocket = null;
private static DataOutputStream os = null;
private static DataInputStream is = null;
private static BufferedReader inputline;
public static String place;
public static ArrayList<String> customers = new ArrayList<String>();
    /**
     * Creates new form TryClient2
     */
    public FoodClient2() {
        initComponents();
    }
    
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        orders = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        foodplace = new javax.swing.JComboBox<>();
        orderready = new javax.swing.JTextField();
        orderReadyButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        orders.setEditable(false);
        orders.setColumns(20);
        orders.setRows(5);
        jScrollPane1.setViewportView(orders);

        jLabel1.setText("Enter Name");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        foodplace.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ChickfilA", "Subway" }));
        foodplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodplaceActionPerformed(evt);
            }
        });

        orderReadyButton.setText("Order up!");
        orderReadyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderReadyButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(foodplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jButton1)))
                        .addGap(0, 63, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(35, 35, 35)
                                .addComponent(orderready, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(orderReadyButton)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 83, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(foodplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderready, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(orderReadyButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String name = "";
        try{
           name = (String)foodplace.getSelectedItem();
           os.writeUTF(name);
           place = name;
           setTitle(name);
           os.flush();
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void orderReadyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderReadyButtonActionPerformed
        // TODO add your handling code here:
        String ready = "";
        try{
            for(int i = 0; i < customers.size(); i++){
                if(orderready.getText().equals(customers.get(i))){
                    ready = orderready.getText().trim() + " your order is ready for pickup";
                    os.writeUTF(ready);
                    //customers.remove(i);
                    System.out.println(customers.get(i));
                    os.flush();
                }else if(!orderready.getText().equals(customers.get(i))){
                    System.out.println("FINLAND!");
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("Order Ready button exception.");
        }
    }//GEN-LAST:event_orderReadyButtonActionPerformed

    private void foodplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodplaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_foodplaceActionPerformed

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
            java.util.logging.Logger.getLogger(FoodClient2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FoodClient2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FoodClient2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FoodClient2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FoodClient2().setVisible(true);
                
                
            }
        });
       
        try {
            clientSocket = new Socket("localhost", 2222);
            System.out.println("Connected to server");
            inputline = new BufferedReader(new InputStreamReader(System.in));
            os = new DataOutputStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
            
            String  msgin = "";
          
          
            while(!msgin.equals("exit")){
              
                msgin = is.readUTF();
                //if the message has any of these in it printthe message
                if(msgin.equals("please submit name")){
                    System.out.println(msgin);
                    break;
                //if it does not then print waiting
                }
                else if(!msgin.equals("please submit name")){
                    break;
             
                }
         
            }
          
          
            while(!msgin.equals("exit")){
                msgin = is.readUTF();
                //if the message starts with hello then print the message
                if(msgin.startsWith("Hello")){
                    System.out.println(msgin);

                    //if not then break
                } else {        
                    break;
                }
            }
          
            while(!msgin.equals("exit")){
                String[] substrings = msgin.split(",");
                msgin = is.readUTF();
                //if the message coming in contains the restaurant name and does not contain the pick up message then print the message
                if(msgin.contains(place) && 
                        !msgin.contains("your order is ready for pickup")){
                    System.out.println(msgin);
                    orders.setText(orders.getText().trim() + "\n" + msgin);
                    customers.add(substrings[1]);
                    System.out.println(customers.toString());
                    //if it does not contain the returaunt name it does contain the pick up message then print nothing
                } else if(!msgin.contains(place) || 
                    msgin.contains("your order is ready for pickup")){
                    System.out.println();
                }
            }         
        }catch(UnknownHostException e){
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e);
        }
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JComboBox<String> foodplace;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton orderReadyButton;
    private javax.swing.JTextField orderready;
    private static javax.swing.JTextArea orders;
    // End of variables declaration//GEN-END:variables
}

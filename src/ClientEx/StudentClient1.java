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

/**
 *
 * @author elijahhunt93
 */

public class StudentClient1 extends javax.swing.JFrame {
    static Menu menu;
    public static String name = "";
    
    private static Socket clientSocket = null; 
    //the output stream
    private static DataOutputStream os = null;
    //the inputstream
    private static DataInputStream is = null;

    private static BufferedReader inputline = null;
    private static boolean closed = false;
    private static Menu subwayMenu;
    private static Menu chickFilAMenu;
    private boolean nameSubmitted = false;
    /**
     * Creates new form TryClient
     */
    public StudentClient1() {
        MenuItem m1 = new MenuItem(1, "chicken sandwich", 
            "chicken sandwich description", 3.50);
        MenuItem m2 = new MenuItem(2, "fries", 
            "fries description", 2.00);
        MenuItem m3 = new MenuItem(3, 
            "fountain drink", "free refills", 2.00);
        chickFilAMenu = new Menu();
        chickFilAMenu.add(m1);
        chickFilAMenu.add(m2);
        chickFilAMenu.add(m3);
        m1 = new MenuItem(1, "subway sandwich", "description", 2.50);
        m2 = new MenuItem(2, "fountain drink", "free refills", 2.00);
        subwayMenu = new Menu();
        subwayMenu.add(m1);
        subwayMenu.add(m2);
        menu = chickFilAMenu;
        initComponents();
        setOrders();
    }
    
    private void setOrders(){
       String string = "";
       for(MenuItem m : menu.items.values()){
           string += m.toString();
       }
       orders.setText(string);
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
        ordername = new javax.swing.JTextField();
        submitNameButton = new javax.swing.JButton();
        SendName = new javax.swing.JLabel();
        itemnumber = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        orderButton = new javax.swing.JButton();
        foodplace = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        orders.setEditable(false);
        orders.setColumns(20);
        orders.setRows(5);
        jScrollPane1.setViewportView(orders);

        ordername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordernameActionPerformed(evt);
            }
        });

        submitNameButton.setText("Submit Name");
        submitNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitNameButtonActionPerformed(evt);
            }
        });

        SendName.setText("Send Name");

        itemnumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", " " }));
        itemnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemnumberActionPerformed(evt);
            }
        });

        jLabel1.setText("What Item Do You Want");

        orderButton.setText("Order Now");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        foodplace.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ChickfilA", "Subway" }));
        foodplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodplaceActionPerformed(evt);
            }
        });

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(submitNameButton)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(SendName)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ordername, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1)
                                    .addComponent(orderButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(itemnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 33, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(foodplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ordername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SendName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(submitNameButton)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(foodplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(itemnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitNameButtonActionPerformed
        // TODO add your handling code here:
        
        try{
            name = ordername.getText().trim();
            os.writeUTF(name);
            setTitle("Hello " + name);
            os.flush();
        }catch(Exception e){
            
        } finally {
            nameSubmitted = true;
        }
    }//GEN-LAST:event_submitNameButtonActionPerformed

    private void itemnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemnumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemnumberActionPerformed

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        // TODO add your handling code here:
        String order = "";
        if(nameSubmitted) {
            try{

                MenuItem menuItem = menu.items.get(Integer.parseInt
                    (itemnumber.getSelectedItem().toString()));
                order = foodplace.getSelectedItem() + "," + name + "," + 
                    menuItem.getIndex();
                os.writeUTF(order);
                os.flush();
            }catch(Exception e){
                System.out.println("Error adding item to order." + "   " + e);
            }
        } else {
            System.out.println("You must first submit your name!");
        }
    }//GEN-LAST:event_orderButtonActionPerformed

    private void foodplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodplaceActionPerformed
        String s = (String) foodplace.getSelectedItem();
        if(s.equals("ChickfilA") && menu != chickFilAMenu) {
            menu = chickFilAMenu;
            setOrders();
        } else if(s.equals("Subway") && menu != subwayMenu) {
            menu = subwayMenu;
            setOrders();
        }
    }//GEN-LAST:event_foodplaceActionPerformed

    private void ordernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ordernameActionPerformed

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
            java.util.logging.Logger.getLogger(StudentClient1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentClient1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentClient1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentClient1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentClient1().setVisible(true);
            }
        });
        try {
            clientSocket = new Socket("localhost", 2222);
            System.out.println("Connected to server");
            inputline = new BufferedReader(new InputStreamReader(System.in));
            os = new DataOutputStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
            
            String msgin = "";
          
            while(!msgin.equals("exit")){
                msgin = is.readUTF();
                if(msgin.equals("please submit name") || 
                        msgin.startsWith("Hello") || msgin.contains(name)){
                    System.out.println(msgin);

                } else {
                    System.out.println(msgin);
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
    private javax.swing.JLabel SendName;
    private javax.swing.JComboBox<String> foodplace;
    private javax.swing.JComboBox<String> itemnumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton orderButton;
    private javax.swing.JTextField ordername;
    private static javax.swing.JTextArea orders;
    private javax.swing.JButton submitNameButton;
    // End of variables declaration//GEN-END:variables



}


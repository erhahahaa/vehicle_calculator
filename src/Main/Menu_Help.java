/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import com.formdev.flatlaf.*;

/**
 *
 * @author Rahmat
 */
public class Menu_Help extends javax.swing.JFrame {

    /**
     * Creates new form Halaman_Utama
     */
    public Menu_Help() {
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

        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        panelShadow2 = new style.CustomJPanel.PanelShadow.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu_File = new javax.swing.JMenu();
        Print = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        btnMenuHome = new javax.swing.JMenu();
        Menu_Help = new javax.swing.JMenu();
        Help_Content = new javax.swing.JMenuItem();
        btnAboutUs = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Car Engine Repair");

        kGradientPanel1.setkBorderRadius(5);
        kGradientPanel1.setkEndColor(new java.awt.Color(204, 204, 255));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Guide");

        panelShadow2.setBackground(new java.awt.Color(232, 234, 255));
        panelShadow2.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow2.setShadowOpacity(0.3F);
        panelShadow2.setShadowSize(30);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("HELP\nApplication Guide\n1. When mechanic open this application, a landing page will appear with 2 buttons namely \"Repair\" and \"Help\".\n2. If the mechanic still doesn’t know how to operate this application, then click \"Help\" to find out how to operate the application.\n3. If the mechanic understands how to operate this application, then click “Back” to go to back to the landing page. And then, the mechanic should click \"Repair\".\n4. After click \"Repair\", it will be directed to the \"User Details\" menu. In this menu, customers are required to fill in some information about their car. The car information is filled by the mechanic.\n5. After the mechanic fill some personal car information, then click next.\n6. Then, the mechanic will be directed to the various sub-menus that provided by this application, including combustion, transmission, air-fuel ratio (AFR), and electrical. On each sub-menu, there are “Reset” and “Count” buttons. This \"Reset\" button is used to reset all data on the sub-menu page. And \"count\" button is used for calculation.\n7. In this sub-menu display, the mechanic starts checking the customer's car and after that the mechanic enters some of the data that he gets after checking. And then, the mechanic will click \"Count\" to do the calculation\n8. In each sub-menu, there are also recommendations that are used as a customer reference about them car damage by recommending several items to be replaced, and there are prices per item, as well as subtotals.\n9. Then, there is a “Print” feature on the file menu bar, and also on the print sub menu. This \"Print\" contains a summary of the previous calculations as well as recommendations in the form of an invoice.\n20. After finish using this application, the mechanic can click on the file navigation, then click exit to exit this application.");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Menu_File.setText("File");
        Menu_File.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Print.setText("Print");
        Print.setPreferredSize(new java.awt.Dimension(70, 28));
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });
        Menu_File.add(Print);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        Menu_File.add(Exit);

        jMenuBar1.add(Menu_File);

        btnMenuHome.setText("Home");
        btnMenuHome.setFocusable(false);
        btnMenuHome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMenuHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuHomeActionPerformed(evt);
            }
        });
        btnMenuHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnMenuHomeKeyPressed(evt);
            }
        });
        jMenuBar1.add(btnMenuHome);

        Menu_Help.setText("Help");
        Menu_Help.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Help_Content.setText("Help Content");
        Help_Content.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Help_ContentActionPerformed(evt);
            }
        });
        Menu_Help.add(Help_Content);

        btnAboutUs.setText("About Us");
        btnAboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutUsActionPerformed(evt);
            }
        });
        Menu_Help.add(btnAboutUs);

        jMenuBar1.add(Menu_Help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrintActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExitActionPerformed

    private void Help_ContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Help_ContentActionPerformed
        // TODO add your handling code here:
         this.setVisible(false);
        new Menu_Help().setVisible(true);
    }//GEN-LAST:event_Help_ContentActionPerformed

    private void btnAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutUsActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new About_Us().setVisible(true);
    }//GEN-LAST:event_btnAboutUsActionPerformed

    private void btnMenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuHomeActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Menu_Help().setVisible(true);
    }//GEN-LAST:event_btnMenuHomeActionPerformed

    private void btnMenuHomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnMenuHomeKeyPressed
        // TODO add your handling code here:
        
        this.setVisible(false);
        new Menu_Help().setVisible(true);
    }//GEN-LAST:event_btnMenuHomeKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Help().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Help_Content;
    private javax.swing.JMenu Menu_File;
    private javax.swing.JMenu Menu_Help;
    private javax.swing.JMenuItem Print;
    private javax.swing.JMenuItem btnAboutUs;
    private javax.swing.JMenu btnMenuHome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow2;
    // End of variables declaration//GEN-END:variables
}
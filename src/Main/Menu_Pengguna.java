/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import java.sql.*;
import com.formdev.flatlaf.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Rahmat
 */
public class Menu_Pengguna extends javax.swing.JFrame {
    
    public Statement st;
    public ResultSet rs;
    Connection cn = koneksi.KoneskiDatabase.BukaKoneksi();    
    
    public Menu_Pengguna() {
        initComponents();
        
        get_merk_mobil();
        get_tipe_mobil();
        get_tahun_mobil();
        get_mekanik();
    }
    
    private void get_merk_mobil() {
        if (cmbCarBrand.getItemCount() == 0) {
            try {
                st = cn.createStatement();
                String sql = "SELECT * FROM brand_mobil";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    cmbCarBrand.addItem(rs.getString("nama_brand_mobil"));
                }
                rs.last();
                int jumlah_data = rs.getRow();
                rs.first();
            } catch (Exception e) {
            }
        }
        
        cmbCarBrand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                get_tipe_mobil();
            }
        });
    }
    
    private void get_tipe_mobil() {
        
        if (cmbCarBrand.getSelectedItem() != "Kosong") {
            try {
                cmbCarType.removeAllItems();
                String merk_mobil = cmbCarBrand.getSelectedItem().toString();
                st = cn.createStatement();
                String sql = "SELECT * FROM tipe_mobil WHERE fk_brand_mobil ='" + merk_mobil + "' ";
                
                rs = st.executeQuery(sql);
                
                while (rs.next()) {
                    cmbCarType.addItem(rs.getString("tipe_mobil"));
                }
                System.out.println(rs.getString("tipe_mobil"));
                rs.last();
                int jumlah_data = rs.getRow();
                rs.first();
            } catch (Exception e) {
            }
        }
        
        cmbCarType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                get_tahun_mobil();
            }
        });
    }
    
    private void get_tahun_mobil() {
        cmbCarYear.removeAllItems();
        if (cmbCarType.getSelectedItem() != "Kosong") {
            
            try {
                
                String tahun_mobil = cmbCarType.getSelectedItem().toString();
                st = cn.createStatement();
                String sql = "SELECT * FROM detail_mobil WHERE fk_tipe_mobil = '" + tahun_mobil + "'";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    cmbCarYear.addItem(rs.getString("thn_pembuatan"));
                }
                rs.last();
                int jumlah_data = rs.getRow();
                rs.first();
            } catch (Exception e) {
            }
        }
        
    }
    
    private void get_mekanik() {
        if (cmbMechanicName.getItemCount() == 0) {
            try {
                st = cn.createStatement();
                String sql = "SELECT * FROM mekanik";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    cmbMechanicName.addItem(rs.getString("nama_mekanik"));
                }
                rs.last();
                int jumlah_data = rs.getRow();
                rs.first();
            } catch (Exception e) {
            }
        }
        
    }
    
    private void input_customer() {
        try {
            String mechanicName = cmbMechanicName.getSelectedItem().toString();
            String carMerk = cmbCarBrand.getSelectedItem().toString();
            String carBrand = cmbCarType.getSelectedItem().toString();
            String carYear = cmbCarYear.getSelectedItem().toString();
            st = cn.createStatement();
            if (txtNamaPelanggan.getText().equals("") || txtNoTelp.getText().equals("") || txtPlatNomor.getText().equals("") || mechanicName.equals("") || carMerk.equals("") || carBrand.equals("") || carYear.equals("")) {
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Validasi Data",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (btnNext.getText() == "Next") {
                
                String sql = "SELECT * FROM konsumer WHERE nama_konsumer = '" + txtNamaPelanggan.getText() + "'";
                rs = st.executeQuery(sql);
                if (!rs.next()) {
                    sql = "INSERT INTO konsumer VALUES(NULL,'" + txtNamaPelanggan.getText() + "','" + txtNoTelp.getText() + "')";
                    st.executeUpdate(sql);
                }
                sql = "INSERT INTO `rekam` VALUES (NULL,'" + txtNamaPelanggan.getText() + "', '" + carBrand + "', '" + mechanicName + "', '" + txtNoTelp.getText() + "', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);";
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Data Berhasil disimpian");
                storeData.setCustomerName(txtNamaPelanggan.getText());
                storeData.setTipeMobil(carBrand);
                storeData.setThnMobil(carYear);
                this.setVisible(false);
                new Menu_Utama().setVisible(true);
            }
            
        } catch (Exception e) {
            
        }
        
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
        panelShadow1 = new style.CustomJPanel.PanelShadow.PanelShadow();
        jLabel3 = new javax.swing.JLabel();
        cmbMechanicName = new javax.swing.JComboBox<>();
        txtNamaMekanik = new style.CustomJTextField.TFShadow.TextField();
        txtNamaPelanggan = new style.CustomJTextField.TFShadow.TextField();
        jLabel9 = new javax.swing.JLabel();
        txtNoTelp = new style.CustomJTextField.TFShadow.TextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cmbCarBrand = new javax.swing.JComboBox<>();
        txtMerkMobil1 = new style.CustomJTextField.TFShadow.TextField();
        jLabel11 = new javax.swing.JLabel();
        cmbCarType = new javax.swing.JComboBox<>();
        txtMerkMobil = new style.CustomJTextField.TFShadow.TextField();
        txtPlatNomor = new style.CustomJTextField.TFShadow.TextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbCarYear = new javax.swing.JComboBox<>();
        txtTahunPembuatan = new style.CustomJTextField.TFShadow.TextField();
        btnNext = new style.CustomJButton.ButtonShadow.Button();
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

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Details");

        panelShadow1.setBackground(new java.awt.Color(232, 234, 255));
        panelShadow1.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow1.setShadowOpacity(0.3F);
        panelShadow1.setShadowSize(30);
        panelShadow1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Mechanic Name");
        panelShadow1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 440, -1));

        cmbMechanicName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbMechanicName.setBorder(null);
        panelShadow1.add(cmbMechanicName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 102, 380, 30));

        txtNamaMekanik.setEditable(false);
        txtNamaMekanik.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNamaMekanik.setMinimumSize(new java.awt.Dimension(25, 30));
        txtNamaMekanik.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow1.add(txtNamaMekanik, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 400, -1));

        txtNamaPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNamaPelanggan.setMinimumSize(new java.awt.Dimension(25, 30));
        txtNamaPelanggan.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow1.add(txtNamaPelanggan, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 400, -1));

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Customer Name");
        panelShadow1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 440, -1));

        txtNoTelp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoTelp.setMinimumSize(new java.awt.Dimension(25, 30));
        txtNoTelp.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow1.add(txtNoTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 400, -1));

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Telephone Number");
        panelShadow1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 440, -1));

        jLabel14.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Car Brand");
        panelShadow1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 390, -1));

        cmbCarBrand.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbCarBrand.setBorder(null);
        cmbCarBrand.setMinimumSize(new java.awt.Dimension(72, 23));
        cmbCarBrand.setPreferredSize(new java.awt.Dimension(72, 23));
        panelShadow1.add(cmbCarBrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 102, 380, 30));

        txtMerkMobil1.setEditable(false);
        txtMerkMobil1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMerkMobil1.setMinimumSize(new java.awt.Dimension(25, 30));
        txtMerkMobil1.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow1.add(txtMerkMobil1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 400, -1));

        jLabel11.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Type Car");
        panelShadow1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 390, -1));

        cmbCarType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbCarType.setBorder(null);
        cmbCarType.setMinimumSize(new java.awt.Dimension(72, 23));
        cmbCarType.setPreferredSize(new java.awt.Dimension(72, 23));
        panelShadow1.add(cmbCarType, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 182, 380, 30));

        txtMerkMobil.setEditable(false);
        txtMerkMobil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMerkMobil.setMinimumSize(new java.awt.Dimension(25, 30));
        txtMerkMobil.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow1.add(txtMerkMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 400, -1));

        txtPlatNomor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPlatNomor.setMinimumSize(new java.awt.Dimension(25, 30));
        txtPlatNomor.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow1.add(txtPlatNomor, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 400, -1));

        jLabel12.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("License Number");
        panelShadow1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 390, -1));

        jLabel13.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Year of Made");
        panelShadow1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 440, -1));

        cmbCarYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbCarYear.setBorder(null);
        panelShadow1.add(cmbCarYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 332, 380, 30));

        txtTahunPembuatan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTahunPembuatan.setMinimumSize(new java.awt.Dimension(25, 30));
        txtTahunPembuatan.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow1.add(txtTahunPembuatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 400, -1));

        btnNext.setText("Next");
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnNext.setRound(50);
        btnNext.setShadowColor(new java.awt.Color(0, 255, 0));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        panelShadow1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 400, 66));

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
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

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        input_customer();
    }//GEN-LAST:event_btnNextActionPerformed

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrintActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        
        this.setVisible(false);
        new Halaman_Utama().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_ExitActionPerformed

    private void btnMenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuHomeActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Halaman_Utama().setVisible(true);
    }//GEN-LAST:event_btnMenuHomeActionPerformed

    private void Help_ContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Help_ContentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Help_ContentActionPerformed

    private void btnAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutUsActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new About_Us().setVisible(true);
    }//GEN-LAST:event_btnAboutUsActionPerformed

    private void btnMenuHomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnMenuHomeKeyPressed
        
        this.setVisible(false);
        new Halaman_Utama().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuHomeKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Pengguna().setVisible(true);
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
    private style.CustomJButton.ButtonShadow.Button btnNext;
    private javax.swing.JComboBox<String> cmbCarBrand;
    private javax.swing.JComboBox<String> cmbCarType;
    private javax.swing.JComboBox<String> cmbCarYear;
    private javax.swing.JComboBox<String> cmbMechanicName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow1;
    private style.CustomJTextField.TFShadow.TextField txtMerkMobil;
    private style.CustomJTextField.TFShadow.TextField txtMerkMobil1;
    private style.CustomJTextField.TFShadow.TextField txtNamaMekanik;
    private style.CustomJTextField.TFShadow.TextField txtNamaPelanggan;
    private style.CustomJTextField.TFShadow.TextField txtNoTelp;
    private style.CustomJTextField.TFShadow.TextField txtPlatNomor;
    private style.CustomJTextField.TFShadow.TextField txtTahunPembuatan;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.text.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rahmat
 */
public class CRUD_Data extends javax.swing.JFrame {

    public Statement st;
    public ResultSet rs;
    Connection cn = koneksi.KoneskiDatabase.BukaKoneksi();

    /**
     * Creates new form CRUD_Data
     */
    public CRUD_Data() {
        initComponents();
        dateOfEntrance.setDateFormatString("yyyy-MM-dd");
        TampilCarData();
        TampilMechanicData();
    }

    private void CleanPanelCarData() {
        txtCarBrand.setText("");
        txtTypeCar.setText("");
        txtCarYear.setText("");
        txtPistonDiameter.setText("");
        txtStrokeLength.setText("");
        txtPistonSpeed.setText("");
        txtCompressionRatio.setText("");
        txtMaximumRPM.setText("");
        txtCarbueratorDiameter.setText("");
        txtBatteryName.setText("");
        btnAddCarData.setText("Add");
        txtCarBrand.setEditable(true);
        txtTypeCar.setEditable(true);
        TampilCarData();
    }

    private void CleanPanelMechanicData() {
        txtIdMechanic.setText("");
        txtMechanicName.setText("");
        dateOfEntrance.setDate(null);
        txtMechanicAddress.setText("");;
        txtIdMechanic.setEditable(true);
        TampilMechanicData();
    }

    private void FindMechanicData() {
        try { 
            String cmbCariSelected = cmbFindMechanicData.getSelectedItem().toString();
            String query = "";
            if (cmbCariSelected == "ID") {
                query = "id_mekanik";
            }
            if (cmbCariSelected == "Name") {
                query = "nama_mekanik";
            }
            if (cmbCariSelected == "Date") {
                query = "tgl_masuk";
            }
            if (cmbCariSelected == "Alamat") {
                query = "alamat";
            }
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM mekanik WHERE " + query + " LIKE '%" + txtFindMechanicData.getText() + "%'");

            DefaultTableModel tblMechanicModel = new DefaultTableModel();
            tblMechanicModel.addColumn("ID.");
            tblMechanicModel.addColumn("Nama");
            tblMechanicModel.addColumn("Tanggal Masuk");
            tblMechanicModel.addColumn("Alamat");

            tblMechanicModel.getDataVector().removeAllElements();
            tblMechanicModel.fireTableDataChanged();
            tblMechanicModel.setRowCount(0);
            while (rs.next()) {
                Object[] data = {
                    rs.getString("id_mekanik"),
                    rs.getString("nama_mekanik"),
                    rs.getString("tgl_masuk"),
                    rs.getString("alamat")};
                tblMechanicModel.addRow(data);
                tblMechanicData.setModel(tblMechanicModel);
                for (int i = 1; i < 4; i++) {
                    tblMechanicData.getColumnModel().getColumn(0).setPreferredWidth(1);
                    tblMechanicData.getColumnModel().getColumn(i).setPreferredWidth(180);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void FindCarData() {
        try {
            String cmbCariSelected = cmbFindCarData.getSelectedItem().toString();
            String query = "";
            if (cmbCariSelected == "Car Brand") {
                query = "fk_brand_mobil";
                System.out.println(query);
            }
            if (cmbCariSelected == "Type Car") {
                query = "fk_tipe_mobil";

                System.out.println(query);
            }
            if (cmbCariSelected == "Year of Made") {
                query = "thn_pembuatan";

                System.out.println(query);
            }
            st = cn.createStatement();
            rs = st.executeQuery("SELECT tipe_mobil.fk_brand_mobil, detail_mobil.id_detail_mobil, detail_mobil.fk_tipe_mobil, detail_mobil.thn_pembuatan, detail_mobil.piston_diameter, detail_mobil.stroke_length, detail_mobil.piston_speed, detail_mobil.compression_ratio, detail_mobil.maximum_rpm, detail_mobil.carbuerator_diameter, detail_mobil.battery_name FROM detail_mobil INNER JOIN tipe_mobil ON tipe_mobil.tipe_mobil = detail_mobil.fk_tipe_mobil WHERE " + query + " LIKE '%" + txtFindCarData.getText() + "%'");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("No.");
            model.addColumn("Car Brand");
            model.addColumn("Car Type");
            model.addColumn("Year of Made");
            model.addColumn("Piston Diameter");
            model.addColumn("Stroke Length");
            model.addColumn("Piston Speed");
            model.addColumn("Compression Ratio");
            model.addColumn("Maximum RPM");
            model.addColumn("Carburator Diameter");
            model.addColumn("Battery Name");
            int no = 1;
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] data = {
                    rs.getString("id_detail_mobil"),
                    rs.getString("fk_brand_mobil"),
                    rs.getString("fk_tipe_mobil"),
                    rs.getString("thn_pembuatan"),
                    rs.getString("piston_diameter"),
                    rs.getString("stroke_length"),
                    rs.getString("piston_speed"),
                    rs.getString("compression_ratio"),
                    rs.getString("maximum_rpm"),
                    rs.getString("carbuerator_diameter"),
                    rs.getString("battery_name"),};
                model.addRow(data);
                tblCarData.setModel(model);
                for (int i = 1; i < 10; i++) {

                    tblCarData.getColumnModel().getColumn(0).setPreferredWidth(30);
                    tblCarData.getColumnModel().getColumn(i).setPreferredWidth(120);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void AddCarData() {
        try {
            st = cn.createStatement();
            if (txtCarBrand.getText().equals("") || txtTypeCar.getText().equals("") || txtCarYear.getText().equals("") || txtPistonDiameter.getText().equals("") || txtStrokeLength.getText().equals("") || txtPistonSpeed.getText().equals("") || txtCompressionRatio.getText().equals("") || txtMaximumRPM.getText().equals("") || txtCarbueratorDiameter.getText().equals("") || txtBatteryName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "There is empty data, Check again!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (btnAddCarData.getText() == "Add") {
                String cek = "SELECT * FROM detail_mobil WHERE fk_tipe_mobil  = '" + txtTypeCar.getText() + "' AND thn_pembuatan = '" + txtCarYear.getText() + "'";
                rs = st.executeQuery(cek);
                if (rs.next()) {
                    JOptionPane.showInternalMessageDialog(null, "This data already inserted");
                } else {
                    cek = "SELECT * FROM brand_mobil WHERE nama_brand_mobil  = '" + txtCarBrand.getText() + "'";
                    rs = st.executeQuery(cek);
                    String sql;
                    if (!rs.next()) {
                        sql = "INSERT INTO brand_mobil VALUES(NULL, '" + txtCarBrand.getText() + "')";
                        st.executeUpdate(sql);
                    }
                    cek = "SELECT * FROM tipe_mobil WHERE tipe_mobil  = '" + txtTypeCar.getText() + "'";
                    rs = st.executeQuery(cek);
                    if (!rs.next()) {
                        sql = "INSERT INTO tipe_mobil VALUES(NULL, '" + txtCarBrand.getText() + "', '" + txtTypeCar.getText() + "')";
                        st.executeUpdate(sql);
                    }
                    sql = "INSERT INTO detail_mobil(`id_detail_mobil`, `fk_tipe_mobil`, `thn_pembuatan`, `piston_diameter`, `stroke_length`, `piston_speed`, `compression_ratio`, `maximum_rpm`, `carbuerator_diameter`, `battery_name`) VALUES(NULL, '" + txtTypeCar.getText() + "', '" + txtCarYear.getText() + "', '" + txtPistonDiameter.getText() + "', '" + txtStrokeLength.getText() + "', '" + txtPistonSpeed.getText() + "', '" + txtCompressionRatio.getText() + "', '" + txtMaximumRPM.getText() + "', '" + txtCarbueratorDiameter.getText() + "', '" + txtBatteryName.getText() + "' )";
                    st.executeUpdate(sql);
                    JOptionPane.showInternalMessageDialog(null, "Data Saved");
                    CleanPanelCarData();
                    TampilCarData();
                }
            }
            if (btnAddCarData.getText() == "Change") {
                try {

                    String IdCarString = tblCarData.getValueAt(tblCarData.getSelectedRow(), 0).toString();
                    System.out.print(IdCarString);
                    String update = "UPDATE detail_mobil SET thn_pembuatan = '" + txtCarYear.getText() + "', piston_diameter = '" + txtPistonDiameter.getText() + "', stroke_length = '" + txtStrokeLength.getText() + "', piston_speed = '" + txtPistonSpeed.getText() + "', compression_ratio = '" + txtCompressionRatio.getText() + "', maximum_rpm = '" + txtMaximumRPM.getText() + "', carbuerator_diameter = '" + txtCarbueratorDiameter.getText() + "', battery_name = '" + txtBatteryName.getText() + "' WHERE id_detail_mobil = '" + IdCarString + "'";
                    st.executeUpdate(update);
                    JOptionPane.showMessageDialog(null, "Data Berhasil disimpan");
                    CleanPanelCarData();
                    TampilCarData();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void AddMechanicData() {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dd = sdf.format(dateOfEntrance.getDate());
            st = cn.createStatement();
            if (txtIdMechanic.getText().equals("") || txtMechanicName.getText().equals("") || txtMechanicAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "There is empty data, Check again!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (btnAddMechanicData.getText() == "Add") {
                String cek = "SELECT * FROM mekanik WHERE id_mekanik = '" + txtIdMechanic.getText() + "'";
                rs = st.executeQuery(cek);
                if (rs.next()) {
                    JOptionPane.showInternalMessageDialog(null, "This data already inserted");
                } else {
                    String sql = "INSERT INTO mekanik VALUES ('" + txtIdMechanic.getText() + "', '" + txtMechanicName.getText() + "', '" + dd + "', '" + txtMechanicAddress.getText() + "')";
                    st.executeUpdate(sql);
                    JOptionPane.showInternalMessageDialog(null, "Data Saved");
                    CleanPanelMechanicData();
                    TampilMechanicData();
                }
            }
            if (btnAddCarData.getText() == "Change") {
                try {

                    String IdMechanicString = tblMechanicData.getValueAt(tblMechanicData.getSelectedRow(), 0).toString();
                    System.out.print(IdMechanicString);
                    String update = "UPDATE mekanik SET id_mekanik = '" + txtIdMechanic.getText() + "', nama_mekanik = '" + txtMechanicName.getText() + "', tgl_masuk = '" + dd + "', alamat = '" + txtMechanicAddress.getText() + "' WHERE id_mekanik = " + txtIdMechanic.getText() + ";";
                    st.executeUpdate(update);
                    JOptionPane.showMessageDialog(null, "Data Berhasil disimpan");
                    CleanPanelMechanicData();
                    TampilMechanicData();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void DeleteCarData() {
        if (txtTypeCar.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Select the exact Data");
        } else {
            int jawab = JOptionPane.showConfirmDialog(null, "This data will be deleted, Continue?", "Confirmation", JOptionPane.YES_OPTION);
            if (jawab == 0) {
                try {
                    st = cn.createStatement();
                    String sql = "DELETE FROM detail_mobil WHERE fk_tipe_mobil = '" + txtTypeCar.getText() + "' AND thn_pembuatan ='" + txtCarYear.getText() + "'";
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data Deleted!");
                    TampilCarData();
                    CleanPanelCarData();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    private void TampilCarData() {
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT tipe_mobil.fk_brand_mobil, detail_mobil.id_detail_mobil, detail_mobil.fk_tipe_mobil, detail_mobil.thn_pembuatan, detail_mobil.piston_diameter, detail_mobil.stroke_length, detail_mobil.piston_speed, detail_mobil.compression_ratio, detail_mobil.maximum_rpm, detail_mobil.carbuerator_diameter, detail_mobil.battery_name FROM detail_mobil INNER JOIN tipe_mobil ON tipe_mobil.tipe_mobil = detail_mobil.fk_tipe_mobil;");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("No.");
            model.addColumn("Car Brand");
            model.addColumn("Car Type");
            model.addColumn("Year of Made");
            model.addColumn("Piston Diameter");
            model.addColumn("Stroke Length");
            model.addColumn("Piston Speed");
            model.addColumn("Compression Ratio");
            model.addColumn("Maximum RPM");
            model.addColumn("Carburator Diameter");
            model.addColumn("Battery Name");
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] data = {
                    rs.getString("id_detail_mobil"),
                    rs.getString("fk_brand_mobil"),
                    rs.getString("fk_tipe_mobil"),
                    rs.getString("thn_pembuatan"),
                    rs.getString("piston_diameter"),
                    rs.getString("stroke_length"),
                    rs.getString("piston_speed"),
                    rs.getString("compression_ratio"),
                    rs.getString("maximum_rpm"),
                    rs.getString("carbuerator_diameter"),
                    rs.getString("battery_name"),};
                model.addRow(data);
                tblCarData.setModel(model);
                for (int i = 1; i < 10; i++) {

                    tblCarData.getColumnModel().getColumn(0).setPreferredWidth(30);
                    tblCarData.getColumnModel().getColumn(i).setPreferredWidth(120);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void TampilMechanicData() {
        try {
            st = cn.createStatement();
            //rs = st.executeQuery("SELECT id_mekanik,nama_mekanik, DATE_FORMAT(tgl_masuk,'%d %M %Y') AS tgl_masuk,alamat FROM mekanik;");
            rs = st.executeQuery("SELECT * FROM mekanik;");

            DefaultTableModel tblMechanicModel = new DefaultTableModel();
            tblMechanicModel.addColumn("ID.");
            tblMechanicModel.addColumn("Nama");
            tblMechanicModel.addColumn("Tanggal Masuk");
            tblMechanicModel.addColumn("Alamat");

            tblMechanicModel.getDataVector().removeAllElements();
            tblMechanicModel.fireTableDataChanged();
            tblMechanicModel.setRowCount(0);
            while (rs.next()) {
                Object[] data = {
                    rs.getString("id_mekanik"),
                    rs.getString("nama_mekanik"),
                    rs.getString("tgl_masuk"),
                    rs.getString("alamat")};
                tblMechanicModel.addRow(data);
                tblMechanicData.setModel(tblMechanicModel);
                for (int i = 1; i < 4; i++) {
                    tblMechanicData.getColumnModel().getColumn(0).setPreferredWidth(1);
                    tblMechanicData.getColumnModel().getColumn(i).setPreferredWidth(180);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelShadow1 = new style.CustomJPanel.PanelShadow.PanelShadow();
        txtPistonSpeed = new style.CustomJTextField.TFShadow.TextField();
        jLabel9 = new javax.swing.JLabel();
        txtCompressionRatio = new style.CustomJTextField.TFShadow.TextField();
        jLabel11 = new javax.swing.JLabel();
        txtMaximumRPM = new style.CustomJTextField.TFShadow.TextField();
        jLabel12 = new javax.swing.JLabel();
        txtCarbueratorDiameter = new style.CustomJTextField.TFShadow.TextField();
        jLabel13 = new javax.swing.JLabel();
        txtBatteryName = new style.CustomJTextField.TFShadow.TextField();
        jLabel14 = new javax.swing.JLabel();
        txtCarBrand = new style.CustomJTextField.TFShadow.TextField();
        jLabel15 = new javax.swing.JLabel();
        txtTypeCar = new style.CustomJTextField.TFShadow.TextField();
        jLabel16 = new javax.swing.JLabel();
        txtCarYear = new style.CustomJTextField.TFShadow.TextField();
        jLabel17 = new javax.swing.JLabel();
        txtPistonDiameter = new style.CustomJTextField.TFShadow.TextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtFindCarData = new style.CustomJTextField.TFShadow.TextField();
        btnCancel = new style.CustomJButton.ButtonShadow.Button();
        jLabel20 = new javax.swing.JLabel();
        cmbFindCarData = new javax.swing.JComboBox<>();
        txtStrokeLength = new style.CustomJTextField.TFShadow.TextField();
        btnAddCarData = new style.CustomJButton.ButtonShadow.Button();
        btnDelete = new style.CustomJButton.ButtonShadow.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCarData = new javax.swing.JTable();
        panelShadow3 = new style.CustomJPanel.PanelShadow.PanelShadow();
        dateOfEntrance = new com.toedter.calendar.JDateChooser();
        txtMechanicName = new style.CustomJTextField.TFShadow.TextField();
        jLabel31 = new javax.swing.JLabel();
        txtMechanicAddress = new style.CustomJTextField.TFShadow.TextField();
        jLabel32 = new javax.swing.JLabel();
        txtIdMechanic = new style.CustomJTextField.TFShadow.TextField();
        jLabel36 = new javax.swing.JLabel();
        txtDateOfEntrance = new style.CustomJTextField.TFShadow.TextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtFindMechanicData = new style.CustomJTextField.TFShadow.TextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMechanicData = new javax.swing.JTable();
        btnCancelMechanicData = new style.CustomJButton.ButtonShadow.Button();
        cmbFindMechanicData = new javax.swing.JComboBox<>();
        btnAddMechanicData = new style.CustomJButton.ButtonShadow.Button();
        btnDeleteMechanicData = new style.CustomJButton.ButtonShadow.Button();
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

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        panelShadow1.setBackground(new java.awt.Color(232, 234, 255));
        panelShadow1.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow1.setShadowOpacity(0.3F);
        panelShadow1.setShadowSize(30);
        panelShadow1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPistonSpeed.setForeground(new java.awt.Color(0, 0, 0));
        txtPistonSpeed.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPistonSpeed.setMinimumSize(new java.awt.Dimension(25, 30));
        txtPistonSpeed.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow1.add(txtPistonSpeed, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, 230, -1));

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Piston Speed");
        panelShadow1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 60, 220, -1));

        txtCompressionRatio.setForeground(new java.awt.Color(0, 0, 0));
        txtCompressionRatio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCompressionRatio.setMinimumSize(new java.awt.Dimension(25, 30));
        txtCompressionRatio.setShadowColor(new java.awt.Color(255, 0, 0));
        txtCompressionRatio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCompressionRatioActionPerformed(evt);
            }
        });
        panelShadow1.add(txtCompressionRatio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 160, 230, -1));

        jLabel11.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Compression Ratio");
        panelShadow1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 140, 220, -1));

        txtMaximumRPM.setForeground(new java.awt.Color(0, 0, 0));
        txtMaximumRPM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaximumRPM.setMinimumSize(new java.awt.Dimension(25, 30));
        txtMaximumRPM.setShadowColor(new java.awt.Color(255, 0, 0));
        txtMaximumRPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaximumRPMActionPerformed(evt);
            }
        });
        panelShadow1.add(txtMaximumRPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 240, 230, -1));

        jLabel12.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Maximum RPM");
        panelShadow1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 220, 220, -1));

        txtCarbueratorDiameter.setForeground(new java.awt.Color(0, 0, 0));
        txtCarbueratorDiameter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCarbueratorDiameter.setMinimumSize(new java.awt.Dimension(25, 30));
        txtCarbueratorDiameter.setShadowColor(new java.awt.Color(255, 0, 0));
        txtCarbueratorDiameter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarbueratorDiameterActionPerformed(evt);
            }
        });
        panelShadow1.add(txtCarbueratorDiameter, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 320, 230, -1));

        jLabel13.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Carbuerator Diameter");
        panelShadow1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 300, 220, -1));

        txtBatteryName.setForeground(new java.awt.Color(0, 0, 0));
        txtBatteryName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBatteryName.setMinimumSize(new java.awt.Dimension(25, 30));
        txtBatteryName.setShadowColor(new java.awt.Color(255, 0, 0));
        txtBatteryName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBatteryNameActionPerformed(evt);
            }
        });
        panelShadow1.add(txtBatteryName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 400, 230, -1));

        jLabel14.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Battery Name");
        panelShadow1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 380, 220, -1));

        txtCarBrand.setForeground(new java.awt.Color(0, 0, 0));
        txtCarBrand.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCarBrand.setMinimumSize(new java.awt.Dimension(25, 30));
        txtCarBrand.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow1.add(txtCarBrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 230, -1));

        jLabel15.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Car Brand");
        panelShadow1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, 220, -1));

        txtTypeCar.setForeground(new java.awt.Color(0, 0, 0));
        txtTypeCar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTypeCar.setMinimumSize(new java.awt.Dimension(25, 30));
        txtTypeCar.setShadowColor(new java.awt.Color(255, 0, 0));
        txtTypeCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTypeCarActionPerformed(evt);
            }
        });
        panelShadow1.add(txtTypeCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 230, -1));

        jLabel16.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Type Car");
        panelShadow1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 140, 220, -1));

        txtCarYear.setForeground(new java.awt.Color(0, 0, 0));
        txtCarYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCarYear.setMinimumSize(new java.awt.Dimension(25, 30));
        txtCarYear.setShadowColor(new java.awt.Color(255, 0, 0));
        txtCarYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarYearActionPerformed(evt);
            }
        });
        panelShadow1.add(txtCarYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, 230, -1));

        jLabel17.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Year of Made");
        panelShadow1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 220, -1));

        txtPistonDiameter.setForeground(new java.awt.Color(0, 0, 0));
        txtPistonDiameter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPistonDiameter.setMinimumSize(new java.awt.Dimension(25, 30));
        txtPistonDiameter.setShadowColor(new java.awt.Color(255, 0, 0));
        txtPistonDiameter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPistonDiameterActionPerformed(evt);
            }
        });
        panelShadow1.add(txtPistonDiameter, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 320, 230, -1));

        jLabel18.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Find Data by");
        panelShadow1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 520, 90, 30));

        jLabel19.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Stroke Length");
        panelShadow1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 380, 220, -1));

        txtFindCarData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFindCarData.setMinimumSize(new java.awt.Dimension(25, 30));
        txtFindCarData.setShadowColor(new java.awt.Color(255, 0, 0));
        txtFindCarData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFindCarDataKeyTyped(evt);
            }
        });
        panelShadow1.add(txtFindCarData, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 560, 480, -1));

        btnCancel.setForeground(new java.awt.Color(0, 0, 0));
        btnCancel.setText("Cancel");
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setShadowColor(new java.awt.Color(0, 255, 0));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        panelShadow1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 460, 150, 45));

        jLabel20.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Piston Diameter");
        panelShadow1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, 220, -1));

        cmbFindCarData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbFindCarData.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car Brand", "Type Car", "Year of Made" }));
        cmbFindCarData.setToolTipText("");
        panelShadow1.add(cmbFindCarData, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 520, 110, 30));

        txtStrokeLength.setForeground(new java.awt.Color(0, 0, 0));
        txtStrokeLength.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtStrokeLength.setMinimumSize(new java.awt.Dimension(25, 30));
        txtStrokeLength.setShadowColor(new java.awt.Color(255, 0, 0));
        txtStrokeLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStrokeLengthActionPerformed(evt);
            }
        });
        panelShadow1.add(txtStrokeLength, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 400, 230, -1));

        btnAddCarData.setForeground(new java.awt.Color(0, 0, 0));
        btnAddCarData.setText("Add");
        btnAddCarData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddCarData.setShadowColor(new java.awt.Color(0, 255, 0));
        btnAddCarData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCarDataActionPerformed(evt);
            }
        });
        panelShadow1.add(btnAddCarData, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 460, 150, 45));

        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Delete");
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setShadowColor(new java.awt.Color(0, 255, 0));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        panelShadow1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, 140, 45));

        tblCarData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblCarData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Car Brand", "Type Car", "Year of Made", "Piston Diameter", "Stroke Length", "Piston Speed", "Compression Ratio", "Maximum RPM", "Carburator Diameter", "Battery Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCarData.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblCarData.setShowGrid(true);
        tblCarData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCarDataMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblCarData);
        if (tblCarData.getColumnModel().getColumnCount() > 0) {
            tblCarData.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(0).setHeaderValue("Car Brand");
            tblCarData.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(1).setHeaderValue("Type Car");
            tblCarData.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(2).setHeaderValue("Year of Made");
            tblCarData.getColumnModel().getColumn(3).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(3).setHeaderValue("Piston Diameter");
            tblCarData.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(4).setHeaderValue("Stroke Length");
            tblCarData.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(5).setHeaderValue("Piston Speed");
            tblCarData.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(6).setHeaderValue("Compression Ratio");
            tblCarData.getColumnModel().getColumn(7).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(7).setHeaderValue("Maximum RPM");
            tblCarData.getColumnModel().getColumn(8).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(8).setHeaderValue("Carburator Diameter");
            tblCarData.getColumnModel().getColumn(9).setPreferredWidth(120);
            tblCarData.getColumnModel().getColumn(9).setHeaderValue("Battery Name");
        }

        panelShadow1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 660, 540));

        jTabbedPane1.addTab("Car Data", panelShadow1);

        panelShadow3.setBackground(new java.awt.Color(232, 234, 255));
        panelShadow3.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow3.setShadowOpacity(0.3F);
        panelShadow3.setShadowSize(30);
        panelShadow3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dateOfEntrance.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow3.add(dateOfEntrance, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 274, 210, 30));

        txtMechanicName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMechanicName.setMinimumSize(new java.awt.Dimension(25, 30));
        txtMechanicName.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow3.add(txtMechanicName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 190, 230, -1));

        jLabel31.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Mechanic Name");
        panelShadow3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 170, 220, -1));

        txtMechanicAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMechanicAddress.setMinimumSize(new java.awt.Dimension(25, 30));
        txtMechanicAddress.setShadowColor(new java.awt.Color(255, 0, 0));
        txtMechanicAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMechanicAddressActionPerformed(evt);
            }
        });
        panelShadow3.add(txtMechanicAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 270, 230, -1));

        jLabel32.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Mechanic Address");
        panelShadow3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 250, 220, -1));

        txtIdMechanic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIdMechanic.setMinimumSize(new java.awt.Dimension(25, 30));
        txtIdMechanic.setShadowColor(new java.awt.Color(255, 0, 0));
        panelShadow3.add(txtIdMechanic, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, 230, -1));

        jLabel36.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("ID Mechanic");
        panelShadow3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 170, 220, -1));

        txtDateOfEntrance.setEditable(false);
        txtDateOfEntrance.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDateOfEntrance.setMinimumSize(new java.awt.Dimension(25, 30));
        txtDateOfEntrance.setShadowColor(new java.awt.Color(255, 0, 0));
        txtDateOfEntrance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateOfEntranceActionPerformed(evt);
            }
        });
        panelShadow3.add(txtDateOfEntrance, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 270, 230, -1));

        jLabel37.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Date Of Entrance");
        panelShadow3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 250, 220, -1));

        jLabel39.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("Find Data by");
        panelShadow3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 410, 90, 30));

        txtFindMechanicData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFindMechanicData.setMinimumSize(new java.awt.Dimension(25, 30));
        txtFindMechanicData.setShadowColor(new java.awt.Color(255, 0, 0));
        txtFindMechanicData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindMechanicDataActionPerformed(evt);
            }
        });
        txtFindMechanicData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFindMechanicDataKeyTyped(evt);
            }
        });
        panelShadow3.add(txtFindMechanicData, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 450, 480, -1));

        tblMechanicData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mechanic Name", "Date Of Entrance", "Mechanic Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMechanicData.setShowGrid(true);
        tblMechanicData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMechanicDataMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMechanicData);

        panelShadow3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 660, 540));

        btnCancelMechanicData.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelMechanicData.setText("Cancel");
        btnCancelMechanicData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelMechanicData.setShadowColor(new java.awt.Color(0, 255, 0));
        btnCancelMechanicData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelMechanicDataActionPerformed(evt);
            }
        });
        panelShadow3.add(btnCancelMechanicData, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 340, 150, 45));

        cmbFindMechanicData.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbFindMechanicData.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Name", "Date", "Adrress" }));
        panelShadow3.add(cmbFindMechanicData, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 410, 110, 30));

        btnAddMechanicData.setForeground(new java.awt.Color(0, 0, 0));
        btnAddMechanicData.setText("Add");
        btnAddMechanicData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddMechanicData.setShadowColor(new java.awt.Color(0, 255, 0));
        btnAddMechanicData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMechanicDataActionPerformed(evt);
            }
        });
        panelShadow3.add(btnAddMechanicData, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 340, 150, 45));

        btnDeleteMechanicData.setForeground(new java.awt.Color(0, 0, 0));
        btnDeleteMechanicData.setText("Delete");
        btnDeleteMechanicData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteMechanicData.setShadowColor(new java.awt.Color(0, 255, 0));
        btnDeleteMechanicData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMechanicDataActionPerformed(evt);
            }
        });
        panelShadow3.add(btnDeleteMechanicData, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 340, 140, 45));

        jTabbedPane1.addTab("Mechanic Data", panelShadow3);

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
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

        this.setVisible(false);
        new Halaman_Utama().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_ExitActionPerformed

    private void btnMenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuHomeActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Halaman_Utama().setVisible(true);
    }//GEN-LAST:event_btnMenuHomeActionPerformed

    private void btnMenuHomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnMenuHomeKeyPressed

        this.setVisible(false);
        new Halaman_Utama().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuHomeKeyPressed

    private void Help_ContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Help_ContentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Help_ContentActionPerformed

    private void btnAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutUsActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new About_Us().setVisible(true);
    }//GEN-LAST:event_btnAboutUsActionPerformed

    private void txtCompressionRatioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCompressionRatioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompressionRatioActionPerformed

    private void txtMaximumRPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaximumRPMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaximumRPMActionPerformed

    private void txtCarbueratorDiameterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarbueratorDiameterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarbueratorDiameterActionPerformed

    private void txtBatteryNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBatteryNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBatteryNameActionPerformed

    private void txtTypeCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTypeCarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTypeCarActionPerformed

    private void txtCarYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarYearActionPerformed

    private void txtPistonDiameterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPistonDiameterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPistonDiameterActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        CleanPanelCarData();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtStrokeLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStrokeLengthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStrokeLengthActionPerformed

    private void btnAddCarDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCarDataActionPerformed
        // TODO add your handling code here:

        AddCarData();
    }//GEN-LAST:event_btnAddCarDataActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        DeleteCarData();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtMechanicAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMechanicAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMechanicAddressActionPerformed

    private void txtDateOfEntranceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateOfEntranceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateOfEntranceActionPerformed

    private void txtFindMechanicDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindMechanicDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindMechanicDataActionPerformed

    private void btnCancelMechanicDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelMechanicDataActionPerformed
        CleanPanelMechanicData();
// TODO add your handling code here:
    }//GEN-LAST:event_btnCancelMechanicDataActionPerformed

    private void btnAddMechanicDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMechanicDataActionPerformed
        // TODO add your handling code here:  
        AddMechanicData();
    }//GEN-LAST:event_btnAddMechanicDataActionPerformed

    private void btnDeleteMechanicDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMechanicDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteMechanicDataActionPerformed

    private void tblCarDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarDataMouseClicked
        // TODO add your handling code here:

        txtCarBrand.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 1).toString());
        txtTypeCar.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 2).toString());
        txtCarYear.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 3).toString());
        txtPistonDiameter.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 4).toString());
        txtStrokeLength.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 5).toString());
        txtPistonSpeed.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 6).toString());
        txtCompressionRatio.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 7).toString());
        txtMaximumRPM.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 8).toString());
        txtCarbueratorDiameter.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 9).toString());
        txtBatteryName.setText(tblCarData.getValueAt(tblCarData.getSelectedRow(), 10).toString());
        txtCarBrand.setEditable(false);
        txtTypeCar.setEditable(false);
        btnAddCarData.setText("Change");
    }//GEN-LAST:event_tblCarDataMouseClicked

    private void txtFindCarDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindCarDataKeyTyped
        // TODO add your handling code here:
        FindCarData();
    }//GEN-LAST:event_txtFindCarDataKeyTyped

    private void tblMechanicDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMechanicDataMouseClicked
        // TODO add your handling code here:

        try {
            String x = tblMechanicData.getValueAt(tblMechanicData.getSelectedRow(), 2).toString();
            System.out.println(x);
            java.util.Date dd = new SimpleDateFormat("yyyy-MM-dd").parse(x);
            dateOfEntrance.setDate(dd);
        } catch (ParseException ex) {
            Logger.getLogger(CRUD_Data.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtIdMechanic.setText(tblMechanicData.getValueAt(tblMechanicData.getSelectedRow(), 0).toString());
        txtMechanicName.setText(tblMechanicData.getValueAt(tblMechanicData.getSelectedRow(), 1).toString());

        txtMechanicAddress.setText(tblMechanicData.getValueAt(tblMechanicData.getSelectedRow(), 3).toString());
        txtIdMechanic.setEditable(false);
        btnAddCarData.setText("Change");
    }//GEN-LAST:event_tblMechanicDataMouseClicked

    private void txtFindMechanicDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindMechanicDataKeyTyped
        // TODO add your handling code here:
        FindMechanicData();
    }//GEN-LAST:event_txtFindMechanicDataKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new CRUD_Data().setVisible(true);
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
    private style.CustomJButton.ButtonShadow.Button btnAddCarData;
    private style.CustomJButton.ButtonShadow.Button btnAddMechanicData;
    private style.CustomJButton.ButtonShadow.Button btnCancel;
    private style.CustomJButton.ButtonShadow.Button btnCancelMechanicData;
    private style.CustomJButton.ButtonShadow.Button btnDelete;
    private style.CustomJButton.ButtonShadow.Button btnDeleteMechanicData;
    private javax.swing.JMenu btnMenuHome;
    private javax.swing.JComboBox<String> cmbFindCarData;
    private javax.swing.JComboBox<String> cmbFindMechanicData;
    private com.toedter.calendar.JDateChooser dateOfEntrance;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow1;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow3;
    private javax.swing.JTable tblCarData;
    private javax.swing.JTable tblMechanicData;
    private style.CustomJTextField.TFShadow.TextField txtBatteryName;
    private style.CustomJTextField.TFShadow.TextField txtCarBrand;
    private style.CustomJTextField.TFShadow.TextField txtCarYear;
    private style.CustomJTextField.TFShadow.TextField txtCarbueratorDiameter;
    private style.CustomJTextField.TFShadow.TextField txtCompressionRatio;
    private style.CustomJTextField.TFShadow.TextField txtDateOfEntrance;
    private style.CustomJTextField.TFShadow.TextField txtFindCarData;
    private style.CustomJTextField.TFShadow.TextField txtFindMechanicData;
    private style.CustomJTextField.TFShadow.TextField txtIdMechanic;
    private style.CustomJTextField.TFShadow.TextField txtMaximumRPM;
    private style.CustomJTextField.TFShadow.TextField txtMechanicAddress;
    private style.CustomJTextField.TFShadow.TextField txtMechanicName;
    private style.CustomJTextField.TFShadow.TextField txtPistonDiameter;
    private style.CustomJTextField.TFShadow.TextField txtPistonSpeed;
    private style.CustomJTextField.TFShadow.TextField txtStrokeLength;
    private style.CustomJTextField.TFShadow.TextField txtTypeCar;
    // End of variables declaration//GEN-END:variables

}

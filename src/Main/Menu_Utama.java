/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author Rahmat
 */
public class Menu_Utama extends javax.swing.JFrame {

    public Statement st;
    public ResultSet rs;
    Connection cn = koneksi.KoneskiDatabase.BukaKoneksi();
    /**
     * Creates new form Menu_Utama
     */
    String customer_session = storeData.getCustomerName();
    String mobil_session = storeData.getTipeMobil();
    String thn_session = storeData.getThnMobil();

    public Menu_Utama() {
        initComponents();
    }

    private void CleanPanelCombustion() {
        Input_PistonDiameter.setText("");
        Input_StrokeLength.setText("");
        Input_NumberofCylinders.setText("");
        Input_EngineVolTDC.setText("");
        Input_SparkPlugThreadVol.setText("");
        Input_EngineVolBDC.setText("");
        Input_EngineRotation.setText("");
        Input_EngineVolume.setText("");
        Input_CompressionRatio.setText("");
        Input_SafeRev.setText("");
        Input_EngineRPM.setText("");
        Input_PistonSpeed.setText("");
        Input_SubTotalCombustion.setText("");
    }

    private void CleanPanelTransmission() {
        Input_Secondary1.setText("");
        Input_Secondary2.setText("");
        Input_Secondary3.setText("");
        Input_Secondary4.setText("");
        Input_Secondary5.setText("");
        Input_Secondary6.setText("");

        Input_Primary1.setText("");
        Input_Primary2.setText("");
        Input_Primary3.setText("");
        Input_Primary4.setText("");
        Input_Primary5.setText("");
        Input_Primary6.setText("");

        Input_Ratio1.setText("");
        Input_Ratio2.setText("");
        Input_Ratio3.setText("");
        Input_Ratio4.setText("");
        Input_Ratio5.setText("");
        Input_Ratio6.setText("");

        Input_GapRatio12.setText("");
        Input_GapRatio23.setText("");
        Input_GapRatio34.setText("");
        Input_GapRatio45.setText("");
        Input_GapRatio56.setText("");

        Input_PercentRPMDROP12.setText("");
        Input_PercentRPMDROP23.setText("");
        Input_PercentRPMDROP34.setText("");
        Input_PercentRPMDROP45.setText("");
        Input_PercentRPMDROP56.setText("");

        Input_RPM12.setText("");
        Input_RPM23.setText("");
        Input_RPM34.setText("");
        Input_RPM45.setText("");
        Input_RPM56.setText("");
        Input_SubTotalTransmission.setText("");
    }

    private void CleanPanelAFR() {
        Input_EngineVolumeAFR.setText("");
        Input_MaxRPMAFR.setText("");
        CarburatorDiameter_AFR.setText("");
        Input_SubTotalAFR.setText("");
    }

    private void CleanPanelElectrical() {
        Input_BatteryAmperage.setText("");
        Input_BatteryOhm.setText("");
        Input_BatteryVoltage.setText("");
        Input_BatteryHealth.setText("");
        Input_SubTotalCombustion.setText("");
    }

    private void CountEngineVolume() {
        try {

            double pistonDiameter = Double.parseDouble(Input_PistonDiameter.getText()) / 2;
            double strokeLength = Double.parseDouble(Input_StrokeLength.getText());
            int totalCylinder;

            if (Input_NumberofCylinders.getText().equals("")) {
                totalCylinder = 1;
                Input_NumberofCylinders.setText("1");
            } else {
                totalCylinder = Integer.parseInt(Input_NumberofCylinders.getText());
            }
            double FindEngineVolume = ((3.14 * (pistonDiameter) * (pistonDiameter) * strokeLength) * totalCylinder) / 1000;
            Input_EngineVolume.setText(String.format("%.0f  CC", FindEngineVolume));
            Input_EngineVolBDC.setText(String.format("%.0f", FindEngineVolume / totalCylinder));

        } catch (Exception e) {
        }
        btnCountCombustion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                InsertCustomerData();
            }
        });
    }

    private void CountCompressionRatio() {
        try {
            double volumeTDC = Double.parseDouble(Input_EngineVolTDC.getText());
            double volumeSpark = Double.parseDouble(Input_SparkPlugThreadVol.getText());
            double volumeBDC = Double.parseDouble(Input_EngineVolBDC.getText());

            double FindCompressionRatio = (volumeTDC + volumeSpark + volumeBDC) / (volumeSpark + volumeTDC);
            Input_CompressionRatio.setText(String.format("%.2f  : 1", FindCompressionRatio));
        } catch (Exception e) {
        }
    }

    private void CountSafeRev() {
        try {
            String pistonSpeed = null;
            st = cn.createStatement();
            String sql = "SELECT * FROM `detail_mobil` WHERE fk_tipe_mobil  = '" + mobil_session + "' AND `thn_pembuatan` = '" + thn_session + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                pistonSpeed = rs.getString("piston_speed");
            }
            double PS = Double.parseDouble(pistonSpeed);
            double strokeLength = Double.parseDouble(Input_StrokeLength.getText()) / 1000;
            double FindCountSafeRev = (60 * PS) / (2 * strokeLength);
            Input_SafeRev.setText(String.format("%.2f RPM", FindCountSafeRev));
        } catch (Exception e) {
        }
    }

    private void CountEngineRPM() {
        try {
            String engineRPM = null;
            st = cn.createStatement();
            String sql = "SELECT * FROM `detail_mobil` WHERE fk_tipe_mobil  ='" + mobil_session + "' AND `thn_pembuatan` = '" + thn_session + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                engineRPM = rs.getString("maximum_rpm");
            }
            Input_EngineRPM.setText(engineRPM + " RPM");
        } catch (Exception e) {
        }
    }

    private void CountPistonSpeed() {
        try {

            double engineRotation = Double.parseDouble(Input_EngineRotation.getText());
            double strokeLength = Double.parseDouble(Input_StrokeLength.getText());

            double FindPistonSpeed = 2 * (strokeLength / 1000) * engineRotation / 60;
            Input_PistonSpeed.setText(String.format("%.2f  M/s", FindPistonSpeed));
        } catch (Exception e) {
        }
    }

    private void CountAFR() {
        try {
            double engineVolume = Double.parseDouble(Input_EngineVolumeAFR.getText()) / 1000;
            double maxRPM = Double.parseDouble(Input_MaxRPMAFR.getText());
            double totalCylinders = Double.parseDouble(Input_TotalOfCylinders.getText());

            double FindAFR = (0.65 * Math.sqrt(engineVolume * maxRPM)) / totalCylinders;
            CarburatorDiameter_AFR.setText(String.format("%.2f mm / Cylinder", FindAFR));
        } catch (Exception e) {
        }
    }

    private void CountTansmission() {
        try {
            double secondary1 = Integer.parseInt(Input_Secondary1.getText());
            double primary1 = Integer.parseInt(Input_Primary1.getText());
            double ratio1 = secondary1 / primary1;
            Input_Ratio1.setText(String.format("%.2f", ratio1));

            double secondary2 = Integer.parseInt(Input_Secondary2.getText());
            double primary2 = Integer.parseInt(Input_Primary2.getText());
            double ratio2 = secondary2 / primary2;
            Input_Ratio2.setText(String.format("%.2f", ratio2));
            double gap12 = ratio1 - ratio2;
            Input_GapRatio12.setText(String.format("%.2f", gap12));
            double percentDrop1 = (gap12 / ratio1) * 100;
            Input_PercentRPMDROP12.setText(String.format("%.0f", percentDrop1 ));

            double secondary3 = Integer.parseInt(Input_Secondary3.getText());
            double primary3 = Integer.parseInt(Input_Primary3.getText());
            double ratio3 = secondary3 / primary3;
            Input_Ratio3.setText(String.format("%.2f", ratio3));
            double gap23 = ratio2 - ratio3;
            Input_GapRatio23.setText(String.format("%.2f", gap23));
            double percentDrop2 = (gap23 / ratio2) * 100;
            Input_PercentRPMDROP23.setText(String.format("%.0f", percentDrop2 ));

            double secondary4 = Integer.parseInt(Input_Secondary4.getText());
            double primary4 = Integer.parseInt(Input_Primary4.getText());
            double ratio4 = secondary4 / primary4;
            Input_Ratio4.setText(String.format("%.2f", ratio4));
            double gap34 = ratio3 - ratio4;
            Input_GapRatio34.setText(String.format("%.2f", gap34));
            double percentDrop3 = (gap12 / ratio3) * 100;
            Input_PercentRPMDROP34.setText(String.format("%.0f", percentDrop3 ));

            double secondary5 = Integer.parseInt(Input_Secondary5.getText());
            double primary5 = Integer.parseInt(Input_Primary5.getText());
            double ratio5 = secondary5 / primary5;
            Input_Ratio5.setText(String.format("%.2f", ratio5));
            double gap45 = ratio4 - ratio5;
            Input_GapRatio23.setText(String.format("%.2f", gap45));
            double percentDrop4 = (gap12 / ratio4) * 100;
            Input_PercentRPMDROP45.setText(String.format("%.0f", percentDrop4 ));

            double secondary6 = Integer.parseInt(Input_Secondary6.getText());
            double primary6 = Integer.parseInt(Input_Primary6.getText());
            double ratio6 = secondary6 / primary6;
            Input_Ratio6.setText(String.format("%.2f", ratio6));
            double gap56 = ratio5 - ratio6;
            Input_GapRatio23.setText(String.format("%.2f", gap56));
            double percentDrop6 = (gap12 / ratio5) * 100;
            Input_PercentRPMDROP56.setText(String.format("%.0f", percentDrop6 ));

        } catch (Exception e) {
        }

    }

    private void InsertCustomerData() {
        try {
            String sql = "UPDATE rekam SET piston_diameter = '" + Input_PistonDiameter.getText() + "',stroke_length = '" + Input_StrokeLength.getText() + "' WHERE fk_konsumer = '" + customer_session + "'";

            rs = st.executeQuery(sql);

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabCombustion = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel7 = new com.k33ptoo.components.KGradientPanel();
        panelShadow2 = new style.CustomJPanel.PanelShadow.PanelShadow();
        Input_SubTotalCombustion = new style.CustomJTextField.TFShadow.TextField();
        jLabel4 = new javax.swing.JLabel();
        Input_CompressionRatio = new style.CustomJTextField.TFShadow.TextField();
        jLabel5 = new javax.swing.JLabel();
        Input_EngineRPM = new style.CustomJTextField.TFShadow.TextField();
        jLabel7 = new javax.swing.JLabel();
        Input_PistonSpeed = new style.CustomJTextField.TFShadow.TextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Input_SafeRev = new style.CustomJTextField.TFShadow.TextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Combustion = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        Input_EngineVolume = new style.CustomJTextField.TFShadow.TextField();
        jLabel10 = new javax.swing.JLabel();
        panelShadow3 = new style.CustomJPanel.PanelShadow.PanelShadow();
        Input_PistonDiameter = new style.CustomJTextField.TFShadow.TextField();
        jLabel11 = new javax.swing.JLabel();
        Input_StrokeLength = new style.CustomJTextField.TFShadow.TextField();
        jLabel12 = new javax.swing.JLabel();
        Input_NumberofCylinders = new style.CustomJTextField.TFShadow.TextField();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        Input_EngineVolTDC = new style.CustomJTextField.TFShadow.TextField();
        jLabel14 = new javax.swing.JLabel();
        Input_SparkPlugThreadVol = new style.CustomJTextField.TFShadow.TextField();
        jLabel15 = new javax.swing.JLabel();
        Input_EngineVolBDC = new style.CustomJTextField.TFShadow.TextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        Input_EngineRotation = new style.CustomJTextField.TFShadow.TextField();
        jLabel17 = new javax.swing.JLabel();
        btnResetCombustion = new style.CustomJButton.ButtonShadow.Button();
        jLabel19 = new javax.swing.JLabel();
        btnCountCombustion = new style.CustomJButton.ButtonShadow.Button();
        tabTransmission = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel8 = new com.k33ptoo.components.KGradientPanel();
        panelShadow4 = new style.CustomJPanel.PanelShadow.PanelShadow();
        Input_SubTotalTransmission = new style.CustomJTextField.TFShadow.TextField();
        jLabel20 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        Input_Ratio1 = new style.CustomJTextField.TFShadow.TextField();
        Input_Ratio2 = new style.CustomJTextField.TFShadow.TextField();
        Input_Ratio3 = new style.CustomJTextField.TFShadow.TextField();
        Input_Ratio4 = new style.CustomJTextField.TFShadow.TextField();
        Input_Ratio5 = new style.CustomJTextField.TFShadow.TextField();
        Input_Ratio6 = new style.CustomJTextField.TFShadow.TextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        Input_GapRatio12 = new style.CustomJTextField.TFShadow.TextField();
        Input_GapRatio23 = new style.CustomJTextField.TFShadow.TextField();
        Input_GapRatio34 = new style.CustomJTextField.TFShadow.TextField();
        Input_GapRatio45 = new style.CustomJTextField.TFShadow.TextField();
        Input_GapRatio56 = new style.CustomJTextField.TFShadow.TextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        Input_PercentRPMDROP12 = new style.CustomJTextField.TFShadow.TextField();
        Input_PercentRPMDROP23 = new style.CustomJTextField.TFShadow.TextField();
        Input_PercentRPMDROP34 = new style.CustomJTextField.TFShadow.TextField();
        Input_PercentRPMDROP45 = new style.CustomJTextField.TFShadow.TextField();
        Input_PercentRPMDROP56 = new style.CustomJTextField.TFShadow.TextField();
        Input_RPM12 = new style.CustomJTextField.TFShadow.TextField();
        Input_RPM23 = new style.CustomJTextField.TFShadow.TextField();
        Input_RPM34 = new style.CustomJTextField.TFShadow.TextField();
        Input_RPM45 = new style.CustomJTextField.TFShadow.TextField();
        Input_RPM56 = new style.CustomJTextField.TFShadow.TextField();
        jSeparator9 = new javax.swing.JSeparator();
        panelShadow5 = new style.CustomJPanel.PanelShadow.PanelShadow();
        jSeparator7 = new javax.swing.JSeparator();
        Input_MaximumEngineRevolution = new style.CustomJTextField.TFShadow.TextField();
        jLabel34 = new javax.swing.JLabel();
        btnResetTransmission = new style.CustomJButton.ButtonShadow.Button();
        jLabel35 = new javax.swing.JLabel();
        btnCountTranssmission = new style.CustomJButton.ButtonShadow.Button();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        Input_Secondary1 = new style.CustomJTextField.TFShadow.TextField();
        Input_Secondary2 = new style.CustomJTextField.TFShadow.TextField();
        Input_Secondary3 = new style.CustomJTextField.TFShadow.TextField();
        Input_Secondary4 = new style.CustomJTextField.TFShadow.TextField();
        Input_Secondary5 = new style.CustomJTextField.TFShadow.TextField();
        Input_Secondary6 = new style.CustomJTextField.TFShadow.TextField();
        jLabel38 = new javax.swing.JLabel();
        Input_Primary1 = new style.CustomJTextField.TFShadow.TextField();
        Input_Primary2 = new style.CustomJTextField.TFShadow.TextField();
        Input_Primary3 = new style.CustomJTextField.TFShadow.TextField();
        Input_Primary4 = new style.CustomJTextField.TFShadow.TextField();
        Input_Primary5 = new style.CustomJTextField.TFShadow.TextField();
        Input_Primary6 = new style.CustomJTextField.TFShadow.TextField();
        jSeparator8 = new javax.swing.JSeparator();
        tabAFR = new com.k33ptoo.components.KGradientPanel();
        tabCombustion1 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel9 = new com.k33ptoo.components.KGradientPanel();
        panelShadow6 = new style.CustomJPanel.PanelShadow.PanelShadow();
        Input_SubTotalAFR = new style.CustomJTextField.TFShadow.TextField();
        jLabel21 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel57 = new javax.swing.JLabel();
        CarburatorDiameter_AFR = new style.CustomJTextField.TFShadow.TextField();
        jLabel58 = new javax.swing.JLabel();
        panelShadow7 = new style.CustomJPanel.PanelShadow.PanelShadow();
        Input_EngineVolumeAFR = new style.CustomJTextField.TFShadow.TextField();
        jLabel59 = new javax.swing.JLabel();
        Input_MaxRPMAFR = new style.CustomJTextField.TFShadow.TextField();
        jLabel60 = new javax.swing.JLabel();
        btnResetAFR = new style.CustomJButton.ButtonShadow.Button();
        jLabel66 = new javax.swing.JLabel();
        btnCountAFR = new style.CustomJButton.ButtonShadow.Button();
        Input_TotalOfCylinders = new style.CustomJTextField.TFShadow.TextField();
        jLabel69 = new javax.swing.JLabel();
        tabElectrical = new com.k33ptoo.components.KGradientPanel();
        tabCombustion2 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel10 = new com.k33ptoo.components.KGradientPanel();
        panelShadow8 = new style.CustomJPanel.PanelShadow.PanelShadow();
        textField17 = new style.CustomJTextField.TFShadow.TextField();
        jLabel22 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        Input_BatteryHealth = new style.CustomJTextField.TFShadow.TextField();
        jLabel63 = new javax.swing.JLabel();
        panelShadow9 = new style.CustomJPanel.PanelShadow.PanelShadow();
        Input_BatteryAmperage = new style.CustomJTextField.TFShadow.TextField();
        jLabel64 = new javax.swing.JLabel();
        Input_BatteryOhm = new style.CustomJTextField.TFShadow.TextField();
        jLabel65 = new javax.swing.JLabel();
        btnResetElectrical = new style.CustomJButton.ButtonShadow.Button();
        jLabel67 = new javax.swing.JLabel();
        btnCountElectrical = new style.CustomJButton.ButtonShadow.Button();
        Input_BatteryVoltage = new style.CustomJTextField.TFShadow.TextField();
        jLabel68 = new javax.swing.JLabel();
        tabPrint = new com.k33ptoo.components.KGradientPanel();
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

        tabCombustion.setkBorderRadius(5);
        tabCombustion.setkEndColor(new java.awt.Color(204, 204, 255));
        tabCombustion.setkStartColor(new java.awt.Color(255, 255, 255));

        kGradientPanel7.setkBorderRadius(5);
        kGradientPanel7.setkEndColor(new java.awt.Color(204, 204, 255));
        kGradientPanel7.setkStartColor(new java.awt.Color(255, 255, 255));

        panelShadow2.setBackground(new java.awt.Color(219, 219, 255));
        panelShadow2.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow2.setShadowOpacity(0.3F);
        panelShadow2.setShadowSize(30);
        panelShadow2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Input_SubTotalCombustion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Input_SubTotalCombustion.setShadowColor(new java.awt.Color(0, 255, 0));
        Input_SubTotalCombustion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input_SubTotalCombustionActionPerformed(evt);
            }
        });
        panelShadow2.add(Input_SubTotalCombustion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 340, -1));

        jLabel4.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Sub Total : Rp.");
        panelShadow2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 170, 50));

        Input_CompressionRatio.setForeground(new java.awt.Color(204, 0, 0));
        Input_CompressionRatio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Input_CompressionRatio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input_CompressionRatioActionPerformed(evt);
            }
        });
        panelShadow2.add(Input_CompressionRatio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 194, -1));

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Compression Ratio");
        panelShadow2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        Input_EngineRPM.setForeground(new java.awt.Color(204, 0, 0));
        Input_EngineRPM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelShadow2.add(Input_EngineRPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 194, -1));

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Engine RPM");
        panelShadow2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        Input_PistonSpeed.setForeground(new java.awt.Color(204, 0, 0));
        Input_PistonSpeed.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelShadow2.add(Input_PistonSpeed, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 194, -1));

        jLabel8.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Piston Speed");
        panelShadow2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 102));
        jSeparator2.setOpaque(true);
        panelShadow2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 447, 2));

        Input_SafeRev.setForeground(new java.awt.Color(204, 0, 0));
        Input_SafeRev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelShadow2.add(Input_SafeRev, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 194, -1));

        jLabel18.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Safe Rev");
        panelShadow2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Engine Volume");
        panelShadow2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        Table_Combustion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Items Name", "Unit Price (Rp)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table_Combustion);

        panelShadow2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 480, 140));

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("CALCULATION RESULTS");
        panelShadow2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        Input_EngineVolume.setForeground(new java.awt.Color(204, 0, 0));
        Input_EngineVolume.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Input_EngineVolume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input_EngineVolumeActionPerformed(evt);
            }
        });
        panelShadow2.add(Input_EngineVolume, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 194, -1));

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("RECOMMENDATION");
        panelShadow2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, -1, -1));

        panelShadow3.setBackground(new java.awt.Color(204, 204, 255));
        panelShadow3.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow3.setShadowOpacity(0.3F);
        panelShadow3.setShadowSize(30);
        panelShadow3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Input_PistonDiameter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow3.add(Input_PistonDiameter, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 194, -1));

        jLabel11.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Piston Diameter");
        panelShadow3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        Input_StrokeLength.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow3.add(Input_StrokeLength, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 194, -1));

        jLabel12.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Stroke Length");
        panelShadow3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        Input_NumberofCylinders.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow3.add(Input_NumberofCylinders, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 194, -1));

        jLabel13.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Total of Cylinder's");
        panelShadow3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 102));
        jSeparator3.setOpaque(true);
        panelShadow3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 2, 240));

        Input_EngineVolTDC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow3.add(Input_EngineVolTDC, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 194, -1));

        jLabel14.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Engine Volume When TDC");
        panelShadow3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, -1, -1));

        Input_SparkPlugThreadVol.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow3.add(Input_SparkPlugThreadVol, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 194, -1));

        jLabel15.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Spark Plug Thread Volume");
        panelShadow3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, -1));

        Input_EngineVolBDC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow3.add(Input_EngineVolBDC, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 194, -1));

        jLabel16.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Engine Volume When BDC");
        panelShadow3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, -1, -1));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 102));
        jSeparator4.setOpaque(true);
        panelShadow3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 447, 2));

        Input_EngineRotation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow3.add(Input_EngineRotation, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 189, -1));

        jLabel17.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Engine Rotation");
        panelShadow3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, -1, -1));

        btnResetCombustion.setForeground(new java.awt.Color(0, 0, 0));
        btnResetCombustion.setText("Reset");
        btnResetCombustion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnResetCombustion.setRound(40);
        btnResetCombustion.setShadowColor(new java.awt.Color(255, 0, 0));
        btnResetCombustion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetCombustionActionPerformed(evt);
            }
        });
        panelShadow3.add(btnResetCombustion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 170, -1));

        jLabel19.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("VALUE INPUT");
        panelShadow3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        btnCountCombustion.setForeground(new java.awt.Color(0, 0, 0));
        btnCountCombustion.setText("Count");
        btnCountCombustion.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCountCombustion.setRound(40);
        btnCountCombustion.setShadowColor(new java.awt.Color(255, 0, 0));
        btnCountCombustion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountCombustionActionPerformed(evt);
            }
        });
        panelShadow3.add(btnCountCombustion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 550, 170, -1));

        javax.swing.GroupLayout kGradientPanel7Layout = new javax.swing.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel7Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(panelShadow3, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShadow3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout tabCombustionLayout = new javax.swing.GroupLayout(tabCombustion);
        tabCombustion.setLayout(tabCombustionLayout);
        tabCombustionLayout.setHorizontalGroup(
            tabCombustionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabCombustionLayout.setVerticalGroup(
            tabCombustionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Combustion", tabCombustion);

        tabTransmission.setkBorderRadius(5);
        tabTransmission.setkEndColor(new java.awt.Color(204, 204, 255));
        tabTransmission.setkStartColor(new java.awt.Color(255, 255, 255));

        kGradientPanel8.setkBorderRadius(5);
        kGradientPanel8.setkEndColor(new java.awt.Color(204, 204, 255));
        kGradientPanel8.setkStartColor(new java.awt.Color(255, 255, 255));

        panelShadow4.setBackground(new java.awt.Color(219, 219, 255));
        panelShadow4.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow4.setShadowOpacity(0.3F);
        panelShadow4.setShadowSize(30);
        panelShadow4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Input_SubTotalTransmission.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Input_SubTotalTransmission.setShadowColor(new java.awt.Color(0, 255, 0));
        Input_SubTotalTransmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input_SubTotalTransmissionActionPerformed(evt);
            }
        });
        panelShadow4.add(Input_SubTotalTransmission, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 340, -1));

        jLabel20.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Sub Total : Rp.");
        panelShadow4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 170, 50));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 204));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 204));
        jSeparator5.setOpaque(true);
        panelShadow4.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 160, 2));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Items Name", "Unit Price (Rp)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        panelShadow4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 480, 80));

        jLabel26.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("CALCULATION RESULTS");
        panelShadow4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        jLabel27.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("RECOMMENDATION");
        panelShadow4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, -1, -1));

        jLabel43.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("4    :");
        panelShadow4.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 264, 50, 70));

        jLabel41.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("2    :");
        panelShadow4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 164, 40, 60));

        jLabel44.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("5    :");
        panelShadow4.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 50, 50));

        jLabel45.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("6    :");
        panelShadow4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 374, 40, 50));

        jLabel39.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("%");
        panelShadow4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jLabel42.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("3    :");
        panelShadow4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 224, 40, 40));

        Input_Ratio1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_Ratio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 60, -1));

        Input_Ratio2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_Ratio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 60, -1));

        Input_Ratio3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_Ratio3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 60, -1));

        Input_Ratio4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_Ratio4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 60, -1));

        Input_Ratio5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_Ratio5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 60, -1));

        Input_Ratio6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_Ratio6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 60, -1));

        jLabel46.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Ratio");
        panelShadow4.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        jLabel47.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Gear");
        panelShadow4.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 60, -1));

        jLabel48.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("1    :");
        panelShadow4.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 40, 50));

        jLabel40.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("5-6");
        panelShadow4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 354, -1, 50));

        jLabel54.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("1-2");
        panelShadow4.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, -1, 50));

        jLabel50.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("2-3");
        panelShadow4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 204, -1, 50));

        jLabel51.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("3-4");
        panelShadow4.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 264, -1, 30));

        jLabel52.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("4-5");
        panelShadow4.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 304, -1, 50));

        Input_GapRatio12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_GapRatio12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 70, -1));

        Input_GapRatio23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_GapRatio23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 70, -1));

        Input_GapRatio34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_GapRatio34, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 70, -1));

        Input_GapRatio45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_GapRatio45, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 70, -1));

        Input_GapRatio56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_GapRatio56, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 70, -1));

        jLabel49.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("Gap Ratio");
        panelShadow4.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, -1));

        jLabel53.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("RPM");
        panelShadow4.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, -1, -1));

        jLabel55.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("RPM Drop");
        panelShadow4.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        Input_PercentRPMDROP12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_PercentRPMDROP12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 70, -1));

        Input_PercentRPMDROP23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_PercentRPMDROP23, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 70, -1));

        Input_PercentRPMDROP34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_PercentRPMDROP34, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 70, -1));

        Input_PercentRPMDROP45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_PercentRPMDROP45, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 70, -1));

        Input_PercentRPMDROP56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_PercentRPMDROP56, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 70, -1));

        Input_RPM12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_RPM12, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 70, -1));

        Input_RPM23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_RPM23, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 70, -1));

        Input_RPM34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_RPM34, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 70, -1));

        Input_RPM45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_RPM45, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 70, -1));

        Input_RPM56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow4.add(Input_RPM56, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 360, 70, -1));

        jSeparator9.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 102));
        jSeparator9.setOpaque(true);
        panelShadow4.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 447, 2));

        panelShadow5.setBackground(new java.awt.Color(204, 204, 255));
        panelShadow5.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow5.setShadowOpacity(0.3F);
        panelShadow5.setShadowSize(30);
        panelShadow5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator7.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 102));
        jSeparator7.setOpaque(true);
        panelShadow5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 400, 2));

        Input_MaximumEngineRevolution.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_MaximumEngineRevolution, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 189, -1));

        jLabel34.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Max. Engine Revolution");
        panelShadow5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, -1, -1));

        btnResetTransmission.setForeground(new java.awt.Color(0, 0, 0));
        btnResetTransmission.setText("Reset");
        btnResetTransmission.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnResetTransmission.setRound(40);
        btnResetTransmission.setShadowColor(new java.awt.Color(255, 0, 0));
        btnResetTransmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTransmissionActionPerformed(evt);
            }
        });
        panelShadow5.add(btnResetTransmission, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 170, -1));

        jLabel35.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("VALUE INPUT");
        panelShadow5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        btnCountTranssmission.setForeground(new java.awt.Color(0, 0, 0));
        btnCountTranssmission.setText("Count");
        btnCountTranssmission.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCountTranssmission.setRound(40);
        btnCountTranssmission.setShadowColor(new java.awt.Color(255, 0, 0));
        btnCountTranssmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountTranssmissionActionPerformed(evt);
            }
        });
        panelShadow5.add(btnCountTranssmission, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 550, 170, -1));

        jLabel29.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Primary");
        panelShadow5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, -1));

        jLabel28.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("6    :");
        panelShadow5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 384, 40, 50));

        jLabel30.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Gear");
        panelShadow5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        jLabel31.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("1    :");
        panelShadow5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 40, 50));

        jLabel32.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("2    :");
        panelShadow5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 184, 40, 50));

        jLabel33.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("3    :");
        panelShadow5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 234, 40, 50));

        jLabel36.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("4    :");
        panelShadow5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 284, 50, 50));

        jLabel37.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("5    :");
        panelShadow5.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 324, 50, 70));

        Input_Secondary1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Secondary1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 110, -1));

        Input_Secondary2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Secondary2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 110, -1));

        Input_Secondary3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Secondary3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 110, -1));

        Input_Secondary4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Secondary4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 110, -1));

        Input_Secondary5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Secondary5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 110, -1));

        Input_Secondary6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Secondary6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 110, -1));

        jLabel38.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("Secondary");
        panelShadow5.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 80, -1));

        Input_Primary1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Primary1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 110, -1));

        Input_Primary2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Primary2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 110, -1));

        Input_Primary3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Primary3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 110, -1));

        Input_Primary4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Primary4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 110, -1));

        Input_Primary5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Primary5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 110, -1));

        Input_Primary6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow5.add(Input_Primary6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 110, -1));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 102));
        jSeparator8.setOpaque(true);
        panelShadow5.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 2, 300));

        javax.swing.GroupLayout kGradientPanel8Layout = new javax.swing.GroupLayout(kGradientPanel8);
        kGradientPanel8.setLayout(kGradientPanel8Layout);
        kGradientPanel8Layout.setHorizontalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel8Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(panelShadow5, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        kGradientPanel8Layout.setVerticalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShadow5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow4, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout tabTransmissionLayout = new javax.swing.GroupLayout(tabTransmission);
        tabTransmission.setLayout(tabTransmissionLayout);
        tabTransmissionLayout.setHorizontalGroup(
            tabTransmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabTransmissionLayout.setVerticalGroup(
            tabTransmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Transmission", tabTransmission);

        tabAFR.setkBorderRadius(5);
        tabAFR.setkEndColor(new java.awt.Color(204, 204, 255));
        tabAFR.setkStartColor(new java.awt.Color(255, 255, 255));

        tabCombustion1.setkBorderRadius(5);
        tabCombustion1.setkEndColor(new java.awt.Color(204, 204, 255));
        tabCombustion1.setkStartColor(new java.awt.Color(255, 255, 255));

        kGradientPanel9.setkBorderRadius(5);
        kGradientPanel9.setkEndColor(new java.awt.Color(204, 204, 255));
        kGradientPanel9.setkStartColor(new java.awt.Color(255, 255, 255));

        panelShadow6.setBackground(new java.awt.Color(219, 219, 255));
        panelShadow6.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow6.setShadowOpacity(0.3F);
        panelShadow6.setShadowSize(30);
        panelShadow6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Input_SubTotalAFR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Input_SubTotalAFR.setShadowColor(new java.awt.Color(0, 255, 0));
        Input_SubTotalAFR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input_SubTotalAFRActionPerformed(evt);
            }
        });
        panelShadow6.add(Input_SubTotalAFR, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 340, -1));

        jLabel21.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Sub Total : Rp.");
        panelShadow6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 170, 50));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 102));
        jSeparator6.setOpaque(true);
        panelShadow6.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 447, 2));

        jLabel56.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("Carburator Diameter");
        panelShadow6.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Items Name", "Unit Price (Rp)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        panelShadow6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 480, 290));

        jLabel57.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("CALCULATION RESULTS");
        panelShadow6.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        CarburatorDiameter_AFR.setForeground(new java.awt.Color(204, 0, 0));
        CarburatorDiameter_AFR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelShadow6.add(CarburatorDiameter_AFR, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 194, -1));

        jLabel58.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("RECOMMENDATION");
        panelShadow6.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        panelShadow7.setBackground(new java.awt.Color(204, 204, 255));
        panelShadow7.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow7.setShadowOpacity(0.3F);
        panelShadow7.setShadowSize(30);
        panelShadow7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Input_EngineVolumeAFR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow7.add(Input_EngineVolumeAFR, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 194, -1));

        jLabel59.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("Engine Volume");
        panelShadow7.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        Input_MaxRPMAFR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow7.add(Input_MaxRPMAFR, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 194, -1));

        jLabel60.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("Max. Engine Revolution");
        panelShadow7.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, -1));

        btnResetAFR.setForeground(new java.awt.Color(0, 0, 0));
        btnResetAFR.setText("Reset");
        btnResetAFR.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnResetAFR.setRound(40);
        btnResetAFR.setShadowColor(new java.awt.Color(255, 0, 0));
        btnResetAFR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetAFRActionPerformed(evt);
            }
        });
        panelShadow7.add(btnResetAFR, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 170, -1));

        jLabel66.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("VALUE INPUT");
        panelShadow7.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        btnCountAFR.setForeground(new java.awt.Color(0, 0, 0));
        btnCountAFR.setText("Count");
        btnCountAFR.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCountAFR.setRound(40);
        btnCountAFR.setShadowColor(new java.awt.Color(255, 0, 0));
        btnCountAFR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountAFRActionPerformed(evt);
            }
        });
        panelShadow7.add(btnCountAFR, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 550, 170, -1));

        Input_TotalOfCylinders.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow7.add(Input_TotalOfCylinders, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 194, -1));

        jLabel69.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Total of Cylinders");
        panelShadow7.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        javax.swing.GroupLayout kGradientPanel9Layout = new javax.swing.GroupLayout(kGradientPanel9);
        kGradientPanel9.setLayout(kGradientPanel9Layout);
        kGradientPanel9Layout.setHorizontalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel9Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(panelShadow7, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(panelShadow6, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        kGradientPanel9Layout.setVerticalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShadow7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow6, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout tabCombustion1Layout = new javax.swing.GroupLayout(tabCombustion1);
        tabCombustion1.setLayout(tabCombustion1Layout);
        tabCombustion1Layout.setHorizontalGroup(
            tabCombustion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabCombustion1Layout.setVerticalGroup(
            tabCombustion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tabAFRLayout = new javax.swing.GroupLayout(tabAFR);
        tabAFR.setLayout(tabAFRLayout);
        tabAFRLayout.setHorizontalGroup(
            tabAFRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(tabAFRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabAFRLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tabCombustion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        tabAFRLayout.setVerticalGroup(
            tabAFRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
            .addGroup(tabAFRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabAFRLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tabCombustion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Air–Fuel Ratio (AFR)", tabAFR);

        tabElectrical.setkBorderRadius(5);
        tabElectrical.setkEndColor(new java.awt.Color(204, 204, 255));
        tabElectrical.setkStartColor(new java.awt.Color(255, 255, 255));

        tabCombustion2.setkBorderRadius(5);
        tabCombustion2.setkEndColor(new java.awt.Color(204, 204, 255));
        tabCombustion2.setkStartColor(new java.awt.Color(255, 255, 255));

        kGradientPanel10.setkBorderRadius(5);
        kGradientPanel10.setkEndColor(new java.awt.Color(204, 204, 255));
        kGradientPanel10.setkStartColor(new java.awt.Color(255, 255, 255));

        panelShadow8.setBackground(new java.awt.Color(219, 219, 255));
        panelShadow8.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow8.setShadowOpacity(0.3F);
        panelShadow8.setShadowSize(30);
        panelShadow8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textField17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textField17.setShadowColor(new java.awt.Color(0, 255, 0));
        textField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField17ActionPerformed(evt);
            }
        });
        panelShadow8.add(textField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 340, -1));

        jLabel22.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Sub Total : Rp.");
        panelShadow8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 170, 50));

        jSeparator10.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator10.setForeground(new java.awt.Color(0, 0, 102));
        jSeparator10.setOpaque(true);
        panelShadow8.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 447, 2));

        jLabel61.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("Battery Health");
        panelShadow8.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Items Name", "Unit Price (Rp)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        panelShadow8.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 480, 290));

        jLabel62.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("CALCULATION RESULTS");
        panelShadow8.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        Input_BatteryHealth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow8.add(Input_BatteryHealth, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 194, -1));

        jLabel63.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("RECOMMENDATION");
        panelShadow8.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        panelShadow9.setBackground(new java.awt.Color(204, 204, 255));
        panelShadow9.setShadowColor(new java.awt.Color(0, 0, 102));
        panelShadow9.setShadowOpacity(0.3F);
        panelShadow9.setShadowSize(30);
        panelShadow9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Input_BatteryAmperage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow9.add(Input_BatteryAmperage, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 194, -1));

        jLabel64.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("Battery Amperage");
        panelShadow9.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        Input_BatteryOhm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        panelShadow9.add(Input_BatteryOhm, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 194, -1));

        jLabel65.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Battery Ohm");
        panelShadow9.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, -1));

        btnResetElectrical.setForeground(new java.awt.Color(0, 0, 0));
        btnResetElectrical.setText("Reset");
        btnResetElectrical.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnResetElectrical.setRound(40);
        btnResetElectrical.setShadowColor(new java.awt.Color(255, 0, 0));
        btnResetElectrical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetElectricalActionPerformed(evt);
            }
        });
        panelShadow9.add(btnResetElectrical, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 170, -1));

        jLabel67.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("VALUE INPUT");
        panelShadow9.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        btnCountElectrical.setForeground(new java.awt.Color(0, 0, 0));
        btnCountElectrical.setText("Count");
        btnCountElectrical.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCountElectrical.setRound(40);
        btnCountElectrical.setShadowColor(new java.awt.Color(255, 0, 0));
        btnCountElectrical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountElectricalActionPerformed(evt);
            }
        });
        panelShadow9.add(btnCountElectrical, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 550, 170, -1));

        Input_BatteryVoltage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Input_BatteryVoltage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input_BatteryVoltageActionPerformed(evt);
            }
        });
        panelShadow9.add(Input_BatteryVoltage, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 194, -1));

        jLabel68.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Battery Voltage");
        panelShadow9.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        javax.swing.GroupLayout kGradientPanel10Layout = new javax.swing.GroupLayout(kGradientPanel10);
        kGradientPanel10.setLayout(kGradientPanel10Layout);
        kGradientPanel10Layout.setHorizontalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel10Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(panelShadow9, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(panelShadow8, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        kGradientPanel10Layout.setVerticalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShadow9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow8, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout tabCombustion2Layout = new javax.swing.GroupLayout(tabCombustion2);
        tabCombustion2.setLayout(tabCombustion2Layout);
        tabCombustion2Layout.setHorizontalGroup(
            tabCombustion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabCombustion2Layout.setVerticalGroup(
            tabCombustion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tabElectricalLayout = new javax.swing.GroupLayout(tabElectrical);
        tabElectrical.setLayout(tabElectricalLayout);
        tabElectricalLayout.setHorizontalGroup(
            tabElectricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(tabElectricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabElectricalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tabCombustion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        tabElectricalLayout.setVerticalGroup(
            tabElectricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
            .addGroup(tabElectricalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabElectricalLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tabCombustion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Electrical", tabElectrical);

        tabPrint.setkBorderRadius(5);
        tabPrint.setkEndColor(new java.awt.Color(204, 204, 255));
        tabPrint.setkStartColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout tabPrintLayout = new javax.swing.GroupLayout(tabPrint);
        tabPrint.setLayout(tabPrintLayout);
        tabPrintLayout.setHorizontalGroup(
            tabPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        tabPrintLayout.setVerticalGroup(
            tabPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Print", tabPrint);

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
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Combustion");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Input_BatteryVoltageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input_BatteryVoltageActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_Input_BatteryVoltageActionPerformed

    private void btnCountElectricalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountElectricalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCountElectricalActionPerformed

    private void btnResetElectricalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetElectricalActionPerformed
        // TODO add your handling code here:
        CleanPanelElectrical();
    }//GEN-LAST:event_btnResetElectricalActionPerformed

    private void textField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField17ActionPerformed

    private void btnCountAFRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountAFRActionPerformed
        CountAFR();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCountAFRActionPerformed

    private void btnResetAFRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetAFRActionPerformed
        // TODO add your handling code here:
        CleanPanelAFR();
    }//GEN-LAST:event_btnResetAFRActionPerformed

    private void Input_SubTotalAFRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input_SubTotalAFRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Input_SubTotalAFRActionPerformed

    private void btnCountTranssmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountTranssmissionActionPerformed
        CountTansmission();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCountTranssmissionActionPerformed

    private void btnResetTransmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTransmissionActionPerformed
        // TODO add your handling code here:
        CleanPanelTransmission();
    }//GEN-LAST:event_btnResetTransmissionActionPerformed

    private void Input_SubTotalTransmissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input_SubTotalTransmissionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Input_SubTotalTransmissionActionPerformed

    private void btnCountCombustionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountCombustionActionPerformed
        // TODO add your handling code here:
        CountSafeRev();
        CountEngineVolume();
        InsertCustomerData();
        CountCompressionRatio();
        CountPistonSpeed();
        CountEngineRPM();
    }//GEN-LAST:event_btnCountCombustionActionPerformed

    private void btnResetCombustionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetCombustionActionPerformed
        // TODO add your handling code here:
        CleanPanelCombustion();
    }//GEN-LAST:event_btnResetCombustionActionPerformed

    private void Input_EngineVolumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input_EngineVolumeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Input_EngineVolumeActionPerformed

    private void Input_CompressionRatioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input_CompressionRatioActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_Input_CompressionRatioActionPerformed

    private void Input_SubTotalCombustionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input_SubTotalCombustionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Input_SubTotalCombustionActionPerformed

    private void btnAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutUsActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new About_Us().setVisible(true);
    }//GEN-LAST:event_btnAboutUsActionPerformed

    private void Help_ContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Help_ContentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Help_ContentActionPerformed

    private void btnMenuHomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnMenuHomeKeyPressed

        this.setVisible(false);
        new Halaman_Utama().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuHomeKeyPressed

    private void btnMenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuHomeActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Halaman_Utama().setVisible(true);
    }//GEN-LAST:event_btnMenuHomeActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed

        this.setVisible(false);
        new Halaman_Utama().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_ExitActionPerformed

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatIntelliJLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Utama().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private style.CustomJTextField.TFShadow.TextField CarburatorDiameter_AFR;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Help_Content;
    private style.CustomJTextField.TFShadow.TextField Input_BatteryAmperage;
    private style.CustomJTextField.TFShadow.TextField Input_BatteryHealth;
    private style.CustomJTextField.TFShadow.TextField Input_BatteryOhm;
    private style.CustomJTextField.TFShadow.TextField Input_BatteryVoltage;
    private style.CustomJTextField.TFShadow.TextField Input_CompressionRatio;
    private style.CustomJTextField.TFShadow.TextField Input_EngineRPM;
    private style.CustomJTextField.TFShadow.TextField Input_EngineRotation;
    private style.CustomJTextField.TFShadow.TextField Input_EngineVolBDC;
    private style.CustomJTextField.TFShadow.TextField Input_EngineVolTDC;
    private style.CustomJTextField.TFShadow.TextField Input_EngineVolume;
    private style.CustomJTextField.TFShadow.TextField Input_EngineVolumeAFR;
    private style.CustomJTextField.TFShadow.TextField Input_GapRatio12;
    private style.CustomJTextField.TFShadow.TextField Input_GapRatio23;
    private style.CustomJTextField.TFShadow.TextField Input_GapRatio34;
    private style.CustomJTextField.TFShadow.TextField Input_GapRatio45;
    private style.CustomJTextField.TFShadow.TextField Input_GapRatio56;
    private style.CustomJTextField.TFShadow.TextField Input_MaxRPMAFR;
    private style.CustomJTextField.TFShadow.TextField Input_MaximumEngineRevolution;
    private style.CustomJTextField.TFShadow.TextField Input_NumberofCylinders;
    private style.CustomJTextField.TFShadow.TextField Input_PercentRPMDROP12;
    private style.CustomJTextField.TFShadow.TextField Input_PercentRPMDROP23;
    private style.CustomJTextField.TFShadow.TextField Input_PercentRPMDROP34;
    private style.CustomJTextField.TFShadow.TextField Input_PercentRPMDROP45;
    private style.CustomJTextField.TFShadow.TextField Input_PercentRPMDROP56;
    private style.CustomJTextField.TFShadow.TextField Input_PistonDiameter;
    private style.CustomJTextField.TFShadow.TextField Input_PistonSpeed;
    private style.CustomJTextField.TFShadow.TextField Input_Primary1;
    private style.CustomJTextField.TFShadow.TextField Input_Primary2;
    private style.CustomJTextField.TFShadow.TextField Input_Primary3;
    private style.CustomJTextField.TFShadow.TextField Input_Primary4;
    private style.CustomJTextField.TFShadow.TextField Input_Primary5;
    private style.CustomJTextField.TFShadow.TextField Input_Primary6;
    private style.CustomJTextField.TFShadow.TextField Input_RPM12;
    private style.CustomJTextField.TFShadow.TextField Input_RPM23;
    private style.CustomJTextField.TFShadow.TextField Input_RPM34;
    private style.CustomJTextField.TFShadow.TextField Input_RPM45;
    private style.CustomJTextField.TFShadow.TextField Input_RPM56;
    private style.CustomJTextField.TFShadow.TextField Input_Ratio1;
    private style.CustomJTextField.TFShadow.TextField Input_Ratio2;
    private style.CustomJTextField.TFShadow.TextField Input_Ratio3;
    private style.CustomJTextField.TFShadow.TextField Input_Ratio4;
    private style.CustomJTextField.TFShadow.TextField Input_Ratio5;
    private style.CustomJTextField.TFShadow.TextField Input_Ratio6;
    private style.CustomJTextField.TFShadow.TextField Input_SafeRev;
    private style.CustomJTextField.TFShadow.TextField Input_Secondary1;
    private style.CustomJTextField.TFShadow.TextField Input_Secondary2;
    private style.CustomJTextField.TFShadow.TextField Input_Secondary3;
    private style.CustomJTextField.TFShadow.TextField Input_Secondary4;
    private style.CustomJTextField.TFShadow.TextField Input_Secondary5;
    private style.CustomJTextField.TFShadow.TextField Input_Secondary6;
    private style.CustomJTextField.TFShadow.TextField Input_SparkPlugThreadVol;
    private style.CustomJTextField.TFShadow.TextField Input_StrokeLength;
    private style.CustomJTextField.TFShadow.TextField Input_SubTotalAFR;
    private style.CustomJTextField.TFShadow.TextField Input_SubTotalCombustion;
    private style.CustomJTextField.TFShadow.TextField Input_SubTotalTransmission;
    private style.CustomJTextField.TFShadow.TextField Input_TotalOfCylinders;
    private javax.swing.JMenu Menu_File;
    private javax.swing.JMenu Menu_Help;
    private javax.swing.JMenuItem Print;
    private javax.swing.JTable Table_Combustion;
    private javax.swing.JMenuItem btnAboutUs;
    private style.CustomJButton.ButtonShadow.Button btnCountAFR;
    private style.CustomJButton.ButtonShadow.Button btnCountCombustion;
    private style.CustomJButton.ButtonShadow.Button btnCountElectrical;
    private style.CustomJButton.ButtonShadow.Button btnCountTranssmission;
    private javax.swing.JMenu btnMenuHome;
    private style.CustomJButton.ButtonShadow.Button btnResetAFR;
    private style.CustomJButton.ButtonShadow.Button btnResetCombustion;
    private style.CustomJButton.ButtonShadow.Button btnResetElectrical;
    private style.CustomJButton.ButtonShadow.Button btnResetTransmission;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private com.k33ptoo.components.KGradientPanel kGradientPanel10;
    private com.k33ptoo.components.KGradientPanel kGradientPanel7;
    private com.k33ptoo.components.KGradientPanel kGradientPanel8;
    private com.k33ptoo.components.KGradientPanel kGradientPanel9;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow2;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow3;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow4;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow5;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow6;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow7;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow8;
    private style.CustomJPanel.PanelShadow.PanelShadow panelShadow9;
    private com.k33ptoo.components.KGradientPanel tabAFR;
    private com.k33ptoo.components.KGradientPanel tabCombustion;
    private com.k33ptoo.components.KGradientPanel tabCombustion1;
    private com.k33ptoo.components.KGradientPanel tabCombustion2;
    private com.k33ptoo.components.KGradientPanel tabElectrical;
    private com.k33ptoo.components.KGradientPanel tabPrint;
    private com.k33ptoo.components.KGradientPanel tabTransmission;
    private style.CustomJTextField.TFShadow.TextField textField17;
    // End of variables declaration//GEN-END:variables
}

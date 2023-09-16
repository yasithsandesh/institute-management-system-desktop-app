/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.admin;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author 94701
 */
public class AllTeachers extends javax.swing.JPanel {

    /**
     * Creates new form AllStudent
     */
    public AllTeachers() {
        initComponents();
        loadTeachers("");
    }

    private void loadTeachers(String serachText) {

        String type = String.valueOf(jComboBox3.getSelectedItem());

        String query = "";
        if (!serachText.isEmpty()) {

            switch (type) {
                case "Teacher ID":
                    query = "SELECT * FROM `teacher` INNER JOIN `gender` ON `teacher`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `class` ON `teacher`.`tno`=`class`.`teacher_tno` INNER JOIN `subject` ON `class`.`subject_subno`=`subject`.`subno` INNER JOIN `teacher_has_address` ON `teacher`.`tno`=`teacher_has_address`.`teacher_tno` WHERE `tno` LIKE '%" + serachText + "%' ";
                    break;
                case "Teacher Email":

                    query = "SELECT * FROM `teacher` INNER JOIN `gender` ON `teacher`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `class` ON `teacher`.`tno`=`class`.`teacher_tno` INNER JOIN `subject` ON `class`.`subject_subno`=`subject`.`subno`INNER JOIN `teacher_has_address` ON `teacher`.`tno`=`teacher_has_address`.`teacher_tno`  WHERE `email` LIKE '%" + serachText + "%' ";
                    break;

            }

        } else {
            query = "SELECT * FROM `teacher` INNER JOIN `gender` ON `teacher`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `class` ON `teacher`.`tno`=`class`.`teacher_tno` INNER JOIN `subject` ON `class`.`subject_subno`=`subject`.`subno` INNER JOIN `teacher_has_address` ON `teacher`.`tno`=`teacher_has_address`.`teacher_tno` ";

        }

        try {
            ResultSet teacherRs = MySQL.execute(query);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            while (teacherRs.next()) {
                Vector<String> tV = new Vector<>();
                tV.add(teacherRs.getString("tno"));
                tV.add(teacherRs.getString("fname"));
                tV.add(teacherRs.getString("lname"));
                tV.add(teacherRs.getString("gender_type"));
                tV.add(teacherRs.getString("tno"));
                tV.add(teacherRs.getString("email"));
                tV.add(teacherRs.getString("mobile"));

                String lineT = teacherRs.getString("line2");

                if (lineT.matches("null")) {
                    tV.add(teacherRs.getString("line1") + ' ' + teacherRs.getString("postal_code"));
                } else {
                    tV.add(teacherRs.getString("line1") + ' ' + teacherRs.getString("line2") + ' ' + teacherRs.getString("postal_code"));
                }
                tV.add(teacherRs.getString("sub_name"));

                model.addRow(tV);
                jTable1.setModel(model);

            }
        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();

        setLayout(new java.awt.GridLayout(1, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher Number", "First Name", "Last Name", "Gender", "Address", "Email", "Mobile", "Registration Date", "Class"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search Type", "Teacher ID", "Teacher Email" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        loadTeachers(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jComboBox3ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
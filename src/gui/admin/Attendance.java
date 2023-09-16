/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.admin;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author 94701
 */
public class Attendance
        extends javax.swing.JPanel {

    private static HashMap<String, String> classIdMap = new HashMap<>();
    private static HashMap<String, String> weekIdMap = new HashMap<>();

    public Attendance() {
        initComponents();
        loadClass();
        loadWeek();
        jButton1.setVisible(false);
        jButton2.setVisible(false);

    }

    private void loadClass() {

        try {

            ResultSet classRS = MySQL.execute("SELECT * FROM `class`");
            Vector<String> classV = new Vector<>();
            classV.add("Select Class");

            while (classRS.next()) {
                classV.add(classRS.getString("className"));
                classIdMap.put(classRS.getString("className"), classRS.getString("classNo"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(classV);
            jComboBox3.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadWeek() {

        try {

            ResultSet classRS = MySQL.execute("SELECT * FROM `week`");
            Vector<String> wV = new Vector<>();
            wV.add("Select Attendance Week");

            while (classRS.next()) {
                wV.add(classRS.getString("w_type"));
                weekIdMap.put(classRS.getString("w_type"), classRS.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(wV);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStudent(String className) {

        try {
            ResultSet studentRs = MySQL.execute("SELECT * FROM `student` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo` WHERE `className`='" + className + "'");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (studentRs.next()) {
                Vector<String> studenTvector = new Vector<>();
                studenTvector.add(studentRs.getString("sno"));
                studenTvector.add(studentRs.getString("fname") + ' ' + studentRs.getString("lname"));
                studenTvector.add(studentRs.getString("class.className"));
                studenTvector.add("20");

                model.addRow(studenTvector);
                jTable1.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void searchStudent(String text, String type, String classId) {

        String col;
        if (type.matches("Student ID")) {
            col = "sno";
        } else {

            col = "email";
        }

        try {

            ResultSet studentRs = MySQL.execute("SELECT * FROM `student_has_class` INNER JOIN `student` ON `student_has_class`.`student_sno`=`student`.`sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`"
                    + " WHERE " + col + " LIKE '%" + text + "%' AND `classNo`='" + classId + "'  ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (studentRs.next()) {
                Vector<String> studenTvector = new Vector<>();
                studenTvector.add(studentRs.getString("sno"));
                studenTvector.add(studentRs.getString("fname") + ' ' + studentRs.getString("lname"));
                studenTvector.add(studentRs.getString("class.className"));
                studenTvector.add("20");

                model.addRow(studenTvector);
                jTable1.setModel(model);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void attendence(int a) {
        int selectedrow = jTable1.getSelectedRow();
        if (selectedrow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select Student", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            String studentId = String.valueOf(jTable1.getValueAt(selectedrow, 0));

            String className = String.valueOf(jComboBox3.getSelectedItem());
            String classId = classIdMap.get(className);

            int monthId = jMonthChooser1.getMonth();
            model.findMonth month = new model.findMonth(monthId);
            String monthName = month.getMonth();

            String weekName = String.valueOf(jComboBox1.getSelectedItem());
            String weekId = weekIdMap.get(weekName);

            System.out.println(studentId);
            System.out.println(classId);
            System.out.println(monthName);
            System.out.println(weekId);
            System.out.println(a);

            try {

                ResultSet findClassMonthAtenRs = MySQL.execute("SELECT * FROM `attendance` WHERE `class_classNo`='" + classId + "' AND `month`='" + monthName + "' AND `week_id`='" + weekId + "'");

                String adenID;

                if (findClassMonthAtenRs.next()) {
                    adenID = findClassMonthAtenRs.getString("id");
       ResultSet stuAtenRs = MySQL.execute("SELECT * FROM `student_has_attendance` WHERE `student_sno`='" + studentId + "' AND `attendance_id`='" + adenID + "' ");
                    if (stuAtenRs.next()) {
                        JOptionPane.showMessageDialog(this, "Attendance Mark", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {

                        MySQL.execute("INSERT INTO `student_has_attendance`(`student_sno`,`attendance_id`,`attendance_type_type_id`)VALUES('" + studentId + "','" + adenID + "','" + a + "')");
                    }
                } else {
//                    adenID = "0";
                    
                         MySQL.execute("INSERT INTO `attendance`(`class_classNo`,`month`,`week_id`)VALUES('" + classId + "','" + monthName + "','" + weekId + "')");
                    ResultSet findClassMonthAtenR = MySQL.execute("SELECT * FROM `attendance` WHERE `class_classNo`='" + classId + "' AND `month`='" + monthName + "' AND `week_id`='" + weekId + "'");

                    if (findClassMonthAtenR.next()) {
                        String ID = findClassMonthAtenR.getString("id");
                        ResultSet stuAtenRs = MySQL.execute("SELECT * FROM `student_has_attendance` WHERE `student_sno`='" + studentId + "' AND `attendance_id`='" + ID + "' ");
                        if (stuAtenRs.next()) {
                            JOptionPane.showMessageDialog(this, "Attendance Mark", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            MySQL.execute("INSERT INTO `student_has_attendance`(`student_sno`,`attendance_id`,`attendance_type_type_id`)VALUES('" + studentId + "','" + ID + "','" + a + "')");
                        }
                    }
                }

//                if (adenID.matches("0")) {
//
//               
//
//                } else {
//
//             
//                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    
    //attendance View

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(966, 610));
        setLayout(new java.awt.GridLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Class" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Class" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search Type", "Student ID", "Student Email" }));

        jButton1.setText("PRESENT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ABSENT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View All Attendance");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 189, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        add(jPanel2);

        jPanel1.setLayout(new java.awt.CardLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Class"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, "card2");

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        String text = jTextField1.getText();
        int selection = jComboBox2.getSelectedIndex();
        String type = String.valueOf(jComboBox2.getSelectedItem());
        String className = String.valueOf(jComboBox3.getSelectedItem());
        String classId = classIdMap.get(className);
        System.out.println(className);

        if (selection == 0) {
            System.out.println("gui.admin.Attendance.jTextField1KeyReleased()");
        } else {
            searchStudent(text, type, classId);
            System.out.println(text);
            System.out.println(type);
            System.out.println(classId);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        String className = String.valueOf(jComboBox3.getSelectedItem());
        if (className.matches("Select Class")) {
            JOptionPane.showMessageDialog(this, "Please select class", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        loadStudent(className);
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {

            jTable1.setEnabled(false);
            jButton1.setVisible(true);
            jButton2.setVisible(true);
            jTextField1.setEnabled(false);
            jComboBox2.setEnabled(false);
            jComboBox3.setEnabled(false);
            jMonthChooser1.setEnabled(false);
            jComboBox1.setEnabled(false);
            jButton3.setVisible(false);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        attendence(2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        attendence(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new AttendanceAllView().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

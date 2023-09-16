/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.admin;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author 94701
 */
public class AllStudent extends javax.swing.JPanel {

    /**
     * Creates new form AllStudent
     */
    public AllStudent() {
        initComponents();
        searchStudent("");
//        loadStudent();
        loadClass();
        loadYear();
    }

    private void loadClass() {

        try {
            ResultSet classRs = MySQL.execute("SELECT * FROM `class`");
            Vector<String> classV = new Vector<>();
            classV.add("Select Class");
            while (classRs.next()) {
                classV.add(classRs.getString("className"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(classV);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadYear() {
        try {
            ResultSet yRs = MySQL.execute("SELECT * from `year`");
            Vector<String> yearV = new Vector<>();
            yearV.add("Select A/L year");
            while (yRs.next()) {
                yearV.add(yRs.getString("yName"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(yearV);
            jComboBox2.setModel(model);
        } catch (Exception e) {
        }
    }

//    private void loadStudent() {
//
//        try {
//            ResultSet studentRs = MySQL.execute("SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` ORDER BY `reg_date` ASC");
//            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//            model.setRowCount(0);
//
//            while (studentRs.next()) {
//                Vector<String> studentVec = new Vector<>();
//
//                studentVec.add(studentRs.getString("sno"));
//                studentVec.add(studentRs.getString("fname"));
//                studentVec.add(studentRs.getString("lname"));
//                studentVec.add(studentRs.getString("gender_type"));
//                String l2 = studentRs.getString("line2");
//
//                if (l2.matches("null")) {
//                    studentVec.add(studentRs.getString("line1") + ' ' + studentRs.getString("postal_code"));
//                } else {
//                    studentVec.add(studentRs.getString("line1") + ' ' + studentRs.getString("line2") + ' ' + studentRs.getString("postal_code"));
//                }
//
//                studentVec.add(studentRs.getString("email"));
//                studentVec.add(studentRs.getString("mobile"));
//                studentVec.add(studentRs.getString("dob"));
//                studentVec.add(studentRs.getString("nic"));
//                studentVec.add(studentRs.getString("reg_date"));
//                studentVec.add(studentRs.getString("yName"));
//
//                model.addRow(studentVec);
//                jTable1.setModel(model);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private void loadStudentSelecterClass() {

        String className = String.valueOf(jComboBox1.getSelectedItem());

        try {
            ResultSet studentRs = MySQL.execute("SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `className`='" + className + "'  ORDER BY `reg_date` ASC");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (studentRs.next()) {
                Vector<String> studentVec = new Vector<>();

                studentVec.add(studentRs.getString("sno"));
                studentVec.add(studentRs.getString("fname"));
                studentVec.add(studentRs.getString("lname"));
                studentVec.add(studentRs.getString("gender_type"));
                String l2 = studentRs.getString("line2");

                if (l2.matches("null")) {
                    studentVec.add(studentRs.getString("line1") + ' ' + studentRs.getString("postal_code"));
                } else {
                    studentVec.add(studentRs.getString("line1") + ' ' + studentRs.getString("line2") + ' ' + studentRs.getString("postal_code"));
                }

                studentVec.add(studentRs.getString("email"));
                studentVec.add(studentRs.getString("mobile"));
                studentVec.add(studentRs.getString("dob"));
                studentVec.add(studentRs.getString("nic"));
                studentVec.add(studentRs.getString("reg_date"));
                studentVec.add(studentRs.getString("yName"));

                model.addRow(studentVec);
                jTable1.setModel(model);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
     private void loadStudentSelecterGender() {

         String gender = String.valueOf(jComboBox4.getSelectedItem());

        try {
            ResultSet studentRs = MySQL.execute("SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `gender_type`='" + gender + "'  ORDER BY `reg_date` ASC");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (studentRs.next()) {
                Vector<String> studentVec = new Vector<>();

                studentVec.add(studentRs.getString("sno"));
                studentVec.add(studentRs.getString("fname"));
                studentVec.add(studentRs.getString("lname"));
                studentVec.add(studentRs.getString("gender_type"));
                String l2 = studentRs.getString("line2");

                if (l2.matches("null")) {
                    studentVec.add(studentRs.getString("line1") + ' ' + studentRs.getString("postal_code"));
                } else {
                    studentVec.add(studentRs.getString("line1") + ' ' + studentRs.getString("line2") + ' ' + studentRs.getString("postal_code"));
                }

                studentVec.add(studentRs.getString("email"));
                studentVec.add(studentRs.getString("mobile"));
                studentVec.add(studentRs.getString("dob"));
                studentVec.add(studentRs.getString("nic"));
                studentVec.add(studentRs.getString("reg_date"));
                studentVec.add(studentRs.getString("yName"));

                model.addRow(studentVec);
                jTable1.setModel(model);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //search student Student ID, Student Email, Student Name, Mobile, NIC
    private void searchStudent(String searchText) {

        String type = String.valueOf(jComboBox3.getSelectedItem());
        String className = String.valueOf(jComboBox1.getSelectedItem());
        String gender = String.valueOf(jComboBox4.getSelectedItem());
        String year = String.valueOf(jComboBox2.getSelectedItem());

        String query = "";
        if (!searchText.isEmpty()) {

            if (className.matches("Select Class") && gender.matches("Select Gender") && year.matches("Select A/L year")) {

                switch (type) {
                    case "Student ID":
                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` WHERE `sno` LIKE '%" + searchText + "%' ORDER BY `reg_date` ASC";
                        break;
                    case "Student Email":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` WHERE `email` LIKE '%" + searchText + "%' ORDER BY `reg_date` ASC";

                        break;

                    case "Student Name":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` WHERE `fname` OR `lname` LIKE '%" + searchText + "%' ORDER BY `reg_date` ASC";

                        break;
                    case "Mobile":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` WHERE `mobile` LIKE '%" + searchText + "%' ORDER BY `reg_date` ASC";

                        break;

                    case "NIC":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` WHERE `nic` LIKE '%" + searchText + "%' ORDER BY `reg_date` ASC";

                        break;

                }
            } else if (!className.matches("Select Class") && gender.matches("Select Gender") && year.matches("Select A/L year")) {

                switch (type) {
                    case "Student ID":
                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `sno` LIKE '%" + searchText + "%' AND `className`='" + className + "' ORDER BY `reg_date` ASC";
                        break;
                    case "Student Email":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `email` LIKE '%" + searchText + "%' AND `className`='" + className + "'  ORDER BY `reg_date` ASC";

                        break;

                    case "Student Name":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `fname` OR `lname` LIKE '%" + searchText + "%' AND `className`='" + className + "'  ORDER BY `reg_date` ASC";

                        break;
                    case "Mobile":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo` WHERE `mobile` LIKE '%" + searchText + "%' AND `className`='" + className + "' ORDER BY `reg_date` ASC";

                        break;

                    case "NIC":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `nic` LIKE '%" + searchText + "%' AND `className`='" + className + "' ORDER BY `reg_date` ASC";

                        break;
                }

            } else if (!className.matches("Select Class") && gender.matches("Select Gender") && !year.matches("Select A/L year")) {

                switch (type) {
                    case "Student ID":
                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `sno` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "' ORDER BY `reg_date` ASC";
                        break;
                    case "Student Email":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `email` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "'  ORDER BY `reg_date` ASC";

                        break;

                    case "Student Name":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `fname` OR `lname` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "'  ORDER BY `reg_date` ASC";

                        break;
                    case "Mobile":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo` WHERE `mobile` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "' ORDER BY `reg_date` ASC";

                        break;

                    case "NIC":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `nic` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "' ORDER BY `reg_date` ASC";

                        break;
                }

            } else if (!className.matches("Select Class") && !gender.matches("Select Gender") && !year.matches("Select A/L year")) {

                switch (type) {
                    case "Student ID":
                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `sno` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "' AND `gender`='" + gender + "' ORDER BY `reg_date` ASC";
                        break;
                    case "Student Email":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `email` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "' AND `gender`='" + gender + "' ORDER BY `reg_date` ASC";

                        break;

                    case "Student Name":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `fname` OR `lname` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "' AND `gender`='" + gender + "' ORDER BY `reg_date` ASC";

                        break;
                    case "Mobile":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo` WHERE `mobile` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "' AND `gender`='" + gender + "' ORDER BY `reg_date` ASC";

                        break;

                    case "NIC":

                        query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` INNER JOIN `student_has_class` ON `student`.`sno`=`student_has_class`.`student_sno` INNER JOIN `class` ON `student_has_class`.`class_classNo`=`class`.`classNo`  WHERE `nic` LIKE '%" + searchText + "%' AND `className`='" + className + "' AND `yName`='" + year + "' AND `gender`='" + gender + "'  ORDER BY `reg_date` ASC";

                        break;
                }

            } else {

                System.out.println("gui.admin.AllStudent.searchStudent()");

            }

        } else {
            query = "SELECT * FROM `student` INNER JOIN `gender` ON `student`.`gender_gender_id`=`gender`.`gender_id` INNER JOIN `year` ON `student`.`year_yId`=`year`.`yId` INNER JOIN `student_has_address` ON `student`.`sno`=`student_has_address`.`student_sno` ORDER BY `reg_date` ASC";

        }

        try {
            ResultSet studentRs = MySQL.execute(query);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (studentRs.next()) {
                Vector<String> studentVec = new Vector<>();

                studentVec.add(studentRs.getString("sno"));
                studentVec.add(studentRs.getString("fname"));
                studentVec.add(studentRs.getString("lname"));
                studentVec.add(studentRs.getString("gender_type"));
                String lineT = studentRs.getString("line2");

                if (lineT.matches("null")) {
                    studentVec.add(studentRs.getString("line1") + ' ' + studentRs.getString("postal_code"));
                } else {
                    studentVec.add(studentRs.getString("line1") + ' ' + studentRs.getString("line2") + ' ' + studentRs.getString("postal_code"));
                }

                studentVec.add(studentRs.getString("email"));
                studentVec.add(studentRs.getString("mobile"));
                studentVec.add(studentRs.getString("dob"));
                studentVec.add(studentRs.getString("nic"));
                studentVec.add(studentRs.getString("reg_date"));
                studentVec.add(studentRs.getString("yName"));

                model.addRow(studentVec);
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();

        setLayout(new java.awt.GridLayout(1, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Number", "First Name", "Last Name", "Gender", "Address", "Email", "Mobile", "DOB", "NIC", "Registration Date", "A/L"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search Type", "Student ID", "Student Email", "Student Name", "Mobile", "NIC" }));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Class" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
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

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Class" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Gender", "Male", "Female" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(353, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        String searchText = jTextField1.getText();
        String sType = String.valueOf(jComboBox3.getSelectedItem());

        if (!sType.matches("Search Type")) {
            searchStudent(searchText);
        } else {
            searchStudent("");
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
         String gender = String.valueOf(jComboBox4.getSelectedItem());
         if(gender.matches("Select Gender")){
          searchStudent("");
         }else{
         loadStudentSelecterGender();
         }
       
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        loadStudentSelecterClass();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jComboBox2ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}

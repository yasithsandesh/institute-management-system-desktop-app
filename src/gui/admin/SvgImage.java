/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.admin;

import com.formdev.flatlaf.extras.FlatSVGIcon;

/**
 *
 * @author 94701
 */
public class SvgImage extends javax.swing.JLabel{
    private FlatSVGIcon svgIcon;
    
    public void setSvgIcon(String image,int width,int hight){
    
        svgIcon = new FlatSVGIcon(image, width, hight);
        setIcon(svgIcon);
        
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 94701
 */
public class findMonth {
    
    private String month;
    
    public findMonth(int index){
    
        setMonth(index);
    
    }
    
    
    private void setMonth(int payMonthIndex){
    
      String payMonth = null;
        
        switch (payMonthIndex) {
            case 0:
                payMonth = "January";
                break;

            case 1:
                payMonth = "February";
                break;

            case 2:
                payMonth = "March";
                break;

            case 3:
                payMonth = "";
                break;

            case 4:
                payMonth = "";
                break;

            case 5:
                payMonth = "";
                break;

            case 6:
                payMonth = "July";
                break;

            case 7:
                payMonth = "";
                break;

            case 8:
                payMonth = "";
                break;

            case 9:
                payMonth = "";
                break;

            case 10:
                payMonth = "";
                break;

            case 11:
                payMonth = "";
                break;
        }
        
        this.month = payMonth;
        
    }

    public String getMonth() {
        return month;
    }

   
    
    
    
}

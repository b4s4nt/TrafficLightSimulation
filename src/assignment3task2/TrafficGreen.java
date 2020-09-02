/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3task2;

import javafx.scene.paint.Color;

/**
 *
 * @author basantakandel
 */
public class TrafficGreen  implements Runnable{
    static Color colorGreen= Color.GREEN;
     static Color colorYellow= Color.BLACK;
     static   Color colorRed= Color.BLACK;  
    
   LigntSimulation circle= new LigntSimulation();
    public void run() {
        
        
        
        
       synchronized (this) {
           
                 System.out.println("Green");
                circle.circleGreen.setFill(colorGreen);
                circle.circleGreen.setFill(colorYellow);
                circle.circleGreen.setFill(colorRed);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                notify();
            }
        }
        
        
        
       
        
       
        
       
       
    

   
}

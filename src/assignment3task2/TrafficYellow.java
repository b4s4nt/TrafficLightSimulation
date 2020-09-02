/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3task2;

import static assignment3task2.TrafficGreen.colorGreen;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author basantakandel
 */
public class TrafficYellow  implements Runnable {
    
    static Color colorGreen= Color.YELLOW;
     static Color colorYellow= Color.BLACK;
     static   Color colorRed= Color.BLACK;  
     LigntSimulation circle= new LigntSimulation();

    @Override
    public void run() {
        
       synchronized (this) {
                System.out.println("Yellow");
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

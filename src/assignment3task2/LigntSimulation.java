/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3task2;

import java.util.concurrent.ExecutorService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * THIS IS TREAFFIC LIGHT SIMULATOR PROGRAM WHICH SIMULATES THE LIGHT ACCORDING
 * TO USER INPUTS
 *
 * @author basantakandel
 */
public class LigntSimulation extends Application implements Runnable {

    // 
    int greentime = 3000;
    int yellowtime = 3000;
    int redtime = 3000;

    TextField greenText;
    TextField yellowText;
    TextField redText;

// Varible decleration and initialization     
    HBox hbox = new HBox();
    Circle circleGreen = new Circle(50);
    Circle circleYellow = new Circle(50);
    Circle circleRed = new Circle(50);
    Rectangle rect = new Rectangle(500, 175);
    Button start = new Button("     Start    ");
    Button stop = new Button("     Stop     ");
    Label green = new Label("  Green: ");
    Label yellow = new Label("  Yellow: ");
    Label red = new Label("  Red: ");
//    TextField gtf = new TextField();
//    TextField ytf = new TextField();
//    TextField rtf = new TextField();
    HBox hbgreen = new HBox(7);
    HBox hbyellow = new HBox(3);
    HBox hbred = new HBox(19);
    HBox x = new HBox(10);
    VBox vbox = new VBox(10);
    HBox btnClk = new HBox(10);
    VBox vbtn = new VBox(50);
    //circleGreen.setFill(Color.GREEN);
    Object object;

    Thread t;// thread variable

    @Override
    public void run() {
        while (true) {
            Thread green = new Thread(new LigntSimulation.TrafficGreen());// crates thread of trafficGreeen subclass
            green.setDaemon(true);
            green.start();
            synchronized (green) {// using synchronization
                try {
                    green.wait();// waits untill it is finished
                } catch (InterruptedException e) {
                }
            }
            Thread yellow = new Thread(new LigntSimulation.TrafficYellow());// creates theread
            yellow.setDaemon(true);
            yellow.start();
            synchronized (yellow) {
                try {
                    yellow.wait();
                } catch (InterruptedException e) {
                }
            }
            Thread red = new Thread(new LigntSimulation.TrafficRed());
            red.setDaemon(true);
            red.start();
            synchronized (red) {
                try {
                    red.wait();
                } catch (InterruptedException e) {
                }
            }

        }

    }

    @Override
    public void start(Stage primaryStage) {

        start.setOnAction(new EventHandler<ActionEvent>() {

            //  action when start is click
            @Override
            public void handle(ActionEvent event) {

                if (!greenText.getText().isEmpty()) {
                    greentime = 1000 * Integer.parseInt(greenText.getText());

                }

                if (!yellowText.getText().isEmpty()) {
                    yellowtime = 1000 * Integer.parseInt(yellowText.getText());
                }

                if (!redText.getText().isEmpty()) {
                    redtime = 1000 * Integer.parseInt(redText.getText());

                }

                t = new Thread((Runnable) object);
                t.setDaemon(true);
                t.start();

            }
        });
        stop.setOnAction(new EventHandler<ActionEvent>() {
//   action when stop is click
            @Override
            public void handle(ActionEvent event) {
                //Thread t= new Thread((Runnable) object);
                circleGreen.setFill(Color.BLACK);
                circleYellow.setFill(Color.BLACK);
                circleRed.setFill(Color.BLACK);

                t.stop();
                System.out.println("Now Traffic light stop");
            }
        });

        //calls the diplay of java fx
        dispalyPage(primaryStage);

        object = this;

//
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates display for java fx interface
     *
     * @param primaryStage
     */
    public void dispalyPage(Stage primaryStage) {

        hbox = new HBox();

        rect = new Rectangle(500, 175);
        //start = new Button("     Start    ");
        // stop = new Button("     Stop     ");
        green = new Label("  Green: ");
        red = new Label("  Red: ");
//     gtf = new TextField("Green Time");
//     ytf = new TextField("Yellow Time");
//     rtf = new TextField("Red Time");
        redText = new TextField("3");
        greenText = new TextField("3");
        yellowText = new TextField("3");

        hbgreen = new HBox(7);
        hbyellow = new HBox(3);
        hbred = new HBox(19);
        x = new HBox(5);
        vbox = new VBox(5);
        btnClk = new HBox(10);
        vbtn = new VBox(50);

        hbox.setAlignment(Pos.CENTER);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(30.0);
        rect.setArcHeight(20.0);

        hbox.getChildren().add(circleGreen);
        hbox.getChildren().add(circleYellow);
        hbox.getChildren().add(circleRed);
        hbred.getChildren().addAll(red, redText);

        hbgreen.getChildren().addAll(green, greenText);
        hbyellow.getChildren().addAll(yellow, yellowText);
        //vbox.getChildren().addAll(gtf,ytf,rtf);
        vbox.getChildren().addAll(hbred, hbgreen, hbyellow);
        vbox.setAlignment(Pos.BOTTOM_LEFT);
        btnClk.getChildren().addAll(start, stop);
        btnClk.setAlignment(Pos.BOTTOM_CENTER);

        StackPane stack = new StackPane();

        stack.getChildren().addAll(rect, hbox);
        stack.getChildren().add(vbox);
        stack.getChildren().add(btnClk);

        Scene scene = new Scene(stack, 1000, 400);

        primaryStage.setTitle("Traffic Light Demo-):");
        primaryStage.setScene(scene);
        primaryStage.show();
        t = new Thread(new LigntSimulation());

    }

    /*
    Sub class to which implements runnable and sets the color of traffic light 
     */
    public class TrafficGreen implements Runnable {

        public void run() {

            synchronized (this) {

                circleGreen.setFill(Color.GREEN);
                circleYellow.setFill(Color.BLACK);
                circleRed.setFill(Color.BLACK);
                try {

                    for (int i = 0; i < greentime / 1000; i++) {

                        int time = greentime / 1000 - i;

                        Thread.sleep(1000);
                        System.out.println("Now light Green after " + time + " sec it will be Yellow");

                    }

                } catch (InterruptedException e) {
                }
                notify();
            }
        }

    }

    // for yellow
    public class TrafficYellow implements Runnable {

        @Override
        public void run() {

            LigntSimulation circle = new LigntSimulation();
            synchronized (this) {
                System.out.println("Yellow");
                circleGreen.setFill(Color.BLACK);
                circleYellow.setFill(Color.YELLOW);
                circleRed.setFill(Color.BLACK);
                try {

                    for (int i = 0; i < yellowtime / 1000; i++) {

                        int time = yellowtime / 1000 - i;

                        Thread.sleep(1000);
                        System.out.println("Now light Green after " + time + " sec it will be Red");

                    }

                } catch (InterruptedException e) {
                }
                notify();
            }

        }

    }

    // for red
    public class TrafficRed implements Runnable {

        @Override
        public void run() {

            LigntSimulation circle = new LigntSimulation();

            synchronized (this) {

                System.out.println("Red");
                circleGreen.setFill(Color.BLACK);
                circleYellow.setFill(Color.BLACK);
                circleRed.setFill(Color.RED);
                try {

                    for (int i = 0; i < redtime / 1000; i++) {

                        int time = redtime / 1000 - i;

                        Thread.sleep(1000);
                        System.out.println("Now light Green after " + time + " sec it will be Green");

                    }

                } catch (InterruptedException e) {
                }
                notify();
            }

        }

    }

}

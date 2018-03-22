//Thien Dang

import java.util.*;
import javafx.scene.shape.PathElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Car extends ImageView{
   private PathElement[][] carPath;
    private String wheels, color, engine;
    private long currentTime, finishedTime, pauseTime, resumeTime, totalPausedTime;
    private int speed;
   


   /** Constructor for Car */
    public Car(String color, String wheels ) {
        super();
        totalPausedTime = 0;
        if (color == "Red" && wheels == "Small") {
            this.setImage(new Image("R.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }else if(color == "Red" && wheels == "Big"){
            this.setImage(new Image("RB.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }else if (color == "Green" && wheels == "Small"){
            this.setImage(new Image("G.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }else if(color == "Green" && wheels == "Big") {
            this.setImage(new Image("GB.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }else if(color == "Blue" && wheels == "Small") {
            this.setImage(new Image ("B.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }else{
            this.setImage(new Image("BB.png"));
            this.setX(-this.getImage().getWidth() / 2);
            this.setY(300 - this.getImage().getHeight());
            this.setRotate(90);
        }
        this.setSpeed();
    }
    
    
   
    
    /** returns the current time in milliseconds */
    public long TimeNow(){
        currentTime = System.currentTimeMillis();
        return currentTime;
    }


     /** initialize and returns the current time in milliseconds */
     public long TimeEnd(){
        finishedTime = System.currentTimeMillis();
        return finishedTime;
    }
    
    
     /** returns the current time in milliseconds */
    public long getTimeNow() {
        return currentTime;
    }
    
    /** initialize and returns the current time in milliseconds */
    public long TimePause(){
        pauseTime = System.currentTimeMillis();
        return pauseTime;
    }
    
     /** returns the current time in milliseconds */
    public long getPausedTime(){
        return pauseTime;
    }
    
    /** initialize and returns the current time in milliseconds */
    public long TimeResume(){
        resumeTime = System.currentTimeMillis();
        return resumeTime;
    }
    
    /** returns the current time in milliseconds */
     public long getResumedTime(){
        return resumeTime;
    }

    /** calculates and returns total paused time */
    public long addTotalPausedTime(long pauseTime, long resumeTime){
        totalPausedTime += (resumeTime - pauseTime);
        return totalPausedTime;
    }
    
    /** and returns total paused time */
    public long getTotalPausedTime(){
        return totalPausedTime;
    }
    
 /** Initializes the speed attribute for this car object */
    public void setSpeed() {
        Random randomNum = new Random();
        int ranNum;
        ranNum = randomNum.nextInt(10) + 5;
        this.speed += (ranNum * 500)*1.5;
    }

     /** Initializes the wheels attribute for this car object */
    public void setWheels(String wheels) {
        this.wheels = wheels;
    }


    /** Initializes the Engine attribute for this car object */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /** Initializes the color attribute for this car object */
    public void setColor(String color){this.color = color;}

                  
    /** Initializes the CarPath attribute to the parameter passed */
                            
    public void setCarPath(PathElement[][] carPath) {
        this.carPath = carPath;
    }                       

    /** Returns the carPath attribute */
    public PathElement[][] getCarPath() {
        return carPath;
    }


    /** Returns the speed attribute */
    public int getSpeed() {
        return speed;
    }

}
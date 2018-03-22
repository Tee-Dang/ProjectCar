//Mike Moschella

import javafx.util.Duration;

import java.util.Random;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.animation.PathTransition;
import javafx.animation.Interpolator;

public class Track {
    
    Car[] cars = new Car[4];
    Path dashedLines;
    Path road;
    String trackTitle;

/** Path Elements for First Race Track */
    PathElement[] path1 = {
            new MoveTo(0, 300),
            new ArcTo(200, 200, 0, 300, 400, false, false),
            new LineTo(300, 400),
    };
    PathElement[] path2 = {
            new MoveTo(300, 400),
            new ArcTo(200, 200, 0, 400, 100, false, false),
            new LineTo(400, 100),
    };
    PathElement[] path3 = {
            new MoveTo(400, 100),
            new ArcTo(200, 200, 0, 100, 0, false, false),
            new LineTo(100, 0),
    };
    PathElement[] path4 = {
            new MoveTo(100, 0),
            new ArcTo(200, 200, 0, 0, 300, false, false),
            new LineTo(0, 300),
    };

  /** Path Elements for Second Race Track */
    PathElement[] additionalPath1 = {
            new MoveTo(0, 300),
            new QuadCurveTo(0,50,300,200),
            new LineTo(300, 400),
    };
    PathElement[] additionalPath2 = {
            new MoveTo(300, 400),
            new ArcTo(100, 100, 0, 400, 100, false, false),
            new LineTo(400, 100),
    };
    PathElement[] additionalPath3 = {
            new MoveTo(400, 100),
            new CubicCurveTo(100,60,40,40,40,40),
            new LineTo(100, 0),
    };
    PathElement[] additionalPath4 = {
            new MoveTo(100, 0),
            new QuadCurveTo(100,100,200,200),
            new LineTo(0, 300),
    };

    

    /** Constructor for track */
    public Track(String trackTitle, String carColor, String carWheel) {
        road = new Path();
        dashedLines = new Path();
        if (trackTitle == "Version 1") {
            this.trackTitle = trackTitle;
            road.setStroke(Color.GREY);
            road.setStrokeWidth(78);
            road.getElements().addAll(path1);
            road.getElements().addAll(path2);
            road.getElements().addAll(path3);
            road.getElements().addAll(path4);
            dashedLines.setStroke(Color.YELLOW);
            dashedLines.setStrokeWidth(4);
            dashedLines.getStrokeDashArray().addAll(10.0, 10.0);
            dashedLines.getElements().addAll(path1);
            dashedLines.getElements().addAll(path2);
            dashedLines.getElements().addAll(path3);
            dashedLines.getElements().addAll(path4);

            Car yourCar = new Car(carColor, carWheel);
            createStartingPositions(yourCar);
            cars[0] = yourCar;

            Car car1 = new Car(carColor, carWheel);
            createStartingPositions(car1);
            cars[1] = car1;

            Car car2 = new Car(carColor, carWheel);
            createStartingPositions(car2);
            cars[2] = car2;

            Car car3 = new Car(carColor, carWheel);
            createStartingPositions(car3);
            cars[3] = car3;

        }
        else {
            this.trackTitle = trackTitle;
            road.setStroke(Color.GREY);
            road.setStrokeWidth(78);
            road.getElements().addAll(additionalPath1);
            road.getElements().addAll(additionalPath2);
            road.getElements().addAll(additionalPath3);
            road.getElements().addAll(additionalPath4);
            dashedLines.setStroke(Color.YELLOW);
            dashedLines.setStrokeWidth(4);
            dashedLines.getStrokeDashArray().addAll(10.0, 10.0);
            dashedLines.getElements().addAll(additionalPath1);
            dashedLines.getElements().addAll(additionalPath2);
            dashedLines.getElements().addAll(additionalPath3);
            dashedLines.getElements().addAll(additionalPath4);

            Car yourCar = new Car(carColor, carWheel);
            createStartingPositions(yourCar);
            cars[0] = yourCar;

            Car car1 = new Car(carColor, carWheel);
            createStartingPositions(car1);
            cars[1] = car1;

            Car car2 = new Car(carColor, carWheel);
            createStartingPositions(car2);
            cars[2] = car2;

            Car car3 = new Car(carColor, carWheel);
            createStartingPositions(car3);
            cars[3] = car3;
        }


    }
    
     

   public Car[] getCars() {
           return cars;
       }
       

    public Path getDashedLines() {
        return dashedLines;
    }

   public Path getRoad() {
           return road;
       }
    
    
   /*  Makes a random path for a the cars to follow */
    public void createStartingPositions(Car car) {
        PathElement[][] path = new PathElement[4][3];
        int random = new Random().nextInt(4);
        if (trackTitle == "Version 1") {
            if (random == 0) {
                path[0] = path1;
                path[1] = path2;
                path[2] = path3;
                path[3] = path4;
            }
            if (random == 1) {
                path[0] = path2;
                path[1] = path3;
                path[2] = path4;
                path[3] = path1;
            }
            if (random == 2) {
                path[0] = path3;
                path[1] = path4;
                path[2] = path1;
                path[3] = path2;
            }
            if (random == 3) {
                path[0] = path4;
                path[1] = path1;
                path[2] = path2;
                path[3] = path3;
            }
            car.setCarPath(path);
        }
        if(trackTitle == "Version 2"){
            if (random == 0) {
                path[0] = additionalPath1;
                path[1] = additionalPath2;
                path[2] = additionalPath3;
                path[3] = additionalPath4;
            }
            if (random == 1) {
                path[0] = additionalPath2;
                path[1] = additionalPath3;
                path[2] = additionalPath4;
                path[3] = additionalPath1;
            }
            if (random == 2) {
                path[0] = additionalPath3;
                path[1] = additionalPath4;
                path[2] = additionalPath1;
                path[3] = additionalPath2;
            }
            if (random == 3) {
                path[0] = additionalPath4;
                path[1] = additionalPath1;
                path[2] = additionalPath2;
                path[3] = additionalPath3;
            }
            car.setCarPath(path);
        }
    } 
    
    
    /*Creates an animation for all 4 cars */
public PathTransition[] createAnim(int car) {

        PathTransition[] animArray = new PathTransition[4];
        
        for(int i = 0; i < 4; i++) {
            PathTransition anim = new PathTransition();
            
            
            anim.setNode(this.getCars()[car]);
            Path carPath = new Path();
            /* This loop puts each pathelement togeth from tip to tail*/
            for(int r = 0; r < 4; r++) {
                for (int c = 0; c < 3; c++) {
                    carPath.getElements().addAll(this.getCars()[car].getCarPath()[r][c]);
                }
                
                
                anim.setPath(carPath);
                anim.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                
                anim.setInterpolator(Interpolator.LINEAR);
                anim.setDuration(new Duration(this.getCars()[car].getSpeed()));
                
                /** Each car goes 1 lap */
                anim.setCycleCount(1);
                animArray[r] = anim;
            }

        }
        return animArray;
    }
    
    
   

    

    
}

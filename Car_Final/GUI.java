import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/*
 * @author Michael M
 * @author Jingming Feng
 * @author Thien Dang
 * 
 * GUI class that allows user to set options for the game with pop up windows,
 * start game, pause and resume the game with buttoms
 */

public class GUI extends Application {
        String colorChoice = null;
        String engineChoice = null;
        Track raceTrack = null;
        String wheelChoice = null;
        Boolean paused = null;
        Boolean car1Finished = false;
        Boolean car2Finished = false;
        Boolean car3Finished = false;
        Boolean car4Finished = false;
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        
        /**Dialog Alert for picking car color */      
        Alert carColorChoice = new Alert(AlertType.CONFIRMATION);
	    carColorChoice.setTitle("Color Options");
        carColorChoice.setHeaderText("Car Color");
        carColorChoice.setContentText("Please choose the color of your car:");
        
        ButtonType colorButtonOne = new ButtonType("Red");
        ButtonType colorButtonTwo = new ButtonType("Green");
        ButtonType colorButtonThree = new ButtonType("Blue");
        
        carColorChoice.getButtonTypes().setAll(colorButtonOne,colorButtonTwo,colorButtonThree);

        Optional<ButtonType> color = carColorChoice.showAndWait();
        if (color.get() == colorButtonOne){
            colorChoice = "Red";
        }
        else if(color.get() == colorButtonTwo){
            colorChoice = "Green";
        }else{
            colorChoice = "Blue";
        }

        /**Dialog Alert for picking car engine */
        Alert carEngineChoice = new Alert(AlertType.CONFIRMATION);
	    carEngineChoice.setTitle("Car Engine Options");
        carEngineChoice.setHeaderText("Car Engine");
        carEngineChoice.setContentText("Please choose the engine of your car:");
        
        ButtonType engineButtonOne = new ButtonType("V6");
        ButtonType engineButtonTwo = new ButtonType("V8");
        
        
        carEngineChoice.getButtonTypes().setAll(engineButtonOne,engineButtonTwo);

        Optional<ButtonType> carEngine = carEngineChoice.showAndWait();
        if (carEngine.get() == engineButtonOne){
            engineChoice = "V6";
        }else{
            engineChoice = "V8";
        }

        /**Dialog Alert for picking car wheels */
        Alert carWheelChoice = new Alert(AlertType.CONFIRMATION);
	    carWheelChoice.setTitle("Wheel Size Options");
        carWheelChoice.setHeaderText("Wheel Size");
        carWheelChoice.setContentText("Please choose the wheel size of your car:");
        
        
        ButtonType wheelButtonOne = new ButtonType("Small");
        ButtonType wheelButtonTwo = new ButtonType("Big");
        
        carWheelChoice.getButtonTypes().setAll(wheelButtonOne,wheelButtonTwo);

        Optional<ButtonType> carWheel = carWheelChoice.showAndWait();
        if (carWheel.get() == wheelButtonOne){
            wheelChoice = "Small";
        }else{
            wheelChoice = "Big";
        }

       /**Dialog Alert for picking race track */
        Alert trackChoice = new Alert(AlertType.CONFIRMATION);
	    trackChoice.setTitle("Race Track Options");
        trackChoice.setHeaderText("Race Track");
        trackChoice.setContentText("Please choose a track:");
        
        ButtonType trackButtonOne = new ButtonType("Version 1");
        ButtonType trackButtonTwo = new ButtonType("Version 2");
        
        trackChoice.getButtonTypes().setAll(trackButtonOne,trackButtonTwo);

        Optional<ButtonType> track = trackChoice.showAndWait();
        if (track.get() == trackButtonOne){
            raceTrack = new Track("Version 1", colorChoice, wheelChoice);
        }else{
            raceTrack = new Track("Version 2", colorChoice, wheelChoice);
        }
        


        TextArea timerPanel = new TextArea();
        timerPanel.setPrefRowCount(8);
        timerPanel.setPrefColumnCount(21);
        
        /** getting the road from Track class */
        Path road = raceTrack.getRoad();
        PathTransition[] pt1 = raceTrack.createAnim(0);
        PathTransition[] pt2 = raceTrack.createAnim(1);
        
          PathTransition[] pt3 = raceTrack.createAnim(2);
        PathTransition[] pt4 = raceTrack.createAnim(3);
        
        
        /** this is the transition animation for each car */
        
        SequentialTransition transition1 = new SequentialTransition(pt1[0]);
        
        SequentialTransition transition2 = new SequentialTransition(pt2[0]);
          SequentialTransition transition3 = new SequentialTransition(pt3[0]);
        SequentialTransition transition4 = new SequentialTransition(pt4[0]);
        Track finalRaceTrack1 = raceTrack;
        long startTime = 0;
        
        long finalStartTime = startTime;
        
        
        /** This will subtract the current car time from the start car time to get the total elapsed time */
        
        transition1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timerPanel.appendText("Your Car's Time: " + Long.toString((finalRaceTrack1.getCars()[0].TimeEnd() -
                        finalRaceTrack1.getCars()[0].getTimeNow() - finalRaceTrack1.getCars()[0].getTotalPausedTime()) / 1000)
                + "\n");
                car1Finished = true;
                if(!car2Finished && !car3Finished && !car4Finished){
                    timerPanel.appendText("The winner is: Your Car!!"+ "\n");
                }
            }
        });
        transition2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timerPanel.appendText("Car 1's Time: " + Long.toString((finalRaceTrack1.getCars()[1].TimeEnd() -
                        finalRaceTrack1.getCars()[1].getTimeNow() - finalRaceTrack1.getCars()[1].getTotalPausedTime()) / 1000) + "\n");
                car2Finished = true;
                if(!car1Finished && !car3Finished && !car4Finished){
                    timerPanel.appendText("The winner is: Car 1!!"+ "\n");
                }
            }
        });
        transition3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timerPanel.appendText("Car 2's Time: " + Long.toString((finalRaceTrack1.getCars()[2].TimeEnd() -
                        finalRaceTrack1.getCars()[2].getTimeNow() - finalRaceTrack1.getCars()[2].getTotalPausedTime()) / 1000) + "\n");
                car3Finished = true;
                if(!car1Finished && !car2Finished && !car4Finished){
                    timerPanel.appendText("The winner is: Car 2!!"+ "\n");
                }
            }
        });
        transition4.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timerPanel.appendText("Car 3's Time: " + Long.toString((finalRaceTrack1.getCars()[3].TimeEnd() -
                        finalRaceTrack1.getCars()[3].getTimeNow() - finalRaceTrack1.getCars()[3].getTotalPausedTime()) / 1000) + "\n");
                car4Finished = true;
                if(!car1Finished && !car2Finished && !car3Finished){
                    timerPanel.appendText("The winner is: Car 3!!"+ "\n");
                }
            }
        });
        transition1.play();
        
        
        transition2.play();
         transition3.play();
         
        transition4.play();
        
        
        
        /** This starts the timer */
        raceTrack.getCars()[0].TimeNow();
        
        raceTrack.getCars()[1].TimeNow();
        raceTrack.getCars()[2].TimeNow();
        
        
        raceTrack.getCars()[3].TimeNow();
        
        

        Path dashedLines = raceTrack.getDashedLines();
        Group root = new Group();
        
        
        
        
        root.getChildren().addAll(road, dashedLines,raceTrack.getCars()[0], raceTrack.getCars()[1],
                raceTrack.getCars()[2], raceTrack.getCars()[3]);
        root.setTranslateX(50);
        root.setTranslateY(50);

        GridPane mainPane = new GridPane();
        mainPane.setHgap(20);
        mainPane.setVgap(20);
        HBox buttonPanel = new HBox();
        buttonPanel.setSpacing(10);
        Button resume = new Button("Resume");
        Button pause = new Button("Pause");
        resume.setPrefSize(100, 40);
        pause.setPrefSize(100, 40);
        Track finalRaceTrack = raceTrack;
        
        
        /**resume the cars*/
        resume.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {      
           if(paused){
            transition1.play();
            transition2.play();
            transition3.play();
            transition4.play();
            finalRaceTrack1.getCars()[0].TimeResume();
            finalRaceTrack1.getCars()[1].TimeResume();
            finalRaceTrack1.getCars()[2].TimeResume();
            finalRaceTrack1.getCars()[3].TimeResume();
            finalRaceTrack1.getCars()[0].addTotalPausedTime(finalRaceTrack1.getCars()[0].getPausedTime(),finalRaceTrack1.getCars()[0].getResumedTime()); 
            finalRaceTrack1.getCars()[1].addTotalPausedTime(finalRaceTrack1.getCars()[1].getPausedTime(),finalRaceTrack1.getCars()[1].getResumedTime()); 
            finalRaceTrack1.getCars()[2].addTotalPausedTime(finalRaceTrack1.getCars()[2].getPausedTime(),finalRaceTrack1.getCars()[2].getResumedTime()); 
            finalRaceTrack1.getCars()[3].addTotalPausedTime(finalRaceTrack1.getCars()[3].getPausedTime(),finalRaceTrack1.getCars()[3].getResumedTime());
            paused = false;
                    }
            }
        });
        /**pause the cars */
        pause.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
           if(!car1Finished || !car2Finished || !car3Finished || !car4Finished){
            transition1.pause();
            transition2.pause();
            transition3.pause();
            transition4.pause(); 
            finalRaceTrack1.getCars()[0].TimePause();
            finalRaceTrack1.getCars()[1].TimePause();
            finalRaceTrack1.getCars()[2].TimePause();
            finalRaceTrack1.getCars()[3].TimePause();
            paused = true;
            }
           }
        });

        buttonPanel.getChildren().addAll(resume, pause);
        buttonPanel.setStyle("-fx-padding: 60px");
        mainPane.add(root,2,0);
        mainPane.add(buttonPanel,2,2);
        mainPane.add(timerPanel,1,2);
        Scene scene = new Scene(mainPane, 1000, 800);

        primaryStage.setTitle("RaceTrack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }




}
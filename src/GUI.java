import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GUI extends Application {
    public static Group group = new Group();

    Image boardImage = new Image("planches_jpg/PlatoRobot.jpg");
    ImageView board = new ImageView(boardImage);

    Image robotBlueImage = new Image("robots/blueRobot.png");
    ImageView robotBlue = new ImageView(robotBlueImage);

    Image robotGreenImage = new Image("robots/greenRobot.png");
    ImageView robotGreen = new ImageView(robotGreenImage);

    Image robotRedImage = new Image("robots/redRobot.png");
    ImageView robotRed = new ImageView(robotRedImage);

    Image robotYellowImage = new Image("robots/yellowRobot.png");
    ImageView robotYellow = new ImageView(robotYellowImage);

    public static Image targetBlueImage = new Image("Targets/TargetBlue.png");
    public static ImageView targetBlueGUI = new ImageView(targetBlueImage);

    public static Image targetRedImage = new Image("Targets/TargetRed.png");
    public static ImageView targetRedGUI = new ImageView(targetRedImage);

    public static Image targetYellowImage = new Image("Targets/TargetYellow.png");
    public static ImageView targetYellowGUI = new ImageView(targetYellowImage);

    public static Image targetGreenImage = new Image("Targets/TargetGreen.png");
    public static ImageView targetGreenGUI = new ImageView(targetGreenImage);

    Board myBoard =  Game.getBoard();
    ArrayList<Robot> playableRobots = myBoard.playableRobots;

    final int[] deplacement = {0};
    Label compterDeplacementLabel = new Label("Number of moves : 0");
    Label timerLabel = new Label();
    int interval;
    Robot r = playableRobots.get(0);
    Robot g = playableRobots.get(1);
    Robot b = playableRobots.get(2);
    Robot y = playableRobots.get(3);

    ImageView currentRobotImage; Robot currentRobot;

    public void addTargetCells(Group group){
        switch(myBoard.TargetCell.Color){
            case "Blue":
                group.getChildren().remove(targetRedGUI);
                if(!group.getChildren().contains(targetBlueGUI)){
                    group.getChildren().add(targetBlueGUI);
                    targetBlueGUI.relocate(14 + (37 * myBoard.TargetCell.y), 38 + (37 * myBoard.TargetCell.x));
                }
                break;

            case "Red":
                if(!group.getChildren().contains(targetRedGUI)){
                    group.getChildren().add(targetRedGUI);
                    targetRedGUI.relocate(14 + (37 * myBoard.TargetCell.y), 38 + (37 * myBoard.TargetCell.x));
                }

                if(group.getChildren().contains(targetYellowGUI)){
                   group.getChildren().remove(targetYellowGUI);
                }
                break;

            case "Yellow":
                group.getChildren().remove(targetGreenGUI);
                if(!group.getChildren().contains(targetYellowGUI)){
                    group.getChildren().add(targetYellowGUI);
                    targetYellowGUI.relocate(14 + (37 * myBoard.TargetCell.y), 38 + (37 * myBoard.TargetCell.x));
                }
                break;

            case "Green":
                group.getChildren().remove(targetBlueGUI);
                if(!group.getChildren().contains(targetGreenGUI)){
                    group.getChildren().add(targetGreenGUI);
                    targetGreenGUI.relocate(14 + (37 * myBoard.TargetCell.y), 38 + (37 * myBoard.TargetCell.x));
                }
                break;
        }
    }
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(group, 620, 630);

        group.getChildren().addAll(Menu(primaryStage,group ));

            board.relocate(board.getX() + 7, 30);
            compterDeplacementLabel.relocate(compterDeplacementLabel.getLayoutX() - 220, compterDeplacementLabel.getLayoutY());

            group.getChildren().addAll(board, robotBlue, robotGreen, robotRed, robotYellow, compterDeplacementLabel, timerLabel);

            setRobots();
            addTargetCells(group);

            compterDeplacementLabel.setTranslateX(600);
            compterDeplacementLabel.setFont(new Font("Arial", 16));

            primaryStage.setTitle("Ricochet robots");
            primaryStage.setScene(scene);
            primaryStage.show();

            robotlink(robotBlue, b);

                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {
                        if(!Won()) {
                            addTargetCells(group);
                            switch (event.getCode()) {
                                case B: {
                                    robotlink(robotBlue, b);
                                    break;
                                }
                                case R: {

                                    robotlink(robotRed, r);
                                    break;
                                }
                                case Y: {
                                    robotlink(robotYellow, y);
                                    break;
                                }
                                case G: {
                                    robotlink(robotGreen, g);
                                    break;
                                }


                                case RIGHT: {
                                    deplacement[0] += 1;
                                    compterDeplacementLabel.setText("Number of moves : " + deplacement[0]);
                                    myBoard.MoveRobot(currentRobot, "Right");
                                    currentRobotImage.setX(14 + (currentRobot.y * 37));
                                    break;
                                }
                                case LEFT: {
                                    deplacement[0] += 1;
                                    compterDeplacementLabel.setText("Number of moves : " + deplacement[0]);
                                    myBoard.MoveRobot(currentRobot, "Left");

                                    currentRobotImage.setX(14 + (currentRobot.y * 37));
                                    break;
                                }

                                case UP: {
                                    deplacement[0] += 1;
                                    compterDeplacementLabel.setText("Number of moves : " + deplacement[0]);
                                    myBoard.MoveRobot(currentRobot, "Up");
                                    currentRobotImage.setY(38 + (currentRobot.x * 37));
                                    break;
                                }

                                case DOWN: {
                                    deplacement[0] += 1;
                                    compterDeplacementLabel.setText("Number of moves : " + deplacement[0]);
                                    myBoard.MoveRobot(currentRobot, "Down");
                                    currentRobotImage.setY(38 + (currentRobot.x * 37));
                                    break;
                                }
                            }
                        }

                        else{
                            OnWon(group, primaryStage);
                        }
                    }
                });

            timerLabel.relocate(timerLabel.getLayoutX() + 200, timerLabel.getLayoutY());

            clockDown();



        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private HBox Menu(Stage stage, Group group){
        Button rules = new Button("Rules");
        Button quit = new Button("Quit");
        Button win = new Button("Win");

        win.setOnMouseClicked(event -> {
            myBoard.TargetCell.x=999;  myBoard.TargetCell.y=999;

            OnWon(group, stage);
        });

        rules.setOnMouseClicked( event -> {
            Group rulesGroup = new Group();
            Scene rulesScene = new Scene(rulesGroup, 500, 400);
            Stage rulesStage = new Stage();
            rulesStage.setTitle("Rules");
            Label theRules = new Label("Une fois 4 planches assemblées pour former un plateau de jeu, les 4 robots de couleur  \n sont placésaléatoirement sur le plateau.\nLes 17 jetons sont tournés face  cachée à côté du plateau.\n" +
                    "La partie commence lorsque l’on retourne le premier jeton et le sablier.\n" +
                    "L’objectif est d’amener le robot qui a la couleur de l’objectif sur la case\n objectif sur le plateau en effectuant le moins de déplacement. Pour ce faire, on\n" +
                    "peut déplacer n’importe lequel des robots. Lorsque l’on avance un robot, il ne\n" +
                    "peut s’arrêter que lorsqu’il rencontre un obstacle (mur, bord du plateau, plaque centrale, autre robot).\n" +
                    "L’action de déplacer un robot horizontalement ou verticalement compte pour\n" +
                    "1, quel que soit le nombre de cases qu’il parcourt.\n" +
                    "Quand on a trouvé une solution, on indique le nombre de déplacement néces\u0002saire. Le sablier démarre pour une durée de 30 secondes. Tant que le sablier\n" +
                    "tourne, on peut proposer un autre nombre de déplacement \n (supérieur ou inférieur). \nUne fois le temps écoulé, le joueur avec le plus petit nombre de déplacements montre sa solution.\n Si sa solution est correcte, il empoche le jeton\n" +
                    "Objectif en jeu. Les tuiles Robot sont alors déplacées en-dessous des nouvelles\n" +
                    "positions des Robots.");
            rulesGroup.getChildren().add(theRules);
            rulesStage.setScene(rulesScene);
            rulesStage.show();

        });
        quit.setOnMouseClicked( event -> {
            stage.close();
        });

        return new HBox( rules, quit, win);
    }

    private void robotlink(ImageView robotImage, Robot robot){
        this.currentRobot = robot;
        this.currentRobotImage = robotImage;
    }

    private void setRobots(){
        robotRed.setX(14+(37*r.y));
        robotRed.setY(38+(37*r.x));
        robotGreen.setX(14+(37*g.y));
        robotGreen.setY(38+(37*g.x));
        robotBlue.setX(14+(37*b.y));
        robotBlue.setY(38+(37*b.x));
        robotYellow.setX(14+(37*y.y));
        robotYellow.setY(38+(37*y.x));
    }


    private void clockDown(){
        Timer timer = new Timer();
        interval = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(!Won())
                {
                    Platform.runLater(() -> timerLabel.setText("Time elapsed "+interval+" s"));
                    interval++;

                }
                else
                    timer.cancel();
            }
        }, 0,1000);
    }

    private Boolean Won(){
        return myBoard.TargetCell.x==999 && myBoard.TargetCell.y==999;
    }

    private void OnWon(Group group, Stage primaryStage){

        group.getChildren().removeAll(group.getChildren());
        group.getChildren().addAll(Menu(primaryStage, group));
        Label won = new Label("Congrats, you are victorious !");
        Label timerElapsed = new Label("You won in "+interval+ " seconds and after "+ deplacement[0] + " moves");
        won.setTextFill(Color.RED);
        won.setFont(new Font("Arial", 16));
        won.setStyle(" -fx-font-weight:bold; -fx-font-size:large;");
        won.relocate(200, 200);
        timerElapsed.relocate(200, 300);

        group.getChildren().addAll(won, timerElapsed);
    }



}




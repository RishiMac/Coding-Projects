import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
/**
 * This file defines the AnimalSanctuary object.
 * @author Rishi.
 * @version 1.
 */
public class AnimalSanctuary extends Application {
    private Animals[][] animallArr = new Animals[2][3];
    private TextField nameHere;
    private TextField healthHere;
    private GridPane gridPane = new GridPane();
    private ComboBox<Animal> animalTypes = new ComboBox<Animal>();
    private Alert fullSanctuary = new Alert(AlertType.NONE);
    private List<Animals> animalsQueue = new ArrayList<Animals>();
    private GridPane animalQueuegrid = new GridPane();
    private ListView<Animals> queuedListView;
    private ObservableList<Animals> queuedAnimalObservableList;
    /**
     * Start method.
     * @param primaryStage the stage being worked with to make AnimalSanctuary image.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Animal Sanctuary");
        Image bkgrndimage = new Image("animalImage.jpg");
        BackgroundImage bImg = new BackgroundImage(bkgrndimage, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        Text title = new Text("The Animal Sanctuary");
        title.setFont(Font.font("Verdana", 50));
        title.setFill(Color.RED);
        title.setX(340);
        title.setY(100);
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");
        Button button5 = new Button("Button 5");
        Button button6 = new Button("Button 6");
        button1.setPrefWidth(150);
        button1.setPrefHeight(150);
        button2.setPrefWidth(150);
        button2.setPrefHeight(150);
        button3.setPrefWidth(150);
        button3.setPrefHeight(150);
        button4.setPrefWidth(150);
        button4.setPrefHeight(150);
        button5.setPrefWidth(150);
        button5.setPrefHeight(150);
        button6.setPrefWidth(150);
        button6.setPrefHeight(150);
        BorderPane rootPane = new BorderPane();
        Pane titlePane = new Pane();
        titlePane.getChildren().add(title);
        HBox inputPane = new HBox();
        inputPane.setSpacing(10);
        inputPane.setPadding(new Insets(15, 20, 10, 10));
        // TextField:Type name here
        nameHere = new TextField("");
        nameHere.setPrefWidth(110);
        inputPane.getChildren().add(nameHere);
        // TextField:Type health here
        healthHere = new TextField("");
        healthHere.setPrefWidth(110);
        inputPane.getChildren().add(healthHere);
        nameHere.setPromptText("Type name here");
        healthHere.setPromptText("Type helath here");
        animalTypes.setPromptText("Pick animal type");
        //Combobox
        animalTypes.getItems().setAll(Animal.values());
        animalTypes.setPromptText("Animal Type");
        inputPane.getChildren().add(animalTypes);
        // Button:Add Animal
        Button addAnimal = new Button("Add Animal");
        addAnimal.setPrefSize(100, 60);
        addAnimal.setOnAction(new AddAnimalListener());
        //Buttons: Relase Animal
        button1.setOnAction(new ReleaseAnimalListener());
        button2.setOnAction(new ReleaseAnimalListener());
        button3.setOnAction(new ReleaseAnimalListener());
        button4.setOnAction(new ReleaseAnimalListener());
        button5.setOnAction(new ReleaseAnimalListener());
        button6.setOnAction(new ReleaseAnimalListener());
        inputPane.getChildren().add(addAnimal);
        gridPane.add(button1, 0, 0, 1, 1);
        gridPane.add(button2, 1, 0, 1, 1);
        gridPane.add(button3, 2, 0, 1, 1);
        gridPane.add(button4, 0, 1, 1, 1);
        gridPane.add(button5, 1, 1, 1, 1);
        gridPane.add(button6, 2, 1, 1, 1);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        inputPane.setAlignment(Pos.BOTTOM_CENTER);
        rootPane.setBackground(bGround);
        initializeGridPane(gridPane);
        rootPane.setCenter(gridPane);
        rootPane.setBottom(inputPane);
        rootPane.setTop(titlePane);
        rootPane.setRight(animalQueuegrid);
        Scene scene = new Scene(rootPane, 1150, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * getNodeByRowColumnIndex method.
     * @param row row.
     * @param column column.
     * @param grid grid.
     * @return Node.
     */
    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane grid) {
        Node result = null;
        ObservableList<Node> childrens = grid.getChildren();
        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
    /**
     * initializeGridPane method.
     * @param grid grid.
     */
    public void initializeGridPane(GridPane grid) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (animallArr[i][j] != null) {
                    Button b = (Button) getNodeByRowColumnIndex(i, j, grid);
                    b.setText(animallArr[i][j].toString());
                } else {
                    Button b = (Button) getNodeByRowColumnIndex(i, j, grid);
                    b.setStyle("-fx-background-color: green; ");
                    b.setText("Empty");
                }
            }
        }
    }
    /**
     * Add Animal EventHandler
     */
    private class AddAnimalListener implements EventHandler<ActionEvent> {
        /**
         * handle method.
         * @param ae actionevent.
         */
        public void handle(ActionEvent ae) {
            boolean animalAdded = false;
            if (!isAnimalSanctuaryFull()) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (animallArr[i][j] == null && !animalAdded) {
                            Animals animals = new Animals(nameHere.getText(), healthHere.getText(),
                                animalTypes.getValue());
                            animallArr[i][j] = animals;
                            Button b = (Button) getNodeByRowColumnIndex(i, j, gridPane);
                            b.setStyle("-fx-background-color: #ff0000; ");
                            b.setText(animallArr[i][j].toString());
                            animalAdded = true;
                        }
                    }
                }
            } else {
                Animals queuedAnimal = new Animals(nameHere.getText(), healthHere.getText(), animalTypes.getValue());
                animalsQueue.add(queuedAnimal);
                animalQueuegrid.setAlignment(Pos.BOTTOM_RIGHT);
                animalQueuegrid.setHgap(15);
                animalQueuegrid.setVgap(20);
                animalQueuegrid.setPadding(new Insets(25, 25, 25, 25));
                queuedListView = new ListView<Animals>();
                queuedAnimalObservableList = FXCollections.observableList(animalsQueue);
                queuedListView.setItems(queuedAnimalObservableList);
                animalQueuegrid.add(queuedListView, 1, 1);
                Button addQueuedAnimal = new Button("Add Animal in Queue");
                HBox hbox2 = new HBox(10);
                hbox2.getChildren().addAll(addQueuedAnimal);
                addQueuedAnimal.setOnAction(new AddQueuedAnimalListener());
                animalQueuegrid.add(hbox2, 1, 2);
            }
            nameHere.setText("");
            healthHere.setText("");
            animalTypes.setValue(null);
        }
    }
    private class ReleaseAnimalListener implements EventHandler<ActionEvent> {
        public void handle(ActionEvent t) {
            Node target = (Node) t.getTarget();
            int  x = GridPane.getRowIndex(target);
            int y = GridPane.getColumnIndex(target);
            animallArr[x][y] = null;
            Button b = (Button) getNodeByRowColumnIndex(x, y, gridPane);
            b.setStyle("-fx-background-color: green; ");
            b.setText("Empty");
        }
    }
    private class AddQueuedAnimalListener implements EventHandler<ActionEvent> {
        /**
         * handle method.
         * @param ae actionevent.
         */
        public void handle(ActionEvent ae) {
            boolean animalAdded = false;
            if (!isAnimalSanctuaryFull() && animalsQueue.size() > 0) {
                Animals queuedAnimal = animalsQueue.get(0);
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (animallArr[i][j] == null && !animalAdded) {
                            animallArr[i][j] = queuedAnimal;
                            Button b = (Button) getNodeByRowColumnIndex(i, j, gridPane);
                            b.setStyle("-fx-background-color: #ff0000; ");
                            b.setText(animallArr[i][j].toString());
                            animalAdded = true;
                            animalsQueue.remove(0);
                            queuedAnimalObservableList = FXCollections.observableList(animalsQueue);
                            queuedListView.setItems(queuedAnimalObservableList);
                        }
                    }
                }
            }
        }
    }
    private boolean isAnimalSanctuaryFull() {
        boolean  isFull = true;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (animallArr[i][j] == null) {
                    return false;
                }
            }
        }
        fullSanctuary.setAlertType(AlertType.INFORMATION);
        fullSanctuary.setTitle("No More Room Alert");
        fullSanctuary.setContentText("There is no more Room");
        fullSanctuary.show();
        return isFull;
    }
    /**
     * Main method.
     * @param args needed for main method.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
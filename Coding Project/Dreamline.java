package com.example.projectdreamline;

import com.example.projectdreamline.data.DatabaseHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;

public class Dreamline extends Application {

    private Button option(String s, int offset) {
        Button button = new Button(s);
        button.setPrefWidth(150);
        button.setPrefHeight(50);
        button.setTranslateX(225);
        button.setTranslateY(offset);
        button.setStyle("-fx-base: cornflowerblue");

        return button;
    }
    private Button exit() {
        Button button = new Button("Exit");
        button.setPrefWidth(150);
        button.setPrefHeight(50);
        button.setTranslateX(225);
        button.setTranslateY(450);
        button.setStyle("-fx-base: darkcyan");

        button.setOnAction(actionEvent -> System.exit(0));

        return button;
    }

    private ObservableList<String> flightScores() {
        ObservableList<String> scores = FXCollections.observableArrayList();
        scores.add("American Airlines AA1      ORD to DFW          90");
        scores.add("American Airlines AA2      SEA to DFW           77");
        scores.add("American Airlines AA6      IND to BWI             52");
        scores.add("United Airlines UA1            LAX to JFK             85");
        scores.add("United Airlines UA5            EWR to LAS          45");

        return scores;
    }

    //View dream_scores screen
    public void dreamScoreScreen(Stage stage, Scene main) {
        stage.setTitle("Dream Scores");

        Label label = new Label("Top Dreamtime Scores");
        label.setTranslateX(175);
        label.setTranslateY(50);
        label.setFont(new Font(25));

        Button menu = option("Menu", 500);
        menu.setOnAction(actionEvent -> {stage.setScene(main);});

        ObservableList<String> scores = flightScores();
        ListView<String> listView = new ListView<>(scores);
        listView.setPrefHeight(400);
        listView.setPrefWidth(400);
        listView.setTranslateX(100);
        listView.setTranslateY(100);
        listView.setStyle("-fx-font-size: 1.5em ; -fx-control-inner-background: azure;");

        Group root = new Group(label, listView, menu);

        Scene scene = new Scene(root, 600, 600, Color.MINTCREAM);
        stage.setScene(scene);
        stage.show();
    }

    private String[] searchData() {
        return new String[]{"AA1 American Airlines ORD to DFW 12:30 to 2:00", "AA2 American Airlines SEA to DFW 2:30 to 5:00", "AA6 American Airlines IND to BWI 5:00 to 8:00", "UA1 United Airlines LAX to JFK 8:15 to 11:00", "UA5 United Airlines EWR to LAS 8:15 to 1:00", "BA2 British Airlines ORD to LAS 12:00 to 3:15", "CD4 Canadian Airlines LAX to LAS 9:15 to 10:00"};
    }

    public void searchscreen(Stage stage, Scene main) {
        stage.setTitle("Search Flights");

        //title of page
        Text title = new Text("SEARCH FLIGHTS");
        title.setFont(Font.font(20));

        //instructions
        Text instruction = new Text("Flight Number:");

        //Search button
        Button search_button = new Button("SEARCH");
        search_button.setStyle("-fx-base: cornflowerblue");

        String[] data = searchData();

        //Back button
        Button back_button = new Button("BACK");
        back_button.setAlignment(Pos.CENTER_LEFT);
        back_button.setStyle("-fx-base: cornflowerblue");

        back_button.setOnAction(actionEvent -> {stage.setScene(main);});


        //Search field text box
        TextField search_flight_number = new TextField();
        search_flight_number.setPromptText("Enter Flight Number");

        ListView<String> results = new ListView<String>();
        results.setMaxHeight(300);
        results.setMaxWidth(250);
        results.setStyle("-fx-font-size: 1.5em ; -fx-control-inner-background: azure;");

        //add results from the search
        search_button.setOnAction(e->
        {
            for (String d:data) {
                if (d.contains(search_flight_number.getText())) {
                    results.getItems().add(d);
                }
            }
        });

        //Layout implementation
        HBox search_options = new HBox(10, instruction, search_flight_number);
        search_options.setAlignment(Pos.CENTER);

        VBox Mid_section = new VBox(50, title, search_options, search_button, results);
        Mid_section.setAlignment(Pos.TOP_CENTER);

        BorderPane layout = new BorderPane();
        layout.setCenter(Mid_section);
        layout.setTop(back_button);
        layout.setStyle("-fx-background-color: MINTCREAM;");

        Scene search_scene = new Scene(layout, 600, 600, Color.MINTCREAM);
        stage.setScene(search_scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Welcome to Dreamline!");

        Label label = new Label("Choose an option!");
        label.setTranslateX(220);
        label.setTranslateY(50);
        label.setFont(new Font(25));

        Scene scene;

        Button searchFlights = option("Search flights", 150);

        Button inputFlight = option("Input flight", 250);

        Button viewDreamscores = option("View dreamscores", 350);


        Button exit = exit();

        Group root = new Group(label, searchFlights, inputFlight, viewDreamscores, exit);

        scene = new Scene(root, 600, 600, Color.MINTCREAM);

        //Buttons implemented to have back buttons to menu
        searchFlights.setOnAction(actionEvent -> {searchscreen(stage, scene);});
        viewDreamscores.setOnAction(actionEvent -> {dreamScoreScreen(stage, scene);});
        inputFlight.setOnAction(actionEvent -> {
            try {
                FlightInfoMain flightInfoMain = new FlightInfoMain();
                flightInfoMain.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

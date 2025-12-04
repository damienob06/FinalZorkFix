package gui;

import game.ZorkULGame;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;



public class GUI extends Application {

    private TextArea terminal;
    private ZorkULGame game;

    @Override
    public void start(Stage stage) {

        game = new ZorkULGame();

        terminal = new TextArea();
        terminal.setEditable(false);
        terminal.setMaxWidth(750);
        terminal.setPrefHeight(300);
        terminal.setWrapText(true);

        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                terminal.appendText(String.valueOf((char) b));
            }
        });
        System.setOut(ps);

        //Control pad buttons
        Button north = new Button("North");
        Button south = new Button("South");
        Button west = new Button("West");
        Button east = new Button("East");
        Button inv = new Button("Inventory");

        north.setPrefSize(80, 80);
        south.setPrefSize(80, 80);
        west.setPrefSize(80, 80);
        east.setPrefSize(80, 80);
        inv.setPrefSize(80, 80);

        north.setOnAction(e -> send("go north"));
        south.setOnAction(e -> send("go south"));
        west.setOnAction(e -> send("go west"));
        east.setOnAction(e -> send("go east"));
        inv.setOnAction(e -> send("inventory"));

        //Button Bar
        Button help = new Button("Help");
        Button save = new Button("Save");
        Button load = new Button("Load");
        Button quit = new Button("Quit");

        help.setPrefSize(80, 60);
        save.setPrefSize(80, 60);
        load.setPrefSize(80, 60);
        quit.setPrefSize(80, 60);

        help.setOnAction(e -> send("help"));
        save.setOnAction(e -> send("save"));
        load.setOnAction(e -> send("load"));
        quit.setOnAction(e -> System.exit(0));

        HBox buttonBar = new HBox(10, help, save, load, quit);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.setPadding(new Insets(10));

        // Control Pad layout
        HBox middleRow = new HBox(10, west, inv, east);
        middleRow.setAlignment(Pos.CENTER);

        VBox controlPad = new VBox(10, north, middleRow, south);
        controlPad.setAlignment(Pos.CENTER);
        controlPad.setPadding(new Insets(10));

        // Button Bar layout
        HBox bottomSection = new HBox(40, controlPad, buttonBar);
        bottomSection.setAlignment(Pos.CENTER_LEFT);
        bottomSection.setPadding(new Insets(10));

        //Command input
        TextField commandInput = new TextField();
        commandInput.setPromptText("Enter Command");
        commandInput.setPrefHeight(30);

        //Enter button
        Button enterCommand = new Button("Enter");
        enterCommand.setPrefSize(80, 30);

        commandInput.setOnAction(e -> {
            String command = commandInput.getText();
            send(command);
            commandInput.clear();
        });

        enterCommand.setOnAction(e -> {
            String command = commandInput.getText();
            send(command);
            commandInput.clear();
        });

        HBox commandRow = new HBox(10, commandInput, enterCommand);
        commandRow.setPadding(new Insets(10));
        commandRow.setAlignment(Pos.CENTER);

        VBox terminalArea = new VBox(10, terminal, commandRow);
        terminalArea.setPadding(new Insets(10));

        //Root layout
        BorderPane root = new BorderPane();
        root.setTop(terminalArea);
        root.setBottom(bottomSection);

//        ImageView backgroundImage = new ImageView(new Image(getClass().getResourceAsStream("/R.png")));
//        backgroundImage.setPreserveRatio(false);
//        backgroundImage.fitWidthProperty().bind(stage.widthProperty());
//        backgroundImage.fitHeightProperty().bind(stage.heightProperty());
//
//        Image img = new Image(getClass().getResourceAsStream("/map.png"));
//        ImageView imgView = new ImageView(img);
//        imgView.setFitWidth(470);
//        imgView.setPreserveRatio(true);
//        StackPane.setAlignment(imgView, Pos.BOTTOM_RIGHT);
//        StackPane.setMargin(imgView, new Insets(10));

        StackPane mainLayout = new StackPane();

        //mainLayout.getChildren().add(backgroundImage);
        mainLayout.getChildren().add(root);
        //mainLayout.getChildren().add(imgView);

        Scene scene = new Scene(mainLayout, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("Keys of the Lost Empire");
        stage.show();

        terminal.appendText(game.getPlayer().getCurrentRoom().getLongDescription() + "\n");

        //BackgroundMusic.playMusic();

    }

    private void send(String command) {
        String output = game.handleCommand(command);
        terminal.appendText(output);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


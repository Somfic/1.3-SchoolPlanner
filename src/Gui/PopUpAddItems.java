package Gui;

import Data.Gender;
import Data.Teacher;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class PopUpAddItems {

    public static void PupUp(String title) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(1920 / 3);
        window.setHeight(1080 / 2);
        window.setResizable(false);

        Label label = new Label();
        label.setText("Add whatever you need... or kill them, what do i care?");

        HBox teacherHBox = new HBox();
        Label teacherLabel = new Label("Teacher: ");
        TextField teacherTextField = new TextField();
        teacherTextField.setText("New teacher name");
        teacherTextField.requestFocus();
        teacherTextField.setOnMouseClicked(event -> {
            teacherTextField.setText(null);
        });
        ToggleGroup radioButtonToggleGroup = new ToggleGroup();
        RadioButton maleRadioButton = new RadioButton("Male");
        maleRadioButton.setToggleGroup(radioButtonToggleGroup);
        RadioButton femaleRadioButton = new RadioButton("Female");
        femaleRadioButton.setToggleGroup(radioButtonToggleGroup);
        RadioButton otherRadioButton = new RadioButton("Other");
        otherRadioButton.setToggleGroup(radioButtonToggleGroup);

        VBox radioVBox = new VBox();
        radioVBox.getChildren().addAll(maleRadioButton, femaleRadioButton, otherRadioButton);


        Button teacherButton = new Button("Confirm");
        teacherButton.setOnAction(event -> {

            Gender teacherGender = Gender.valueOf(radioButtonToggleGroup.getSelectedToggle().getUserData().toString());
            String teacherName = teacherTextField.getText();

            Teacher teacher = new Teacher(teacherGender, teacherName);
            //TODO save teacher somewhere...
        });

        teacherHBox.getChildren().addAll(teacherLabel, teacherTextField, radioVBox, teacherButton);


        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            window.close();
        });

        VBox VBox = new VBox(10);
        VBox.getChildren().addAll(label, teacherHBox, closeButton);
        VBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(VBox);
        window.setScene(scene);
        window.showAndWait();

    }

}

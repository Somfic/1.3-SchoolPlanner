package Gui;

import Data.Gender;
import Data.Lesson;
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

        //region Teacher
        HBox teacherHBox = new HBox(10);
        teacherHBox.setAlignment(Pos.CENTER);
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

        VBox radioVBox = new VBox(2);
        radioVBox.getChildren().addAll(maleRadioButton, femaleRadioButton, otherRadioButton);


        Button teacherButton = new Button("Confirm");
        teacherButton.setOnAction(event -> {

            Gender teacherGender;
            if (maleRadioButton.isSelected()) {
                teacherGender = Gender.MALE;
                System.out.println("male");
            } else if (femaleRadioButton.isSelected()) {
                teacherGender = Gender.FEMALE;
                System.out.println("female");
            } else {
                teacherGender = Gender.OTHER;
                System.out.println("other");
            }
            if (!teacherTextField.getText().equals("New teacher name") && teacherTextField.getText().length() <= 25) {
                String teacherName = teacherTextField.getText();
                Teacher teacher = new Teacher(teacherGender, teacherName);
                //TODO save teacher somewhere...
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Teacher name not valid");
                errorAlert.setContentText("Teacher name is either not changed, or too long. Please edit the teacher and try again");
                errorAlert.showAndWait();
            }

        });

        teacherHBox.getChildren().addAll(teacherLabel, teacherTextField, radioVBox, teacherButton);
        //endregion

        //region Subject
        HBox subjectHBox = new HBox(10);
        subjectHBox.setAlignment(Pos.CENTER);
        Label subjectLabel = new Label("Subject: ");
        TextField subjectTextField = new TextField();
        subjectTextField.setText("New subject name");
        subjectTextField.setOnMouseClicked(event -> {
            subjectTextField.setText(null);
        });

        if (!subjectTextField.getText().equals("New subject name") && subjectTextField.getText().length() <= 25) {
            String subjectName = subjectTextField.getText();
            //TODO save subject somewhere...
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Subject name not valid");
            errorAlert.setContentText("Subject name is either not changed, or too long. Please edit the subject and try again");
            errorAlert.showAndWait();
        }
        Lesson subject = new Lesson(subjectTextField.getText());
        //endregion


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

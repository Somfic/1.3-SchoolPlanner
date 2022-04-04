package gui.schedule;

import data.Gender;
import data.Lesson;
import data.Teacher;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class PopUpAddItems {
    private static Labeled teacherLabel;
    private static Labeled subjectLabel;

    public static void PupUp(String title, SelectButtons selectButtons) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(1920 / 3);
        window.setHeight(1080 / 2);
        window.setResizable(false);

        HBox teacherHBox = new HBox(10);
        teacherHBox.setAlignment(Pos.CENTER);
        Label teacherLabel = new Label("Teacher:");
        TextField teacherTextField = new TextField();
        teacherTextField.setPromptText("New teacher name");
        ToggleGroup radioButtonToggleGroup = new ToggleGroup();
        RadioButton maleRadioButton = new RadioButton(Gender.MALE.name());
        maleRadioButton.requestFocus();
        maleRadioButton.setSelected(true);
        maleRadioButton.setToggleGroup(radioButtonToggleGroup);
        RadioButton femaleRadioButton = new RadioButton(Gender.FEMALE.name());
        femaleRadioButton.setToggleGroup(radioButtonToggleGroup);
        RadioButton otherRadioButton = new RadioButton(Gender.OTHER.name());
        otherRadioButton.setToggleGroup(radioButtonToggleGroup);

        VBox genderVBox = new VBox(3);
        genderVBox.getChildren().addAll(maleRadioButton, femaleRadioButton, otherRadioButton);


        Button teacherConfirmButton = new Button("Confirm");
        teacherConfirmButton.setOnAction(event -> {
            Gender teacherGender;
            if (maleRadioButton.isSelected()) {
                teacherGender = Gender.MALE;
            } else if (femaleRadioButton.isSelected()) {
                teacherGender = Gender.FEMALE;
            } else {
                teacherGender = Gender.OTHER;
            }
            if (!teacherTextField.getText().equals("New teacher name") && teacherTextField.getText().length() <= 25 && teacherTextField.getText().length() >= 2) {
                String teacherName = teacherTextField.getText();
                Teacher teacher = new Teacher(teacherGender, teacherName);
                teacherTextField.setText("");
                selectButtons.addTeacher(teacher);
                //TODO save teacher somewhere...
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Teacher name not valid");
                errorAlert.setContentText("Teacher name is either not changed, or you entered incorrect information. Please try again");
                errorAlert.showAndWait();
            }
        });

        teacherHBox.getChildren().addAll(teacherLabel, teacherTextField, genderVBox, teacherConfirmButton);

        HBox subjectHBox = new HBox(10);
        subjectHBox.setAlignment(Pos.CENTER);
        Label subjectLabel = new Label("Subject:");
        TextField subjectTextField = new TextField();
        subjectTextField.setPromptText("New subject name");
        Button subjectButton = new Button("Confirm");
        subjectButton.setOnAction(event -> {
            if (!subjectTextField.getText().equals("New subject name") && subjectTextField.getText().length() <= 25 && subjectTextField.getText().length() >= 2) {
                Lesson subject = new Lesson(subjectTextField.getText());

                subjectTextField.setText("");
                selectButtons.addSubject(subject);
                //TODO save subject somewhere...
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Subject name not valid");
                errorAlert.setContentText("Subject name is either not changed, or you filled in garbage. Please edit the subject and try again");
                errorAlert.showAndWait();
            }
        });

        subjectHBox.getChildren().addAll(subjectLabel, subjectTextField, subjectButton);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            window.close();
        });

        VBox VBox = new VBox(10);
        VBox.getChildren().addAll(new Label("Enter a Teacher name and gender to add teacher."), teacherHBox,new Label("Enter a Subject name to add subject."), subjectHBox, closeButton);
        VBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(VBox);
        window.setScene(scene);
        window.showAndWait();
    }
}

package gui.schedule;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ScheduleCell extends Pane {
    //For all cells with text
    public ScheduleCell(String text, int type, boolean isLeft) {
        this.setStyle(type, isLeft);

        Label label = new Label(text);
        label.setFont(new Font(18));
        label.layoutXProperty().bind(this.widthProperty().subtract(label.widthProperty()).divide(2));
        this.getChildren().add(label);
    }

    //For empty cells
    public ScheduleCell(int type, boolean isLeft) {
        this.setStyle(type, isLeft);
    }

    private void setStyle(int type, boolean isLeft) {
        String border = "-fx-border-width: 1; -fx-border-style: solid";

        //Background and border of cell
        switch (type) {         //0 = odd (class block) cell | 1 = even cell | 2 = top row
            case 0:
                super.setStyle(border);
                break;

            case 1:
                super.setStyle("-fx-background-color: #d9d9d9 ;" + border);
                break;

            case 2:
                super.setStyle("-fx-background-color: #b4b4b4 ;" + border);
        }

        //Size of cell
        if (isLeft) {
            this.setPrefSize(200, 50);
        } else {
            this.setPrefSize(215, 50);
        }
    }
}
package Gui.Settings;

import Gui.Settings.Saving.ColorDeserializer;
import Gui.Settings.Saving.LocalTimeDeserializer;
import Gui.Settings.Saving.LocalTimeSerializer;
import Logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class SettingView implements SpeedSelectorCallback, ColorCallback, ClassBlockCallback, BreakTimeCallback, StartTimeCallback {
    private BorderPane borderPane;
    private Label titleLabel;
    private Label speedLabel;
    private Label colorSelectorLabel;
    private Label classBlockLabel;
    private Label breakfastLabel;
    private Label breakLunchLabel;
    private SpeedSelector speedSelector;
    private ColorSelector colorSelector;
    private ClassBlock classBlock;
    private FastBreak fastBreak;
    private LunchBreak lunchBreak;
    private StartTime startTime;
    private VBox centralPane;
    private Button confirm = new Button("Confirm");
    private Button cancel = new Button("Cancel");
    private int speedSave;
    private Color themeColorSave;
    private boolean textDarkness;
    private int classBlockLengthSave;
    private int fastBreakTimeSave;
    private int fastBreakLengthSave;
    private int lunchBreakTimeSave;
    private int lunchBreakLengthSave;
    private LocalTime startingTime;
    private SettingCallback callback;

    public SettingView(SettingCallback callback) {
        this.callback = callback;
        this.borderPane = new BorderPane();
        this.titleLabel = new Label("Settings");
        this.speedLabel = new Label("Simulator Speed");
        this.colorSelectorLabel = new Label("Theme Color");
        this.classBlockLabel = new Label("Class Block Length (in minutes)");
        this.breakfastLabel = new Label("Fast break (length in minutes, Starts after Class Block X)");
        this.breakLunchLabel = new Label("Lunch break (length in minutes, Starts after Class Block X)");
        this.speedSelector = new SpeedSelector(this);
        this.colorSelector = new ColorSelector(this);
        this.classBlock = new ClassBlock(this);
        this.fastBreak = new FastBreak(this);
        this.lunchBreak = new LunchBreak(this);
        this.startTime = new StartTime(this);
        this.centralPane = new VBox(speedLabel, speedSelector.getContent(), colorSelectorLabel, colorSelector.getContent(), classBlockLabel, classBlock.getContent(), breakfastLabel, fastBreak.getContent(), breakLunchLabel, lunchBreak.getContent(), startTime.getContent(), new HBox(confirm, cancel));
        this.centralPane.setFillWidth(true);
        this.centralPane.setSpacing(15);


        confirm.setOnAction(event -> {
            speedSelector.confirm();
            colorSelector.confirm();
            classBlock.confirm();
            fastBreak.confirm();
            lunchBreak.confirm();
            startTime.confirm();
            callback.onSettingChange(new SettingCallback.ScheduleSettings(speedSave, themeColorSave, textDarkness, classBlockLengthSave, fastBreakTimeSave, fastBreakLengthSave, lunchBreakTimeSave, lunchBreakLengthSave, startingTime));
        });
        cancel.setOnAction(event -> {
            speedSelector.cancel();
            colorSelector.cancel();
            classBlock.cancel();
            fastBreak.cancel();
            lunchBreak.cancel();
            startTime.cancel();
        });
        borderPane.setTop(titleLabel);
        borderPane.setCenter(centralPane);
    }

    public BorderPane getContent() {
        return this.borderPane;
    }

    public void save() {
        Logger.debug("Saving settings");
        cancel.fire();
        SettingCallback.ScheduleSettings save = new SettingCallback.ScheduleSettings(speedSave, themeColorSave, textDarkness, classBlockLengthSave, fastBreakTimeSave, fastBreakLengthSave, lunchBreakTimeSave, lunchBreakLengthSave, startingTime);

        //Adding custom serializer
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        mapper.registerModule(module);

        ObjectWriter json = mapper.writer().withDefaultPrettyPrinter();
        try (FileWriter fileWriter = new FileWriter("src/Gui/Settings/Saving/settingsSave.txt")) {
            fileWriter.write(json.writeValueAsString(save));
        } catch (IOException e) {
            Logger.error(e, e.getMessage());
        }
    }

    public void load() {
        Logger.debug("Loading settings");

        try (Scanner scanner = new Scanner(new File("src/Gui/Settings/Saving/settingsSave.txt"))) {
            ObjectMapper json = new ObjectMapper();

            //Adding custom deserializers
            SimpleModule colorModule = new SimpleModule();
            colorModule.addDeserializer(Color.class, new ColorDeserializer());
            colorModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
            json.registerModule(colorModule);

            //Read saved settings
            StringBuilder settings = new StringBuilder();
            while (scanner.hasNextLine()) {
                settings.append(scanner.nextLine());
            }
            SettingCallback.ScheduleSettings save = json.readValue(settings.toString(), SettingCallback.ScheduleSettings.class);

            //Set saved settings
            speedSelector.setSpeed(save.getSpeed());
            colorSelector.setColor(save.getColor());
            textDarkness = save.isTextBrightness();
            classBlock.set(save.getClassBlockLength());
            fastBreak.set(save.getFastBreakTime(), save.getFastBreakLength());
            lunchBreak.set(save.getLunchBreakTime(), save.getLunchBreakLength());
            startTime.set(save.getTime());

            confirm.fire();
        } catch (IOException e) {
            Logger.error(e, e.getMessage());
        }

        confirm.fire();
    }

    @Override
    public void onSpeedChange(int speed) {
        this.speedSave = speed;
    }

    @Override
    public void onThemeColorChange(Color color) {
        this.themeColorSave = color;
        this.startTime.setColor(color);
    }

    /**
     * Chooses if the text within the schedule is gray or black.
     * @param darkness of the theme color, true if darkness is above 0.4, false if darkness is under 0.4
     */
    @Override
    public void onTextBrightnessChange(boolean darkness) {
        this.textDarkness = darkness;
    }

    @Override
    public void ClassBlockLengthChanged(int length) {
        this.classBlockLengthSave = length;
    }

    @Override
    public void onFastBreakTimeChange(Pair<Integer, Integer> time) {
        this.fastBreakTimeSave = time.getValue();
        this.fastBreakLengthSave = time.getKey();
    }

    @Override
    public void onLunchBreakTimeChange(Pair<Integer, Integer> time) {
        this.lunchBreakTimeSave = time.getValue();
        this.lunchBreakLengthSave = time.getKey();
    }

    @Override
    public void newStartTime(LocalTime time) {
        this.startingTime = time;
    }
}

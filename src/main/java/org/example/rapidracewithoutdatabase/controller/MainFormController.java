package org.example.rapidracewithoutdatabase.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.rapidracewithoutdatabase.model.Horse;
import org.example.rapidracewithoutdatabase.model.HorseController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public Pane HorsePane;
    public Pane RacePane;
    public Label MainTitle;
    public JFXTextField IdTextField;
    public JFXTextField NameTextField;
    public JFXTextField AgeTextField;
    public JFXTextField BreedTextField;
    public JFXTextField JockeyTextField;
    public JFXTextField ParticipatedRacesCountTextField;
    public JFXTextField WonRacesCountTextField;
    public JFXTextField GroupTextField;
    public JFXButton AddHorseButton;
    public JFXButton UpdateHorseButton;
    public JFXButton DeleteHorseButton;
    public JFXButton ClearHorseButton;
    public JFXButton SaveHorseDataFile;
    public JFXButton PaneHorseSelector;
    public JFXButton PaneRaceSelector;
    public JFXButton PaneExitApplication;
    public TableView<Horse> ViewHorsesTable;
    public TableColumn<Horse, Integer> TableColumnID;
    public TableColumn<Horse, String> TableColumnName;
    public TableColumn<Horse, Integer> TableColumnAge;
    public TableColumn<Horse, String> TableColumnBreed;
    public TableColumn<Horse, String> TableColumnJockey;
    public TableColumn<Horse, Integer> TableColumnRacesParticipatedCount;
    public TableColumn<Horse, Integer> TableColumnRacesWonCount;
    public TableColumn<Horse, String> TableColumnGroup;
    public TableColumn<Horse, Image> TableColumnImage;
    public ImageView ViewHorseImage;
    public JFXButton ImageButtonClicked;
    public JFXTextField SelectHorseFromGroupA;
    public JFXTextField SelectHorseFromGroupB;
    public JFXTextField SelectHorseFromGroupC;
    public JFXTextField SelectHorseFromGroupD;
    public JFXButton StartRaceButton;
    public JFXButton SelectWinnerButton;
    public JFXButton VisualizeWinnerTimeButton;
    public JFXTextField NameOfFirstPlace;
    public JFXTextField NameOfSecondPlace;
    public JFXTextField NameOfThirdPlace;
    public JFXTextField TimeOfFirstPlace;
    public JFXTextField TimeOfSecondPlace;
    public JFXTextField TimeOfThirdPlace;
    public BarChart VisualizingWinnerTimeBarChart;
    private HorseController horseController;
    private boolean IsRaceButtonIsOn = false;
    private List<Horse> randomHorses = new ArrayList<>();

    public MainFormController() {this.horseController = new HorseController();}
    public void HorsePaneSelector(ActionEvent actionEvent) {
        HorsePane.setVisible(true);
        RacePane.setVisible(false);
    }
    public void RaceOnAction(ActionEvent actionEvent) {
        RacePane.setVisible(true);
        HorsePane.setVisible(false);
    }

    public void ExitOnAction(ActionEvent actionEvent) {System.exit(0);}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HorsePane.setVisible(true);
        RacePane.setVisible(false);

        this.horseController = new HorseController();

        TableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumnBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        TableColumnJockey.setCellValueFactory(new PropertyValueFactory<>("jockeyName"));
        TableColumnRacesParticipatedCount.setCellValueFactory(new PropertyValueFactory<>("racesParticipated"));
        TableColumnRacesWonCount.setCellValueFactory(new PropertyValueFactory<>("racesWon"));
        TableColumnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        TableColumnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
    }



    public void SelectFirstSecondThirdPlacesOfTheRace(ActionEvent actionEvent) {
        if (!IsRaceButtonIsOn || randomHorses.isEmpty()) {
            showAlert("Error", "Cannot start race or no random horses selected.");
            return;
        }

        Random random = new Random();
        for (Horse horse : randomHorses) {
            int raceTime = random.nextInt(81) + 10;
            horse.setRaceTime(raceTime);
        }

        int n = randomHorses.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (randomHorses.get(j).getRaceTime() > randomHorses.get(j + 1).getRaceTime()) {
                    Horse temp = randomHorses.get(j);
                    randomHorses.set(j, randomHorses.get(j + 1));
                    randomHorses.set(j + 1, temp);
                }
            }
        }

        // Show the  race results
        StringBuilder raceResultDetails = new StringBuilder("Race Results:\n");
        for (int i = 0; i < Math.min(randomHorses.size(), 3); i++) {
            int place = i + 1;
            String horseName = randomHorses.get(i).getName();
            int raceTime = randomHorses.get(i).getRaceTime();
            String formattedRaceTime = raceTime + "s";
            raceResultDetails.append(place).append(". ").append(horseName).append(" - Time: ").append(formattedRaceTime).append("\n");

            switch (place) {
                case 1:
                    NameOfFirstPlace.setText(horseName);
                    TimeOfFirstPlace.setText(formattedRaceTime);
                    break;
                case 2:
                    NameOfSecondPlace.setText(horseName);
                    TimeOfSecondPlace.setText(formattedRaceTime);
                    break;
                case 3:
                    NameOfThirdPlace.setText(horseName);
                    TimeOfThirdPlace.setText(formattedRaceTime);
                    break;
            }
        }
        showAlert("Race Results", raceResultDetails.toString());
    }

    public void VisualizeFirstSecondThirdPlacesTimeOfTheRace(ActionEvent actionEvent) {
        // Check if the race has been started and winners have been selected
        if (NameOfFirstPlace.getText().isEmpty() || NameOfSecondPlace.getText().isEmpty() || NameOfThirdPlace.getText().isEmpty()) {
            showAlert("Error", "Cannot visualize race times. Winners have not been selected yet.");
            return;
        }

        // Clear existing data in the bar chart
        VisualizingWinnerTimeBarChart.getData().clear();

        // Create series for each winner
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("First Place", Integer.parseInt(TimeOfFirstPlace.getText().replaceAll("[^\\d]", ""))));
        series.getData().add(new XYChart.Data<>("Second Place", Integer.parseInt(TimeOfSecondPlace.getText().replaceAll("[^\\d]", ""))));
        series.getData().add(new XYChart.Data<>("Third Place", Integer.parseInt(TimeOfThirdPlace.getText().replaceAll("[^\\d]", ""))));

        // Add series to the bar chart
        VisualizingWinnerTimeBarChart.getData().add(series);
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

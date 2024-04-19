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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.example.rapidracewithoutdatabase.model.Horse;
import org.example.rapidracewithoutdatabase.model.HorseController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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

    public void SaveToFileWhenDataEntered(ActionEvent actionEvent) {
        List<Horse>HorseList=horseController.getHorses();
        try {
            File file = new File("Horse_details.txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Iterate over unique groups
            Set<String> uniqueGroups = new HashSet<>();
            for (Horse horse : HorseList) {
                uniqueGroups.add(horse.getGroup());
            }

            // Write horse details for each group
            for (String group : uniqueGroups) {
                bufferedWriter.write("Group " + group + ":\n");
                for (Horse horse : HorseList) {
                    if (horse.getGroup().equals(group)) {
                        bufferedWriter.write("Horse ID : " + horse.getId() + ",");
                        bufferedWriter.write(" Name : " + horse.getName() + ",");
                        bufferedWriter.write(" Age : " + horse.getAge() + ",");
                        bufferedWriter.write(" Breed : " + horse.getBreed() + ",");
                        bufferedWriter.write(" Jockey Name : " + horse.getJockeyName() + ",");
                        bufferedWriter.write(" Races Participated : " + horse.getRacesParticipated() + ",");
                        bufferedWriter.write(" Races Won : " + horse.getRacesWon() + ",");
                        bufferedWriter.write("Image Path : " + horse.getImage() + "\n");
                    }
                }
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();
            showAlert("HorseDetails.txt", "File Saved Successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("HorseDetails.txt", "File Save was Unsuccessful.");
        }
    }

    public void TableClickToSelectRowOfData(MouseEvent mouseEvent) {
        Horse selectedHorse = ViewHorsesTable.getSelectionModel().getSelectedItem();

        IdTextField.setText(String.valueOf(selectedHorse.getId()));
        NameTextField.setText(selectedHorse.getName());
        AgeTextField.setText(String.valueOf(selectedHorse.getAge()));
        BreedTextField.setText(selectedHorse.getBreed());
        JockeyTextField.setText(selectedHorse.getJockeyName());
        ParticipatedRacesCountTextField.setText(String.valueOf(selectedHorse.getRacesParticipated()));
        WonRacesCountTextField.setText(String.valueOf(selectedHorse.getRacesWon()));
        GroupTextField.setText(selectedHorse.getGroup());
        if (selectedHorse.getImage() != null) {
            ViewHorseImage.setImage(selectedHorse.getImage());
        }
    }

    public void AddImageToTheHorseClass(ActionEvent actionEvent) {
        ImageButtonClicked.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image");

            File initialDirectory = new File(System.getProperty("user.home"));
            fileChooser.setInitialDirectory(initialDirectory);

            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
            fileChooser.getExtensionFilters().add(imageFilter);

            File selectedFile = fileChooser.showOpenDialog(ImageButtonClicked.getScene().getWindow());

            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                ViewHorseImage.setImage(image);
            }
        });
    }
    public void StartRaceAndSetHorsesForTheRace(ActionEvent actionEvent) {
        if (IsRaceButtonIsOn) {
            showAlert("Error", "Cannot select random horses. Race has already started.");
            return;
        }

        // make raceStarted flag to true
        IsRaceButtonIsOn = true;

        // Check if each group has at least one horse in them
        boolean groupAExists = false;
        boolean groupBExists = false;
        boolean groupCExists = false;
        boolean groupDExists = false;

        // Iterate through the horses to check their groups and select random horses for each group
        List<Horse> groupAHorses = new ArrayList<>();
        List<Horse> groupBHorses = new ArrayList<>();
        List<Horse> groupCHorses = new ArrayList<>();
        List<Horse> groupDHorses = new ArrayList<>();

        for (Horse horse : horseController.getHorses()) {
            switch (horse.getGroup()) {
                case "A":
                    groupAExists = true;
                    groupAHorses.add(horse);
                    break;
                case "B":
                    groupBExists = true;
                    groupBHorses.add(horse);
                    break;
                case "C":
                    groupCExists = true;
                    groupCHorses.add(horse);
                    break;
                case "D":
                    groupDExists = true;
                    groupDHorses.add(horse);
                    break;
            }
        }

        // If one of the group does not have atleast one horse show the error message
        if (!(groupAExists && groupBExists && groupCExists && groupDExists)) {
            showAlert("Error", "Each group must have at least one member.");
            IsRaceButtonIsOn = false;
            return;
        }
        //randomly selected 4 horses are added to the randomHorses List

        Random random = new Random();
        Horse selectedGroupAHorse = groupAHorses.get(random.nextInt(groupAHorses.size()));
        Horse selectedGroupBHorse = groupBHorses.get(random.nextInt(groupBHorses.size()));
        Horse selectedGroupCHorse = groupCHorses.get(random.nextInt(groupCHorses.size()));
        Horse selectedGroupDHorse = groupDHorses.get(random.nextInt(groupDHorses.size()));

        // Set the text of the JFXTextField components with the details of the selected horses
        SelectHorseFromGroupA.setText(selectedGroupAHorse.toString());
        SelectHorseFromGroupB.setText(selectedGroupBHorse.toString());
        SelectHorseFromGroupC.setText(selectedGroupCHorse.toString());
        SelectHorseFromGroupD.setText(selectedGroupDHorse.toString());

        // Adding  horses to the randomHorses list
        randomHorses.add(selectedGroupAHorse);
        randomHorses.add(selectedGroupBHorse);
        randomHorses.add(selectedGroupCHorse);
        randomHorses.add(selectedGroupDHorse);
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

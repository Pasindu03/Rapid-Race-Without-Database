package org.example.rapidracewithoutdatabase.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
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

        /*loadPreExistingData();*/

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
}

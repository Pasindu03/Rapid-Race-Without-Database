module org.example.rapidracewithoutdatabase {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.rapidracewithoutdatabase to javafx.fxml;
    exports org.example.rapidracewithoutdatabase;
}
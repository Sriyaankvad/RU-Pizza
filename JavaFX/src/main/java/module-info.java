module proj_module {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.project4 to javafx.fxml;
    exports pizza.junit to junit;
    exports com.example.project4;
}
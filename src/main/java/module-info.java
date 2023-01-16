module edu.ggc.itec.cutcardscli {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ggc.itec.cutcardscli to javafx.fxml;
    exports edu.ggc.itec.cutcardscli;
}
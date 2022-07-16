module com.resrec.resrec {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hxmalar.resrec to javafx.fxml;
    exports com.hxmalar.resrec;
}
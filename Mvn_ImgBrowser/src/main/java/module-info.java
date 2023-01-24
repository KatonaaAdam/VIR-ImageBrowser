module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires shiro.core;
    requires sqlite.jdbc;
    requires java.desktop;

    opens org.example to javafx.fxml;
    exports org.example;
}

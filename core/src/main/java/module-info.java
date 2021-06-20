import org.example.Figures;
import org.example.factory.FiguresFactory;


module core {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires com.google.gson;

    uses Figures;
    uses FiguresFactory;

    opens org.example.Serialize;
    opens org.example.Figure;
    opens org.example;

    exports org.example.Serialize;
    exports org.example;
    exports org.example.Figure;
    exports org.example.factory;
}
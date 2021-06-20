import org.example.Figures;
import org.example.Trapezoid.Trapezoid;
import org.example.Trapezoid.TrapezoidFactory;
import org.example.factory.FiguresFactory;


module TrapezoidModule {

    requires core;
    requires javafx.graphics;
    requires javafx.controls;
    requires com.google.gson;

    opens org.example.Trapezoid;

    exports org.example.Trapezoid;

    provides FiguresFactory with TrapezoidFactory;
    provides Figures with Trapezoid;

}
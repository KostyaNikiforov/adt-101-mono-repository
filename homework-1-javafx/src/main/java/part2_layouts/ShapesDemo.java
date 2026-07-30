package part2_layouts;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ShapesDemo extends Pane {
    public ShapesDemo() {
        setBackground(new Background(
                new BackgroundFill(Color.web("#e8f4ff"), CornerRadii.EMPTY, Insets.EMPTY)));

        getChildren().addAll(
                createSun(),
                createCloud(),
                createHouse(),
                createRoof(),
                createRoad(),
                createFence(),
                createCaption()
        );
    }

    private Circle createSun() {
        Circle sun = new Circle(420, 70, 40);
        sun.setFill(Color.GOLD);
        sun.setOpacity(0.75);
        sun.setStroke(Color.ORANGE);
        sun.setStrokeWidth(3);
        return sun;
    }

    private Ellipse createCloud() {
        Ellipse cloud = new Ellipse(150, 70, 70, 30);
        cloud.setFill(Color.WHITE);
        cloud.setOpacity(0.8);
        cloud.setStroke(Color.LIGHTGRAY);
        cloud.setStrokeWidth(2);
        cloud.getStrokeDashArray().addAll(8.0, 6.0);
        return cloud;
    }

    private Rectangle createHouse() {
        Rectangle house = new Rectangle(180, 200, 160, 120);
        house.setFill(Color.web("#ffe0b2"));
        house.setStroke(Color.SADDLEBROWN);
        house.setStrokeWidth(5);
        house.setStrokeLineJoin(StrokeLineJoin.ROUND);
        return house;
    }

    private Polyline createRoof() {
        Polyline roof = new Polyline(175.0, 200.0, 260.0, 140.0, 345.0, 200.0);
        roof.setFill(Color.INDIANRED);
        roof.setStroke(Color.DARKRED);
        roof.setStrokeWidth(6);
        roof.setStrokeLineJoin(StrokeLineJoin.MITER);
        roof.setStrokeLineCap(StrokeLineCap.ROUND);
        return roof;
    }

    private Line createRoad() {
        Line road = new Line(40, 350, 480, 350);
        road.setStroke(Color.DIMGRAY);
        road.setStrokeWidth(10);
        road.setStrokeLineCap(StrokeLineCap.ROUND);
        road.getStrokeDashArray().addAll(30.0, 20.0);
        return road;
    }

    private Polyline createFence() {
        Polyline fence = new Polyline(
                40.0, 320.0, 40.0, 280.0,
                70.0, 280.0, 70.0, 320.0,
                100.0, 320.0, 100.0, 280.0,
                130.0, 280.0, 130.0, 320.0);
        fence.setFill(null);
        fence.setStroke(Color.BURLYWOOD);
        fence.setStrokeWidth(6);
        fence.setStrokeLineCap(StrokeLineCap.SQUARE);
        fence.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        return fence;
    }

    private Text createCaption() {
        Text caption = new Text(140, 400, "Shapes make a picture");
        caption.setFont(new Font(26));
        caption.setFill(Color.CORNFLOWERBLUE);
        caption.setStroke(Color.DARKBLUE);
        caption.setStrokeWidth(1);
        caption.setOpacity(0.9);
        return caption;
    }
}

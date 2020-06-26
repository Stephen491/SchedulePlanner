import com.sun.jdi.StackFrame;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.shape.Box;
import javafx.scene.Group;
import javafx.scene.shape.*;
import java.util.Calendar;
import java.util.Hashtable;

public class MainController extends Application{


    private Week currentWeek;

    public static void main(String args[]) {
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Schedule Planner");
        Box box;
        Rectangle[][] boxes = new Rectangle[7][24];
        Text[][] hour_stamps = new Text[7][24];

        VBox root = new VBox();
        int init_horizontal_distance = 100;
        int horitzontal_distance = 150;
        int vertical_distance = 100;
        ScrollPane sp = new ScrollPane();
        StackPane stackpane = new StackPane();
        Group group = new Group();
        initialize_boxes(boxes, hour_stamps);


        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                ScheduleItem item = this.find_affected_item(e);
                System.out.println(item.get_box());
            }

            public ScheduleItem find_affected_item(MouseEvent e) {
                Point2D coordinates = new Point2D(e.getX(), e.getY());
                for(int i = 0; i<7; i++) {
                    for(int x = 0; x<24; x++) {
                        if(boxes[i][x].contains(coordinates)){
                            return get_schedule_item(i,x);
                        }
                    }
                }
                return null; //Problematic
            }
        };


        for(int i = 0; i<7; i++) {
            for(int x = 0; x<24; x++) {
                boxes[i][x].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                group.getChildren().add(boxes[i][x]);
                group.getChildren().add(hour_stamps[i][x]);
            }
        }




        stackpane.getChildren().add(group);
        sp.setFitToWidth(true);
        sp.setContent(stackpane);

        Scene scene = new Scene(sp, 1500,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    ScheduleItem get_schedule_item(int day, int hour) {
        return currentWeek.get_days().get(day).get_hours().get(hour);
    }

    void initialize_boxes(Rectangle[][] boxes, Text[][] hour_stamps) {
        Hashtable<String, Integer> time_dictionary = get_current_time();
        currentWeek = new Week(time_dictionary);
        for(int i = 0; i<7; i++) {
            for(int x = 0; x<24; x++) {
                boxes[i][x] = get_schedule_item(i,x).get_box();
                hour_stamps[i][x] = get_schedule_item(i,x).get_hour_stamp();
            }
        }
    }

    Hashtable<String, Integer> get_current_time() {
        Calendar cal = Calendar.getInstance();
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK)-1;
        int dayofthemonthstarting = cal.DAY_OF_MONTH-dayofweek;
        int month = cal.MONTH;
        int year = cal.YEAR;
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        Hashtable<String, Integer> time_dictionary = new Hashtable<String, Integer>();
        time_dictionary.put("dayofweek",dayofweek);
        time_dictionary.put("hour", hour);
        time_dictionary.put("dayofmonthstart", dayofthemonthstarting);
        time_dictionary.put("month", month);
        time_dictionary.put("year", year);
        return time_dictionary;
    }
}



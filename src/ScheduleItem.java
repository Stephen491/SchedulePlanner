import javafx.scene.control.TextField;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import java.util.Calendar;
import java.time.LocalTime;
import java.util.Hashtable;

import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class ScheduleItem {
    String name;
    String description;
    LocalTime time;
    Rectangle box;
    int dayOfTheWeek;
    int hour;
    int day;
    int month;
    int year;
    int hourBlock;
    boolean completed = false;
    boolean expired = false;
    int vertical_distance = 100;
    int box_width = 200;
    int box_height = 90;
    int box_depth = 100;
    int init_horizontal_distance = 100;
    int horizontal_distance = 215;
    TextField textfield;

    Hashtable<String, Integer> time_dictionary;
    Text hour_stamp;

    //Constructor1
    ScheduleItem(Hashtable<String, Integer> time_dictionary, int hourBlock) {
        this.hourBlock = hourBlock;

    }
    ScheduleItem(Hashtable<String, Integer> time_dictionary, int hourBlock, int day) {
        this.hourBlock = hourBlock;
        this.time_dictionary = time_dictionary;
        this.day = day;
        box = new Rectangle(day*horizontal_distance+init_horizontal_distance,vertical_distance*hourBlock,box_width, box_height);
        box.setFill(javafx.scene.paint.Color.WHITE);
        update_time();
       // box.setTranslateX(day);
       // box.setTranslateY(vertical_distance*hourBlock);
        hour_stamp = new Text(day*horizontal_distance+init_horizontal_distance+10,vertical_distance*hourBlock+20, hourBlock+":00");
        textfield = new TextField();
        textfield.setLayoutX(day*horizontal_distance+init_horizontal_distance+10);
        textfield.setLayoutY(vertical_distance*hourBlock+30);
        textfield.setVisible(false);

    }

    static ScheduleItem create_schedule_item(Hashtable<String, Integer> time_dictionary, int hourBlock, int day) {
        ScheduleItem new_schedule_item = new ScheduleItem(time_dictionary, hourBlock, day);



        return new_schedule_item;


    }
    void set_name(String new_name) {
        name = new_name;
    }



    void show_text_field() {
        textfield.setVisible(true);
    }

    void hide_text_field() {
        textfield.setVisible(false);
    }

    void set_description(String new_description) {
        description = new_description;
    }

    String get_name() {
        return name;
    }

    String get_description() {
        return description;
    }

    Rectangle get_box() { return box; }

    TextField get_textfield()  {
        return textfield;
    }

    void set_item_status(boolean status) {
        completed = status;
    }
    void expire_item() {
        expired = true;
        box.setFill(javafx.scene.paint.Color.DARKGRAY);
    }
    Boolean is_expired() {
        return expired;
    }
    Text get_hour_stamp() {
        return hour_stamp;
    }

    void update_time() {
        if(time_dictionary.get("dayofweek")>day||(time_dictionary.get("dayofweek")==day && time_dictionary.get("hour")>hourBlock)) {
            this.expire_item();

        }

    }


}






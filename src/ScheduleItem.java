import javafx.scene.control.TextArea;
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
    int vertical_distance = 175;
    int box_width = 200;
    int box_height = 150;
    int init_horizontal_distance = 100;
    int horizontal_distance = 215;
    int description_field_width = 150;
    int description_field_height = 75;
    TextField name_field;
    TextArea description_field;

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
        name_field = new TextField();
        name_field.setLayoutX(day*horizontal_distance+init_horizontal_distance+10);
        name_field.setLayoutY(vertical_distance*hourBlock+30);
        name_field.setVisible(false);

        description_field = new TextArea();
        description_field.setLayoutX(day*horizontal_distance+init_horizontal_distance+10);
        description_field.setLayoutY(vertical_distance*hourBlock+60);
        description_field.setVisible(false);
        description_field.setPrefHeight(description_field_height);
        description_field.setPrefWidth(description_field_width);
    }

    static ScheduleItem create_schedule_item(Hashtable<String, Integer> time_dictionary, int hourBlock, int day) {
        ScheduleItem new_schedule_item = new ScheduleItem(time_dictionary, hourBlock, day);



        return new_schedule_item;


    }


    void set_name(String new_name) {
        name = new_name;
    }





    TextField get_name_field()  {

        return name_field;
    }

    void show_name_field() {
        name_field.setVisible(true);
    }

    void hide_name_field() {

        name_field.setVisible(false);
    }


    TextArea get_description_field() {
        return description_field;
    }

    void show_description_field() {
        description_field.setVisible(true);
    }

    void hide_description_field() {
        description_field.setVisible(false);
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






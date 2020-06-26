import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;


public class Week {
    ArrayList<Day> days = new ArrayList<Day>();
    int xPos;
    int init_horizontal_distance = 100;
    int horizontal_distance = 215;

    Week(Hashtable<String, Integer> time_dictionary) {
        for(int i = 0; i<7;i++) {
           // xPos = init_horizontal_distance+horizontal_distance*i;
            days.add(i, new Day(time_dictionary, i, i));
        }
    }

    //set date/time for archiving purposes
    void update_time() {

    }

    void set_name(String name, int day, int hour) {
        days.get(day).set_name(hour, name);

    }
    void set_description(String description, int day, int hour) {
        days.get(day).set_description(hour, description);
    }
    ArrayList<Day> get_days() {
        return days;
    }



}

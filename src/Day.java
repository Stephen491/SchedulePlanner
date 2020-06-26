import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

public class Day {
        ArrayList<ScheduleItem> hourBlocks;
        int column; //day of the week
        int year;
        int month;
        int weekofthemonth;
        int init_horizontal_distance = 100;
        int horitzontal_distance = 150;
        int vertical_distance = 100;


        Day(Hashtable<String, Integer> time_dictionary, int dayoftheweek, int day) {
            hourBlocks = new ArrayList<ScheduleItem>();
            for(int i = 0; i<24; i++) {
                hourBlocks.add(i, new ScheduleItem(time_dictionary, i, day));
            }
            column = dayoftheweek;

        }

        ArrayList<ScheduleItem> get_hours() {
            return hourBlocks;
        }

        //set the time for backup/archive purposes
        void set_time(LocalTime time, Calendar date) {


        }


        //Set the day of the week when the week is initialized
        void set_column(int c) {
            column = c;
        }

        //get this day's day of the week
        int get_day_of_the_week() {
            return column;
        }

        //A function that is called over a periodic time period or when the program is reloaded
        void update_time() {
            int hour = LocalTime.now().getHour();
            int currentDay = Calendar.DAY_OF_WEEK;
            if(currentDay==column) {
                this.expire_up_to(hour);
            }
            else if(currentDay>column) {
                this.expire_all_hours();
            }
        }

        void set_name(int itemRow, String name) {
            hourBlocks.get(itemRow).set_name(name);

        }
        void set_description(int itemRow, String description) {

            hourBlocks.get(itemRow).set_description(description);
        }

        void expire_up_to(int block) {
            for (int i = 0; i < block; i++) {
                hourBlocks.get(i).expire_item();
            }
        }

        void expire_all_hours() {
            for(ScheduleItem block: hourBlocks) {
                block.expire_item();
            }
        }



}

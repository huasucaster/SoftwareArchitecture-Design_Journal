import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class _ClockPane extends Pane {
        private int hour,minute,second;
        public _ClockPane()
        {
            setCurrentTime();
        }
        public _ClockPane(int hour, int minute, int second)
        {
            this.hour=hour;this.minute=minute;this.second=second;
        }
        public int getHour() {return hour;}
        public int getMinute() {return minute;}
        public int getSecond() {return second;}
        public void setHour(int h) {this.hour=h;paintClock();}
        public void setMinute(int m) {this.minute=m;paintClock();}
        public void setSecond(int s) {this.second=s;paintClock();}
        public void setCurrentTime()
        {
            //Set the time!
            Calendar calendar=new GregorianCalendar();
            this.hour=calendar.get(Calendar.HOUR_OF_DAY);
            this.minute=calendar.get(Calendar.MINUTE);
            this.second=calendar.get(Calendar.SECOND);
            paintClock();
        }
        //Build the clock!
        public void paintClock()
        {
            getChildren().clear();
            double clockRadius=250*0.8*0.4;
            double centerX = 150 / 2.0 + 80;
            double centerY = 300 / 2.0-85;
            for(int i = 0; i < 60; i++) {
                double x = centerX + Math.sin((i / 60.0) * 2 * Math.PI) * clockRadius;
                double y = centerY + Math.cos((i / 60.0) * 2 * Math.PI) * clockRadius;
                double radius= i%15==0?5:(i%5==0?3:1);
                Circle c = new Circle(x,y,radius);
                if(i%5!=0 && i%15!=0) c.setFill(Color.BLACK);
                else c.setFill(Color.SANDYBROWN);

                getChildren().add(c);
            }
            //second hand
            double slength = clockRadius * 0.8;
            double secondX = centerX + slength * Math.sin((second / 60.0) * 2 * Math.PI);
            double secondY = centerY - slength * Math.cos((second / 60.0) * 2 * Math.PI);
            Line sLine = new Line(centerX,centerY,secondX,secondY);
            sLine.setStrokeWidth(1);
            sLine.setStroke(Color.SALMON);
            //minute hand
            double mlength = clockRadius * 0.65;
            double minuteX = centerX + mlength * Math.sin((Math.PI / 60) * 2 * minute);
            double minuteY = centerY - mlength * Math.cos((Math.PI / 60) * 2 * minute);
            Line mLine = new Line(centerX,centerY,minuteX,minuteY);
            mLine.setStrokeWidth(2);
            mLine.setStroke(Color.SKYBLUE);
            //hour hand
            double hlength = clockRadius * 0.5;
            double hourX = centerX + hlength * Math.sin((Math.PI / 12) * 2 * (hour % 12 + minute / 60.0));
            double hourY = centerY - hlength * Math.cos((Math.PI / 12) * 2 * (hour % 12 + minute / 60.0));
            Line hLine = new Line(centerX,centerY,hourX,hourY);
            hLine.setStrokeWidth(4);
            hLine.setStroke(Color.PEACHPUFF);
            setStyle("-fx-background-color:NULL");
            getChildren().addAll(sLine,mLine,hLine);
        }
}

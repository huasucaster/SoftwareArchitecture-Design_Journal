import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class _CalendarPane extends GridPane
{
    public _CalendarPane(int year, int month, GetJournal log)
    {
        Label sun=new Label("SUN");sun.setFont(Font.font("Arial Rounded MT Bold",FontWeight.BOLD,12));
        Label mon=new Label("MON");mon.setFont(Font.font("Arial Rounded MT Bold",FontWeight.BOLD,12));
        Label tue=new Label("TUE");tue.setFont(Font.font("Arial Rounded MT Bold",FontWeight.BOLD,12));
        Label wed=new Label("WED");wed.setFont(Font.font("Arial Rounded MT Bold",FontWeight.BOLD,12));
        Label thu=new Label("THU");thu.setFont(Font.font("Arial Rounded MT Bold",FontWeight.BOLD,12));
        Label fri=new Label("FRI");fri.setFont(Font.font("Arial Rounded MT Bold",FontWeight.BOLD,12));
        Label sat=new Label("SAT");sat.setFont(Font.font("Arial Rounded MT Bold",FontWeight.BOLD,12));
        this.add(sun, 0, 0);
        this.add(mon, 1, 0);
        this.add(tue, 2, 0);
        this.add(wed,3, 0);
        this.add(thu, 4, 0);
        this.add(fri,5, 0);
        this.add(sat, 6, 0);

        Calendar c=new GregorianCalendar(year, month-1,1);
        this.setGridLinesVisible(false);
        int hang=1;
        int totalDaysOfThisMonth=c.getActualMaximum(Calendar.DAY_OF_MONTH);//Get the size of days in the month
        Button[] dates=new Button[40];
        for(int i=1;i<=totalDaysOfThisMonth;i++)
        {
            int day=i;
            int dayOfWeek=c.get(Calendar.DAY_OF_WEEK)-1;//Sunday is the first day, Saturday is the last day!
            dates[i]=new Button(Integer.toString(i));
            dates[i].setStyle("-fx-background-color:null;-fx-background-insets: 0; -fx-pref-width: 42px;-fx-pref-height: 30px;");
            Button date=dates[i];
            int k=i;
            dates[i].setOnAction(e->{
                date.setStyle(" -fx-pref-width: 42px;-fx-pref-height: 30px;-fx-background-color:mistyrose");//选中的颜色
                for(int j=1;j<=totalDaysOfThisMonth;j++)
                {
                    if(j!=k)
                        dates[j].setStyle("-fx-background-color:null;-fx-background-insets: 0;  -fx-pref-width: 42px;-fx-pref-height: 30px;");
                }
                log.setTitle( month + " / " + day + " / "+year);
                String s=String.format("%04d%02d%02d",year,month,day);
                s+=".txt";
                log.setURL(s);
            });//Set the date of calendar to the right side!
            this.add(dates[i], dayOfWeek, hang);
            if(c.get(Calendar.DAY_OF_WEEK)==7) hang++;
            c.add(Calendar.DAY_OF_MONTH,1);//Day number adds 1, become next day.
        }
    }
}

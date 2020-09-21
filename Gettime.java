import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class Gettime {
    public BorderPane time(int year, int month,GetJournal log)
    {
        BorderPane leftOftheWholePane=new BorderPane();
        _ClockPane clock=new _ClockPane();//Transfer time to the clock!
        EventHandler<ActionEvent> eventHandler = e -> {
            clock.setCurrentTime();
        };
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(1000),eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        _CalendarPane calender= new _CalendarPane(year,month,log);//Transfer time to the calendar!
        leftOftheWholePane.setCenter(calender);
        leftOftheWholePane.setBottom(clock);
        return leftOftheWholePane;
    }
}

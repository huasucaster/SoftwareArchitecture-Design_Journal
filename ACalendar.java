//Refer to https://blog.csdn.net/xHibiki/article/details/82925408；
//Refer to https://www.linkedin.com/learning/learning-javafx-gui-development/blend-shapes?u=2139050；
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ACalendar extends Application
{
    public BorderPane wholePane=new BorderPane();
    Calendar currentDate=new GregorianCalendar();
    //Set the journal path
    GetJournal log=new GetJournal(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1,currentDate.get(Calendar.DAY_OF_MONTH));
    //Set the time;
    Gettime t=new Gettime();
    BorderPane left=t.time(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1,log);
    public void start(Stage primaryStage)
    {
        _Editjournal bottom=new _Editjournal(log);
        _DateSelection top=new _DateSelection(currentDate.get(Calendar.YEAR),currentDate.get(Calendar.MONTH)+1,log,wholePane);
        top.setPadding(new Insets(5,5,5,5));
        left.setPadding(new Insets(5,5,5,5));
        bottom.setPadding(new Insets(5,5,5,5));
        log.setPadding(new Insets(5,5,5,5));
        wholePane.setTop(top);wholePane.setLeft(left);
        wholePane.setBottom(bottom);wholePane.setRight(log);
        Scene scene = new Scene(wholePane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
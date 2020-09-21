import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class _DateSelection extends HBox
{
    public int year,month;
    public TextField yearText = new TextField();
    public TextField monthText = new TextField();
    public Button btLastYear = new Button("Last year");
    public Button btNextYear = new Button("Next year");
    public Button btLastMonth = new Button("Last month");
    public Button btNextMonth = new Button("Next month");
    public BorderPane sp=new BorderPane();
    Gettime T=new Gettime();
    public _DateSelection(int year, int month, GetJournal log, BorderPane wholePane)
    {
        sp=wholePane;
        this.year=year;this.month=month;
        this.yearText.setText(Integer.toString(year));
        this.monthText.setText(Integer.toString(month));
        yearText.setAlignment(Pos.CENTER);
        monthText.setAlignment(Pos.CENTER);
        yearText.setEditable(true);
        monthText.setEditable(true);

        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        yearText.setOnAction(e->{
            int newYear=Integer.parseInt(yearText.getText()),newMonth=Integer.parseInt(monthText.getText());
            setCalendarBaseOnGivenDate(newYear,newMonth,log);
        });
        monthText.setOnAction(e->{
            int newYear=Integer.parseInt(yearText.getText()),newMonth=Integer.parseInt(monthText.getText());
            setCalendarBaseOnGivenDate(newYear,newMonth,log);
        });
        btLastYear.setOnAction(e->{
            this.year--;
            yearText.setText(String.valueOf(this.year));
            log.setTextArea("");
            sp.setLeft(T.time(this.year,this.month,log));
        });
        btNextYear.setOnAction(e->{
            this.year++;
            yearText.setText(String.valueOf(this.year));
            log.setTextArea("");
            sp.setLeft(T.time(this.year,this.month,log));
        });
        btLastMonth.setOnAction(e->{
            if(this.month==1)
            {
                this.month=12;this.year--;
            }
            else
                this.month--;
            yearText.setText(String.valueOf(this.year));
            monthText.setText(String.valueOf(this.month));
            log.setTextArea("");
            sp.setLeft(T.time(this.year,this.month,log));
        });
        btNextMonth.setOnAction(e->{
            if(this.month==12)
            {
                this.month=1;this.year++;
            }
            else
                this.month++;
            yearText.setText(String.valueOf(this.year));
            monthText.setText(String.valueOf(this.month));
            log.setTextArea("");
            sp.setLeft(T.time(this.year,this.month,log));
        });
        yearText.setPrefColumnCount(6);
        yearText.setPrefHeight(5);
        monthText.setPrefColumnCount(6);
        monthText.setPrefHeight(5);
        this.getChildren().addAll(btLastYear,yearText,btNextYear,btLastMonth,monthText,btNextMonth);
    }
    public void setCalendarBaseOnGivenDate(int year,int month,GetJournal log)
    {
        if(year>0 && month>0 && month<=12)
        {
            this.year=year;this.month=month;
            sp.setLeft(T.time(this.year,this.month,log));
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Set the date");
            alert.setContentText("Error!Please set legal date!");
            alert.showAndWait();
        }
    }

}

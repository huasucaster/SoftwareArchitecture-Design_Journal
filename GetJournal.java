import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GetJournal extends VBox
{
    public Label title;
    public TextArea logArea;
    public String url;
    //Set the date to the right block!
    public GetJournal(int year, int month, int day)
    {
        title=new Label();
        logArea=new TextArea();
        logArea.setWrapText(true);
        title.setAlignment(Pos.CENTER);
        logArea.setPrefColumnCount(25);
        logArea.setPrefRowCount(21);
        String s=String.format("%02d%02d%04d",month,day,year);
        this.url=s+".txt";
        setTitle(month + " / " + day + " / "+year);
        title.setStyle("-fx-pref-width: 350px;-fx-pref-height: 20px;-fx-background-color:mistyrose");
        title.setFont(Font.font("Arial Rounded MT Bold", FontWeight.BOLD,15));
        title.setTextFill(Color.BLACK);
        setAlignment(Pos.CENTER);
        ScrollPane scrollPane=new ScrollPane(logArea);
        getChildren().addAll(title,logArea);
    }
    //set
    public void setTitle(String s) {title.setText(s);}
    public void setTextArea(String s) {logArea.setText(s);}
    public void setURL(String s) {this.url=s;}
    //get
    public String getTitle() {return title.getText();}
    public String getTextArea() {return logArea.getText();}
    public String getURL() {return this.url;}
}

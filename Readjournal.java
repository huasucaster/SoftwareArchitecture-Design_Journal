import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.util.Scanner;

public class Readjournal {
    Button btLogRead=new Button("Read journal");
    public Readjournal(GetJournal log) {
        btLogRead.setOnAction(e -> {
            try {
                Scanner input = new Scanner(new File(log.getURL()));
                String s = "";
                while (input.hasNext())
                    s += input.nextLine();
                log.setTextArea(s);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Read journal");
                alert.setContentText("Successful to read journal!");
                alert.showAndWait();
                input.close();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Read journal");
                alert.setContentText("Error!None journal exist!");
                alert.showAndWait();
            }
        });
    }
}

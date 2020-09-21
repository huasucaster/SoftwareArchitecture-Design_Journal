import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;

public class Deletejournal {
    Button btLogDelete=new Button("Delete journal");
    public Deletejournal(GetJournal log) {
        btLogDelete.setOnAction(e -> {
            log.setTextArea("");
            try {
                String s = log.getURL();
                File f = new File(s);
                f.delete();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete journal");
                alert.setContentText("Successful to delete journal!");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete journal");
                alert.setContentText("Failed to delete journal");
                alert.showAndWait();
            }
        });
    }
}


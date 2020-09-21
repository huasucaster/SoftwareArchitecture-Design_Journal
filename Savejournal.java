import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileWriter;

public class Savejournal{
    Button btLogSave=new Button("Save journal");
    public Savejournal(GetJournal log)

    {
        btLogSave.setOnAction(e -> {
            try {
                File file = new File(log.getURL());
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(log.getTextArea());
                fileWriter.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Save journal");
                alert.setContentText("Successful to save!");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Save journal");
                alert.setContentText("Failed to Save!");
                alert.showAndWait();
            }
        });
    }
}

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class _Editjournal extends HBox{
    public _Editjournal(GetJournal log) {
        Savejournal sj = new Savejournal(log);
        Readjournal rj = new Readjournal(log);
        Deletejournal dj = new Deletejournal(log);
        ExportPDF xp=new ExportPDF(log);
        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(sj.btLogSave, rj.btLogRead, dj.btLogDelete,xp.btLogExport);



    }
}
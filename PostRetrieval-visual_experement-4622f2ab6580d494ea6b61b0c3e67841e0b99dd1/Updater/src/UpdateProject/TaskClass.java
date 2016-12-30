package UpdateProject;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 30.06.2016.
 */
public class TaskClass extends Task{
        @Override public Void call() {
            UpdaterLogic logic = new UpdaterLogic();

            updateProgress(0, 100);
            try {
                List<String> newVersion = logic.readNewVersionFile(logic.linkVersionFile);

                logic.getNewVersion(newVersion);

                Thread.sleep(1000);
                updateProgress(20, 100);
                updateMessage("подготовка программы.....");

                Thread.sleep(1000);
                updateProgress(40, 100);
                updateMessage("подготовка программы.....");

                logic.copyNewVersion(new File(logic.tempFiles), new File(logic.pathToFiles));
                updateProgress(65, 100);
                updateMessage("копирование......");

                logic.delete(new File(logic.tempFiles));
                updateProgress(85, 100);
                Thread.sleep(1000);
                updateMessage("копирование......");

                updateProgress(90, 100);
                Thread.sleep(1000);
                updateProgress(100, 100);
                updateMessage("Завершение обновления....");

                Thread.sleep(1000);
                Platform.exit();

            } catch (IOException | InterruptedException e) {
                updateMessage("Somthings wrong.... =(\nCheck Internet connection!");
                logic.delete(new File(logic.tempFiles));
            }
            return null;
        }
}
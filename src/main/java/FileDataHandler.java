import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDataHandler {

    private final static Path FOLDERPATH = Paths.get(System.getProperty("user.dir") + "/data");

    private final static Path FILEPATH = Paths.get(FOLDERPATH + "/duke.txt");


    public FileDataHandler() throws IOException {

    }

    public static TaskList load() throws  IOException {
        boolean directoryExists = java.nio.file.Files.exists(FOLDERPATH);
        boolean fileExists = java.nio.file.Files.exists(FILEPATH);
        TaskList taskList = new TaskList();

        if(!directoryExists) {
            Files.createDirectories(FOLDERPATH);
        }

        if(!fileExists) {
            FileWriter fw = new FileWriter(FILEPATH.toString());
            fw.close();
        }
//        return readFile();

        File f = new File(FILEPATH.toString()); // create a File for the given file path
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            char taskType = line.charAt(1);
//            System.out.println(taskType);
            if (taskType == 'T') {
                String description = line.substring(7);
                taskList.addTaskWithoutPrinting(new ToDos(description));
            } else if (taskType == 'D') {
                String descriptionAndDate = line.substring(7);
                String[] arguments  = descriptionAndDate.split("\\(");
                String description = arguments[0];
                String date = removeLastChar(arguments[1]);
                taskList.addTaskWithoutPrinting(new Deadlines(description, date));
            }else if (taskType == 'E') {
                String descriptionAndDate = line.substring(7);
                String[] arguments  = descriptionAndDate.split("\\(");
                String description = arguments[0];
                String date = removeLastChar(arguments[1]);
                taskList.addTaskWithoutPrinting(new Events(description, date));
            }
        }
        return  taskList;
    }

//    the code should save the whole file again instead of appending line by line
    public static void save(ArrayList<Task> taskListToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH.toString());
        String fullText = "";
        int ListLength = taskListToAdd.size();
        for (int i = 0; i < ListLength; i++) {
            fullText = fullText + taskListToAdd.get(i).getDescription() + System.lineSeparator();
        }

        fw.write(fullText);
        fw.close();
    }

    public static String removeLastChar(String s) {
        s = s.substring(0, s.length()-1);
        return s;
    }

//    public static Scanner readFile() throws FileNotFoundException {
//        File f = new File(FILEPATH.toString()); // create a File for the given file path
//        Scanner s = new Scanner(f);
//        return s;
//    }

}

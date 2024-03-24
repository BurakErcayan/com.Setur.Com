package utilities.fileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
    public static String file;
    public static String getCityName(){
        file = "src/test/resources/csv/tatilSehri.csv";
        return readCSV(file);
    }
    public static String readCSV(String filePath) {
        StringBuilder data = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}
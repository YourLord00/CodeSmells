package Loaders;

import Entities.SingleTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TableFileLoader {

    private final static String[] HEADERS = {"Table Number", "Number of Seats"};
    private String fileName;
    public TableFileLoader(String fileName){
        this.fileName = fileName;
    }

    public File getDefaultFile() {
        return new File("./tables.txt");
    }


    public List<SingleTable> load(){
        List<SingleTable> tables = new ArrayList<SingleTable>();
        int numberOfColumns = HEADERS.length;
        boolean useDefault = true;

        BufferedReader breader = null;
        File file = new File(fileName);;
        List<List<String>> result = new ArrayList<List<String>>();
        try {
            if (!file.exists()) {
                if (useDefault) {
                    file = getDefaultFile();
                } else {
                    throw new IllegalArgumentException(
                            "The specified Components.Menu File does not exist.");}
            }
            breader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line = breader.readLine();
            while (line != null) {
                String[] entries = line.split(",");
                List<String> lineEntry = new ArrayList<String>();
                if (entries.length != numberOfColumns) {
                    throw new IllegalArgumentException("The specified Columns are incorrect.");
                }
                for (String entry : entries) {
                    lineEntry.add(entry);
                }
                result.add(lineEntry);
                line = breader.readLine();
            }
            breader.close();
        }catch (IOException exception){exception.printStackTrace();}
        finally {return getTables(result,tables);}
    }
    public List<SingleTable> getTables(List<List<String>> result, List<SingleTable> tables){
        for(List<String> line : result ){
            int tableIndex = Integer.parseInt(line.get(0).trim());
            int tableSeats = Integer.parseInt(line.get(1).trim());
            SingleTable table = new SingleTable(tableIndex,tableSeats);
            tables.add(table);
        }
        return tables;
    }
}

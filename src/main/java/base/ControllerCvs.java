package base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class ControllerCvs{
    private final String csv = "data.csv";
    public void initFile(int actions, int positions){

        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < positions; i++){
            String[] record = new String[actions];
            for(int j = 0; j < actions; j++){
                record[j] = "0.0";
            }
            writer.writeNext(record);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[][] read(){
        CSVReader reader = null;

        try {
            reader = new CSVReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String[]> allRows = null;
        try {
            allRows = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[][] result = new double[allRows.size()][];
        for(int j = 0; j < allRows.size(); j++){
            String[] row = allRows.get(j);
            int n = row.length;
            double[] buff = new double[n];
//            System.out.println(row[j]);
            for(int i = 0; i < n; i++){
                buff[i] = Double.parseDouble(row[i]);

            }
            result[j] = buff;
        }
        return result;
    }


    public void updateFile(double[][] qvalues){

        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int positions = qvalues.length;
        int actions = qvalues[0].length;
        for(int i = 0; i < positions; i++){
            String[] record = new String[actions];
            for(int j = 0; j < actions; j++){
                record[j] = Double.toString(qvalues[i][j]);
            }
            writer.writeNext(record);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

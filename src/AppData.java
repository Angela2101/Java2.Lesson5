import java.io.*;
import java.util.Arrays;

public class AppData {
    private String[] header;
    private int[][] data = null;
    private int rows;
    private int columns;
    private int sumOflines = 0;

    public void setHeader(String[] header) {
        this.header = header;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void parseHeader(String h){
        header = h.split(";");
        rows = header.length;

        for (int i = 0; i < header.length; i++) {
            header[i] = header[i].trim();
        }
        System.out.println(Arrays.toString(header));

    }

    public void setColumns (int numberColums){
        columns = numberColums;
    }

    public void parseRow(String r){
        int [] timeInt = new int[rows];
        String [] time = r.split(";");
        if (data == null) {
            data = new int[columns][rows];
        }
        for(int i = 0; i < time.length; i++){
            timeInt[i] = Integer.parseInt(time[i].trim());

        }
        System.arraycopy(timeInt, 0, data[sumOflines], 0, rows);
        sumOflines++;
        System.out.println(Arrays.toString(time));
    }
    public void save(){
        File file = new File("src/lesson5.csv");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            bufferedWriter.write(String.join("; ", header) + "\n");
            for (int i = 0; i < columns; i++){
                String [] newInt = new String[rows];
                for(int j = 0; j < newInt.length; j++){
                    newInt[j] = Integer.toString(data[i][j]);

                }
                bufferedWriter.write(String.join("; ", newInt) + "\n");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
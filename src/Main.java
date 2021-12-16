import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Random;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("src/lesson5.csv");
        int numOfLines = 0;


        try (Stream<String> lines = Files.lines(file.toPath(), Charset.defaultCharset())) {
            numOfLines = (int) lines.count()-1;



        } catch (Exception e){

        }

        String count;

        AppData appData = new AppData();
        appData.setColumns(numOfLines);

        //Проверка для записи
        String[] header = new String[1];
        header[0] = "Value1;Value2;Value3";
        int[][] data = new int[2][3];
        populateArray(data);
        appData.setHeader(header);
        appData.setData(data);
        appData.setRows(3);


        //Сохранение в файл
        appData.save();

        //Чтение из файла
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            System.out.println("Чтение из файла");
            String headers = bufferedReader.readLine();
            appData.parseHeader(headers);
            while ((count = bufferedReader.readLine()) != null) {
                appData.parseRow(count);
            }
        } catch (IOException e){
            e.printStackTrace();
        }




    }

    private static void populateArray(int[][] data)
    {
        System.out.println("Заполнение массива случайными числами");
        Random rnd = new Random();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = rnd.nextInt(10) + 1;
            }
        }
        for (int i = 0; i < data.length; i++, System.out.println()) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
        }
        System.out.println();
    }
}







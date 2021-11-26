package ru.com.lineWriter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class App {


public static boolean flag=true;
//public static boolean flagRight=false;
//public static boolean flagLeft=false;
//public static boolean flagUp=false;
//public static boolean flagDown=false;
    public static int count;
    public static  FileWriter fr = null;
    public static ArrayList<Integer> tupeList = new ArrayList<>();

    public static void main(String[] args) {
        int xy = 5;
        int outWhile =1;
          Validation valid=new Validation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите колличество фигур : ");
        count = valid.validCount();
        int[][] cord = new int[count][xy];

        //берём точку начала отсчёта
        System.out.print("Введите начальные координаты лазера"+'\n'+ "x : ");
        double startX = valid.validCord();
        System.out.print("y : ");
        double startY = valid.validCord();

        for (int i = 1; i <= count; i++) {

            System.out.println("Вправо : 1");
            System.out.println("Влево : 2");
            System.out.println("Вверх : 3");
            System.out.println("Вниз : 4");

            System.out.println("Введите направление движения лазера в фигуре номер " + i);
            tupeList.add(valid.validTupe());
            System.out.println("Введите координаты левого верхнего угла фигуры " + i);
            System.out.print("x = ");
            cord[i-1][0] =(int)  (valid.validCord()-startX);
            System.out.print("y = ");
            cord[i-1][1] =(int) (valid.validCord()-startY);
            System.out.println("Ведите координаты правого нижнего угла фигуры " + i);
            System.out.print("x = ");
            cord[i-1][2] =(int) (valid.validCordX(cord[i-1][0])-startX);
            System.out.print("y = ");
            cord[i-1][3] =(int) (valid.validCordY(cord[i-1][1])-startY);
            //Параметр созданный для повторяющихся направлений
            cord[i-1][4] =1;
        }






        //создание файла
        File workFile=createFile();
        System.out.println("файл создан в мэйне");

        while(outWhile>0){
            outWhile=0;
            System.out.println("зашли в вайл");
            for(int i = 1; i <= count; i++){
                System.out.println("Зашли в фор");
                switch (tupeList.get(i - 1)) {
                    case (1) -> {
                        cord[i - 1][0] = verticalRright(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                        if (cord[i - 1][0]-14 >cord[i - 1][2]){
                            cord[i - 1][4]=0;
                        }
                    }
                    case (2) ->{cord[i - 1][2] = verticalLeft(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                        if (cord[i - 1][2]+14<cord[i - 1][0]){
                            cord[i - 1][4]=0;
                        }
                    }
                    case (3) -> {cord[i - 1][3] = horizontalUp(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                    if (cord[i - 1][3]-14>cord[i - 1][1]){
                        cord[i - 1][4]=0;
                    }
                    }
                    case (4) -> {cord[i - 1][1] = horizontalDown(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                        if (cord[i - 1][1]+14<cord[i - 1][3]){
                            cord[i - 1][4]=0;
                        }
                    }
                }
                outWhile += cord[i - 1][4];
            }
            writeFile("\n");

            System.out.println("вышли из фора");
        }
        System.out.println("вышли из вайла");
        //Вывод списка направлений лазера









        //работа с файлом









            // Копирование данных из файла в буфер обмена
            copiFile();
            // удаление файла
            deliteFile(workFile);
        System.out.println("Всё готово, можете использовать данные");

      }


        public static int verticalRright(int x1, int y1, int x2, int y2) {
             if(x1-14>x2) {
               // flagRight = false;
                System.out.println("flag 1 упал");
             }
            else{
                String vivod ="Move "+ x1+","+ y1+'\n'+"Line "+x1+","+y2+'\n';
                writeFile(vivod);
                x1 = x1 + 15;
             }
            return x1;
        }

        public static int verticalLeft(int x1, int y1, int x2, int y2) {
        if(x2+14<x1){
           // flagLeft = false;
            System.out.println("flag 2 упал");
        }
        else{
            String vivod="Move "+ x2+","+ y1+'\n'+"Line "+x2+","+y2+'\n';
            writeFile(vivod);
            x2=x2-15;
        }
            return x2;
        }


        public static int horizontalUp(int x1, int y1, int x2, int y2) {
            if(y2-14>y1){
                System.out.println("flag 3 упал");
               // flagUp = false;
            }
            else{
                String vivod="Move "+ x1+","+ y2+'\n'+"Line "+x2+","+y2+'\n';
                writeFile(vivod);
                y2=y2+15;
            }
            return y2;
        }


        public static int horizontalDown(int x1, int y1, int x2, int y2) {
            if(y1+14<y2){
              //  flagDown = false;
                System.out.println("flag 4 упал");
            }
            else{
                String vivod="Move "+ x1+","+ y1+'\n'+"Line "+x2+","+y1+'\n';
                writeFile(vivod);
                y1=y1-15;
            }
            return y1;
        }
//-----------------------------------------------------------------------------------------------------------------
        public static File createFile(){
            File newFile = new File("MyFile.txt");
            try {
                boolean created = newFile.createNewFile();
                if (created)
                    System.out.println("File has been created");
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
            System.out.println("файл создан");
            return newFile;
        }

        public static void writeFile(String text){
            try {
                 fr = new FileWriter("MyFile.txt",true);
                //единократная запись заголовка
                if(flag){
                fr.write("""
                        Move 0,0
                        Layer 1
                        Layer 2
                        """);
                flag=false;
                    System.out.println("появилась запись");
                }
                //запись остального текста

                fr.write(text);
                System.out.println("появилась запись");
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void copiFile(){
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = Files.newBufferedReader(Paths.get("MyFile.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(sb);
            System.out.println("Запись скопирована");
            String myString = sb.toString();
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }

        public static void deliteFile(File fileName){
            System.out.println("Файл удалён");
              fileName.delete();
        }


    }



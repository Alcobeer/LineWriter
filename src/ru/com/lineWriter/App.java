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
public static boolean flagRight=false;
public static boolean flagLeft=false;
public static boolean flagUp=false;
public static boolean flagDown=false;
    public static int count;
    public static  FileWriter fr = null;
    public static ArrayList<Integer> tupeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int xy = 4;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите колличество фигур : ");
        count = scanner.nextInt();
        int[][] cord = new int[count][xy];

        //берём точку начала отсчёта
        System.out.print("Введите начальные координаты лазера"+'\n'+ "x : ");
        double startX = scanner.nextDouble();
        System.out.print("y : ");
        double startY = scanner.nextDouble();

        for (int i = 1; i <= count; i++) {

            System.out.println("Вправо : 1");
            System.out.println("Влево : 2");
            System.out.println("Вверх : 3");
            System.out.println("Вниз : 4");

            System.out.println("Введите направление движения лазера в фигуре номер " + i);
            tupeList.add(scanner.nextInt());
            System.out.println("Введите координаты левого верхнего угла фигуры " + i);
            System.out.print("x = ");
            cord[i-1][0] =(int)  (scanner.nextDouble()-startX);
            System.out.print("y = ");
            cord[i-1][1] =(int) (scanner.nextDouble()-startY);
            System.out.println("Ведите координаты правого нижнего угра фигуры " + i);
            System.out.print("x = ");
            cord[i-1][2] =(int) (scanner.nextDouble()-startX);
            System.out.print("y = ");
            cord[i-1][3] =(int) (scanner.nextDouble()-startY);
        }

        // обозначаем с какими фигурами продолжится работа
        for (Integer s : tupeList) {
            switch (s) {
                case (1) -> flagRight = true;
                case (2) -> flagLeft = true;
                case (3) -> flagUp = true;
                case (4) -> flagDown = true;
            }

        }



        //            int moveX= (int) (x1-startX);
//            int moveY= (int) (y1-startY);
//            int moveEndY= (int) (y2-startY);
//            int moveEndX= (int) (x2-startX);

        //создание файла
        File workFile=createFile();
        System.out.println("файл создан в мэйне");

        while(flagDown | flagLeft | flagUp |flagRight){
            System.out.println("зашли в вайл");
            for(int i = 1; i <= count; i++){
                System.out.println("Зашли в фор");
                switch (tupeList.get(i - 1)) {
                    case (1) -> cord[i - 1][0] = verticalRright(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                    case (2) -> cord[i - 1][2] = verticalLeft(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                    case (3) -> cord[i - 1][3] = horizontalUp(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                    case (4) -> cord[i - 1][1] = horizontalDown(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                }
            }
            System.out.println("вышли из фора");
        }
        System.out.println("вышли из вайла");
        //Вывод списка направлений лазера
//        System.out.println("Список направлений");


//        for (Integer s : tupeList) {
//            System.out.println(s);
//        }
//        System.out.println("Список координат");
//        for (int i = 0; i < count; i++) {
//            for (int j = 0; j < xy; j++) {
//                System.out.print(cord[i][j] + " ");
//            }
//        }




        //работа с файлом









            // Копирование данных из файла в буфер обмена
            copiFile();
            // удаление файла
            deliteFile(workFile);
        System.out.println("Всё готово, можете использовать данные");

      }


        public static int verticalRright(int x1, int y1, int x2, int y2) {
             if(x1-14>x2) {
                flagRight = false;
                System.out.println("flag 1 упал");
                 return x1;
            }
            else{
                String vivod ="Move "+ x1+","+ y1+'\n'+"Line "+x1+","+y2+'\n';
                writeFile(vivod);
                x1 = x1 + 15;
                return x1;}
        }

        public static int verticalLeft(int x1, int y1, int x2, int y2) {
        if(x2+14<x1){
            flagLeft = false;
            System.out.println("flag 2 упал");
            return x2;
        }
        else{
            String vivod="Move "+ x2+","+ y1+'\n'+"Line "+x2+","+y2+'\n';
            writeFile(vivod);
            x2=x2-15;
        return x2;
        }
        }


        public static int horizontalUp(int x1, int y1, int x2, int y2) {
            if(y2-14>y1){
                System.out.println("flag 3 упал");
                flagUp = false;
                return y2;
            }
            else{
                String vivod="Move "+ x1+","+ y2+'\n'+"Line "+x2+","+y2+'\n';
                writeFile(vivod);
                y2=y2+15;
                return y2;
            }
        }


        public static int horizontalDown(int x1, int y1, int x2, int y2) {
            if(y1+14<y2){
                flagDown = false;
                System.out.println("flag 4 упал");
                return y1;
            }
            else{
                String vivod="Move "+ x1+","+ y1+'\n'+"Line "+x2+","+y1+'\n';
                writeFile(vivod);
                y1=y1-15;
                return y1;
            }
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
//                int i=0;
//                fr.write(i);
//                i++;
                //              fr.write("привет");
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



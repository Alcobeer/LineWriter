package ru.com.lineWriter;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static ru.com.lineWriter.FileWork.*;


// конструктор класса приложения


public class App {
    //extends JFrame {
//    public App() {
//        super("My First Window"); // 2 конструктор базового класса
//        setBounds(100, 100, 300, 200); // 3 размер и положение окна
//    }

    public static int count;

    public static ArrayList<Integer> tupeList = new ArrayList<>();

    public static void main(String[] args) {
//        App app = new App();  // 1
//        app.setVisible(true); // 4 Приложение запущено!
        FileWork fileWork=new FileWork();
        Pyramid piramid= new Pyramid();
        Rectangle rect=new Rectangle();
        int xy = 9;
        int outWhile = 1;
        Validation valid = new Validation();

        System.out.println("Введите колличество фигур : ");
        count = valid.validCount();
        int[][] cord = new int[count][xy];

        //берём точку начала отсчёта
        System.out.print("Введите начальные координаты лазера" + '\n' + "x : ");
        double startX = valid.validCord();
        System.out.print("y : ");
        double startY = valid.validCord();

        for (int i = 1; i <= count; i++) {

            System.out.println("Вправо : 1");
            System.out.println("Влево : 2");
            System.out.println("Вверх : 3");
            System.out.println("Вниз : 4");
            System.out.println("Наклонная  влево: 5");
            System.out.println("Наклонная  вправо: 6");


            System.out.println("Введите направление движения лазера в фигуре номер " + i);
            tupeList.add(valid.validTupe());

            if (tupeList.get(i - 1) < 5)
                System.out.println("Введите координаты левого верхнего угла фигуры " + i);
            else System.out.println("Введите координаты  верхней точки  " + i);
            System.out.print("x = ");
            cord[i - 1][0] = (int) (valid.validCord() - startX);
            System.out.print("y = ");
            cord[i - 1][1] = (int) (valid.validCord() - startY);
            // добавление координат, если у нас пирамида

//            if (tupeList.get(i - 1) > 4) {
//                if (tupeList.get(i - 1) != 6)
//                    System.out.println("Введите координаты левого нижнего угла фигуры " + i);
//                else System.out.println("Введите координаты правого нижнего угла фигуры " + i);
//                System.out.print("y2 = ");
//                cord[i - 1][6] = (int) (valid.validCord() - startY);
//            }
            if (tupeList.get(i - 1) < 5)
                System.out.println("Ведите координаты X правого нижнего угла фигуры " + i);
            else System.out.println("Введите координаты нижней точки " + i);
            System.out.print("x = ");
            cord[i - 1][2] = (int) (valid.validCord() - startX);
            System.out.print("y = ");
            cord[i - 1][3] = (int) (valid.validCordY((int) (cord[i - 1][1] + startY)) - startY);

            if (tupeList.get(i - 1) > 4) {
                System.out.print("Введите длину сдвига вбок : ");
                int vbok = (int) valid.validCord() ;
                System.out.println("Введите длину сдвига вверх");
                int vverh= (int) valid.validCord();
                //x1

                cord[i - 1][7] = cord[i - 1][0]+ vbok;
                cord[i - 1][5] =  cord[i - 1][2]+vbok;
                cord[i - 1][0] = cord[i - 1][0]-vbok;
                 cord[i - 1][1] =cord[i - 1][1]+vverh;
                cord[i - 1][2] = cord[i - 1][2]-vbok;
                cord[i - 1][3] =cord[i - 1][3]-vverh;

                cord[i - 1][6] = cord[i - 1][3];

                cord[i - 1][8] = cord[i - 1][1];


            }


            //Параметр созданный для повторяющихся направлений
            cord[i - 1][4] = 1;
        }


        //создание файла
        File workFile =fileWork.createFile();

//цикл, проходящий по параметрам и создающий запись в файле
        while (outWhile > 0) {
            outWhile = 0;
            for (int i = 1; i <= count; i++) {
                switch (tupeList.get(i - 1)) {
                    case (1): {
                        cord[i - 1][0] = rect.verticalRright(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                        //параметр для повторяющихся направлений
                        if (cord[i - 1][0] - 14 > cord[i - 1][2]) {
                            cord[i - 1][4] = 0;
                        }
                        break;
                    }

                    case (2): {
                        cord[i - 1][2] =rect.verticalLeft(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                        //параметр для повторяющихся направлений
                        if (cord[i - 1][2] + 14 < cord[i - 1][0]) {
                            cord[i - 1][4] = 0;
                        }
                        break;
                    }
                    case (3): {
                        cord[i - 1][3] =rect.horizontalUp(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                        //параметр для повторяющихся направлений
                        if (cord[i - 1][3] - 14 > cord[i - 1][1]) {
                            cord[i - 1][4] = 0;
                        }
                        break;
                    }
                    case (4): {
                        cord[i - 1][1] =rect.horizontalDown(cord[i - 1][0], cord[i - 1][1], cord[i - 1][2], cord[i - 1][3]);
                        //параметр для повторяющихся направлений
                        if (cord[i - 1][1] + 14 < cord[i - 1][3]) {
                            cord[i - 1][4] = 0;
                        }
                        break;
                    }
                    case (5): {
                        cord[i - 1][7] =piramid.cornerLeft(cord[i - 1][7],
                                cord[i - 1][8],
                                (cord[i - 1][2] + (cord[i - 1][7] - cord[i - 1][0])),
                                cord[i - 1][6], cord[i - 1][0]);

                        //параметр для повторяющихся направлений
                        if (cord[i - 1][7] + 14 < cord[i - 1][0]) {
                            cord[i - 1][4] = 0;
                        }
                        break;
                    }
                    case (6): {
                        cord[i - 1][0] =piramid.cornerRight(cord[i - 1][0],
                                cord[i - 1][1],
                                (cord[i - 1][5] - (cord[i - 1][7] - cord[i - 1][0])),
                                cord[i - 1][3],
                                cord[i - 1][7]);

                        //параметр для повторяющихся направлений
                        if (cord[i - 1][0] - 14 > cord[i - 1][7]) {
                            cord[i - 1][4] = 0;
                        }
                        break;
                    }

//                    case (5) -> {
//                        cord[i - 1][1] = corner(cord[i - 1][0], cord[i - 1][1],
//                                (cord[i - 1][2]+(cord[i-1][1]-cord[i-1][6])),
//                                cord[i - 1][3],cord[i-1][6]);
//
//                        //параметр для повторяющихся направлений
//                        if (cord[i - 1][1]+14<cord[i - 1][6]){
//                            cord[i - 1][4]=0;
//                        }
//                    }
//                    case (6) -> {
//                        cord[i - 1][1] = corner(cord[i - 1][0], cord[i - 1][1],
//                                (cord[i - 1][2]-(cord[i-1][1]-cord[i-1][6])),
//                                cord[i - 1][3],cord[i-1][6]);
//
//                        //параметр для повторяющихся направлений
//                        if (cord[i - 1][1]+14<cord[i - 1][6]){
//                            cord[i - 1][4]=0;
//                        }
//                    }
                }
                outWhile += cord[i - 1][4];
            }
            //обеспечиваем читаемость с помощью отступа
            fileWork.writeFile("\n");
        }
        // Копирование данных из файла в буфер обмена
        fileWork.copiFile();
        // удаление файла
        fileWork.deliteFile(workFile);
        System.out.print("Всё готово, можете использовать данные");

    }
}



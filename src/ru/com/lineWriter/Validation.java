package ru.com.lineWriter;

import java.util.Scanner;

public class Validation {
    Scanner scanner = new Scanner(System.in);
    public int validCount(){

byte i=0;
        int count;
        do {
if(i>0)
    System.out.println("Вы не правильно ввели колличество фигур\n" +
                    "Вводить можно только числа от 1 до 20\n" +
                    "Повторите попытку");
            while (!scanner.hasNextInt()) {
                System.out.println("Вы  ввели символ\n" +
                                "Вводить можно только числа от 1 до 20\n" +
                                "Повторите попытку");
                scanner.next(); // this is important!
            }
            count = scanner.nextInt();
            i++;
        } while (count <= 0 || count>=20 );

        return count;
    }
    public double validCord(){
        double cord;
        while (!scanner.hasNextDouble()) {
            System.out.println("Вы  ввели символ\n" +
                            "Вводить можно только числа \n" +
                            "Возможно вы поставили '.' вместо ','\n" +
                            "Повторите попытку");
            scanner.next(); // this is important!
        }
        cord=scanner.nextDouble();
        return cord;
    }
    public double validCordX(int x){
        double cord;
        byte i=0;
        do {
            if(i>0)
                System.out.println("Вы не правильно ввели координату X\n" +
                                "Она должна быть больше чем X1\n" +
                                "Повторите попытку");
            while (!scanner.hasNextDouble()) {
                System.out.println("Вы  ввели символ\n" +
                                "Вводить можно только числа больше чем X1\n" +
                        "Возможно вы поставили '.' вместо ','\n" +
                                "Повторите попытку");
                scanner.next(); // this is important!
            }
            cord=scanner.nextDouble();
            i++;
        } while (cord <= x);


        return cord;
    }
    public double validCordY(int y){
        double cord;
        byte i=0;
        do {
            if(i>0)
                System.out.println("Вы не правильно ввели координату Y\n" +
                                "Она должна быть меньше чем Y1\n" +
                                "Повторите попытку"
);
            while (!scanner.hasNextDouble()) {
                System.out.println("Вы  ввели символ\n" +
                                "Вводить можно только числа меньше чем Y1\n" +
                        "Возможно вы поставили '.' вместо ','\n" +
                                "Повторите попытку");
                scanner.next(); // this is important!
            }
            cord=scanner.nextDouble();
            i++;
        } while (cord >= y);

        return cord;
    }
    public int validTupe(){
        int tupe;
        byte i=0;
        do {
            if(i>0)
                System.out.println("Вы не правильно ввели направление\n" +
                                "Вводить можно только цыфры от 1 до 4\n" +
                                "Повторите попытку");
            while (!scanner.hasNextInt()) {
                System.out.println("Вы  ввели символ\n" +
                                "Вводить можно только цыфры от 1 до 4\n" +
                                "Повторите попытку");
                scanner.next(); // this is important!
            }
            tupe=scanner.nextInt();
            i++;
        } while (tupe <= 0 || tupe>=5);

        return tupe;
    }

}

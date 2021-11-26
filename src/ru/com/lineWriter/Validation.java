package ru.com.lineWriter;

import java.util.Scanner;

public class Validation {
    Scanner scanner = new Scanner(System.in);
    public int validCount(){

byte i=0;
        int count;
        do {
if(i>0)
    System.out.println("""
            Вы не правильно ввели колличество фигур
            Вводить можно только числа от 1 до 20
            Повторите попытку\s""");
            while (!scanner.hasNextInt()) {
                System.out.println("""
                        Вы  ввели символ
                        Вводить можно только числа от 1 до 20
                        Повторите попытку\s""");
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
            System.out.println("""
                        Вы  ввели символ
                        Вводить можно только числа\040
                        Возможно вы поставили '.' вместо ','
                        Повторите попытку\s""");
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
                System.out.println("""
            Вы не правильно ввели координату X
            Она должна быть больше чем X1
            Повторите попытку\s""");
            while (!scanner.hasNextDouble()) {
                System.out.println("""
                        Вы  ввели символ
                        Вводить можно только числа больше чем X1
                        Повторите попытку\s""");
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
                System.out.println("""
            Вы не правильно ввели координату Y
            Она должна быть меньше чем Y1
            Повторите попытку\s""");
            while (!scanner.hasNextDouble()) {
                System.out.println("""
                        Вы  ввели символ
                        Вводить можно только числа меньше чем Y1
                        Повторите попытку\s""");
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
                System.out.println("""
            Вы не правильно ввели направление
            Вводить можно только цыфры от 1 до 4
            Повторите попытку\s""");
            while (!scanner.hasNextInt()) {
                System.out.println("""
                        Вы  ввели символ
                        Вводить можно только цыфры от 1 до 4
                        Повторите попытку\s""");
                scanner.next(); // this is important!
            }
            tupe=scanner.nextInt();
            i++;
        } while (tupe <= 0 || tupe>=5);

        return tupe;
    }
}

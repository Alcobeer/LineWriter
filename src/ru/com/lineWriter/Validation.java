package ru.com.lineWriter;

import java.util.Scanner;

public class Validation {

    public int validCount(){
        Scanner scanner = new Scanner(System.in);
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
        scanner.close();
        return count;
    }
    public double validCord(){
        Scanner scanner = new Scanner(System.in);
        double cord;
        while (!scanner.hasNextDouble()) {
            System.out.println("""
                        Вы  ввели символ
                        Вводить можно только числа 
                        Возможно вы поставили '.' вместо ','
                        Повторите попытку\s""");
            scanner.next(); // this is important!
        }
        cord=scanner.nextDouble();
        scanner.close();
        return cord;
    }

}

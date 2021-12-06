package ru.com.lineWriter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWork {
    public  FileWriter fr = null;
    public boolean flag =true;
    //создание файла
    public  File createFile() {
        //проверка, если что-то не работает
//            try {
//                boolean created = newFile.createNewFile();
//                if (created)
//                    System.out.println("Файл создан");
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
        return new File("MyFile.txt");
    }

    //запись в файл
    public void writeFile(String text) {
        try {
            fr = new FileWriter("MyFile.txt", true);
            //единократная запись заголовка
            if (flag) {
                fr.write("Move 0,0" + "\n" + "Layer 1\nLayer 2\n\n");

                flag = false;
            }
            //запись остального текста
            fr.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Запись в буфер обмена
    public  void copiFile() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("MyFile.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(sb); - для вывода в консоль скопированных данных
        String myString = sb.toString();
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public  void deliteFile(File fileName) {
        fileName.delete();
    }

}

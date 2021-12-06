package ru.com.lineWriter;

public class Pyramid extends Figure{
    FileWork fileWork=new FileWork();
    public  int cornerLeft(int x1, int y1, int x2, int y2, int x3) {
        if (x1 + 14 >= x3) {
            String vivod = "Move " + x1 + "," + y1 + '\n' + "Line " + x2 + "," + y2 + '\n';
           fileWork.writeFile(vivod);
            x1 = x1 - 15;
        }
        return x1;
    }

    public  int cornerRight(int x1, int y1, int x2, int y2, int x3) {
        if (x1 - 14 <= x3) {
            String vivod = "Move " + x1 + "," + y1 + '\n' + "Line " + x2 + "," + y2 + '\n';
            fileWork.writeFile(vivod);
            x1 = x1 + 15;
        }
        return x1;
    }
}

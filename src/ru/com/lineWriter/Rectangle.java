package ru.com.lineWriter;

public class Rectangle extends Figure{
FileWork fileWork=new FileWork();

    //Срез лазера вправо
    public  int verticalRright(int x1, int y1, int x2, int y2) {
        //не исправлять , иначе всё ломается
        if (x1 - 14 <= x2) {
            String vivod = "Move " + x1 + "," + y1 + '\n' + "Line " + x1 + "," + y2 + '\n';
            fileWork.writeFile(vivod);
            x1 = x1 + 15;
        }
        return x1;
    }

    //Срез лазера влево
    public  int verticalLeft(int x1, int y1, int x2, int y2) {
        if (x2 + 14 >= x1) {
            String vivod = "Move " + x2 + "," + y1 + '\n' + "Line " + x2 + "," + y2 + '\n';
            fileWork.writeFile(vivod);
            x2 = x2 - 15;
        }
        return x2;
    }


    ////Срез лазера вверх
    public  int horizontalUp(int x1, int y1, int x2, int y2) {
        if (y2 - 14 <= y1) {
            String vivod = "Move " + x1 + "," + y2 + '\n' + "Line " + x2 + "," + y2 + '\n';
            fileWork.writeFile(vivod);
            y2 = y2 + 15;
        }
        return y2;
    }


    //Срез лазера вниз
    public  int horizontalDown(int x1, int y1, int x2, int y2) {
        if (y1 + 14 >= y2) {
            String vivod = "Move " + x1 + "," + y1 + '\n' + "Line " + x2 + "," + y1 + '\n';
            fileWork.writeFile(vivod);
            y1 = y1 - 15;
        }
        return y1;
    }
}

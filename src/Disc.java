import java.awt.*;
import javax.swing.*;

public class Disc extends JButton {
    int number; // 标记数目和大小
    TowerPoint point;

    public Disc() {
        this.setBackground(Color.CYAN);
        number=-1;
    }

    public void setNumber(int n) {
        number = n;
    }

    public int getNumber() {
        return number;
    }

    public void setPoint(TowerPoint p) {
        point = p;
    }

    public TowerPoint getPoint() {
        return point;
    }
}

import java.awt.*;

public class TowerPoint {
    int x, y;
    boolean existDisc;
    Disc disc = null;

    public TowerPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isExistDisc() {
        return existDisc;
    }

    public void setExistDisc(boolean boo) {
        existDisc = boo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(TowerPoint p) {
        if (p.getX() == this.getX() && p.getY() == this.getY())
            return true;
        else
            return false;
    }

    public void putDisc(Component com, Container con) {
        disc = (Disc) com;
        con.setLayout(null);
        con.add(disc);
        int w = disc.getBounds().width;
        int h = disc.getBounds().height;
        disc.setBounds(x - w / 2, y - h / 2, w, h);
        existDisc = true;
        disc.setPoint(this);
        con.validate();
    }

    public Disc getDisc() {
        return disc;
    }

    public void removeDisc(Component com, Container con) {
        if (com != null)
            con.remove(com);
        con.validate();
    }

    public static boolean serach(TowerPoint[] array, TowerPoint point) {
        for (int i = 0; i < array.length; i++)
            if (array[i].equals(point) == true)
                return true;
        return false;
    }
}

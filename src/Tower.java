import javax.swing.*;

import java.awt.*;

public class Tower extends JPanel {
    static char[] name = { 'A', 'B', 'C' };
    int amountOfDisc = 3;
    Disc[] disc;
    int maxWidth, minWidth, height;
    TowerPoint[] pointA, pointB, pointC;
    HandleMouse handleMouse;
    AutoMove autoMove;

    Tower() {
        handleMouse = new HandleMouse(this);
        autoMove = new AutoMove(this);
        setLayout(null);
        setBackground(new Color(200, 226, 226));
    }

    public void setAmountOfDisc(int number) {
        if (number <= 1)
            amountOfDisc = 1;
        else
            amountOfDisc = number;
    }

    public void setMaxWidth(int m) {
        maxWidth = m;
    }

    public void setMinWidth(int m) {
        minWidth = m;
    }

    public void setHeight(int h) {
        height = h;
    }

    public void setAutoMove(AutoMove autoMove) {
        this.autoMove = autoMove;
    }

    public void putDiscOnTower() {
        removeDisk();
        int n = (maxWidth - minWidth) / amountOfDisc;
        disc = new Disc[amountOfDisc];
        for (int i = 0; i < disc.length; i++) {
            disc[i] = new Disc();
            disc[i].setNumber(i);
            disc[i].setText(String.valueOf(disc[i].getNumber() + 1));
            int diskwidth = minWidth + i * n;
            disc[i].setSize(diskwidth, height);
            disc[i].addMouseListener(handleMouse);
            disc[i].addMouseMotionListener(handleMouse);
        }
        pointA = new TowerPoint[amountOfDisc];
        pointB = new TowerPoint[amountOfDisc];
        pointC = new TowerPoint[amountOfDisc];
        int vertialDistance = height;
        for (int i = 0; i < pointA.length; i++) {
            pointA[i] = new TowerPoint(maxWidth, 100 + vertialDistance);
            vertialDistance = vertialDistance + height;
        }
        vertialDistance = height;
        for (int i = 0; i < pointB.length; i++) {
            pointB[i] = new TowerPoint(2 * maxWidth, 100 + vertialDistance);
            vertialDistance = vertialDistance + height;
        }
        vertialDistance = height;
        for (int i = 0; i < pointC.length; i++) {
            pointC[i] = new TowerPoint(3 * maxWidth, 100 + vertialDistance);
            vertialDistance = vertialDistance + height;
        }
        for (int i = 0; i < pointA.length; i++) {
            pointA[i].putDisc(disc[i], this);
        }
        handleMouse.setPointA(pointA);
        handleMouse.setPointB(pointB);
        handleMouse.setPointC(pointC);
        autoMove = new AutoMove(this);
        autoMove.setAmountOfDisc(amountOfDisc);
        autoMove.setPointA(pointA);
        autoMove.setPointB(pointB);
        autoMove.setPointC(pointC);
        validate();
        repaint();
    }

    public void removeDisk() {
        if (pointA != null) {
            for (int i = 0; i < pointA.length; i++) {
                pointA[i].removeDisc(pointA[i].getDisc(), this);
                pointB[i].removeDisc(pointB[i].getDisc(), this);
                pointC[i].removeDisc(pointC[i].getDisc(), this);
            }
        }
    }

    public AutoMove getAutoMoveDisc() {
        return autoMove;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x1, y1, x2, y2;
        x1 = pointA[0].getX();
        y1 = pointA[0].getY() - height / 2;
        x2 = pointA[amountOfDisc - 1].getX();
        y2 = pointA[amountOfDisc - 1].getY() + height / 2;
        g.drawLine(x1, y1, x2, y2);
        x1 = pointB[0].getX();
        y1 = pointB[0].getY() - height / 2;
        x2 = pointB[amountOfDisc - 1].getX();
        y2 = pointB[amountOfDisc - 1].getY() + height / 2;
        g.drawLine(x1, y1, x2, y2);
        x1 = pointC[0].getX();
        y1 = pointC[0].getY() - height / 2;
        x2 = pointC[amountOfDisc - 1].getX();
        y2 = pointC[amountOfDisc - 1].getY() + height / 2;
        g.drawLine(x1, y1, x2, y2);
        g.setColor(Color.blue);
        x1 = pointA[amountOfDisc - 1].getX() - maxWidth / 2;
        y1 = pointA[amountOfDisc - 1].getY() + height / 2;
        x2 = pointC[amountOfDisc - 1].getX() + maxWidth / 2;
        y2 = pointC[amountOfDisc - 1].getY() + height / 2;
        int length = x2 - x1, height = 6;
        g.fillRect(x1, y1, length, height);
        int size = 5;
        for (int i = 0; i < pointA.length; i++) {
            g.fillOval(pointA[i].getX() - size / 2, pointA[i].getY() - size / 2, size, size);
            g.fillOval(pointB[i].getX() - size / 2, pointB[i].getY() - size / 2, size, size);
            g.fillOval(pointC[i].getX() - size / 2, pointC[i].getY() - size / 2, size, size);
        }
        g.drawString(name[0] + " ", pointA[amountOfDisc - 1].getX(), pointA[amountOfDisc - 1].getY() + 50);
        g.drawString(name[1] + " ", pointB[amountOfDisc - 1].getX(), pointB[amountOfDisc - 1].getY() + 50);
        g.drawString(name[2] + " ", pointC[amountOfDisc - 1].getX(), pointC[amountOfDisc - 1].getY() + 50);
    }

    public static int getTowerUp(TowerPoint[] towerPoint) {
        for (int i = 0; i < towerPoint.length; i++)
            if (towerPoint[i].existDisc == true)
                return i;
        return -1;
    }
}
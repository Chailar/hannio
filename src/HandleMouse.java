import java.awt.event.*;
import java.util.Stack;
import java.awt.*;

public class HandleMouse implements MouseListener, MouseMotionListener {
    TowerPoint[] pointA, pointB, pointC;
    TowerPoint startPoint = null, endPoint = null;
    int leftX, leftY, x0, y0;
    boolean move = false, countTime = false;
    Container con;
    int times;
    Control control;
    Stack<processRecord> stack;

    HandleMouse(Container con) {
        this.con = con;
        this.times=0;
        this.stack=new Stack<processRecord>();
    }

    public void setPointA(TowerPoint[] pointA) {
        this.pointA = pointA;
    }

    public void setPointB(TowerPoint[] pointB) {
        this.pointB = pointB;
    }

    public void setPointC(TowerPoint[] pointC) {
        this.pointC = pointC;
    }

    public void mousePressed(MouseEvent e) {
        move = false;
        Disc disc = null;
        disc = (Disc) e.getSource();
        startPoint = disc.getPoint();
        x0 = e.getX();
        y0 = e.getY();
        int m = 0;
        for (int i = 0; i < pointA.length; i++) {
            if (pointA[i].equals(startPoint)) {
                m = i;
                if (m > 0 && (pointA[m - 1].isExistDisc() == false)) {
                    move = true;
                    break;
                } else if (m == 0) {
                    move = true;
                    break;
                }
            }
        }

        for (int i = 0; i < pointB.length; i++) {
            if (pointB[i].equals(startPoint)) {
                m = i;
                if (m > 0 && (pointB[m - 1].isExistDisc() == false)) {
                    move = true;
                    break;
                } else if (m == 0) {
                    move = true;
                    break;
                }
            }
        }
        for (int i = 0; i < pointC.length; i++) {
            if (pointC[i].equals(startPoint)) {
                m = i;
                if (m > 0 && (pointC[m - 1].isExistDisc() == false)) {
                    move = true;
                    break;
                } else if (m == 0) {
                    move = true;
                    break;
                }
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void setControl(Control c){
        control=c;
    }

    public void mouseDragged(MouseEvent e) {
        Disc disc = null;
        disc = (Disc) e.getSource();
        leftX = disc.getBounds().x;
        leftY = disc.getBounds().y;
        int x = e.getX();
        int y = e.getY();
        leftX = leftX + x;
        leftY = leftY + y;
        if (move == true)
            disc.setLocation(leftX - x0, leftY - y0);
    }

    public void mouseReleased(MouseEvent e) {
        Disc disc = null;
        disc = (Disc) e.getSource();
        Rectangle rect = disc.getBounds();
        boolean location = false;
        int x = -1, y = -1;
        for (int i = 0; i < pointA.length; i++) {
            x = pointA[i].getX();
            y = pointA[i].getY();
            if (rect.contains(x, y)) {
                endPoint = pointA[i];
                if (i == pointA.length - 1 && endPoint.isExistDisc() == false) {
                    location = true;
                    break;
                } else if (i < pointA.length - 1 && pointA[i + 1].isExistDisc() == true && endPoint.isExistDisc() == false
                        && pointA[i + 1].getDisc().getNumber() > disc.getNumber()) {
                    location = true;
                    break;
                }
            }
        }
        for (int i = 0; i < pointB.length; i++) {
            x = pointB[i].getX();
            y = pointB[i].getY();
            if (rect.contains(x, y)) {
                endPoint = pointB[i];
                if (i == pointB.length - 1 && endPoint.isExistDisc() == false) {
                    location = true;
                    break;
                } else if (i < pointB.length - 1 && pointB[i + 1].isExistDisc() == true && endPoint.isExistDisc() == false
                        && pointB[i + 1].getDisc().getNumber() > disc.getNumber()) {
                    location = true;
                    break;
                }
            }
        }
        for (int i = 0; i < pointC.length; i++) {
            x = pointC[i].getX();
            y = pointC[i].getY();
            if (rect.contains(x, y)) {
                endPoint = pointC[i];
                if (i == pointC.length - 1 && endPoint.isExistDisc() == false) {
                    location = true;
                    break;
                } else if (i < pointC.length - 1 && pointC[i + 1].isExistDisc() == true && endPoint.isExistDisc() == false
                        && pointC[i + 1].getDisc().getNumber() > disc.getNumber()) {
                    location = true;
                    break;
                }
            }
        }
        if (endPoint != null && location == true) {
            endPoint.putDisc(disc, con);
            startPoint.setExistDisc(false);
            stack.add(new processRecord(startPoint, endPoint));
            times++;
            this.control.text2.setText(String.valueOf(times));
            control.button[2].setEnabled(true);
        } else
            startPoint.putDisc(disc, con);
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }
}
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class AutoMove extends JDialog implements ActionListener {
    int amountOfDisc = 3;
    TowerPoint[] pointA, pointB, pointC;
    Container con;
    StringBuffer moveStep;
    JTextArea showStep;
    JButton Start, Stop, Continue;
    Timer time;
    int i = 0, number = 0, j = 0;
    StringBuffer discNumber;

    public AutoMove(Container con) {
        setModal(true);
        setTitle("自动演示搬盘子过程");
        this.con = con;
        moveStep = new StringBuffer();
        discNumber = new StringBuffer();
        time = new Timer(1000, this);
        time.setInitialDelay(10);
        showStep = new JTextArea(10, 12);
        showStep.setEditable(false);
        Start = new JButton("演示");
        Stop = new JButton("暂停");
        Continue = new JButton("继续");
        Start.addActionListener(this);
        Stop.addActionListener(this);
        Continue.addActionListener(this);
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.add(Start);
        south.add(Stop);
        south.add(Continue);
        add(new JScrollPane(showStep), BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                time.stop();
                setVisible(false);
            }
        });
    }

    public void setAmountOfDisc(int n) {
        amountOfDisc = n;
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == time) {
            number++;
            char cStart, cEnd, cDiscNumber;
            if (i <= moveStep.length() - 2) {
            cStart = moveStep.charAt(i);
            cEnd = moveStep.charAt(i + 1);
            autoMoveDisc(cStart, cEnd);
            cDiscNumber = discNumber.charAt(j++);
            showStep.append("(" + number + ")圆盘" + cDiscNumber + "从" + cStart + "座搬到" +
            cEnd + "座\n");
            }
            i = i + 2;
            if (i >= moveStep.length() - 1)
            time.stop();
            // if (amountOfDisc % 2 != 0) {   //注释部分为非递归算法
            //     int i;
            //     int j;
            //     while (Tower.getTowerUp(pointC) != 0) {
            //         autoMoveDisc(Tower.name[0], Tower.name[2]);// A到C
            //         number++;
            //         showStep.append("(" + number + ")圆盘1" + "从A" + "座搬到" + "C座\n");
            //         if (Tower.getTowerUp(pointC) == 0)
            //             break;
            //         i = 66666666;
            //         if (Tower.getTowerUp(pointA) != -1)
            //             i = pointA[Tower.getTowerUp(pointA)].getDisc().getNumber() + 1;
            //         j = 66666666;
            //         if (Tower.getTowerUp(pointB) != -1)
            //             j = pointB[Tower.getTowerUp(pointB)].getDisc().getNumber() + 1;
            //         if (i < j) {
            //             autoMoveDisc(Tower.name[0], Tower.name[1]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + i + "从" + Tower.name[0] + "座搬到" + Tower.name[1] + "座\n");
            //         } else {
            //             autoMoveDisc(Tower.name[1], Tower.name[0]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + j + "从" + Tower.name[1] + "座搬到" + Tower.name[0] + "座\n");
            //         }
            //         autoMoveDisc(Tower.name[2], Tower.name[1]);// C到B
            //         number++;
            //         showStep.append("(" + number + ")圆盘1" + "从C" + "座搬到" + "B座\n");
            //         i = 66666666;
            //         if (Tower.getTowerUp(pointA) != -1)
            //             i = pointA[Tower.getTowerUp(pointA)].getDisc().getNumber() + 1;
            //         j = 66666666;
            //         if (Tower.getTowerUp(pointC) != -1)
            //             j = pointC[Tower.getTowerUp(pointC)].getDisc().getNumber() + 1;
            //         if (i < j) {
            //             autoMoveDisc(Tower.name[0], Tower.name[2]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + i + "从" + Tower.name[0] + "座搬到" + Tower.name[2] + "座\n");
            //         } else {
            //             autoMoveDisc(Tower.name[2], Tower.name[0]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + j + "从" + Tower.name[2] + "座搬到" + Tower.name[0] + "座\n");
            //         }
            //         autoMoveDisc(Tower.name[1], Tower.name[0]);// B到A
            //         number++;
            //         showStep.append("(" + number + ")圆盘1" + "从B" + "座搬到" + "A座\n");
            //         i = 66666666;
            //         if (Tower.getTowerUp(pointB) != -1)
            //             i = pointB[Tower.getTowerUp(pointB)].getDisc().getNumber() + 1;
            //         j = 66666666;
            //         if (Tower.getTowerUp(pointC) != -1)
            //             j = pointC[Tower.getTowerUp(pointC)].getDisc().getNumber() + 1;
            //         if (i < j) {
            //             autoMoveDisc(Tower.name[1], Tower.name[2]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + i + "从" + Tower.name[1] + "座搬到" + Tower.name[2] + "座\n");
            //         } else {
            //             autoMoveDisc(Tower.name[2], Tower.name[1]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + j + "从" + Tower.name[2] + "座搬到" + Tower.name[1] + "座\n");
            //         }
            //     }
            //     time.stop();
            // } else {
            //     int i;
            //     int j;
            //     while (Tower.getTowerUp(pointC) != 0) {
            //         autoMoveDisc(Tower.name[0], Tower.name[1]);// A到B
            //         number++;
            //         showStep.append("(" + number + ")圆盘1" + "从A" + "座搬到" + "B座\n");
            //         if (Tower.getTowerUp(pointC) == 0)
            //             break;
            //         i = 66666666;
            //         if (Tower.getTowerUp(pointA) != -1)
            //             i = pointA[Tower.getTowerUp(pointA)].getDisc().getNumber() + 1;
            //         j = 66666666;
            //         if (Tower.getTowerUp(pointC) != -1)
            //             j = pointC[Tower.getTowerUp(pointC)].getDisc().getNumber() + 1;
            //         if (i < j) {
            //             autoMoveDisc(Tower.name[0], Tower.name[2]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + i + "从" + Tower.name[0] + "座搬到" + Tower.name[2] + "座\n");
            //         } else {
            //             autoMoveDisc(Tower.name[2], Tower.name[0]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + j + "从" + Tower.name[2] + "座搬到" + Tower.name[0] + "座\n");
            //         }
            //         autoMoveDisc(Tower.name[1], Tower.name[2]);// B到C
            //         number++;
            //         showStep.append("(" + number + ")圆盘1" + "从B" + "座搬到" + "C座\n");
            //         i = 66666666;
            //         if (Tower.getTowerUp(pointA) != -1)
            //             i = pointA[Tower.getTowerUp(pointA)].getDisc().getNumber() + 1;
            //         j = 66666666;
            //         if (Tower.getTowerUp(pointB) != -1)
            //             j = pointB[Tower.getTowerUp(pointB)].getDisc().getNumber() + 1;
            //         if (i < j) {
            //             autoMoveDisc(Tower.name[0], Tower.name[1]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + i + "从" + Tower.name[0] + "座搬到" + Tower.name[1] + "座\n");
            //         } else {
            //             autoMoveDisc(Tower.name[1], Tower.name[0]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + j + "从" + Tower.name[1] + "座搬到" + Tower.name[0] + "座\n");
            //         }
            //         autoMoveDisc(Tower.name[1], Tower.name[0]);// C到A
            //         number++;
            //         showStep.append("(" + number + ")圆盘1" + "从C" + "座搬到" + "A座\n");
            //         i = 66666666;
            //         if (Tower.getTowerUp(pointB) != -1)
            //             i = pointB[Tower.getTowerUp(pointB)].getDisc().getNumber() + 1;
            //         j = 66666666;
            //         if (Tower.getTowerUp(pointC) != -1)
            //             j = pointC[Tower.getTowerUp(pointC)].getDisc().getNumber() + 1;
            //         if (i < j) {
            //             autoMoveDisc(Tower.name[1], Tower.name[2]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + i + "从" + Tower.name[1] + "座搬到" + Tower.name[2] + "座\n");
            //         } else {
            //             autoMoveDisc(Tower.name[2], Tower.name[1]);
            //             number++;
            //             showStep.append("(" + number + ")圆盘" + j + "从" + Tower.name[2] + "座搬到" + Tower.name[1] + "座\n");
            //         }
            //     }
            // }
            // time.stop();

        } else if (e.getSource() == Start)

        {
            if (moveStep.length() == 0) {
                if (time.isRunning() == false) {
                    i = 0;
                    moveStep = new StringBuffer();
                    MoveDisc(amountOfDisc, Tower.name[0], Tower.name[1], Tower.name[2]);
                    // System.out.println(moveStep);
                    number = 0;
                    j = 0;
                    time.start();
                }
            }
        } else if (e.getSource() == Stop) {
            if (time.isRunning() == true)
                time.stop();
        }

    }

    public void MoveDisc(int n, char A, char B, char C) {
        if (n == 1) {
            moveStep.append(A);
            moveStep.append(C);
        } else {
            MoveDisc(n - 1, A, C, B);
            moveStep.append(A);
            moveStep.append(C);
            MoveDisc(n - 1, B, A, C);
        }
    }

    public void autoMoveDisc(char cStart, char cEnd) {
        Disc disc = null;
        if (cStart == Tower.name[0]) {
            for (int i = 0; i < pointA.length; i++) {
                if (pointA[i].isExistDisc() == true) {
                    disc = pointA[i].getDisc();
                    discNumber.append(String.valueOf(disc.number + 1));
                    pointA[i].setExistDisc(false);
                    break;
                }
            }
        }
        if (cStart == Tower.name[1]) {
            for (int i = 0; i < pointB.length; i++) {
                if (pointB[i].isExistDisc() == true) {
                    disc = pointB[i].getDisc();
                    discNumber.append(String.valueOf(disc.number + 1));
                    pointB[i].setExistDisc(false);
                    break;
                }
            }
        }
        if (cStart == Tower.name[2]) {
            for (int i = 0; i < pointC.length; i++) {
                if (pointC[i].isExistDisc() == true) {
                    disc = pointC[i].getDisc();
                    discNumber.append(String.valueOf(disc.number + 1));
                    pointC[i].setExistDisc(false);
                    break;
                }
            }
        }
        TowerPoint endPoint = null;
        int i = 0;
        if (cEnd == Tower.name[0]) {
            for (i = 0; i < pointA.length; i++) {
                if (pointA[i].isExistDisc() == true) {
                    if (i > 0) {
                        endPoint = pointA[i - 1];
                        break;
                    } else if (i == 0)
                        break;
                }
            }
            if (i == pointA.length)
                endPoint = pointA[pointA.length - 1];
        }
        if (cEnd == Tower.name[1]) {
            for (i = 0; i < pointB.length; i++) {
                if (pointB[i].isExistDisc() == true) {
                    if (i > 0) {
                        endPoint = pointB[i - 1];
                        break;
                    } else if (i == 0)
                        break;
                }
            }
            if (i == pointB.length)
                endPoint = pointB[pointB.length - 1];
        }
        if (cEnd == Tower.name[2]) {
            for (i = 0; i < pointC.length; i++) {
                if (pointC[i].isExistDisc() == true) {
                    if (i > 0) {
                        endPoint = pointC[i - 1];
                        break;
                    } else if (i == 0)
                        break;
                }
            }
            if (i == pointC.length)
                endPoint = pointC[pointC.length - 1];
        }
        if (endPoint != null && disc != null) {
            endPoint.putDisc(disc, con);
            endPoint.setExistDisc(true);
        }
    }
}

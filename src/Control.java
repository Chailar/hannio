import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Control extends JPanel implements ActionListener, ChangeListener {
    static String[] strings1 = { "游戏规则", "重新开始", "返回操作", "自动操作" };
    static String[] strings2 = { "选择层数", "最少移动次数", "你移动的次数" };
    JPanel panel1, panel2;
    JSpinner number;
    JTextField text1, text2;
    JButton[] button;
    JLabel[] label;
    Tower tower; // 传递参数，事件响应

    public Control(Tower tower) {
        this.setSize(230, 100);
        this.setLayout(new GridLayout(2, 1));
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 2, 6, 5));
        this.add(panel1);
        label = new JLabel[strings2.length];
        button = new JButton[strings1.length];
        for (int i = 0; i < strings2.length; i++)
            label[i] = new JLabel(strings2[i]);
        SpinnerNumberModel Range = new SpinnerNumberModel(3, 3, 100, 1);
        this.number = new JSpinner(Range);
        this.number.addChangeListener(this);
        panel1.add(label[0]);
        panel1.add(number);
        text1 = new JTextField("7");
        text1.setEditable(false);
        panel1.add(label[1]);
        panel1.add(text1);
        text2 = new JTextField("0");
        text2.setEditable(false);
        panel1.add(label[2]);
        panel1.add(text2);
        panel2 = new JPanel();
        this.add(panel2);
        for (int i = 0; i < strings1.length; i++) {
            button[i] = new JButton(strings1[i]);
            button[i].addActionListener(this);
            panel2.add(button[i]);
        }
        button[2].setEnabled(false);
        this.tower = tower;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "游戏规则")
            new regulation();
        if (e.getActionCommand() == "重新开始") {
            int i = (int) (this.number.getValue());
            tower.setAmountOfDisc(i);
            tower.putDiscOnTower();
            text2.setText("0");
            tower.handleMouse.times = 0;
            button[2].setEnabled(false);
        }
        if (e.getActionCommand() == "返回操作") {
            processRecord processRecord = tower.handleMouse.stack.pop();
            TowerPoint start = processRecord.getStartTowerPoint(), end = processRecord.getEndTowerPoint();
            start.putDisc(end.getDisc(), tower.handleMouse.con);
            end.setExistDisc(false);
            tower.handleMouse.times--;
            text2.setText(String.valueOf(tower.handleMouse.times));
            if (tower.handleMouse.times == 0)
                button[2].setEnabled(false);
        }
        if (e.getActionCommand() == "自动操作") {
            int x = this.getRootPane().getParent().getX(), y = this.getRootPane().getParent().getY(),
                    width = this.getRootPane().getParent().getWidth(),
                    height = this.getRootPane().getParent().getHeight();
            tower.setAmountOfDisc(tower.amountOfDisc);
            tower.putDiscOnTower();
            tower.getAutoMoveDisc().setLocation(x + width, y);
            tower.getAutoMoveDisc().setSize(width / 4, height);
            tower.getAutoMoveDisc().setVisible(true);
        }
    }

    public void stateChanged(ChangeEvent e) {
        int i = (int) (this.number.getValue());
        tower.setAmountOfDisc(i);
        tower.putDiscOnTower();
        i = (int) (Math.pow(2, i)) - 1;
        this.text1.setText(String.valueOf(i));
        tower.handleMouse.times = 0;
        text2.setText("0");
        button[2].setEnabled(false);
    }
}

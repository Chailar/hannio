import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Hannoi extends JFrame implements ComponentListener {
    static int amountOfDisc = 3;
    Tower tower;
    Control controlPanel;

    public Hannoi() {
        super("汉诺塔演示");
        Dimension dim = this.getToolkit().getScreenSize();
        int width = (int) (dim.width / 1.5);
        int height = (int) (dim.height / 1.5);
        this.setBounds(dim.width / 2-width/2, dim.height/2-height / 2, width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.tower = new Tower();
        tower.setAmountOfDisc(amountOfDisc);
        tower.setMaxWidth(width / 4);
        tower.setMinWidth(width / amountOfDisc / 5);
        tower.setHeight((height - height / amountOfDisc) / (amountOfDisc + 5));
        tower.putDiscOnTower();
        this.add(tower, BorderLayout.CENTER);
        controlPanel = new Control(tower);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.tower.handleMouse.setControl(controlPanel);
        this.setVisible(true);
        this.addComponentListener(this);
        validate();
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        int width = this.getContentPane().getSize().width;
        int height = this.getContentPane().getSize().height;
        tower.setMaxWidth(width / 4);
        tower.setMinWidth(width / amountOfDisc / 5);
        tower.setHeight((height - height / amountOfDisc) / (amountOfDisc + 5));
        tower.putDiscOnTower();
        validate();
    }

    public static void main(String[] args) {
        new Hannoi();
    }
}

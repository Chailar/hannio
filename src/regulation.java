import java.awt.*;
import javax.swing.*;

public class regulation extends JFrame {
    JLabel label;
    static String string = "<html>汉诺塔（又称河内塔）问题是源于印度一个古老传说的益智玩具。大梵天创造世界的时候做了三根金刚石柱子，在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘。</html>";

    public regulation() {
        super("游戏规则");
        Dimension dim = this.getToolkit().getScreenSize();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds((int) (dim.width / 3), (int) (dim.height / 3), 300, 360);
        label = new JLabel(string);
        this.add(label);
        this.setVisible(true);
    }
}
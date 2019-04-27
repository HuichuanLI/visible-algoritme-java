import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.Arrays;

public class AlgoVisualizer {


    private int[] money;        // 数据 model
    private AlgoFrame frame;    // 视图 view

    public AlgoVisualizer(int sceneWidth, int sceneHeight) {


        money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {

        while (true) {
            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(10);
            for(int k=0;k<50;k++){
                for (int i = 0; i < money.length; i++) {
//                    if (money[i] > 0) {
                        int j = (int) (Math.random() * money.length);
                        money[i]--;
                        money[j] += 1;
//                    }
                }
            }
        }
    }


    public static void main(String[] args) {

        int sceneWidth = 1000;
        int sceneHeight = 1000;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}

/**
 * Created by Ramon Brand on 20-Oct-16.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class UserInterface implements Runnable {

    private JFrame frame;
    private JPanel board;

    private UIData uiData = new UIData();
    private Stage stage = new Stage(uiData);

    public UserInterface(){

    }

    @Override
    public void run() {
        frame = new JFrame();
        frame.setTitle("Canvas");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        addListeners();

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        board = new JPanel(){
            public void paintComponent(Graphics g){
                render(g);
            }
        };
        board.setPreferredSize(new Dimension(800, 600));
        container.add(board);

    }

    private void render(Graphics g){

        //Draw stage
        stage.render(g);
    }

    private void addListeners() {
        frame.addKeyListener(new KeyboardListener(board, stage));
        board.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        board.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                uiData.mouseX = e.getX();
                uiData.mouseY = e.getY();
                frame.repaint();
            }
        });
    }
}
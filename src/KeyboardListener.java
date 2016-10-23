import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Ramon Brand on 20-Oct-16.
 */
public class KeyboardListener implements KeyListener {

    private Component component;
    private Stage stage;

    public KeyboardListener(Component component, Stage stage){
        this.component = component;
        this.stage = stage;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_1){
            stage.test();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
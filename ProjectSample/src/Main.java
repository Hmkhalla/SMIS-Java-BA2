import Controller.Keyboard;
import Controller.Mouse;
import Model.Game;
import View.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("Game");

        Game game = new Game(window);
        Keyboard keyboard = new Keyboard(game);
        Mouse mouse = new Mouse(game);
        window.setKeyListener(keyboard);
        window.setMouseListener(mouse);
    }
}

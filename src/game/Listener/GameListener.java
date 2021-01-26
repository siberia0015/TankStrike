package game.Listener;

import game.GameInterface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameListener implements KeyListener {
    public GameInterface game;

    public GameListener(GameInterface game){
        this.game=game;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
                if(!game.gameIsBegin){
                    game.gameIsBegin=true;
                    game.tanks.add(game.player_1);
                }
                break;
            case KeyEvent.VK_2:
                if(!game.gameIsBegin){
                    game.coop=true;
                    game.gameIsBegin=true;
                    game.tanks.add(game.player_1);
                    game.tanks.add(game.player_2);
                }
                break;
            case KeyEvent.VK_3:
                if (game.music)
                    game.music = false;
                else
                    game.music = true;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

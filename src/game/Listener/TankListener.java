package game.Listener;

import game.GameInterface;
import game.java.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankListener implements KeyListener {
    public GameInterface game;

    public TankListener(GameInterface game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.game.gameIsBegin)
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    this.game.player_1.dir_up = true;
                    this.game.player_1.dir_down = false;
                    this.game.player_1.dir_left = false;
                    this.game.player_1.dir_right = false;
                    this.game.player_1.setDirection("UP");
                    break;
                case KeyEvent.VK_DOWN:
                    this.game.player_1.dir_down = true;
                    this.game.player_1.dir_up = false;
                    this.game.player_1.dir_left = false;
                    this.game.player_1.dir_right = false;
                    this.game.player_1.setDirection("DOWN");
                    break;
                case KeyEvent.VK_LEFT:
                    this.game.player_1.dir_left = true;
                    this.game.player_1.dir_up = false;
                    this.game.player_1.dir_down = false;
                    this.game.player_1.dir_right = false;
                    this.game.player_1.setDirection("LEFT");
                    break;
                case KeyEvent.VK_RIGHT:
                    this.game.player_1.dir_right = true;
                    this.game.player_1.dir_up = false;
                    this.game.player_1.dir_down = false;
                    this.game.player_1.dir_left = false;
                    this.game.player_1.setDirection("RIGHT");
                    break;
                case KeyEvent.VK_NUMPAD0:
                    this.game.player_1.shoot();
                    break;
                case KeyEvent.VK_W:
                    if (this.game.coop) {
                        this.game.player_2.dir_up = true;
                        this.game.player_2.dir_down = false;
                        this.game.player_2.dir_left = false;
                        this.game.player_2.dir_right = false;
                        this.game.player_2.setDirection("UP");
                    }
                    break;
                case KeyEvent.VK_S:
                    if (this.game.coop) {
                        this.game.player_2.dir_down = true;
                        this.game.player_2.dir_up = false;
                        this.game.player_2.dir_left = false;
                        this.game.player_2.dir_right = false;
                        this.game.player_2.setDirection("DOWN");
                    }
                    break;
                case KeyEvent.VK_A:
                    if (this.game.coop) {
                        this.game.player_2.dir_left = true;
                        this.game.player_2.dir_up = false;
                        this.game.player_2.dir_down = false;
                        this.game.player_2.dir_right = false;
                        this.game.player_2.setDirection("LEFT");
                    }
                    break;
                case KeyEvent.VK_D:
                    if (this.game.coop) {
                        this.game.player_2.dir_right = true;
                        this.game.player_2.dir_up = false;
                        this.game.player_2.dir_down = false;
                        this.game.player_2.dir_left = false;
                        this.game.player_2.setDirection("RIGHT");
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (this.game.coop)
                        this.game.player_2.shoot();
                    break;
                case KeyEvent.VK_5:
                    Tank tank = new Tank(this.game, "enemy",375, 120);
                    this.game.tanks.add(tank);
                    break;
                default:
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.game.gameIsBegin)
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    this.game.player_1.dir_up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    this.game.player_1.dir_down = false;
                    break;
                case KeyEvent.VK_LEFT:
                    this.game.player_1.dir_left = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    this.game.player_1.dir_right = false;
                    break;
                case KeyEvent.VK_W:
                    this.game.player_2.dir_up = false;
                    break;
                case KeyEvent.VK_S:
                    this.game.player_2.dir_down = false;
                    break;
                case KeyEvent.VK_A:
                    this.game.player_2.dir_left = false;
                    break;
                case KeyEvent.VK_D:
                    this.game.player_2.dir_right = false;
                    break;
                default:
                    break;
            }
    }
}

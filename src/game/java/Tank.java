package game.java;

import game.GameInterface;

import java.awt.*;

public class Tank {
    private int health = 1;
    private int speed = 5;
    private int position_x;
    private int position_y;
    private int volume = 25;
    private String direction = "UP";
    public boolean dir_up = false;
    public boolean dir_down = false;
    public boolean dir_left = false;
    public boolean dir_right = false;
    private boolean outOfAmmo = false;
    private String identity = null;
    private GameInterface game;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPosition_x() {
        return position_x;
    }

    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isDir_up() {
        return dir_up;
    }

    public void setDir_up(boolean dir_up) {
        this.dir_up = dir_up;
    }

    public boolean isDir_down() {
        return dir_down;
    }

    public void setDir_down(boolean dir_down) {
        this.dir_down = dir_down;
    }

    public boolean isDir_left() {
        return dir_left;
    }

    public void setDir_left(boolean dir_left) {
        this.dir_left = dir_left;
    }

    public boolean isDir_right() {
        return dir_right;
    }

    public void setDir_right(boolean dir_right) {
        this.dir_right = dir_right;
    }

    public boolean isOutOfAmmo() {
        return outOfAmmo;
    }

    public void setOutOfAmmo(boolean outOfAmmo) {
        this.outOfAmmo = outOfAmmo;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public GameInterface getGame() {
        return game;
    }

    public void setGame(GameInterface game) {
        this.game = game;
    }

    public Tank(GameInterface game, String identity, int position_x, int position_y) {
        this.game = game;
        this.identity = identity;
        this.position_x = position_x;
        this.position_y = position_y;
    }
    public Tank(GameInterface game, String identity, int health,int position_x, int position_y) {
        this.game = game;
        this.identity = identity;
        this.health=health;
        this.position_x = position_x;
        this.position_y = position_y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    //画坦克
    public void drawTank(Graphics g) {
        g.drawRect(position_x, position_y, volume * 2, volume * 2);
        g.fillRect(position_x, position_y, volume * 2, volume * 2);
        switch (direction) {
            case "UP":
                g.drawRect(position_x + volume - 5, position_y - volume + 5, 10, 20);
                g.fillRect(position_x + volume - 5, position_y - volume + 5, 10, 20);
                break;
            case "DOWN":
                g.drawRect(position_x + volume - 5, position_y + 2 * volume, 10, 20);
                g.fillRect(position_x + volume - 5, position_y + 2 * volume, 10, 20);
                break;
            case "LEFT":
                g.drawRect(position_x - volume + 5, position_y + volume - 5, 20, 10);
                g.fillRect(position_x - volume + 5, position_y + volume - 5, 20, 10);
                break;
            case "RIGHT":
                g.drawRect(position_x + 2 * volume, position_y + volume - 5, 20, 10);
                g.fillRect(position_x + 2 * volume, position_y + volume - 5, 20, 10);
                break;
            default:
                break;
        }
        if (dir_up) {
            g.drawRect(position_x + volume - 5, position_y - volume + 5, 10, 20);
            g.fillRect(position_x + volume - 5, position_y - volume + 5, 10, 20);
        } else if (dir_down) {
            g.drawRect(position_x + volume - 5, position_y + 2 * volume, 10, 20);
            g.fillRect(position_x + volume - 5, position_y + 2 * volume, 10, 20);
        } else if (dir_left) {
            g.drawRect(position_x - volume + 5, position_y + volume - 5, 20, 10);
            g.fillRect(position_x - volume + 5, position_y + volume - 5, 20, 10);
        } else if (dir_right) {
            g.drawRect(position_x + 2 * volume, position_y + volume - 5, 20, 10);
            g.fillRect(position_x + 2 * volume, position_y + volume - 5, 20, 10);
        } else {

        }
    }

    //坦克的移动
    public void move() {
        areaCollisionDetection();
            if (dir_up) {
                this.position_y -= speed;
            } else if (dir_down) {
                this.position_y += speed;
            } else if (dir_left) {
                this.position_x -= speed;
            } else if (dir_right) {
                this.position_x += speed;
            }
            mapEdgeCollisionDetection();
    }

    //坦克随机移动
    public void randomMove() {
        int act = (int) (1 + Math.random() * (8 - 1 + 1));
        switch (act) {
            case 1:
                dir_up = true;
                dir_down = false;
                dir_left = false;
                dir_right = false;
                direction = "UP";
                break;
            case 2:
                dir_down = true;
                dir_up = false;
                dir_left = false;
                dir_right = false;
                direction = "DOWN";
                break;
            case 3:
                dir_left = true;
                dir_up = false;
                dir_down = false;
                dir_right = false;
                direction = "LEFT";
                break;
            case 4:
                dir_right = true;
                dir_up = false;
                dir_down = false;
                dir_left = false;
                direction = "RIGHT";
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                shoot();
                break;
            default:
                break;
        }
    }

    //坦克的开火
    public void shoot() {
        if (!outOfAmmo) {
            switch (direction) {
                case "UP":
                    addMissile("UP");
                    break;
                case "DOWN":
                    addMissile("DOWN");
                    break;
                case "LEFT":
                    addMissile("LEFT");
                    break;
                case "RIGHT":
                    addMissile("RIGHT");
                    break;
                default:
                    break;
            }
        }
    }

    //坦克的装弹
    public void addMissile(String direction) {
        Missile missile = null;
        switch (direction) {
            case "UP":
                missile = new Missile(game, this, direction, position_x + volume - 4, position_y);
                break;
            case "DOWN":
                missile = new Missile(game, this, direction, position_x + volume - 4, position_y + 2 * volume);
                break;
            case "LEFT":
                missile = new Missile(game, this, direction, position_x, position_y + volume - 4);
                break;
            case "RIGHT":
                missile = new Missile(game, this, direction, position_x + 2 * volume, position_y + volume - 4);
                break;
            default:

                break;
        }
        this.game.missiles.add(missile);
        outOfAmmo = true;
    }

    //坦克的地图边缘碰撞检测
    public void mapEdgeCollisionDetection() {
        if (position_x > this.game.width - 2 * volume - 4) {
            position_x = this.game.width - 2 * volume - 4;
        } else if (position_x < 0) {
            position_x = 0;
        }
        if (position_y > this.game.height - 4 * volume + 20 - 200) {
            position_y = this.game.height - 4 * volume + 20 - 200;
        } else if (position_y < 0) {
            position_y = 0;
        }
    }

    //坦克的墙体碰撞检测
    public void areaCollisionDetection() {
        for (int i = 0; i < this.game.maps.size(); i++) {
            Area area = this.game.maps.get(i);
            if (Math.abs(position_x-area.getPosition_x())<45&&Math.abs(position_y-area.getPosition_y())<45&&area.getType()!="grass") {
                if(position_x<area.getPosition_x()+45&&position_x>area.getPosition_x()-45) {
                    if (position_y > area.getPosition_y()) {
                        dir_up=false;
                    } else if (position_y < area.getPosition_y()) {
                        dir_down=false;
                    }
                }
                if(position_y<area.getPosition_y()+45&&position_y>area.getPosition_y()-45) {
                    if (position_x > area.getPosition_x()) {
                        dir_left=false;
                    } else if (position_x < area.getPosition_x()) {
                        dir_right=false;
                    }
                }
            }
        }
    }

    public void explodeCollisionDetection() {
        for (int i = 0; i < this.game.missiles.size(); i++) {
            Missile missile = this.game.missiles.get(i);
            if (position_x > this.game.width - 2 * volume - 4) {
                position_x = this.game.width - 2 * volume - 4;
            } else if (position_x < 0) {
                position_x = 0;
            }
            if (position_y > this.game.height - 4 * volume + 20) {
                position_y = this.game.height - 4 * volume + 20;
            } else if (position_y < 0) {
                position_y = 0;
            }
        }
    }
}

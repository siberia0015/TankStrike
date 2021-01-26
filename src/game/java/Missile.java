package game.java;

import game.GameInterface;

import java.awt.*;
import java.util.ArrayList;

public class Missile {
    private Tank tank;
    private int position_x;
    private int position_y;
    private String direction;
    private int speed=1;
    private int radius=4;
    public GameInterface game;

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Missile(GameInterface game, Tank tank, String direction, int position_x, int position_y){
        this.game=game;
        this.tank=tank;
        this.direction=direction;
        this.position_x=position_x;
        this.position_y=position_y;
    }
    //画导弹
    public void drawMissile(Graphics g){
        g.setColor(Color.yellow);
        switch (direction){
            case "UP":
                g.drawOval(position_x,position_y-=speed,radius*2,radius*2);
                g.fillOval(position_x,position_y-=speed,radius*2,radius*2);
                break;
            case "DOWN":
                g.drawOval(position_x,position_y+=speed,radius*2,radius*2);
                g.fillOval(position_x,position_y+=speed,radius*2,radius*2);
                break;
            case "LEFT":
                g.drawOval(position_x-=speed,position_y,radius*2,radius*2);
                g.fillOval(position_x-=speed,position_y,radius*2,radius*2);
                break;
            case "RIGHT":
                g.drawOval(position_x+=speed,position_y,radius*2,radius*2);
                g.fillOval(position_x+=speed,position_y,radius*2,radius*2);
                break;
            default:
                break;
        }
        if(mapEdgeCollisionDetection()){
            try {
                this.game.missiles.remove(this);
                this.tank.setOutOfAmmo(false);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        areaCollisionDetection();
        if(tankCollisionDetection()){
            try {
                this.game.missiles.remove(this);
                this.tank.setOutOfAmmo(false);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
    public boolean mapEdgeCollisionDetection(){
        if(position_x>this.game.width-4*radius){
            return true;
        }else if(position_x<0){
            return true;
        }
        if(position_y>this.game.height-10*radius-200){
            return true;
        }else if(position_y<0){
            return true;
        }
        return false;
    }
    public boolean areaCollisionDetection(){
        for (int i = 0; i < this.game.maps.size(); i++) {
            Area area = this.game.maps.get(i);
            if (position_x < area.getPosition_x() + 50 && position_x > area.getPosition_x() && position_y < area.getPosition_y() + 50 && position_y > area.getPosition_y()) {
                switch (area.getType()){
                    case "wall":
                        this.game.maps.remove(i);
                        this.game.missiles.remove(this);
                        this.tank.setOutOfAmmo(false);
                        break;
                    case "grass":
                        break;
                    case "water":
                        break;
                    case "steel":
                        this.game.missiles.remove(this);
                        this.tank.setOutOfAmmo(false);
                        break;
                    case "base":
                        this.game.missiles.remove(this);
                        this.game.maps.remove(i);
                        this.game.base=false;
                        this.tank.setOutOfAmmo(false);
                    default:
                        break;
                }
                return true;
            }
        }
        return  false;
    }
    public boolean tankCollisionDetection() {
        for (int i = 0; i < this.game.tanks.size(); i++) {
            Tank tank = this.game.tanks.get(i);
            if (position_x < tank.getPosition_x() + 50 && position_x > tank.getPosition_x() && position_y < tank.getPosition_y() + 50 && position_y > tank.getPosition_y()) {
                if (tank.getIdentity() == "enemy") {
                    if(this.tank.getIdentity()=="player_1"){
                        this.game.kills1++;
                    } else if(this.tank.getIdentity()=="player_2"){
                        this.game.kills2++;
                    }
                    this.game.tanks.remove(i);
                }
                else if(tank.getIdentity()=="player_1"){
                    if(this.tank.getIdentity()=="enemy"){
                        this.game.kills1--;
                    } else if(this.tank.getIdentity()=="player_2"){
                        this.game.kills2--;
                    }
                }else if(tank.getIdentity()=="player_2"){
                    if(this.tank.getIdentity()=="enemy"){
                        this.game.kills2--;
                    } else if(this.tank.getIdentity()=="player_1"){
                        this.game.kills1--;
                    }
                }
                return true;
            }
        }
        return false;
    }
}

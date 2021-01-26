package game.java;

import java.awt.*;

public class Area {
    private boolean state=true;
    private String type;
    private boolean breakable;
    private boolean passable;
    private boolean toutable;
    private int volume=25;
    private int position_x;
    private int position_y;
    public Area(String type,int position_x,int position_y){
        this.type=type;
        this.position_x=position_x;
        this.position_y=position_y;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isToutable() {
        return toutable;
    }

    public void setToutable(boolean toutable) {
        this.toutable = toutable;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
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

    //画地形
    public void drawArea(Graphics g) {
        switch (type) {
            case "wall":
                g.setColor(Color.ORANGE);
                this.breakable=true;
                this.passable=false;
                this.toutable=false;
                break;
            case "water":
                g.setColor(Color.CYAN);
                this.breakable=false;
                this.passable=true;
                this.toutable=false;
                break;
            case "steel":
                g.setColor(Color.WHITE);
                this.breakable=false;
                this.passable=false;
                this.toutable=false;
                break;
            case "grass":
                g.setColor(Color.GREEN);
                this.breakable=false;
                this.passable=true;
                this.toutable=true;
                break;
            case "base":
                g.setColor(Color.magenta);
                this.breakable=true;
                this.passable=false;
                this.toutable=false;
                break;
            default:
                break;
        }
        g.drawRect(position_x,position_y,volume*2,volume*2);
        g.fillRect(position_x,position_y,volume*2,volume*2);
    }
}

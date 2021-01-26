package game;

import game.Listener.GameListener;
import game.Listener.TankListener;
import game.java.Area;
import game.java.Missile;
import game.java.Tank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameInterface extends JPanel {
    public JFrame game_jfram ;
    public boolean situationIsChange=false;
    public boolean gameIsBegin=false;
    public boolean gameIsOver=false;
    public boolean coop=false;
    public boolean music=true;
    public boolean base=true;
    public int width=805;
    public int height=800;
    public int kills1=0;
    public int kills2=0;
    public Tank player_1;
    public Tank player_2;
    public ArrayList<Tank> tanks;
    public ArrayList<Missile> missiles;
    public ArrayList<Area> maps;
    public GameInterface(){
        player_1=new Tank(this,"player_1",5,250,470);
        player_2=new Tank(this,"player_2",5,500,470);
        tanks=new ArrayList<Tank>();
        missiles = new ArrayList<>();
        maps=new ArrayList<>();
        game_jfram = new JFrame("坦克大战");
        game_jfram.setSize(width,height);
        game_jfram.setLayout(null);
        game_jfram.setLocationRelativeTo(null);
        game_jfram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game_jfram.setResizable(false);

        GameListener gameListener = new GameListener(this);
        TankListener tankListener = new TankListener(this);
        game_jfram.addKeyListener(gameListener);
        game_jfram.addKeyListener(tankListener);
        game_jfram.add(this);
        game_jfram.setVisible(true);

        this.setSize(width,height);
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        Font font=new Font("TimesRoman",Font.ITALIC,60);
        this.setFont(font);

        new Thread(() -> {

            while(base){
                try{

                    for(int i=0;i<tanks.size();i++){
                        Tank tank=tanks.get(i);
                        tank.move();
                    }
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            createMap();

                while (base) {
                        try {
                            int position=(int)(1+Math.random()*(2-1+1));
                            Tank tank=null;
                            if(position==1){
                                tank = new Tank(this,"enemy", 0, 0);
                            }else if(position==2){
                                tank = new Tank(this,"enemy", 750, 0);
                            }
                            if(gameIsBegin&&tanks.size()<10)
                                tanks.add(tank);
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
        }).start();
        new Thread(() -> {

            while(base){
                try{
                    for(int i=0;i<tanks.size();i++){
                        Tank tank=tanks.get(i);
                        if (tank.getIdentity()== "enemy") {
                            tank.randomMove();

                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void paint(Graphics g){
        super.paint(g);
        if(!gameIsBegin) {
            opening(g);
        }else/* if(!gameIsOver)*/{
            g.drawString("杀敌数："+String.valueOf(kills1),10,650);
            g.drawString("杀敌数："+String.valueOf(kills2),460,650);
            for (int i = 0; i < tanks.size(); i++) {
                Tank tank = tanks.get(i);
                switch (tank.getIdentity()){
                    case "player_1":
                        g.setColor(Color.RED);
                        break;
                    case "player_2":
                        g.setColor(Color.BLUE);
                        break;
                    case "enemy":
                        g.setColor(Color.BLACK);
                        break;
                    default:
                        break;
                }
                tank.drawTank(g);
            }
            if(situationIsChange){
                for (int i = 0; i < maps.size(); i++) {
                    Area area = maps.get(i);
                    area.drawArea(g);
                }
            }
            for (int i = 0; i < missiles.size(); i++) {
                Missile missile = missiles.get(i);
                missile.drawMissile(g);
            }
            g.setColor(Color.BLACK);
            g.drawRect(0,570,805,20);
            g.fillRect(0,570,805,20);

            if(kills1+kills2<-5){
                g.drawString("任务失败，分数过低！", 10, 700);
                gameIsOver=true;
            }else if(kills1+kills2>7){
                g.drawString("任务成功，敌军全灭！", 10, 700);
                gameIsOver=true;
            } else if(!base) {
                g.drawString("任务失败，基地已毁！", 10, 700);
                gameIsOver=true;
            }
        }
        if (game_jfram.isEnabled())
            repaint();
    }
    public void opening(Graphics g){
        g.setColor(Color.RED);
        g.drawString("TankStrike", 220, 100);
        g.setColor(Color.BLACK);
        g.drawString("Button 1", 50, 200);
        g.drawString("Solo", 400, 200);
        g.drawString("Button 2", 50, 280);
        g.drawString("Cooperation", 400, 280);
        g.drawString("Button 3", 50, 360);
        g.drawString("Music:", 400, 360);
        if (music) g.drawString("ON", 600, 360);
        else g.drawString("OFF", 600, 360);
        g.drawString("Button ESC", 50, 440);
        g.drawString("Quit", 400, 440);

        situationIsChange=true;
    }
    public void createMap(){
        Area area=null;
//line1
        area=new Area("wall",300,520);
        maps.add(area);
        area=new Area("base",375,520);
        maps.add(area);
        area=new Area("wall",450,520);
        maps.add(area);
//line2
        area=new Area("wall",50,470);
        maps.add(area);
        area=new Area("wall",100,470);
        maps.add(area);
        area=new Area("wall",150,470);
        maps.add(area);
        area=new Area("wall",300,470);
        maps.add(area);
        area=new Area("wall",350,470);
        maps.add(area);
        area=new Area("wall",400,470);
        maps.add(area);
        area=new Area("wall",450,470);
        maps.add(area);
        area=new Area("wall",600,470);
        maps.add(area);
        area=new Area("wall",650,470);
        maps.add(area);
        area=new Area("wall",700,470);
        maps.add(area);
//line3
        area=new Area("steel",50,420);
        maps.add(area);
        area=new Area("wall",100,420);
        maps.add(area);
        area=new Area("wall",150,420);
        maps.add(area);
        area=new Area("steel",350,420);
        maps.add(area);
        area=new Area("steel",400,420);
        maps.add(area);
        area=new Area("wall",600,420);
        maps.add(area);
        area=new Area("wall",650,420);
        maps.add(area);
        area=new Area("steel",700,420);
        maps.add(area);
//line4
        area=new Area("wall",250,370);
        maps.add(area);
        area=new Area("wall",300,370);
        maps.add(area);
        area=new Area("wall",350,370);
        maps.add(area);
        area=new Area("wall",400,370);
        maps.add(area);
        area=new Area("wall",450,370);
        maps.add(area);
        area=new Area("wall",500,370);
        maps.add(area);
//line5
        area=new Area("water",50,320);
        maps.add(area);
        area=new Area("water",100,320);
        maps.add(area);
        area=new Area("water",150,320);
        maps.add(area);
        area=new Area("steel",200,320);
        maps.add(area);
        area=new Area("steel",250,320);
        maps.add(area);
        area=new Area("steel",300,320);
        maps.add(area);
        area=new Area("wall",350,320);
        maps.add(area);
        area=new Area("wall",400,320);
        maps.add(area);
        area=new Area("steel",450,320);
        maps.add(area);
        area=new Area("steel",500,320);
        maps.add(area);
        area=new Area("steel",550,320);
        maps.add(area);
        area=new Area("water",600,320);
        maps.add(area);
        area=new Area("water",650,320);
        maps.add(area);
        area=new Area("water",700,320);
        maps.add(area);
//line6
        area=new Area("water",50,270);
        maps.add(area);
        area=new Area("wall",100,270);
        maps.add(area);
        area=new Area("water",150,270);
        maps.add(area);
        area=new Area("water",600,270);
        maps.add(area);
        area=new Area("wall",650,270);
        maps.add(area);
        area=new Area("water",700,270);
        maps.add(area);
//line7
        area=new Area("water",50,220);
        maps.add(area);
        area=new Area("water",100,220);
        maps.add(area);
        area=new Area("water",150,220);
        maps.add(area);
        area=new Area("steel",300,220);
        maps.add(area);
        area=new Area("steel",350,220);
        maps.add(area);
        area=new Area("steel",400,220);
        maps.add(area);
        area=new Area("steel",450,220);
        maps.add(area);
        area=new Area("water",600,220);
        maps.add(area);
        area=new Area("water",650,220);
        maps.add(area);
        area=new Area("water",700,220);
        maps.add(area);
//line8
        area=new Area("grass",50,170);
        maps.add(area);
        area=new Area("grass",100,170);
        maps.add(area);
        area=new Area("grass",150,170);
        maps.add(area);
        area=new Area("wall",200,170);
        maps.add(area);
        area=new Area("wall",250,170);
        maps.add(area);
        area=new Area("wall",300,170);
        maps.add(area);
        area=new Area("wall",350,170);
        maps.add(area);
        area=new Area("wall",400,170);
        maps.add(area);
        area=new Area("wall",450,170);
        maps.add(area);
        area=new Area("wall",500,170);
        maps.add(area);
        area=new Area("wall",550,170);
        maps.add(area);
        area=new Area("grass",600,170);
        maps.add(area);
        area=new Area("grass",650,170);
        maps.add(area);
        area=new Area("grass",700,170);
        maps.add(area);
//line9
        area=new Area("grass",50,120);
        maps.add(area);
        area=new Area("grass",100,120);
        maps.add(area);
        area=new Area("grass",150,120);
        maps.add(area);
        area=new Area("grass",600,120);
        maps.add(area);
        area=new Area("grass",650,120);
        maps.add(area);
        area=new Area("grass",700,120);
        maps.add(area);
//line10
        area=new Area("wall",200,70);
        maps.add(area);
        area=new Area("wall",250,70);
        maps.add(area);
        area=new Area("wall",300,70);
        maps.add(area);
        area=new Area("wall",350,70);
        maps.add(area);
        area=new Area("wall",400,70);
        maps.add(area);
        area=new Area("wall",450,70);
        maps.add(area);
        area=new Area("wall",500,70);
        maps.add(area);
        area=new Area("wall",550,70);
        maps.add(area);
//line11
        area=new Area("wall",200,20);
        maps.add(area);
        area=new Area("wall",250,20);
        maps.add(area);
        area=new Area("wall",300,20);
        maps.add(area);
        area=new Area("wall",350,20);
        maps.add(area);
        area=new Area("wall",400,20);
        maps.add(area);
        area=new Area("wall",450,20);
        maps.add(area);
        area=new Area("wall",500,20);
        maps.add(area);
        area=new Area("wall",550,20);
        maps.add(area);
    }
}

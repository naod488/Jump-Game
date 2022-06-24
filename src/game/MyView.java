package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
public class MyView extends UserView {
    private World world;
    private int width;
    private int height;
    private Projectile projectile;

    private static final Image poweredUp  = new ImageIcon("data/StarActiveIndicator.png").getImage();
    private static final Image powerUsed  = new ImageIcon("data/StarIndicator.png").getImage();
    private static final Image background0 = new ImageIcon("data/backgroundSMW.png").getImage();
    private static final Image background1 = new ImageIcon("data/backgroundSMB3.jpg").getImage();
    private static final Image background2 = new ImageIcon("data/smw-clouds.png").getImage();
    private static final Image background3 = new ImageIcon("data/smw-hills.png").getImage();

    private static final Image PowerBar = new ImageIcon("data/PowerBar.png").getImage();
    private static final Image PowerBarOverlay  = new ImageIcon("data/PowerBarOverlay.png").getImage();

    public MyView(World world, int width, int height, Projectile projectile) {
        super(world, width, height);
        this.world = world;
        this.width = width;
        this.height = height;
        this.projectile = projectile;
    }

    @Override
    protected void paintBackground(Graphics2D g) {

        if (projectile.getlevel() == 0) {
            g.drawImage(background0, 0, 0, 500,500, null);
        } else if (projectile.getlevel() == 1){
            g.drawImage(background1, 0, 0, 500,500, null);
        } else if (projectile.getlevel() == 2){
            g.drawImage(background2, 0, 0, 500,500, null);
        } else if (projectile.getlevel() == 3){
            g.drawImage(background3, 0, 0, 500,500, null);
        }

        if (projectile.isBoostflag() && !projectile.islaunchflag()){
            g.drawImage(poweredUp, width/2-21, height/2-24, null);
        }
        else if (projectile.isBoostflag() && projectile.islaunchflag()){
            g.drawImage(powerUsed, width/2-21, height/2-24, null);
        }
    }
    @Override
    protected void paintForeground(Graphics2D g) {
        g.drawString("Level: " + projectile.getlevel(), 10, 10);
        g.drawString("Launches: " + projectile.getlaunches(), 10, 30);
        g.drawImage(PowerBar, 50, height - 100, null);
        g.drawImage(PowerBarOverlay, 50, height - ControlPlatform.distance/7 - 68, null);
    }
}

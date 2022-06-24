package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.SoundClip;
import city.cs.engine.WorldView;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.math.BigDecimal;

//launch the player object towards mouse position, check if boost is active
// and apply the reduced density on projectile starting from next until tehe second next launch
public class Launcher extends MouseAdapter {
    private Projectile projectile;
    private final float mutltiplier = 150;
    private SoundClip launchSound;

    {
        System.out.println("Launcher.java: Initializing MouseListener for view");
    }

    public Launcher(Projectile projectile){
        this.projectile = projectile;
    }

    public void mousePressed(MouseEvent m) {
        System.out.println("Launcher.java: MouseButton Pressed");
        System.out.println("Launcher.java: Check if projectile.isincontact returns true");
        System.out.println("Launcher.java: " + projectile.isIncontact());
        System.out.println();
        if (projectile.isIncontact()) {
            if (m.getButton() == MouseEvent.BUTTON1) {
                System.out.println("Launcher.java: Check if projectile.islaunchflag");
                System.out.println("Launcher.java: " + projectile.islaunchflag());
                if (this.projectile.islaunchflag()) {
                    this.projectile.setLaunchflag(false);
                    this.projectile.setBoostflag(false);
                    projectile.setGravityScale(projectile.getDensity());
                    System.out.println("Launcher.java: you already used the boost: platform.setlaunchflag(false); projectil.setboostflag(false)");
                    System.out.println();
                } else{ System.out.println();}

                System.out.println("Launcher.java: Check if projectile.isboostflag");
                System.out.println("Launcher.java: " + projectile.isBoostflag());
                if (this.projectile.isBoostflag()) {
                    this.projectile.setLaunchflag(true);
                    projectile.setGravityScale(projectile.getDensity());
                    System.out.println("Launcher.java: you launched while having the boost: platform.setlaunchflag(true)");
                    System.out.println();
                } else{ System.out.println();}
                projectile.applyForce(new Vec2(GetLaunch(m.getX(), m.getY())));
                projectile.setLaunches();
                System.out.println("Launcher.java: Launched with a force of: " + (m.getX() - 250)+ "x, " + (m.getY() - 250) * -1+ "y");
                System.out.println("Launcher.java: Projectile velocity is: "+ projectile.getLinearVelocity());
                System.out.println("Launcher.java: Launch count incremented: calling projectile.setLaunches (increment launches in projectile by 1)");
                System.out.println("Launcher.java: New launch count is: " + projectile.getlaunches());
                System.out.println();

                try {
                    launchSound = new SoundClip("data/smb_jump.wav");
                    launchSound.play();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public Vec2 GetLaunch(float x, float y) {// Limits the launch strength
        float angle = (float) Math.atan2(x - 250, (y - 250) * -1);
        float newx = (float) Math.sin(angle)*ControlPlatform.distance;//find X
        float newy = (float) Math.cos(angle)*ControlPlatform.distance;//find Y
        return new Vec2(newx*mutltiplier, newy*mutltiplier);
    }
}
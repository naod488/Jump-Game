package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//.destroy() or reset the position of the player object (projectile) upon collision with the DeathWalls object instance
public class KillPlayer implements CollisionListener {
    private Projectile projectile;
    private Game game;
    private SoundClip deathSound;

    {
        System.out.println("KillPlayer.java: Initializing CollisionListener for DeathWalls object");
    }

    public KillPlayer(Projectile projectile, Game game){
        this.projectile = projectile;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Projectile) {
            projectile.setLaunchflag(false);
            projectile.setBoostflag(false);
            game.goNextLevel();
            System.out.println("KillPlayer.java: Player touched the grinder reloading level");
            System.out.println();

            try {//play sound
                deathSound = new SoundClip("data/smb_bowserfire.wav");
                deathSound.play();
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
    }
}
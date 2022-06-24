package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {
    private Projectile projectile;
    private List<Platform> platforms;
    private List<DeathWalls> deathwalls;
    private List<PowerUp> powerUps;
    private Cloud cloud;
    private List<Bumper> bumpers;

    public Projectile getProjectile() {
        return projectile;
    }
    public List<Platform> getPlatforms() { return platforms; }
    public List<DeathWalls> getDeathwalls() {return deathwalls; }
    public List<PowerUp> getPowerUps() {return powerUps; }
    public Cloud getCloud() {return cloud; }
    public List<Bumper> getBumpers() {return bumpers; }

    private CollisionListener cloudlistener;


    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     */
    public void populate(Game game) {
        projectile = new Projectile(this);
        projectile.setPosition(startPosition());

        platforms = new ArrayList<>(0);
        deathwalls = new ArrayList<>(0);
        powerUps = new ArrayList<>(0);
        bumpers = new ArrayList<>(0);

        ///Cloud/Objective When reached next level is loaded
        if (cloudlistener != null && cloud != null){
            cloud.removeCollisionListener(cloudlistener);
        }
        cloud = new Cloud(this);
        this.cloudlistener = new GoalReached(projectile, game);
        cloud.setPosition(cloudPosition());
        cloud.addCollisionListener(cloudlistener);
        System.out.println(cloud.getPosition().x + " " + cloud.getPosition().y);
    }

    /** The initial position of the player. */
    public abstract Vec2 startPosition();

    /** The position of the exit door. */
    public abstract Vec2 cloudPosition();
}

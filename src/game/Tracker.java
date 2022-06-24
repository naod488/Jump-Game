package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.List;

/**
 * Pan the view to follow a particular body.
 * Apply constact force to projectile to keep it from geeting stale
 */
public class Tracker implements StepListener {
    /** The view */
    private WorldView view;

    /** All bodies */
    private Projectile projectile;
    private List<Platform> platforms;
    private List<DeathWalls> deathwalls;
    private List<PowerUp> powerUps;
    private List<Bumper> bumpers;
    private Cloud cloud;
    private float platformxmovement = 0.025f;

    public Tracker(WorldView view, Projectile projectile, List<Platform> platforms, List<DeathWalls> deathwalls, List<PowerUp> powerUps,  List<Bumper> bumpers, Cloud cloud) {
        this.view = view;
        this.projectile = projectile;
        this.platforms = platforms;
        this.deathwalls = deathwalls;
        this.powerUps = powerUps;
        this.bumpers = bumpers;
        this.cloud = cloud;
    }

    @Override
    public void preStep(StepEvent e) {
    }

    @Override
    public void postStep(StepEvent e) {
        if (projectile.getlevel() == 1){
            for (int i = 0; i < bumpers.size(); i++){
                bumpers.get(i).setLinearVelocity(new Vec2(0, bumpers.get(i).getLinearVelocity().y));
            }
        }
        if (projectile.getlevel() != 2){
            cloud.setLinearVelocity(new Vec2(0,0));
        } else if (projectile.getlevel() == 2){
            if (cloud.getLinearVelocity().x > 7){
                cloud.setLinearVelocity(new Vec2(7, cloud.getLinearVelocity().y));
            } else if (cloud.getLinearVelocity().x < -7){
                cloud.setLinearVelocity(new Vec2(-7, cloud.getLinearVelocity().y));
            }
            if (cloud.getLinearVelocity().y > 7){
                cloud.setLinearVelocity(new Vec2(cloud.getLinearVelocity().x, 7));
            } else if (cloud.getLinearVelocity().y < -7){
                cloud.setLinearVelocity(new Vec2(cloud.getLinearVelocity().x, -7));
            }
        }
        if (projectile.getlevel() == 3){
            for (int i = 0; i < platforms.size(); i++){
                Platform currentplatform = platforms.get(i);
                if (currentplatform.getPosition().x > 45 || currentplatform.getPosition().x < -45){
                    platformxmovement = platformxmovement*(-1);
                }
                currentplatform.setPosition(new Vec2(currentplatform.getPosition().x + platformxmovement, currentplatform.getPosition().y));
                float currentx = platforms.get(i).getPosition().x;
                currentplatform.setPosition(new Vec2(currentplatform.getPosition().x, currentx/7*currentx/7));
            }
        }

        for (int i = 0; i < powerUps.size(); i++){//speed threshold for the powerup
            if (powerUps.get(i).getLinearVelocity().x > 7){
                powerUps.get(i).setLinearVelocity(new Vec2(7, powerUps.get(i).getLinearVelocity().y));
            }
            if (powerUps.get(i).getLinearVelocity().y > 15){
                powerUps.get(i).setLinearVelocity(new Vec2(powerUps.get(i).getLinearVelocity().x, 15));
            }
        }
        view.setCentre(new Vec2(projectile.getPosition()));//Makes view centered to the projectile
        projectile.applyForce(new Vec2(0f,0f));//Prevents the projectile from getting stale (is coloured blue in DebugView when stale)
    }
}

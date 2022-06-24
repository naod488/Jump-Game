package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

//Check for collision of projectile and the powerUp, sets boostflag which halves the projectile density status is being constantly checked by tracker.java
public class Boost implements CollisionListener {
    private Projectile projectile;

    {
        System.out.println("Boost.java: Initializing CollisionListener for PowerUp object");
    }

    public Boost(Projectile projectile){
        this.projectile = projectile;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Projectile) {
            e.getReportingBody().destroy();
            System.out.println("Boost.java: you got the boost:collision Projectile&PowerUp; platform.setlaunchflag(false); projectil.setboostflag(True)");
            projectile.setBoostflag(true);
            projectile.setLaunchflag(false);
        }
    }
}

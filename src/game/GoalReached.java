package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

//.destroy() the player object (projectile) upon collision with DeathWalls object
public class GoalReached implements CollisionListener {
    private Projectile projectile;
    private Game game;
    {
        System.out.println("GoalReached.java: Initializing CollisionListener for Cloud object");
    }

    public GoalReached(Projectile projectile, Game game){
        this.projectile = projectile;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        System.out.println();
        System.out.println("GoalReached.java: Cloud touched: collision Projectile&Cloud");
        System.out.println();
        if (e.getOtherBody() instanceof Projectile && (projectile.getPosition().x >= e.getReportingBody().getPosition().x-0.5f
                && projectile.getPosition().x <= e.getReportingBody().getPosition().x+0.5f)
                && projectile.getPosition().y >= e.getReportingBody().getPosition().y){
            System.out.println("GoalReached.java: You beat level " + projectile.getlevel() + ": calling projectile.nextlevel (increment level by 1)");
            projectile.nextlevel();
            game.goNextLevel();
            System.out.println("GoalReached.java: Progressing to level " + projectile.getlevel());
            System.out.println();
        }
    }
}
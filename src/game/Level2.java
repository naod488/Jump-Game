package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public class Level2 extends GameLevel {

    @Override
    public void populate(Game game) {
        super.populate(game);
        //Make Platform, add sensor when projectile is in contact with senor enable the incontact attribute (enables launching),
        // controller is being called in tracker tacking the plaforms from the array for platform rotation towards mouse position.
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 6; y++) {
                int randx = (int)(Math.random() * 10) - 5    ;
                int randy = (int)(Math.random() * 10) - 5;
                Platform platform = new Platform(this, 5f + x * 15f + randx, 5f + y * 10f + randy);
                Sensor platformSensor = new Sensor(platform, platform.getPlatformShape());
                platformSensor.addSensorListener(new Launch(getProjectile()));
                getPlatforms().add(platform);
                System.out.println();
            }
        }

        //Make Death walls, "kills player" (reloads level)
        for (int i = 0; i < 15; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, -10f, -10 + i * 5f, false);
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        for (int i = 0; i < 15; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, 75f, -10 + i * 5f, false);
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        for (int i = 0; i < 18; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, -10 + i * 5f, -10f, false);
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        for (int i = 0; i < 18; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, -10 + i * 5f, 60f, false);
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        System.out.println();

    }
    @Override
    public Vec2 startPosition() {
        return new Vec2(0f, 6f);
    }

    @Override
    public Vec2 cloudPosition() {
        return new Vec2((int)(Math.random() * 50) + 25, (int)(Math.random() * 35) + 20);
    }
}

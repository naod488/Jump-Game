package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public class Level3 extends GameLevel {

    @Override
    public void populate(Game game) {
        super.populate(game);
        //Make Platform, add sensor when projectile is in contact with senor enable the incontact attribute (enables launching)
        // also calls controller for platform rotation towards mouse position
        for (int x = 0; x < 5; x++) {
            Platform platform = new Platform(this, 0f + x * 10f, x*x /10);
            Sensor platformSensor = new Sensor(platform, platform.getPlatformShape());
            platformSensor.addSensorListener(new Launch(getProjectile()));
            getPlatforms().add(platform);
            System.out.println();
        }

        //Make Death walls, "kills player" (puts him back at the starting position)
        for (int i = 0; i < 11; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, -50f, -10 + i * 5f, false);//horizontal
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        for (int i = 0; i < 11; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, 40f, -10 + i * 5f, false);//horizontal
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        for (int i = 0; i < 19; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, -50 + i * 5f, -10f, false);//vertical
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        for (int i = 0; i < 19; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, -50 + i * 5f, -10f + 10*5, false);//vertical
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        System.out.println();

    }
    @Override
    public Vec2 startPosition() {
        return new Vec2(0f, 1f);
    }

    @Override
    public Vec2 cloudPosition() {
        return new Vec2(0f, 10f);
    }
}

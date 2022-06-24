package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends GameLevel {

    @Override
    public void populate(Game game) {
        super.populate(game);
        //Make Platform, add sensor when projectile is in contact with senor enable the incontact attribute (enables launching)
        // also calls controller for platform rotation towards mouse position
        for (int x = 0; x < 4; x++) {
            Platform platform = new Platform(this, 0f + x * 20f, 0f);
            Sensor platformSensor = new Sensor(platform, platform.getPlatformShape());
            platformSensor.addSensorListener(new Launch(getProjectile()));
            getPlatforms().add(platform);
            System.out.println();
        }
        ///PowerUp, decreases projectile density upon collection until second launch after collection
        PowerUp star = new PowerUp(this);
        star.setPosition(new Vec2(4, 3));
        star.setLinearVelocity(new Vec2(4,0));
        star.addCollisionListener(new Boost(getProjectile()));
        getPowerUps().add(star);
        System.out.println();

        //Make Death walls, "kills player" (puts him back at the starting position)
        for (int i = 0; i < 10; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, -10f, -10 + i * 5f, false);
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        for (int i = 0; i < 10; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, 75f, -10 + i * 5f, false);
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        for (int i = 0; i < 18; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, -10 + i * 5f, -10f, false);
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        for (int i = 0; i < 18; i++) {
            DeathWalls PlayerDied = new DeathWalls(this, -10 + i * 5f, -10f + 10*5, false);
            PlayerDied.addCollisionListener(new KillPlayer(getProjectile(), game));
        }
        System.out.println();

        //Create obstacles
        for (int i = 0; i < 3; i++) {
            Bumper bumper = new Bumper(this, 8+i*20, 10);
            getBumpers().add(bumper);
            bumper = new Bumper(this, 13+i*20, 10);
            getBumpers().add(bumper);
        }

    }
    @Override
    public Vec2 startPosition() {
        return new Vec2(0f, 1f);
    }

    @Override
    public Vec2 cloudPosition() {
        return new Vec2(50f, 5f);
    }
}

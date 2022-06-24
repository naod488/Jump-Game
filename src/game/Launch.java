package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.MouseEvent;

//Check for collision between the projectile and platform and enable launching the player per mouse klick(not fully working)
public class Launch implements SensorListener {
    private static Projectile projectile;

    {
        System.out.println("Launch.java: Initializing SensorListener for platformSensor object");
    }

    public Launch(Projectile projectile) {
        this.projectile= projectile;
    }

    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof Projectile) {
            System.out.println("Launch.java: Hello platform: sensor beginContact Projectile&Platform");
            System.out.println();
            this.projectile.setIncontact(true);
        }
    }

    @Override
    public void endContact(SensorEvent e) {
        if (e.getContactBody() instanceof Projectile) {
            System.out.println("Launch.java: Goodbye platform: sensor endContact projectile&platform");
            System.out.println();
            this.projectile.setIncontact(false);
        }
    }
}
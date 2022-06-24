package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

//The other player object orientation changes depending on mouse position(handeled in MouseListener ControlPlatform.java)
//Launchflag is a flag to determine if the boost from the PowerUp/projectile collision(Boost.java) has been used yet (also used in Launcher.java))
public class Platform extends StaticBody {
    private static final Shape platformShape = new BoxShape(5f, 0.5f);
    private static final Shape platformsensorShape = new BoxShape(5f, 0.7f);
    private static final BodyImage platformImage= new BodyImage("data/Platform2.png", 1.0f);

    {
        System.out.println("Platform.java: Building platform: Platform object created");
    }

    public Shape getPlatformShape(){//return the shpae of the platform used for the sensor
        return platformsensorShape;
    }

    public Platform(World world, float x, float y) {
        super(world);
        SolidFixture platformfixture = new SolidFixture(this, platformShape);
        platformfixture.setFriction(200f);
        platformfixture.setRestitution(0f);
        setPosition(new Vec2(x, y));
        this.addImage(platformImage);
    }
}

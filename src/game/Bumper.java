package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

//Will maybe used to transition levels, serves as the goal to be reached
public class Bumper extends DynamicBody {
    private static final float RADIUS = 1.0f;
    private static final Shape ballShape = new CircleShape(RADIUS);
    private static final BodyImage ballImage = new BodyImage("data/Bumper.png", 2*RADIUS);

    {
        System.out.println("Bumper.java: creating a Bumper");
    }

    public Bumper(World w, int x, int y) {
        super(w);
        SolidFixture ballfixture = new SolidFixture(this, ballShape, 20);
        ballfixture.setRestitution(1f);
        this.setPosition(new Vec2(x, y));
        this.addImage(ballImage);
    }
}
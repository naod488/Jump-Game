package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

//Add kill plain Body
public class DeathWalls extends StaticBody {
    private static final BodyImage GrinderImage = new BodyImage("data/Grinder.png", 5.0f);
    private static final Shape RoundGrinderShape = new CircleShape(2.5f);
    private static final Shape FlatGrinderShape = new BoxShape(2.5f, 2.5f);

    {
        System.out.println("DeathWalls.java: Building Grinder/DeathWalls: DeathWalls object created");
    }

    public DeathWalls(World world, float x, float y, Boolean round) {
        super(world);
        if (round) {
            SolidFixture wallfixture = new SolidFixture(this, RoundGrinderShape);
        }
        else{
            SolidFixture wallfixture = new SolidFixture(this, FlatGrinderShape);
        }
        this.setPosition(new Vec2(x, y));
        this.addImage(GrinderImage);
    }
}

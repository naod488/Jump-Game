package game;

import city.cs.engine.*;

//A star shape referenced in Game.java as star
public class PowerUp extends Walker {
    private static BodyImage StarImage = new BodyImage("data/Star.png", 1.5f);
    private static PolygonShape topshape = new PolygonShape(-0.22f,0.28f, 0f,0.74f, 0.22f,0.29f);
    private static PolygonShape topleftshape = new PolygonShape(-0.24f,0.28f, -0.65f,0.28f, -0.65f,0.09f, -0.37f,-0.18f);
    private static PolygonShape toprightshape = new PolygonShape(0.24f,0.28f, 0.65f,0.28f, 0.65f,0.09f, 0.37f,-0.18f);
    private static PolygonShape bottomleftshape = new PolygonShape(-0.37f,-0.18f, -0.56f,-0.74f, 0f,-0.46f);
    private static PolygonShape bottomrightshape = new PolygonShape(0.37f,-0.18f, 0.56f,-0.74f, 0f,-0.46f);

    {
        System.out.println("PowerUp.java: Building PowerUp: PowerUp object created");
    }

    public PowerUp(World world) {
        super(world);
        SolidFixture topfixture = new SolidFixture(this, topshape);
        SolidFixture topleftfixture = new SolidFixture(this, topleftshape);
        SolidFixture toprightfixture = new SolidFixture(this, toprightshape);
        SolidFixture bottomleftfixture = new SolidFixture(this, bottomleftshape);
        SolidFixture bottomrightfixture = new SolidFixture(this, bottomrightshape);

        topfixture.setRestitution(1);
        topleftfixture.setRestitution(1);
        toprightfixture.setRestitution(1);
        bottomleftfixture.setRestitution(1);
        bottomrightfixture.setRestitution(1);

        topfixture.setFriction(0);
        topleftfixture.setFriction(0);
        toprightfixture.setFriction(0);
        bottomleftfixture.setFriction(0);
        bottomrightfixture.setFriction(0);

        this.addImage(StarImage);
    }
}

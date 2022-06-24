package game;

import city.cs.engine.*;

//Will maybe used to transition levels, serves as the goal to be reached
public class Cloud extends Walker {
    private static final Shape leftshape = new PolygonShape(-0.8f,0.6f, -0.9f,0f, -0.84f,-0.277f, -0.46f,-0.277f);//top, left side, bottom left, bottom right
    private static final Shape bottomshape = new PolygonShape(-0.84f,-0.277f, -0.46f,-0.75f, 0.46f,-0.75f, 0.84f,-0.285f);
    private static final Shape rightshape = new PolygonShape(0.8f,0.6f, 0.9f,0f, 0.84f,-0.277f, 0.46f,-0.277f);//?top, right side, bottom right, bottom left
    private static final BodyImage cloud = new BodyImage("data/Cloud.png", 1.5f);

    {
        System.out.println("Cloud.java: Building Cloud: Cloud object created");
    }

    public Cloud(World w) {
        super(w);
        SolidFixture leftfixture = new SolidFixture(this, leftshape, 10);
        SolidFixture bottomfixture = new SolidFixture(this, bottomshape, 10);
        SolidFixture rightfixture = new SolidFixture(this, rightshape, 10);

        leftfixture.setRestitution(1.2f);
        bottomfixture.setRestitution(1.2f);
        rightfixture.setRestitution(1.2f);

        this.setGravityScale(0);
        this.addImage(cloud);
    }
}
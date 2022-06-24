package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//One of the two bodys controlled (steplistener tracker keeps it geting stale) is Controlled by Launcher.java
//Boostflag activates upon collision with the PowerUp(star in Game.java) setting projectile density to halve
// or if allready active, to normal

//in contact is used when the projectile collides with the platform sensor(Launch.java) it determines if the
// mouse event handler (Launcher.java) can run the code that launches the projectile or not

//height and level will accumulate the score, the level increments by reaching the cloud object and touching it(Cloud.java, GoalReached.java),
// the level also changes the currently loaded in game level(different levels and level change not added yet)
public class Projectile extends DynamicBody {
    private SoundClip ToadChant;

    Shape bulletshape = new PolygonShape(
            -0.55f,0.5f, 0.1f,0.5f, 0.55f,0f, 0.1f,-0.5f, -0.55f,-0.5f);//top left, top middle  , front, bottom middle, bottom left
    Shape Toad = new PolygonShape(
            -0.084f,0.5f, -0.615f,-0.034f, -0.347f,-0.494f, 0.613f,-0.103f, 0.613f,0.227f);
    Shape block = new BoxShape(0.5f, 0.5f);
    BodyImage bulletImage = new BodyImage("data/BulletBill.png", 1.0f);
    BodyImage toadImage = new BodyImage("data/BulletToad.png", 1.0f);
    BodyImage blockImage = new BodyImage("data/GroundBlock.png", 1.0f);
    private static int blockID;
    private static boolean boostflag = false;
    private static boolean launchflag = false;
    private static float density = 1;
    private static boolean incontact;
    private static int launches;
    private static int level;
    private static SolidFixture fixture;

    public void SwapProjectile(){
        blockID++;
        if (blockID > 2){
            blockID = 0;
        }
        this.removeAllImages();
        this.fixture.destroy();
        System.out.println(getWorld());
        CreateProjectile(blockID);
        System.out.println("Projectile.java: Projectile was swaped");


    }

    public boolean isBoostflag(){
        return this.boostflag;
    }
    public void setBoostflag(boolean flag){
        this.boostflag = flag;
        if (this.boostflag){
            System.out.println("Projectile.java: You are felling lighter: density = 0.5");
            this.density = 0.5f;
            System.out.println();
        } else{
            System.out.println("Projectile.java: You are felling heavy again: density = 1");
            this.density = 1f;
            System.out.println();
        }
    }

    public boolean islaunchflag(){
        return this.launchflag;
    }
    public void setLaunchflag(boolean flag){
        this.launchflag = flag;
    }

    public float getDensity(){
        return density;
    }

    public boolean isIncontact(){
        return incontact;
    }
    public void setIncontact(boolean flag){
        this.incontact = flag;
    }

    public int getlaunches(){
        return this.launches;
    }
    public void setLaunches(){ this.launches++; }

    public int getlevel(){
        return this.level;
    }
    public void nextlevel(){
        this.level++;
    }

    public void reset(){
        this.boostflag = false;
        this.launchflag = false;
        this.incontact = false;
        this.launches = 0;
        this.level = 0;
    }

    public Projectile(World w) {
        super(w);
        CreateProjectile(blockID);
        System.out.println("Projectile.java: Building Projectile, blockID " + blockID + ": Projectile object created");
    }

    public Projectile(World w, int blockID) {
        super(w);
        CreateProjectile(blockID);
        System.out.println("Projectile.java: Building Projectile, blockID " + blockID + ": Projectile object created");
    }

    private void CreateProjectile(int blockID){
        if (blockID == 1) {
            fixture = new SolidFixture(this, bulletshape, 60f);
            fixture.setFriction(255f);
            fixture.setRestitution(0f);
            this.addImage(bulletImage);
        }
        else if (blockID == 2) {
            fixture = new SolidFixture(this, Toad, 30);
            fixture.setFriction(255f);
            fixture.setRestitution(0f);
            this.addImage(toadImage);

            try {//play sound
                ToadChant = new SoundClip("data/Im_the_best.wav");
                ToadChant.play();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
        else {
            fixture = new SolidFixture(this, block, 40);
            fixture.setFriction(255f);
            fixture.setRestitution(0f);
            this.addImage(blockImage);
        }
        System.out.println("fixture created");
    }
}

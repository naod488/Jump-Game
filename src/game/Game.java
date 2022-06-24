package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/* MILESTONE 1
 *
 *1. New Bodys Created 7:
 * Static: 4 - (DeathWalls.java, Cloud.java, PowerUp.java, Platform.java)
 *      (the platform in Platform.java moves depending on mouse position, controlled in ControlPlatform.java)
 * Dynamic: 3 - (Projectiles.java) - (Upon calling the object variable BlockID determines object created,
 *      there are 3 different projectiles that can be loaded depending on blockID)
 *
 *2. All created Bodys are mapped to Images and aligned to a degree where it doesn't disturb the fell of the game
 * note: A polygonshape for grinder seemed unnecessary as it serves as a boundary for levels and not a direct obstacle,
 *       may be due to change in later versions of the game
 *
 *3.Projectile: num of used attributes 6
 *
 *4.Controls: Mouse Movement, Left Mouse Press - (mouse controls are centered around the projectile)
 * The projectile is controllable (Launcher.java) - (projectiles gets launched into the direction of the mouse (distance affects strength))
 * Platforms are controllable (ControlPlatform) - (their orientation changes depending on mouse position)
 *
 *5.Platform: Has a Sensor with a Listener (Launch.java) - (reacting to projectile: affects projectile)
 *  DeathWalls: Has a CollisionListener (KillPlayer.java) - (reacting to projectile: affects projectile)
 *  PowerUp:Has a CollisionListener (Boost.java) - (reacting to projectile: affects powerUp and projectile)
 *  Cloud: Has a CollisionListener (GoalReached.Java) - (reacting to projectile: affects projectile and potentially all loaded objects)
 *  view: Has a MouseListener (Launcher) - (reacting to mouse press: affects projectile)
 *   and a MouseMotionListener - (reacting to mouse movement: affects platform)
 *
 */

/* MILESTONE 2
 *
 *1. Levels 4: (Gameworld(level 0), Level1, Level2 and Level3
 * Level 1 Features a change of difficulty as there are now obstacles
 * Level 2 Changes the behaviour of the cloud bject to consistently move around when pushed + Has a random placement of the platforms
 * Level 3 Changes the behaviour from the platforms from standing still to moving in an arc
 * All levels have a different background
 *
 * 2. Graphical UI elements 3: Levels conter, Launch counter and Launch strength indicator(made with images)
 * Level counter and  Launch counter are carried over to the next level aswell as the current player shape
 *
 * 3. Buttons 3:
 * Pause button: pauses/unpauses the game
 * Reset button: resets game to starting state( level 0, launches 0 only player shape is kept)
 * Swap button: Changes the shape of the player to one of 3 possible shapes
 * Added sfx for all buttons
 *
 * 4. Dynamic Objects 4: Projectile, PowerUp, Cloud and Bumper
 * Projectile has non-physics related events associated with all objects except Bumper
 * PowerUp and Cloud have special event upon contact with the player
 * the Cloud objects behaviour changes in level 2
 * Bumper has a special behaviour of not leaving its x-axis
 * PowerUp, Bumper, Deathwalls and Platform are all passed into ArrayList for their respective object types(only used ones are powerUps, bouncers and platforms)
 */

/**
 * A world with some bodies.
 */
public class Game {

    /** The World in which the bodies move and interact. */
    //private World world;
    private GameLevel world;

    /** A graphical display of the world (a specialised JPanel). */
    private UserView view;
    private DebugViewer debugView;
    private ButtonPanel buttons;

    private MouseListener launchercontroller;
    private MouseMotionListener platformcontroller;
    private StepListener stepcontroller;

    /** Initialise a new Game. */
    public Game() {
        // make the world
        world = new GameWorld();
        world.populate(this);

        // make a view
        view = new MyView(world, 500, 500, world.getProjectile());

        // display the view in a frame
        final JFrame frame = new JFrame("Jump Game");

        buttons = new ButtonPanel(this, view, world);
        frame.add(buttons, BorderLayout.SOUTH);

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        /*
         */
        // uncomment this to make a debugging view
        //debugView = new DebugViewer(world, 500, 500);
        // uncomment this to draw a 1-metre grid over the view
        //view.setGridResolution(1);
        /*
         */



        /*--------------------///START OF MY CODE///--------------------*/
        //Instance the controller for the projectile launching
        this.launchercontroller = new Launcher(world.getProjectile());
        view.addMouseListener(launchercontroller);
        System.out.println();

        //Synchronise platforms with the mouse
        this.platformcontroller = new ControlPlatform(world.getPlatforms());
        view.addMouseMotionListener(new ControlPlatform(world.getPlatforms()));

        //Track the projectile and manipulate all bodys how neccessary
        this.stepcontroller = new Tracker(view, world.getProjectile(), world.getPlatforms(), world.getDeathwalls(), world.getPowerUps(), world.getBumpers(), world.getCloud());
        world.addStepListener(stepcontroller);
        System.out.println("Tracker.java: Creating Tracker for world view");

        // start!
        world.start();
        System.out.println("Game.java: Starting world (world.start())");
        System.out.println();
        System.out.println("/////////////________________JUMP_GAME________________/////////////");
        System.out.println("Press the left mouse button to launch the projectile when it is on a platform,");
        System.out.println("You can change the platform orientation by moving the mouse around the projectile,");
        System.out.println("mouse distance = launch strength, the star makes you lighter and easier to launch.");
        System.out.println("Try to land the cube inside the cloud at the top from the top side (like in basketball)");
        System.out.println("and avoid touching the grinders. Have Fun with this simple Demo");
        System.out.println();
        System.out.println();
    }

    /** Advance to the next level of the game. */
    public void goNextLevel() {
        world.stop();
        if (world.getProjectile().getlevel() == 0) {
            // get a new world
            world = new GameWorld();
        }
        else if (world.getProjectile().getlevel() == 1) {
            // get a new world
            world = new Level1();
        }
        else if (world.getProjectile().getlevel() == 2) {
            // get a new world
            world = new Level2();
        }
        else if (world.getProjectile().getlevel() == 3) {
            // get a new world
            world = new Level3();
        }
        else{
            System.out.println("Game.java: This is all of the game thus far - END -");
            System.out.println("Game.java: sys.exit(0)");
            System.exit(0);
        }
        // fill it with bodies
        world.populate(this);
        // show the new world in the view
        view.setWorld(world);
        if (debugView != null) {
            debugView.setWorld((world));
        }

        //Set buttons to the world (only neccessary because of swapAction.java)
        buttons.setbuttonToWorld(world);

        //Instance the controller for the projectile launching
        view.removeMouseListener(launchercontroller);
        this.launchercontroller = new Launcher(world.getProjectile());
        view.addMouseListener(launchercontroller);
        System.out.println();

        //Synchronise platforms with the mouse
        view.removeMouseMotionListener(platformcontroller);
        this.platformcontroller = new ControlPlatform(world.getPlatforms());
        view.addMouseMotionListener(platformcontroller);

        //Track the projectile
        world.removeStepListener(stepcontroller);
        this.stepcontroller = new Tracker(view, world.getProjectile(), world.getPlatforms(), world.getDeathwalls(), world.getPowerUps(), world.getBumpers(), world.getCloud());
        world.addStepListener(stepcontroller);

        //Start new world
        world.start();
        if (world.getProjectile().getlevel() == 2){//Level 3 make the cloud have an Itial velocity
            world.getCloud().setLinearVelocity(new Vec2(10,10));
        }
        System.out.println("hello new world");
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}

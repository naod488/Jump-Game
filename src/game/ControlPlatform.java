package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

//Changes platform rotation depending on mouse position
public class ControlPlatform extends MouseAdapter {
    private float angle = 0;
    private float x;
    private float y;
    private List<Platform> platforms;
    public static int distance;

    {
        System.out.println("ControlPlatform.java: Initializing MouseMotionListener for view");
    }

    public ControlPlatform(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public void mouseMoved(MouseEvent e) {
        this.angle = GetAngle((e.getX() - 250) * (-1), (e.getY() - 250) * (-1));
        for (int i = 0; i < platforms.size(); i++) {
            platforms.get(i).setAngle(angle);
        }
    }

    public float GetAngle(float x, float y) {// and update the distance for MyView.java
        distance = (int) Math.hypot(x, y);
        if (distance > 250){
            distance = 250;
        }
        BigDecimal angled = new BigDecimal(Math.atan2(x, y));
        float anglef = angled.floatValue();
        return anglef;
    }
}
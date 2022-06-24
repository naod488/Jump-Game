package game;

import city.cs.engine.SoundClip;
import city.cs.engine.WorldView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonPanel extends JPanel implements ActionListener {
    private JButton pauseButton = new JButton("Pause");
    private JButton resetButton = new JButton("Reset");
    private JButton swapButton = new JButton("Swap");

    private Game game;
    private WorldView view;
    private GameLevel world;

    private SoundClip pauseSound;
    private SoundClip resetSound;

    public void setbuttonToWorld(GameLevel world){
        swapButton.removeActionListener(this);
        this.world = world;
        swapButton.addActionListener(this);
    }

    public ButtonPanel(Game game,WorldView view, GameLevel world){
        this.add(pauseButton);
        this.add(resetButton);
        this.add(swapButton);

        this.game = game;
        this.view = view;
        this.world = world;

        //this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setLayout(new GridLayout(1,3));

        pauseButton.addActionListener(this);
        resetButton.addActionListener(this);
        swapButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Pausebutton event
        if (e.getSource() == pauseButton){
            if (view.getWorld().isRunning()) {
                view.getWorld().stop();
                pauseButton.setText("Play ");
            } else {
                view.getWorld().start();
                pauseButton.setText("Pause");
            }

            try {//play sound
                pauseSound = new SoundClip("data/smb_pause.wav");
                pauseSound.play();
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
        //Resetbutton event
        else if (e.getSource() == resetButton){
            world.getProjectile().reset();
            game.goNextLevel();

            try {//play sound
                resetSound = new SoundClip("data/smb_bowserfire.wav");
                resetSound.play();
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
        //Swapbutton event
        else if (e.getSource() == swapButton){
            world.getProjectile().SwapProjectile();
        }
    }
}

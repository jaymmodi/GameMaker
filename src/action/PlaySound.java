package action;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.Utility;
import model.SpriteModel;

//Play Sound whenever needed
public class PlaySound implements Action{
	
	@Override
	public void performAction(SpriteModel sprite1, SpriteModel sprite2) {
		//Play Sound during Collision
		if ((sprite1.getRectangle(sprite1)).intersects(sprite2
				.getRectangle(sprite2))) {
			try {
				File soundFile = new File(getClass().getClassLoader().getResource("sounds/sound.wav").toURI());
				AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (Exception ex) {
			}
		}
		
		//Play Sound during Game Win
		if(Utility.getInstance().getGameFlag() == 3)
		{
			try {
				File soundFile = new File(getClass().getClassLoader().getResource("sounds/clap.wav").toURI());
				AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (Exception ex) {
			}
		}
	}

	@Override
	public void performAction(SpriteModel sprite) {
		
	}

}

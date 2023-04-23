package Object;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	public Clip clip;
	public URL soundURL[] = new URL[30];
	public FloatControl fc;
	//full volume is 6
	public int volumeScale = 3;
	public float volume;
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sounds/pacman_beginning.wav");
		soundURL[1] = getClass().getResource("/sounds/pacman_chomp.wav");
		soundURL[2] = getClass().getResource("/sounds/pacman_death.wav");
		soundURL[3] = getClass().getResource("/sounds/pacman_death.wav");

	}
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	public void checkVolume() {
		switch (volumeScale) {
		case 0: volume = -80f; break;
		case 1:volume = -20f; break;
		case 2:volume = -12f; break;
		case 3:volume = -5f; break;
		case 4:volume = 1f; break;
		case 5:volume = 6f; break;

		}
		fc.setValue(volume);
	}
}

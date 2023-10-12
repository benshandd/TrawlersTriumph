package nz.ac.wgtn.swen225.lc.renderer;

import java.util.HashMap;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Audio unit class is responsible for playing audio clips
 *
 * @author Alex Manning (300600549)
 */
public class AudioUnit {
	public enum AudioClip {
		BACKGROUND, AMBIENCE, SEAGULL1, SEAGULL2, SEAGULL3, FISH_CAUGHT, ENEMY_ATTACK, DOOROPEN, KEYCOLLECT, FISH1,
		FISH2, FISH3
	}

	private final HashMap<AudioClip, Clip> audioClips = new HashMap<>();

	public AudioUnit() {
		loadClips();

	}

	/**
	 * Loads in audio clips into audioClips map
	 */
	private void loadClips() {
		addClip(AudioClip.BACKGROUND, "bg.wav", -5f);
		addClip(AudioClip.AMBIENCE, "ambience.wav", -10f);
		addClip(AudioClip.SEAGULL1, "seagull1.wav", -5f);
		addClip(AudioClip.SEAGULL2, "seagull2.wav", -5f);
		addClip(AudioClip.SEAGULL3, "seagull3.wav", -5f);
		addClip(AudioClip.SEAGULL3, "seagull3.wav", -5f);
		addClip(AudioClip.DOOROPEN, "DoorOpen.wav", -5f);
		addClip(AudioClip.KEYCOLLECT, "KeyCollect.wav", -5f);
		addClip(AudioClip.FISH1, "Fish1.wav", -5f);
		addClip(AudioClip.FISH2, "Fish2.wav", -5f);
		addClip(AudioClip.FISH3, "Fish3.wav", -5f);
	}

	/**
	 * Adds an audio clip to the collection with the specified identifier, file
	 * path, and volume level.
	 *
	 * @param id     The unique identifier for the audio clip.
	 * @param file   The relative file path of the audio clip.
	 * @param volume The volume level for the audio clip.
	 */
	private void addClip(AudioClip id, String file, float volume) {
		String path = "LarryCroftsAdventures/assets/audioFiles/";
		// Ensure that the provided AudioClip enum is valid
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(path + file)));
			FloatControl backgroundVolumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			backgroundVolumeControl.setValue(volume);
			audioClips.put(id, clip);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Starts background music (looping)
	 */
	public void startBackgroundMusic() {
		Clip bg = audioClips.get(AudioClip.BACKGROUND);
		bg.loop(10);
	}

	/**
	 * Starts ambience (looping)
	 */
	public void startAmbience() {
		Clip ambience = audioClips.get(AudioClip.AMBIENCE);
		ambience.loop(10);
	}

	/**
	 * Plays one of three possible seagull SFXs
	 */
	public void playSeagullSFX() {
		// Generate a random number between 1 and 3
		int randomNumber = (int) (Math.random() * 3 + 1);
		AudioClip seagull = switch (randomNumber) {
		case 1 -> AudioClip.SEAGULL1;
		case 2 -> AudioClip.SEAGULL2;
		case 3 -> AudioClip.SEAGULL3;
		default -> throw new Error();
		};
		audioClips.get(seagull).setFramePosition(0); // reset frame position if sound has previously been played
		audioClips.get(seagull).start();
	}

	/**
	 * Plays one of three possible fish SFXs
	 */
	public void playFishSFX() {
		// Generate a random number between 1 and 3
		int randomNumber = (int) (Math.random() * 3 + 1);
		AudioClip fish = switch (randomNumber) {
		case 1 -> AudioClip.FISH1;
		case 2 -> AudioClip.FISH2;
		case 3 -> AudioClip.FISH3;
		default -> throw new Error();
		};
		audioClips.get(fish).setFramePosition(0); // reset frame position if sound has previously been played
		audioClips.get(fish).start();
	}

	/**
	 * Plays the key pickup SFX
	 */
	public void playSound(AudioClip ac) {
		audioClips.get(ac).setFramePosition(0); // reset frame position if sound has previously been played
		audioClips.get(ac).start();
	}

	/**
	 * Stops all audio
	 */
	public void stopAll() {
		for (Clip clip : audioClips.values()) {
			clip.stop();
		}
	}
}

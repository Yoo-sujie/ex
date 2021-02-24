package chat.sound;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class ChatSound extends JFrame implements ActionListener {
	
	public static void Audio(String fName){

		new Thread() {
			public void run() {
				Clip clip=null;
				try {
					clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fName));
					clip.open(inputStream);
					clip.start();


				} catch (Exception e) {
					System.err.println(e.getMessage());
				}

				while (true){
					try{
						Thread.sleep(10);
					}catch(InterruptedException ie2){
					}
					if (clip!=null && !clip.isRunning()){
						clip.close();
						break;
					}


				}

			}
		}.start();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

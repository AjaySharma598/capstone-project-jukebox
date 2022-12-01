/*
 * Author:Ajay Sharma
 * Date : 30-11-2022
 *Created With : Intellij IDEA Community Edition
 */

package com.niit.jdp.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayerService {
    Clip clip;
    AudioInputStream audioInputStream;

    public void play(String songPath) {                                  //1. path of the song file to be played

        Scanner scanner = new Scanner(System.in);                           //2. create a File object to represent the song file

        File songFile = new File(songPath);                               //3. create an object of AudioInputStream class

        try {
            audioInputStream = AudioSystem.getAudioInputStream(songFile);

            clip = AudioSystem.getClip();                                //4. get a clip object from the AudioSystem

            clip.open(audioInputStream);                                  //5. open the clip and load the audio input stream

            clip.loop(Clip.LOOP_CONTINUOUSLY);                             //6. set a loop for the sound file
            System.out.println("enter  1 to start 2 to stop");

            int choice = 0;

            do {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1: {
                        clip.start();                      //7. start the clip
                        break;
                    }
                    case 2: {
                        clip.stop();                        //8. stop the clip
                        break;
                    }
                }
            } while (choice > 0);

            long songLength = clip.getMicrosecondLength() / 1000L;   //8. get the exact length of the song

            Thread.sleep(songLength);                        //9. Pause the current thread for the time the song is playing
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            exception.printStackTrace();
        } catch (InterruptedException exception) {
            System.err.println("The song was interrupted in between");
        }
    }
}

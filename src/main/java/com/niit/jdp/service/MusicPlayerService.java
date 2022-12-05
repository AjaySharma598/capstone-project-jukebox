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

    public void play(String songPath) {
        //1. path of the song file to be played
        Scanner scanner = new Scanner(System.in);
        //2. create a File object to represent the song file
        File songFile = new File(songPath);
        //3. create an object of AudioInputStream class
        try {
            audioInputStream = AudioSystem.getAudioInputStream(songFile);

            //4. get a clip object from the AudioSystem
            clip = AudioSystem.getClip();

            //5. open the clip and load the audio input stream
            clip.open(audioInputStream);

            //6. set a loop for the sound file
            System.out.println("****************************************************************");
            System.out.println("1. Play the Song\n2. Stop the Song\n0. Exit");
            System.out.println("****************************************************************");

            int choice = 0;
            //7. start the clip
            do {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: {
                        clip.start();
                        break;
                    }
                    case 2: {
                        clip.stop();
                        break;
                    }
                    case 0: {
                        clip.stop();
                        clip.close();
                        break;
                    }
                    default: {
                        clip.stop();
                        System.err.println("Invalid! ");
                        break;
                    }
                }
            } while (choice > 0);
            //8. get the exact length of the song
            //long songLength = clip.getMicrosecondLength() / 1000L;

            //9. Pause the current thread for the time the song is playing
            // Thread.sleep(songLength);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            exception.printStackTrace();
        }
    }

}

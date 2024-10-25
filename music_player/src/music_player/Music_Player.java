package music_player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Music_Player {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        Scanner scanner = new Scanner(System.in);
        
        // Specify the actual path to your .wav file
        File file = new File("C:\\Users\\MUGILAN\\Downloads\\bgm.tamil.wav");

        if (!file.exists()) {
            System.out.println("Audio file not found.");
            return;
        }
        
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        
        String response = "";
        
        while (!response.equals("Q")) {
            System.out.println("P = Play, S = Stop, R = Reset, Q = Quit");
            System.out.print("Enter your choice: ");
            
            response = scanner.next().toUpperCase();
            
            switch (response) {
                case ("P"): 
                    clip.start();
                    break;
                case ("S"): 
                    clip.stop();
                    break;
                case ("R"): 
                    clip.setMicrosecondPosition(0);
                    break;
                case ("Q"): 
                    clip.close();
                    break;
                default: 
                    System.out.println("Not a valid response");
            }
        }
        
        scanner.close();
        System.out.println("Byeeee!");
    }
}

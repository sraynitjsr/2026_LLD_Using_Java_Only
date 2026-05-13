// Adapter Pattern: Allows incompatible interfaces to work together
// It acts as a bridge between two incompatible interfaces

// Target Interface - What the client expects
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee - The incompatible interface that needs to be adapted
interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Concrete Adaptee implementations
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }
    
    @Override
    public void playMp4(String fileName) {
        // Do nothing
    }
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // Do nothing
    }
    
    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// Adapter - Makes AdvancedMediaPlayer compatible with MediaPlayer
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer = new Mp4Player();
        }
    }
    
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}

// Concrete implementation using the adapter
class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;
    
    @Override
    public void play(String audioType, String fileName) {
        // Built-in support for mp3
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        }
        // Use adapter for other formats
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type: " + audioType + " format not supported");
        }
    }
}

public class MyAdapter {
    public static void Start() {
        System.out.println("\n=== Adapter Pattern Demo ===");
        System.out.println("Purpose: Make incompatible interfaces work together\n");
        
        AudioPlayer audioPlayer = new AudioPlayer();
        
        // Play different audio formats
        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("mp4", "video.mp4");
        audioPlayer.play("vlc", "movie.vlc");
        audioPlayer.play("avi", "film.avi");
        
        System.out.println("\nKey Benefits:");
        System.out.println("- Allows existing classes to work with others without modifying their source code");
        System.out.println("- Promotes code reusability");
        System.out.println("- Follows Open/Closed Principle");
    }
}

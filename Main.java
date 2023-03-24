import audio.Audio;

public class Main {
    public static void main(String[] args) {
        Audio audio = new Audio(" CDEFGAB");
        audio.makeMusic();
        audio.saveMidiFile();
    }
}
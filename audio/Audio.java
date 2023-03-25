package audio;

import notes.Parser;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.io.File;
import java.io.IOException;

public class Audio extends Parser {

    private Pattern pattern;

    public Audio(String input) {
        super(input);
        super.processInput();
        this.pattern = new Pattern();
        Pattern pattern = new Pattern();
        for (int i = 0; i < super.getNotes().size(); i++){
            Pattern pattern2 = new Pattern(":CON(7, " + super.getNotes().get(i).getVolume() + ") " + super.getNotes().get(i).getKey());
            pattern2.setInstrument(super.getNotes().get(i).getInstrument());
            pattern.add(pattern2);
        }
        this.pattern = pattern;
    }

    public void playMusic(){
        Player player = new Player();
        player.play(this.pattern);
    }

    public void saveMidiFile(){
        try{
            File filePath = new File("music.mid");
            MidiFileManager.savePatternToMidi(this.pattern, filePath);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}

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
        this.pattern = new Pattern();
    }

    public void makeMusic(){
        super.processInput();
        Player player = new Player();
        Pattern pattern = new Pattern();
        for (int i = 0; i < super.getNotes().size(); i++){
            Pattern pattern2 = new Pattern(":CON(7, " + super.getNotes().get(i).getVolume() + ") " + super.getNotes().get(i).getKey());
            pattern2.setInstrument(super.getNotes().get(i).getInstrument());
            pattern.add(pattern2);
        }
        player.play(pattern);
        this.pattern = pattern;
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

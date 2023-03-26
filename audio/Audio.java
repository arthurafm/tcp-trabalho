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
        /* Inicializa o Parser */
        super(input);
        /* Transforma o input em um array de Note's */
        super.processInput();
        Pattern pattern = new Pattern();
        /* Adiciona as Note's ao Pattern */
        for (int i = 0; i < super.getNotes().size(); i++){
            Pattern new_pattern = new Pattern(":CON(7, " + super.getNotes().get(i).getVolume() + ") " + super.getNotes().get(i).getKey());
            new_pattern.setInstrument(super.getNotes().get(i).getInstrument());
            pattern.add(new_pattern);
        }
        /* Armazena a Pattern gerada */
        this.pattern = pattern;
    }

    /* Toca a pattern gerada */
    public void playMusic() {
        Player player = new Player();
        player.play(this.pattern);
    }

    /* Salva um arquivo MIDI da Pattern */
    public void saveMidiFile() {
        try {
            File filePath = new File("music.mid");
            MidiFileManager.savePatternToMidi(this.pattern, filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

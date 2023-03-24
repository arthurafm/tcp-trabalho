package audio;

import notes.Parser;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Audio extends Parser {


    public Audio(String input) {
        super(input);
    }

    public void makeMusic(){
        super.processInput();
        Player player = new Player();
        Pattern pattern = new Pattern();
        for (int i = 0; i < super.getNotes().size(); i++){
            Pattern pattern2 = new Pattern(":CON(7, " + super.getNotes().get(i).getVolume() + ") " + super.getNotes().get(i).getKey());
            pattern2.setInstrument(super.getNotes().get(i).getInstrument());
            pattern.add(pattern2);
            System.out.printf("V: %d, K: %d, I: %d\n", super.getNotes().get(i).getVolume(), super.getNotes().get(i).getKey(), super.getNotes().get(i).getInstrument());
        }
        player.play(pattern);
    }
}

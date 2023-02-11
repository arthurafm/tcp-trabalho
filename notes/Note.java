package notes;

public class Note {
    private int key;
    private int instrument;
    private int volume;
    private int bpm;

    /* Construtor com todos os parâmetros definidos */
    Note(int key, int instrument, int volume, int bpm) {
        this.key = key;
        this.instrument = instrument;
        this.volume = volume;
        this.bpm = bpm;
    }

    /* Construtor vazio para ser utilizado como abstração de entidades relacionadas a pausa */
    Note() {
        this.key = -1;
        this.instrument = -1;
        this.volume = -1;
        this.bpm = -1;
    }

    /* Construtor que copia uma Note */
    Note(Note og) {
        this.key = og.getKey();
        this.instrument = og.getInstrument();
        this.volume = og.getVolume();
        this.bpm = og.getBpm();
    }

    /* Getter e Setters */

    public int getKey() {
        return this.key;
    }

    public int getInstrument() {
        return this.instrument;
    }

    public int getVolume() {
        return this.volume;
    }

    public int getBpm() {
        return this.bpm;
    }

}

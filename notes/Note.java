package notes;

public class Note {
    private int key;
    private int instrument;
    private int volume;

    /* Construtor com todos os parâmetros definidos */
    public Note(int key, int instrument, int volume) {
        this.key = key;
        this.instrument = instrument;
        this.volume = volume;
    }

    /* Construtor vazio para ser utilizado como abstração de entidades relacionadas a pausa */
    public Note() {
        this.key = 0;
        this.instrument = 0;
        this.volume = 0;
    }

    /* Construtor que copia uma Note */
    Note(Note og) {
        this.key = og.getKey();
        this.instrument = og.getInstrument();
        this.volume = og.getVolume();
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


}
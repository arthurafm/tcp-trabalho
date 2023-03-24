package notes;

import java.util.ArrayList;

public class Parser {

    /* Atributos de entrada e saída do Parse */
    private String input;
    private ArrayList<Note> notes;

    /* Constantes utilizadas no processamento de entrada */
    private static final int VOLUMEDEFAULT = 30;
    private static final int VOLUMEMAX = 127;
    private static final int INSTRUMENTMAX = 128;
    private static final int INSTRUMENTMIN = 0;
    private static final int HARPSICHORD = 6;
    private static final int TUBULAR_BELLS = 14;
    private static final int CHURCH_ORGAN = 19;
    private static final int PAN_FLUTE = 75;
    private static final int AGOGO = 113;
    private static final int KEY_C = 0;
    private static final int KEY_D = 2;
    private static final int KEY_E = 4;
    private static final int KEY_F = 5;
    private static final int KEY_G = 7;
    private static final int KEY_A = 9;
    private static final int KEY_B = 11;
    private static final int OCTAVEJMP = 12;
    private static final int OCTAVEDEFAULT = 4;
    private static final int OCTAVEMAX = 9;

    /* Atributos temporários para geração da saída */
    private int octave;
    private int instrument;
    private int volume;

    /* Construtor com entrada */
    public Parser(String input) {
        this.input = input;
        this.notes = new ArrayList<>();
        this.octave = OCTAVEDEFAULT;
        this.instrument = INSTRUMENTMIN;
        this.volume = VOLUMEDEFAULT;
    }

    /* Processamento da entrada */
    public void processInput() {
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                /* Nota lá */
                case 'A': {
                    Note newNote = new Note(KEY_A + (octave * OCTAVEJMP), instrument, volume);
                    notes.add(newNote);
                    break;
                }
                /* Nota Si */
                case 'B': {
                    Note newNote = new Note(KEY_B + (octave * OCTAVEJMP), instrument, volume);
                    notes.add(newNote);
                    break;
                }
                /* Nota Dó */
                case 'C': {
                    Note newNote = new Note(KEY_C + (octave * OCTAVEJMP), instrument, volume);
                    notes.add(newNote);
                    break;
                }
                /* Nota Ré */
                case 'D': {
                    Note newNote = new Note(KEY_D + (octave * OCTAVEJMP), instrument, volume);
                    notes.add(newNote);
                    break;
                }
                /* Nota Mi */
                case 'E': {
                    Note newNote = new Note(KEY_E + (octave * OCTAVEJMP), instrument, volume);
                    notes.add(newNote);
                    break;
                }
                /* Nota Fá */
                case 'F': {
                    Note newNote = new Note(KEY_F + (octave * OCTAVEJMP), instrument, volume);
                    notes.add(newNote);
                    break;
                }
                /* Nota Sol */
                case 'G': {
                    Note newNote = new Note(KEY_G + (octave * OCTAVEJMP), instrument, volume);
                    notes.add(newNote);
                    break;
                }
                /* Aumenta o volume para o DOBRO do valor atual, caso haja overflow, volta ao default */
                case ' ': {
                    if ((2 * volume) <= VOLUMEMAX) {
                        volume *= 2;
                    } else {
                        volume = VOLUMEDEFAULT;
                    }
                }
                    break;
                /* Troca o instrumento para o AGOGO */
                case '!': {
                    this.instrument = AGOGO;
                    break;
                }
                /* Troca o instrumento para o HARPSICHORD */
                case 'o':
                case 'O':
                case 'i':
                case 'I':
                case 'u':
                case 'U': {
                    this.instrument = HARPSICHORD;
                    break;
                }
                /* Soma o valor encontrado ao instrumento, limitado por constante */
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    if (this.instrument < INSTRUMENTMAX) {
                        this.instrument += Integer.parseInt(String.valueOf(input.charAt(i)));
                    } else {
                        this.instrument = INSTRUMENTMAX;
                    }
                    break;
                }
                /* Aumenta uma oitava, caso haja, overflow, volta ao default */
                case '?':
                case '.': {
                    if (octave + 1 <= OCTAVEMAX) {
                        octave++;
                    } else {
                        octave = OCTAVEDEFAULT;
                    }
                    break;
                }
                /* Troca o instrumento para TUBULAR BELLS */
                case '\n': {
                    this.instrument = TUBULAR_BELLS;
                    break;
                }
                /* Troca o instrumento para PAN FLUTE */
                case ';': {
                    this.instrument = PAN_FLUTE;
                    break;
                }
                /* Troca o instrumento para CHURCH ORGAN */
                case ',': {
                    this.instrument = CHURCH_ORGAN;
                    break;
                }
                /* Se caractere anterior era nota, repete nota, caso contrário, pausa */
                default: {
                    if (i > 0) {
                        if (isNote(i - 1)) {
                            Note newNote = new Note(notes.get(notes.size() - 1));
                            notes.add(newNote);
                        } else {
                            Note newNote = new Note();
                            notes.add(newNote);
                            break;
                        }
                    } else {
                        Note newNote = new Note();
                        notes.add(newNote);
                        break;
                    }
                    break;
                }
            }
        }
    }

    /* Função que testa se o caractere passado no input era uma nota (A-G) */
    private boolean isNote(int index) {
        boolean returnValue;
        switch (input.charAt(index)) {
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G': {
                returnValue = true;
                break;
            }
            default: {
                returnValue = false;
                break;
            }
        }
        return returnValue;
    }
    public ArrayList<Note> getNotes(){
        return this.notes;
    }
}

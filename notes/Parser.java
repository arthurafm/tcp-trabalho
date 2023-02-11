package notes;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Parser {

    /* Atributos de entrada e saída do Parse */
    private String input;
    private ArrayList<Note> notes;

    /* Constantes utilizadas no processamento de entrada */
    private static final int VOLUMEDEFAULT = 10;
    private static final int BPMINCREASE = 80;
    private static final int BPMMAX = 220;
    private static final int BPMMIN = 40;
    private static final int BPMDEFAULT = 60;
    private static final int INSTRUMENTMAX = 128;
    private static final int INSTRUMENTMIN = 0;
    private static final int VOLUMEMAX = 127;
    private static final int KEY_C = 0;
    private static final int KEY_D = 2;
    private static final int KEY_E = 4;
    private static final int KEY_F = 5;
    private static final int KEY_G = 7;
    private static final int KEY_A = 9;
    private static final int KEY_B = 11;
    private static final int NUMNOTES = 7;
    private static final int OCTAVEJMP = 12;
    private static final int OCTAVEMIN = 0;
    private static final int OCTAVEMAX = 9;
    private static final int TELEPHONE = 124;

    /* Atributos temporários para geração da saída */
    private int octave;
    private int instrument;
    private int volume;
    private int bpm;

    /* Construtor com entrada */
    Parser(String input) {
        this.input = input;
        this.notes = new ArrayList<Note>();
        this.octave = 4;
        this.instrument = 0;
        this.volume = VOLUMEDEFAULT;
        this.bpm = BPMDEFAULT;
    }

    /* Processamento da entrada */
    public void processInput () {
        for (int i = 0; i < input.length(); i++) {
            switch(input.charAt(i)) {
                /* Nota lá */
                case 'a':
                case 'A': {
                    Note newNote = new Note(KEY_A + (octave * OCTAVEJMP), instrument, volume, bpm);
                    notes.add(newNote);
                    break;
                }
                /* Nota Si */
                case 'b':
                case 'B': {
                    /* Aumenta BPM em 80 unidades, limitado por constantes */
                    if (input.length () - 3 > i) {
                        if (input.charAt(i + 1) == 'P') {
                            if (input.charAt(i + 2) == 'M') {
                                if (input.charAt(i + 3) == '+') {
                                    if (bpm + BPMINCREASE <= BPMMAX) {
                                        bpm += BPMINCREASE;
                                    }
                                    else {
                                        bpm = BPMMAX;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    Note newNote = new Note(KEY_B + (octave * OCTAVEJMP), instrument, volume, bpm);
                    notes.add(newNote);
                    break;
                }
                /* Nota Dó */
                case 'c':
                case 'C': {
                    Note newNote = new Note(KEY_C + (octave * OCTAVEJMP), instrument, volume, bpm);
                    notes.add(newNote);
                    break;
                }
                /* Nota Ré */
                case 'd':
                case 'D': {
                    Note newNote = new Note(KEY_D + (octave * OCTAVEJMP), instrument, volume, bpm);
                    notes.add(newNote);
                    break;
                }
                /* Nota Mi */
                case 'e':
                case 'E': {
                    Note newNote = new Note(KEY_E + (octave * OCTAVEJMP), instrument, volume, bpm);
                    notes.add(newNote);
                    break;
                }
                /* Nota Fá */
                case 'f':
                case 'F': {
                    Note newNote = new Note(KEY_F + (octave * OCTAVEJMP), instrument, volume, bpm);
                    notes.add(newNote);
                    break;
                }
                /* Nota Sol */
                case 'g':
                case 'G': {
                    Note newNote = new Note(KEY_G + (octave * OCTAVEJMP), instrument, volume, bpm);
                    notes.add(newNote);
                    break;
                }
                /* Silêncio ou pausa */
                case ' ': {
                    Note newNote = new Note();
                    notes.add(newNote);
                    break;
                }
                /* Aumenta o volume para o DOBRO do valor atual, limitado por constantes */
                case '+': {
                    if((2 * volume) <= VOLUMEMAX) {
                        volume *= 2;
                    }
                    else {
                        volume = VOLUMEMAX;
                    }
                }
                /* Volume volta a ser o volume original */
                case '-': {
                    volume = VOLUMEDEFAULT;
                }
                /* Se caractere anterior era nota, repete nota, caso contrário, toca telefone */
                case 'o':
                case 'O':
                case 'i':
                case 'I':
                case 'u':
                case 'U': {
                    if (i > 0) {
                        if (isNote(i - 1)) {
                            Note newNote = new Note(notes.get(notes.size() - 1));
                            notes.add(newNote);
                        }
                        else {
                            Note newNote = new Note(notes.get(notes.size() - 1).getKey(), TELEPHONE, volume, bpm);
                            notes.add(newNote);
                        }
                    }
                    else {
                        Note newNote = new Note(KEY_A + (octave * OCTAVEJMP), TELEPHONE, volume, bpm);
                        notes.add(newNote);
                    }
                    break;
                }
                /* Modifica a oitava */
                case 'R': {
                    if (input.length() - 1 > i) {
                        /* Aumenta */
                        if (input.charAt(i + 1) == '+') {
                            if (octave + 1 <= OCTAVEMAX) {
                                octave++;
                            } else {
                                octave = OCTAVEMAX;
                            }
                            i++;
                            /* Diminui */
                        } else if (input.charAt(i + 1) == '-') {
                            if (octave - 1 >= OCTAVEMIN) {
                                octave--;
                            } else {
                                octave = OCTAVEMIN;
                            }
                            i++;
                        }
                    }
                    break;
                }
                /* Toca uma nota aleatória */
                case '?': {
                    Note newNote = new Note(randomKey() + (octave * OCTAVEJMP), instrument, volume, bpm);
                    notes.add(newNote);
                    break;
                }
                /* Troca instrumento */
                case '\n': {
                    randomInstrument();
                    break;
                }
                /* Atribui valor aleatório ao BPM */
                case ';': {
                    randomBPM();
                    break;
                }
                /* Não realiza nenhuma operação */
                default:
                    break;
            }
        }
    }


    /* Função que testa se o caractere passado no input era uma nota (A-G) */
    private boolean isNote (int index) {
        boolean returnValue;
        switch(input.charAt(index)) {
            case 'a':
            case 'A':
            case 'b':
            case 'B':
            case 'c':
            case 'C':
            case 'd':
            case 'D':
            case 'e':
            case 'E':
            case 'f':
            case 'F':
            case 'g':
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

    /* Função que gera uma nota aleatória */
    private int randomKey () {
        int randomNum = ThreadLocalRandom.current().nextInt(0, NUMNOTES);
        int returnValue = 0;
        switch(randomNum) {
            case 0: {
                returnValue = KEY_C;
            }
            case 1: {
                returnValue = KEY_D;
            }
            case 2: {
                returnValue = KEY_E;
            }
            case 3: {
                returnValue = KEY_F;
            }
            case 4: {
                returnValue = KEY_G;
            }
            case 5: {
                returnValue = KEY_A;
            }
            case 6: {
                returnValue = KEY_B;
            }
        }
        return returnValue;
    }

    /* Função que troca o instrumento para um valor aleatório */
    private void randomInstrument(){
        int randomNum = ThreadLocalRandom.current().nextInt(INSTRUMENTMIN, INSTRUMENTMAX);
        this.instrument = randomNum;
    }

    /* Função que gera um BPM aleatório */
    private void randomBPM(){
        int randomNum = ThreadLocalRandom.current().nextInt(BPMMIN, BPMMAX + 1);
        this.bpm = randomNum;
    }
}


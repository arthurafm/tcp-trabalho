package notes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ParserTest {
    private static final int VOLUMEDEFAULT = 30;
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

    @Test
    void processInputTest() {
        /* Criação do output esperado */
        Note nA = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, INSTRUMENTMIN, VOLUMEDEFAULT);
        Note nB = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_B, INSTRUMENTMIN, VOLUMEDEFAULT);
        Note nC = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_C, INSTRUMENTMIN, VOLUMEDEFAULT);
        Note nD = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_D, INSTRUMENTMIN, VOLUMEDEFAULT);
        Note nE = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_E, INSTRUMENTMIN, VOLUMEDEFAULT);
        Note nF = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_F, INSTRUMENTMIN, VOLUMEDEFAULT);
        Note nG = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_G, INSTRUMENTMIN, VOLUMEDEFAULT);
        Note nPause = new Note();
        Note nA_2vol = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, INSTRUMENTMIN, 2*VOLUMEDEFAULT);
        Note nA_4vol = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, INSTRUMENTMIN, 4*VOLUMEDEFAULT);
        Note nA_agogo = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, AGOGO, VOLUMEDEFAULT);
        Note nB_agogo = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_B, AGOGO, VOLUMEDEFAULT);
        Note nC_agogo = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_C, AGOGO, VOLUMEDEFAULT);
        Note nA_harpischord = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, HARPSICHORD, VOLUMEDEFAULT);
        Note nA_harpischordAddOne = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, HARPSICHORD + 1, VOLUMEDEFAULT);
        Note nA_harpischordAddThree = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, HARPSICHORD + 3, VOLUMEDEFAULT);
        Note nA_agogo_2oct = new Note(((OCTAVEDEFAULT + 1) * OCTAVEJMP) + KEY_A, AGOGO, VOLUMEDEFAULT);
        Note nA_agogo_3oct = new Note(((OCTAVEDEFAULT + 2) * OCTAVEJMP) + KEY_A, AGOGO, VOLUMEDEFAULT);
        Note nA_agogo_4oct = new Note(((OCTAVEDEFAULT + 3) * OCTAVEJMP) + KEY_A, AGOGO, VOLUMEDEFAULT);
        Note nA_agogo_5oct = new Note(((OCTAVEDEFAULT + 4) * OCTAVEJMP) + KEY_A, AGOGO, VOLUMEDEFAULT);
        Note nA_agogo_6oct = new Note(((OCTAVEDEFAULT + 5) * OCTAVEJMP) + KEY_A, AGOGO, VOLUMEDEFAULT);
        Note nA_tubularbells = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, TUBULAR_BELLS, VOLUMEDEFAULT);
        Note nA_panflute = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, PAN_FLUTE, VOLUMEDEFAULT);
        Note nA_churchorgan = new Note((OCTAVEDEFAULT * OCTAVEJMP) + KEY_A, CHURCH_ORGAN, VOLUMEDEFAULT);

        ArrayList<Note> arrayExpected = new ArrayList<Note>();
        arrayExpected.add(nA);
        arrayExpected.add(nB);
        arrayExpected.add(nC);
        arrayExpected.add(nD);
        arrayExpected.add(nE);
        arrayExpected.add(nF);
        arrayExpected.add(nG);
        arrayExpected.add(nG);
        arrayExpected.add(nPause);
        arrayExpected.add(nA);
        arrayExpected.add(nA);
        arrayExpected.add(nPause);
        arrayExpected.add(nB);
        arrayExpected.add(nB);
        arrayExpected.add(nPause);
        arrayExpected.add(nC);
        arrayExpected.add(nC);
        arrayExpected.add(nPause);
        arrayExpected.add(nD);
        arrayExpected.add(nD);
        arrayExpected.add(nPause);
        arrayExpected.add(nE);
        arrayExpected.add(nE);
        arrayExpected.add(nPause);
        arrayExpected.add(nF);
        arrayExpected.add(nF);
        arrayExpected.add(nPause);
        arrayExpected.add(nA_2vol);
        arrayExpected.add(nA_4vol);
        arrayExpected.add(nA);
        arrayExpected.add(nA_agogo);
        arrayExpected.add(nB_agogo);
        arrayExpected.add(nC_agogo);
        arrayExpected.add(nA_harpischord);
        arrayExpected.add(nA_agogo);
        arrayExpected.add(nA_harpischord);
        arrayExpected.add(nA_agogo);
        arrayExpected.add(nA_harpischord);
        arrayExpected.add(nA_harpischord);
        arrayExpected.add(nPause);
        arrayExpected.add(nA_harpischordAddOne);
        arrayExpected.add(nA_harpischordAddThree);
        arrayExpected.add(nA_agogo_2oct);
        arrayExpected.add(nA_agogo_3oct);
        arrayExpected.add(nA_agogo_4oct);
        arrayExpected.add(nA_agogo_5oct);
        arrayExpected.add(nA_agogo_6oct);
        arrayExpected.add(nA_agogo);
        arrayExpected.add(nA_tubularbells);
        arrayExpected.add(nA_panflute);
        arrayExpected.add(nA_churchorgan);
        arrayExpected.add(nA_churchorgan);
        arrayExpected.add(nPause);

        /* Teste de todos os eventos propostos */
        String s = new String("ABCDEFGaaAbbBccCddDeeEffFgg A A A!ABCOA!AUA!AIAYY1A2A!.A?A.A?A.A?A\nA;A,A[[");
        Parser p = new Parser(s);
        p.processInput();
        ArrayList<Note> arrayOutput = p.getNotes();
        assertEquals(arrayOutput.size(), arrayExpected.size());
        for (int i = 0; i < arrayExpected.size(); i++) {
            int lambdaI = i;
            assertAll(
                    () -> assertEquals(arrayExpected.get(lambdaI).getKey(), arrayOutput.get(lambdaI).getKey()),
                    () -> assertEquals(arrayExpected.get(lambdaI).getInstrument(), arrayOutput.get(lambdaI).getInstrument()),
                    () -> assertEquals(arrayExpected.get(lambdaI).getVolume(), arrayOutput.get(lambdaI).getVolume())
            );
        }
    }
}
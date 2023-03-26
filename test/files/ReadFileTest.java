package files;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {
    @Test
    void readTest() {
        /* Testa a leitura de arquivo */
        ReadFile rf = new ReadFile();
        assertEquals(rf.read(), "CGEGCGEGDGFGCGEGCAFACGEGBGDGCGEG");
    }
}
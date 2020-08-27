import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class Oblig1Test {
    int[] a = {};
    int[][] aa = {
            {1,2,3,4}, {1,2,4,3}, {1,3,2,4}, {1,3,4,2}, {1,4,2,3}, {1,4,3,2},
            {2,1,3,4}, {2,1,4,3}, {2,3,1,4}, {2,3,4,1}, {2,4,1,3}, {2,3,4,1},
            {3,1,2,4}, {3,1,4,2}, {3,2,1,4}, {3,2,4,1}, {3,4,1,2}, {3,4,2,1},
            {4,1,2,3}, {4,1,3,2}, {4,2,1,3}, {4,2,3,1}, {4,3,1,2}, {4,3,2,1}
    };
    int[] b = {22, 49, 5, 115, -2};
    int[] b1 = {115, 22, 49, 5, -2};
    int[] b2 = {22, 49, 5, -2, 115};
    int[] b3 = {115, 49, 5, -2, 22};
    int[] b4 = {-2, 5, 22, 49, 115};
    int[] c = {1};


    @Test
    void maks() {
        assertThrows(NoSuchElementException.class, () -> Oblig1.maks(a));
        for (int i = 0; i < aa.length; i++){
            assertEquals(4, Oblig1.maks(aa[i]));
        }
        assertEquals(115, Oblig1.maks(b));
        assertEquals(115, Oblig1.maks(b1));
        assertEquals(115, Oblig1.maks(b2));
        assertEquals(1, Oblig1.maks(c));
    }

    @Test
    void ombyttinger() {
        assertThrows(NoSuchElementException.class, () -> Oblig1.ombyttinger(a));

        int[] aaResults = {
                0, 1, 1, 1, 2, 2,
                1, 2, 1, 1, 2, 1,
                2, 2, 2, 2, 2, 2,
                3, 3, 3, 3, 3, 3
        };
        for (int i = 0; i < aa.length; i++){
            assertEquals(aaResults[i], Oblig1.ombyttinger(aa[i]));
        }
        assertEquals(2, Oblig1.ombyttinger(b));
        assertEquals(4, Oblig1.ombyttinger(b1));
        assertEquals(2, Oblig1.ombyttinger(b2));
        assertEquals(4, Oblig1.ombyttinger(b3));
        assertEquals(0, Oblig1.ombyttinger(b4));
        assertEquals(0, Oblig1.ombyttinger(c));
    }
}
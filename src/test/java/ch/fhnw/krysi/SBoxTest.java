package ch.fhnw.krysi;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SBoxTest {

    SBox sbox = new SBox(Map.ofEntries(
            Map.entry("0000", "1111"),
            Map.entry("0101", "1010"),
            Map.entry("0001", "1110")
    ), 4);

    @Test
    void transforms() {
        assertEquals("111110101110", sbox.transform("000001010001"));
        assertEquals("1111", sbox.transform("0000"));
    }

    @Test
    void transformsInverted() {
        assertEquals("000001010001", sbox.transformInverted("111110101110"));
    }

    @Test
    void testsBlockSize() {
        assertThrows(IllegalArgumentException.class, () -> sbox.transform("110"));
    }
}

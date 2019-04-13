package ch.fhnw.krysi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitStringTest {

    @Test
    void xor() {
        assertEquals("0", BitString.xor("0", "0"));
        assertEquals("0", BitString.xor("1", "1"));
        assertEquals("1", BitString.xor("0", "1"));
        assertEquals("1", BitString.xor("1", "0"));
    }

    @Test
    void padBlock() {
        assertEquals("1100", BitString.padBlock("1", 4));
        assertEquals("1110", BitString.padBlock("11", 4));
        assertEquals("11010000", BitString.padBlock("110", 4));
        assertEquals("1101", BitString.padBlock("1101", 4));
    }

    @Test
    void padBinary() {
        assertEquals("0001", BitString.padBinary("1", 4));
        assertEquals("0001", BitString.padBinary("001", 4));
        assertEquals("0001", BitString.padBinary("0001", 4));
    }

    @Test
    void increment() {
        assertEquals("0001", BitString.increment("0000", 4));
        assertEquals("1111", BitString.increment("1110", 4));
        assertEquals("0000", BitString.increment("1111", 4));
    }
}


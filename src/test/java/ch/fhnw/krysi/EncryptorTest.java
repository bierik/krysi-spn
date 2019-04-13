package ch.fhnw.krysi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class EncryptorTest {

  BitPermutation permutation = new BitPermutation(new ArrayList<Pair<Integer>>(Arrays.asList(
            new Pair<Integer>(0, 0),
            new Pair<Integer>(1, 4),
            new Pair<Integer>(2, 8),
            new Pair<Integer>(3, 12),
            new Pair<Integer>(4, 1),
            new Pair<Integer>(5, 5),
            new Pair<Integer>(6, 9),
            new Pair<Integer>(7, 13),
            new Pair<Integer>(8, 2),
            new Pair<Integer>(9, 6),
            new Pair<Integer>(10, 10),
            new Pair<Integer>(11, 14),
            new Pair<Integer>(12, 3),
            new Pair<Integer>(13, 7),
            new Pair<Integer>(14, 11),
            new Pair<Integer>(15, 15)
    )));

    SBox sbox = new SBox(Map.ofEntries(
            Map.entry("0000", "1110"),
            Map.entry("0001", "0100"),
            Map.entry("0010", "1101"),
            Map.entry("0011", "0001"),
            Map.entry("0100", "0010"),
            Map.entry("0101", "1111"),
            Map.entry("0110", "1011"),
            Map.entry("0111", "1000"),
            Map.entry("1000", "0011"),
            Map.entry("1001", "1010"),
            Map.entry("1010", "0110"),
            Map.entry("1011", "1100"),
            Map.entry("1100", "0101"),
            Map.entry("1101", "1001"),
            Map.entry("1110", "0000"),
            Map.entry("1111", "0111")
    ), 4);

    KeyGenerator generator = new SimpleKeyGenerator("00111010100101001101011000111111", permutation, 4);

    int rounds = 4;

    Encryptor encryptor = new SimpleEncryptor(generator, sbox, permutation, rounds);

    @Test
    void encrypts() {
        assertEquals("1110110010101011",
            encryptor.encrypt("0011101010010100"));
    }

    @Test
    void decrypts() {
        assertEquals("0011101010010100",
            encryptor.decrypt("1110110010101011"));
    }

}

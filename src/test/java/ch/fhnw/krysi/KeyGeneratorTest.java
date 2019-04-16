package ch.fhnw.krysi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyGeneratorTest {

  BitPermutation permutation = new BitPermutation(new ArrayList<Pair<Integer>>(
      Arrays.asList(new Pair<Integer>(0, 0), new Pair<Integer>(1, 4), new Pair<Integer>(2, 8), new Pair<Integer>(3, 12),
          new Pair<Integer>(4, 1), new Pair<Integer>(5, 5), new Pair<Integer>(6, 9), new Pair<Integer>(7, 13),
          new Pair<Integer>(8, 2), new Pair<Integer>(9, 6), new Pair<Integer>(10, 10), new Pair<Integer>(11, 14),
          new Pair<Integer>(12, 3), new Pair<Integer>(13, 7), new Pair<Integer>(14, 11), new Pair<Integer>(15, 15))));

  KeyGenerator generator = new SimpleKeyGenerator("00111010100101001101011000111111", permutation, 4);

  @Test
  void generatesKey() {
    assertEquals("0011101010010100", generator.generateKey(0));
    assertEquals("1010100101001101", generator.generateKey(1));
    assertEquals("1001010011010110", generator.generateKey(2));
    assertEquals("0100110101100011", generator.generateKey(3));
  }

  @Test
  void generatesInvertedKey() {
    assertEquals("1101011000111111", generator.generateInvertedKey(0));
    assertEquals("0011101010010100", generator.generateInvertedKey(4));
    assertEquals("1101001110000101", generator.generateInvertedKey(1));
    assertEquals("1010011100011010", generator.generateInvertedKey(2));
  }
}

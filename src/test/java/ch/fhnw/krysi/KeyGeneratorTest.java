package ch.fhnw.krysi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyGeneratorTest {

  BitPermutation permutation = new BitPermutation(Arrays.asList(
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
  ));

  KeyGenerator generator = new SimpleKeyGenerator("00010001001010001000110000000000", permutation, 4);

  @Test
  void generatesKey() {
    assertEquals("0001000100101000", generator.generateKey(0));
    assertEquals("0001001010001000", generator.generateKey(1));
    assertEquals("0010100010001100", generator.generateKey(2));
    assertEquals("1000100011000000", generator.generateKey(3));
  }

  @Test
  void generatesInvertedKey() {
    assertEquals("1000110000000000", generator.generateInvertedKey(0));
    assertEquals("0001000100101000", generator.generateInvertedKey(4));
    assertEquals("1110001000000000", generator.generateInvertedKey(1));
    assertEquals("0111000110000000", generator.generateInvertedKey(2));
  }
}

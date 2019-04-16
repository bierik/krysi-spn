package ch.fhnw.krysi;

import ch.fhnw.krysi.modes.CTR;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

class CTRTests {
  String key = "00010001001010001000110000000000";
  int rounds = 4;
  int n = 4;
  int m = 4;

  BitPermutation permutation = new BitPermutation(new ArrayList<Pair<Integer>>(
      Arrays.asList(new Pair<Integer>(0, 0), new Pair<Integer>(1, 4), new Pair<Integer>(2, 8), new Pair<Integer>(3, 12),
          new Pair<Integer>(4, 1), new Pair<Integer>(5, 5), new Pair<Integer>(6, 9), new Pair<Integer>(7, 13),
          new Pair<Integer>(8, 2), new Pair<Integer>(9, 6), new Pair<Integer>(10, 10), new Pair<Integer>(11, 14),
          new Pair<Integer>(12, 3), new Pair<Integer>(13, 7), new Pair<Integer>(14, 11), new Pair<Integer>(15, 15))));

  SBox sbox = new SBox(Map.ofEntries(Map.entry("0000", "1110"), Map.entry("0001", "0100"), Map.entry("0010", "1101"),
      Map.entry("0011", "0001"), Map.entry("0100", "0010"), Map.entry("0101", "1111"), Map.entry("0110", "1011"),
      Map.entry("0111", "1000"), Map.entry("1000", "0011"), Map.entry("1001", "1010"), Map.entry("1010", "0110"),
      Map.entry("1011", "1100"), Map.entry("1100", "0101"), Map.entry("1101", "1001"), Map.entry("1110", "0000"),
      Map.entry("1111", "0111")), n);

  KeyGenerator generator = new SimpleKeyGenerator(key, permutation, rounds);

  Encryptor encryptor = new SimpleEncryptor(generator, sbox, permutation, rounds);

  SPNMode spn = new CTR(encryptor, rounds, n, m);

  @Test
  void encrypts() {
    String cypher = "00000100110100101001101001011101";
    String plaintext = "0001001010001111";
    assertEquals(cypher, spn.encrypt(plaintext));
  }

  @Test
  void decrypts() {
    String plaintext = "00000100110100100001001010001111";
    String cypher = "00000100110100101001101001011101";
    assertEquals(plaintext, spn.decrypt(cypher));
  }
}

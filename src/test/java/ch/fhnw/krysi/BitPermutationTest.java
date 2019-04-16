package ch.fhnw.krysi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BitPermutationTest {

  BitPermutation permutation = new BitPermutation(new ArrayList<Pair<Integer>>(Arrays.asList(new Pair<Integer>(0, 2),
      new Pair<Integer>(1, 1), new Pair<Integer>(2, 0), new Pair<Integer>(3, 3))));

  @Test
  void transforms() {
    assertEquals("0111", permutation.permute("1101"));
  }

  @Test
  void testsBlockSize() {
    assertThrows(IllegalArgumentException.class, () -> permutation.permute("110"));
  }
}

package ch.fhnw.krysi;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BitString {

  private static Character xorChar(Character a, Character b) {
    if (a.equals(b)) {
      return '0';
    }
    return '1';
  }

  public static String xor(String a, String b) {
    return IntStream.range(0, a.length()).mapToObj(i -> BitString.xorChar(a.charAt(i), b.charAt(i)))
        .map(String::valueOf).collect(Collectors.joining());
  }

  public static String padBlock(String string, int blockSize) {
    int extra = string.length() % blockSize;
    if (extra == 0) {
      return string;
    } else if (extra + 1 == blockSize) {
      return string + "1" + "0".repeat(blockSize);
    }
    return string + "1" + "0".repeat(blockSize - extra - 1);
  }

  public static String padBinary(String string, int blockSize) {
    int extra = string.length() % blockSize;
    if (extra == 0) {
      return string;
    }
    return "0".repeat(blockSize - extra) + string;
  }

  public static String increment(String string, int blockSize) {
    return BitString.padBinary(Integer.toBinaryString((Integer.parseInt(string, 2) + 1) % (int) Math.pow(2, blockSize)),
        blockSize);
  }
}

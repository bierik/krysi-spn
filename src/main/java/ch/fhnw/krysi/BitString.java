package ch.fhnw.krysi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Helper class for doing bit manipulations on a bit like String
 */
public class BitString {

  /**
   * xor operator for single character
   * @param a
   * @param b
   * @return 0 if a and b matches, 1 otherwise
   */
  private static Character xorChar(Character a, Character b) {
    if (a.equals(b)) {
      return '0';
    }
    return '1';
  }

  /**
   * xor operator for BitString
   * @param a
   * @param b
   * @return xored BitString
   */
  public static String xor(String a, String b) {
    return IntStream
      .range(0, a.length())
      .mapToObj(i -> BitString.xorChar(a.charAt(i), b.charAt(i)))
      .map(String::valueOf)
      .collect(Collectors.joining());
  }

  /**
   * Add padding of 10.. if necessary
   * @param string Block to pad
   * @param blockSize size to fill the block up to
   * @return padded block
   */
  public static String padBlock(String string, int blockSize) {
    int extra = string.length() % blockSize;
    if (extra == 0) {
      return string;
    } else if (extra + 1 == blockSize) {
      return string + "1" + "0".repeat(blockSize);
    }
    return string + "1" + "0".repeat(blockSize - extra - 1);
  }

  /**
   * Make a BitString of given size with leading 0
   * @param string
   * @param blockSize
   * @return padded block
   */
  public static String padBinary(String string, int blockSize) {
    int extra = string.length() % blockSize;
    if (extra == 0) {
      return string;
    }
    return "0".repeat(blockSize - extra) + string;
  }

  /**
   * Increment binary string by one with overflow
   * @param string
   * @param blockSize
   * @return incremented binary string
   */
  public static String increment(String string, int blockSize) {
    return BitString.padBinary(Integer.toBinaryString((Integer.parseInt(string, 2) + 1) % (int) Math.pow(2, blockSize)),
        blockSize);
  }

  /**
   * Generates list of string chunks of given size
   * @param string String to make chunks of
   * @param blockSize size of each chunk
   * @return list of chunks
   */
  public static List<String> makeChunks(String string, int blockSize) {
    if (string.length() % blockSize != 0) {
      throw new IllegalArgumentException("BitString is not divisible in the block size");
    }
    List<String> chunks = new ArrayList<String>();
    for(int i = 0; i < string.length(); i += blockSize) {
      chunks.add(string.substring(i, i + blockSize));
    }
    return chunks;
  }
}

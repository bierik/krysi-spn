package ch.fhnw.krysi;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents sbox
 */
public class SBox {

  private Map<String, String> sbox;
  private Map<String, String> sboxInverted;
  private int size;

  /**
   * Receives sbox as map
   * Also generates the inverted sbox
   */
  public SBox(Map<String, String> sbox, int size) {
    this.sbox = sbox;
    this.sboxInverted = sbox
      .entrySet()
      .stream()
      .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    this.size = size;
  }

  /**
   * Check if the sbox is capable of computing the block
   */
  private void checkBlockSize(String block) {
    if (block.length() % this.size != 0) {
      throw new IllegalArgumentException("Size of block and permutation table does not match");
    }
  }

  /**
   * Transform a given block with a given sbox
   */
  private String transform(String block, Map<String, String> sbox) {
    return BitString.makeChunks(block, this.size)
      .stream()
      .map(b -> sbox.get(b))
      .collect(Collectors.joining());
  }

  /**
   * Transform a given block
   */
  public String transform(String block) {
    this.checkBlockSize(block);
    return this.transform(block, this.sbox);
  }

  /**
   * Transform a given block with the inverted sbox
   */
  public String transformInverted(String block) {
    this.checkBlockSize(block);
    return this.transform(block, this.sboxInverted);
  }
}

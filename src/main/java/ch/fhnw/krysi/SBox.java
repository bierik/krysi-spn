package ch.fhnw.krysi;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SBox {

  private Map<String, String> sbox;
  private Map<String, String> sboxInverted;
  private int size;

  public SBox(Map<String, String> sbox, int size) {
    this.sbox = sbox;
    this.sboxInverted = sbox.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    this.size = size;
  }

  private void checkBlockSize(String block) {
    if (block.length() % this.size != 0) {
      throw new IllegalArgumentException("Size of block and permutation table does not match");
    }
  }

  private String transform(String block, Map<String, String> sbox) {
    return IntStream.range(0, block.length() / this.size)
        .mapToObj(x -> block.substring(x * this.size, x * this.size + this.size)).map(b -> sbox.get(b))
        .collect(Collectors.joining());
  }

  public String transform(String block) {
    this.checkBlockSize(block);
    return this.transform(block, this.sbox);
  }

  public String transformInverted(String block) {
    this.checkBlockSize(block);
    return this.transform(block, this.sboxInverted);
  }
}

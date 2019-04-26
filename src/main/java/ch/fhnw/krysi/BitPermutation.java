package ch.fhnw.krysi;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is able to permute a BitString based on a given permutation
 */
public class BitPermutation {

  private List<Pair<Integer>> permutation;

  /**
   * @param permutation Permutation configuration
   */
  public BitPermutation(List<Pair<Integer>> permutation) {
    this.permutation = permutation;
  }

  /**
   * Permutes a block of BitString with the given permutation
   * @param block Block to permute
   * @return The permuted BitString
   */
  public String permute(String block) {
    if (block.length() != this.permutation.size()) {
      throw new IllegalArgumentException("Size ob block and permutation table does not match");
    }

    return permutation
      .stream()
      .map(p -> block.charAt(p.getTo()))
      .map(String::valueOf)
      .collect(Collectors.joining());
  }

  public int size() {
    return this.permutation.size();
  }
}

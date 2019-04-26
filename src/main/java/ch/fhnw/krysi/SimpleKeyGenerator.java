package ch.fhnw.krysi;

/**
 * Implementation of a simple KeyGenerator
 */
public class SimpleKeyGenerator extends KeyGenerator {

  public SimpleKeyGenerator(String key, BitPermutation bitPermutation, int rounds) {
    super(key, bitPermutation, rounds);
  }

  /**
   * Generates key (4i + 16)
   */
  @Override
  public String generateKey(int round) {
    return this.key.substring(4 * round, 4 * round + 16);
  }
}

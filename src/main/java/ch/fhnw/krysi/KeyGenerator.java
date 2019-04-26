package ch.fhnw.krysi;

/**
 * Base class for a key generator
 */
public abstract class KeyGenerator {
  protected String key;
  protected BitPermutation bitPermutation;
  protected int rounds;

  public KeyGenerator(String key, BitPermutation bitPermutation, int rounds) {
    this.key = key;
    this.bitPermutation = bitPermutation;
    this.rounds = rounds;
  };

  /**
   * Generates encryption key for given round
   * This method is special for every key generator
   * @param round
   * @return
   */
  public abstract String generateKey(int round);

  /**
   * Generates decryption key at given round
   * @param round
   * @return
   */
  public String generateInvertedKey(int round) {
    if (round == 0 || round == this.rounds) {
      return this.generateKey(this.rounds - round);
    }
    return this.bitPermutation.permute(this.generateKey(this.rounds - round));
  }
}

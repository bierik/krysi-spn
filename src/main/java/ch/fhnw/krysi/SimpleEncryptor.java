package ch.fhnw.krysi;

/**
 * Implementation of a simple encryptor
 */
public class SimpleEncryptor extends Encryptor {

  public SimpleEncryptor(KeyGenerator generator, SBox sbox, BitPermutation permutation, int rounds) {
    super(generator, sbox, permutation, rounds);
  }

  @Override
  public String encrypt(String block) {
    String output = block;

    // Initialer Weissschritt
    output = BitString.xor(output, this.generator.generateKey(0));

    // Reguläre Runden
    for (int round = 1; round < this.rounds; round++) {
      output = this.sbox.transform(output);
      output = this.permutation.permute(output);
      output = BitString.xor(output, this.generator.generateKey(round));
    }

    // Verkürtzte Runde
    output = this.sbox.transform(output);
    output = BitString.xor(output, this.generator.generateKey(this.rounds));

    return output;
  }

  @Override
  public String decrypt(String block) {
    String output = block;

    // Initialer Weissschritt
    output = BitString.xor(output, this.generator.generateInvertedKey(0));

    // Reguläre Runden
    for (int round = 1; round < this.rounds; round++) {
      output = this.sbox.transformInverted(output);
      output = this.permutation.permute(output);
      output = BitString.xor(output, this.generator.generateInvertedKey(round));
    }

    // Verkürtzte Runde
    output = this.sbox.transformInverted(output);
    output = BitString.xor(output, this.generator.generateInvertedKey(this.rounds));

    return output;
  }
}

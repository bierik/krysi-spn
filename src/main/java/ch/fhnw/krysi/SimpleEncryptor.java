package ch.fhnw.krysi;

public class SimpleEncryptor extends Encryptor {

    public SimpleEncryptor(KeyGenerator generator, SBox sbox, BitPermutation permutation, int rounds) {
      super(generator, sbox, permutation, rounds);
    }

    @Override
    public String encrypt(String block) {
        String output = block;

        // Initialer Weissschritt
        output = BitString.xor(block, this.generator.generateKey(0));

        // Reguläre Runden
        for(int round = 1; round < this.rounds; round ++) {
          output = this.sbox.transform(block);
          output = this.permutation.permute(block);
          output = BitString.xor(block, this.generator.generateKey(round));
        }

        // Verkürtzte Runde
        output = this.sbox.transform(block);
        output = BitString.xor(block, this.generator.generateKey(this.rounds));

        return output;
    }

    @Override
    public String decrypt(String block) {
        return this.encrypt(block);
    }
}

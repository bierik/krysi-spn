package ch.fhnw.krysi;

public abstract class Encryptor {

    protected SBox sbox;
    protected BitPermutation permutation;
    protected int rounds;
    protected KeyGenerator generator;

    public Encryptor(KeyGenerator generator, SBox sbox, BitPermutation permutation, int rounds) {
      this.sbox = sbox;
      this.permutation = permutation;
      this.rounds = rounds;
      this.generator = generator;
    }

    public abstract String encrypt(String plaintext);
    public abstract String decrypt(String cipher);
}

package ch.fhnw.krysi;

public abstract class KeyGenerator {
    protected String key;
    protected BitPermutation bitPermutation;
    protected int rounds;

    public KeyGenerator(String key, BitPermutation bitPermutation, int rounds) {
        this.key = key;
        this.bitPermutation = bitPermutation;
        this.rounds = rounds;
    };

    public abstract String generateKey(int round);
    public abstract String generateInvertedKey(int round);
}

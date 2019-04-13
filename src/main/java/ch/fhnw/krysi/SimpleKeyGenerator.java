package ch.fhnw.krysi;

public class SimpleKeyGenerator extends KeyGenerator {

    public SimpleKeyGenerator(String key, BitPermutation bitPermutation, int rounds) {
        super(key, bitPermutation, rounds);
    }

    @Override
    public String generateKey(int round) {
        return this.key.substring(4 * round, 4 * round + 16);
    }

    @Override
    public String generateInvertedKey(int round) {
        if(round == 0) {
            return this.generateKey(this.rounds);
        } else if(round == rounds) {
            return this.generateKey(0);
        }
        return this.bitPermutation.permute(this.generateKey(round));
    }
}

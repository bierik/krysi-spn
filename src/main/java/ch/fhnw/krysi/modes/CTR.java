package ch.fhnw.krysi.modes;

import java.util.List;

import ch.fhnw.krysi.*;

/**
 * Implementation of CTR SPNMode
 */
public class CTR extends SPNMode {

    public CTR(Encryptor encryptor, int r, int n, int m) {
        super(encryptor, r, n, m);
    }

    /**
     * Static initialize Vector (should be random in production)
     */
    private String generateInitializeVector() {
        return "0000101110111000";
    }

    /**
     * Encrypts plaintext blockwise
     */
    @Override
    public String encrypt(String plaintext) {
        String cypherVector = generateInitializeVector();
        String paddedPlaintext = BitString.padBlock(plaintext, this.size);

        String result = cypherVector;

        for(String block : BitString.makeChunks(paddedPlaintext, this.size)) {
          result += BitString.xor(this.encryptor.encrypt(cypherVector), block);
          cypherVector = BitString.increment(cypherVector, this.size);
        }
        return result;
    }

    /**
     * Decrypts plaintext blockwise
     */
    @Override
    public String decrypt(String ciphertext) {
      if (ciphertext.length() == 0) {
        return "";
      }

      List<String> blocks = BitString.makeChunks(ciphertext, this.size);
      String cypherVector = blocks.remove(0);

      String result = "";

      for(String block : blocks) {
        result += BitString.xor(this.encryptor.decrypt(cypherVector), block);
        cypherVector = BitString.increment(cypherVector, this.size);
      }
      return result;
    }
}

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
     * Encrypts plaintext blockwise
     */
    @Override
    public String encrypt(String plaintext) {
        throw new UnsupportedOperationException("Not yet implemented");
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
      String cypherVector = blocks.get(0);

      String result = "";

      for(String block : blocks.subList(1, blocks.size())) {
        result += BitString.xor(this.encryptor.encrypt(cypherVector), block);
        cypherVector = BitString.increment(cypherVector, this.size);
      }
      return result;
    }
}

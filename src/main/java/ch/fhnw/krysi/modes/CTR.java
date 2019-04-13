package ch.fhnw.krysi.modes;

import ch.fhnw.krysi.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CTR extends SPNMode {

    public CTR(Encryptor encryptor, int r, int n, int m) {
        super(encryptor, r, n, m);
    }

    private String generateInitializeVector() {
        return "0000010011010010";
    }

    @Override
    public String encrypt(String plaintext) {
        String initializeVector = generateInitializeVector();
        String cyptherVecotor = initializeVector;
        String paddedPlaintext = BitString.padBlock(plaintext, this.size);

        List<String> streamCyper = new ArrayList<String>();

        for(int i = 0; i < paddedPlaintext.length() / this.size; i++) {
          streamCyper.add(this.encryptor.encrypt(cyptherVecotor));
          cyptherVecotor = BitString.increment(cyptherVecotor, this.size);
        }

        return initializeVector + IntStream
          .range(0, paddedPlaintext.length() / this.size)
          .mapToObj(i -> BitString.xor(streamCyper.get(i), paddedPlaintext.substring(i * this.size, i * this.size + this.size)))
          .collect(Collectors.joining());
    }

    @Override
    public String decrypt(String ciphertext) {
        return encrypt(ciphertext);
    }
}

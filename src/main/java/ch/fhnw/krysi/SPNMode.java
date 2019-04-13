package ch.fhnw.krysi;

public abstract class SPNMode {

    protected Encryptor encryptor;
    protected int r;
    protected int n;
    protected int m;
    protected int size;

    public SPNMode(Encryptor encryptor, int r, int n, int m) {
        this.encryptor = encryptor;
        this.r = r;
        this.n = n;
        this.m = m;
        this.size = n * m;
    }

    public abstract String encrypt(String plaintext);
    public abstract String decrypt(String ciphertext);
}

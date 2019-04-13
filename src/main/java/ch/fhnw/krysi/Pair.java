package ch.fhnw.krysi;

public class Pair<T> {
    private T from;
    private T to;

    public Pair(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public T getFrom() {
        return this.from;
    }

    public T getTo() {
        return this.to;
    }
}


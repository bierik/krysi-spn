package ch.fhnw.krysi;

import java.util.List;
import java.util.stream.Collectors;

public class BitPermutation {

    private List<Pair<Integer>> permutaiton;

    public BitPermutation(List<Pair<Integer>> permutaiton) {
        this.permutaiton = permutaiton;
    }

    public String permute(String block) {
        if(block.length() != this.permutaiton.size()) {
            throw new IllegalArgumentException("Size ob block and permutation table does not match");
        }

        return permutaiton
                .stream()
                .map(p -> block.charAt(p.getTo()))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public int size() { return this.permutaiton.size(); }
}

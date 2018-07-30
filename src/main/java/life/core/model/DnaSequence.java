package life.core.model;

import life.utils.DNAUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexb on 2/4/2018.
 */
public class DnaSequence {

    protected static final Map<Character, AMINO_ACID> DNA_AMINO_ACID = new HashMap<>(4);

    private List<AMINO_ACID> dnaSequence = new ArrayList<>();

    static {
        DNA_AMINO_ACID.put(AMINO_ACID.A.getOneLetterName(), AMINO_ACID.A);
        DNA_AMINO_ACID.put(AMINO_ACID.T.getOneLetterName(), AMINO_ACID.T);
        DNA_AMINO_ACID.put(AMINO_ACID.G.getOneLetterName(), AMINO_ACID.G);
        DNA_AMINO_ACID.put(AMINO_ACID.C.getOneLetterName(), AMINO_ACID.C);
    }

    public DnaSequence(byte[] dnaByteSequence, long totalNumberOfBp) {
        this.dnaSequence = DNAUtils.getSequenceFromBytes(dnaByteSequence, totalNumberOfBp);
    }

    public DnaSequence(List<AMINO_ACID> dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    public List<AMINO_ACID> getDnaSequence() {
        return dnaSequence;
    }

    public void setDnaSequence(List<AMINO_ACID> dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        dnaSequence.stream().forEach(c -> {
            sb.append(c);
        });
        return sb.toString();
    }

}

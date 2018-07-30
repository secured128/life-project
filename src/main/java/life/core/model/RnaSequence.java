package life.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexb on 2/4/2018.
 */
public class RnaSequence {

    protected static final Map<Character, AMINO_ACID> RNA_AMINO_ACID = new HashMap<>(4);

    private List<AMINO_ACID> rnaSequence = new ArrayList<>();

    private static final Map<AMINO_ACID, AMINO_ACID> DNA_TEMPLATE_2_RNA_MAPPING = new HashMap<>();

    static {
        DNA_TEMPLATE_2_RNA_MAPPING.put(AMINO_ACID.A, AMINO_ACID.U);
        DNA_TEMPLATE_2_RNA_MAPPING.put(AMINO_ACID.C, AMINO_ACID.G);
        DNA_TEMPLATE_2_RNA_MAPPING.put(AMINO_ACID.T, AMINO_ACID.A);
        DNA_TEMPLATE_2_RNA_MAPPING.put(AMINO_ACID.G, AMINO_ACID.C);
    }

    public RnaSequence(DnaSequence dnaTemplateSequence) {
        dnaTemplateSequence.getDnaSequence().stream().forEach(c -> {
            rnaSequence.add(DNA_TEMPLATE_2_RNA_MAPPING.get(c));
        });
    }

    public List<AMINO_ACID> getRnaSequence() {
        return rnaSequence;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        rnaSequence.stream().forEach(c -> {
            sb.append(c);
        });
        return sb.toString();
    }
}

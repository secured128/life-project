package life.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexb on 2/14/2018.
 */
public class Protein {

    private List<AMINO_ACID> proteinSequence = new ArrayList<>();
    List<AMINO_ACID> aminoAcidList = new ArrayList<>();

    public Protein(RnaSequence rnaSequence) {
        loop:
        rnaSequence.getRnaSequence().stream().anyMatch(c -> {
            if (aminoAcidList.size() < 3) {
                aminoAcidList.add(c);
            }
            if (aminoAcidList.size() == 3) {
                CODON codon = CODON.getCodonBy3AminoAcids(aminoAcidList);
                if (codon.getIsStopCodon()) {
                    return true;
                } else {
                    proteinSequence.add(CODON.getCodonBy3AminoAcids(aminoAcidList).getAminoAcid());
                    aminoAcidList.clear();
                }
            }
            return false;
        });
        aminoAcidList = null;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        proteinSequence.stream().forEach(c -> {
            sb.append(c);
        });
        return sb.toString();
    }

}

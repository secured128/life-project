package life.core.model;

import java.util.List;

public enum CODON {
    UGU(AMINO_ACID.C, false),
    UCU(AMINO_ACID.S, false),
    AUA(AMINO_ACID.I, false),
    CUA(AMINO_ACID.L, false),
    AUC(AMINO_ACID.I, false),
    CUC(AMINO_ACID.L, false),
    GUA(AMINO_ACID.V, false),
    AUG(AMINO_ACID.M, false),
    CUG(AMINO_ACID.L, false),
    GUC(AMINO_ACID.V, false),
    GUG(AMINO_ACID.V, false),
    UUA(AMINO_ACID.L, false),
    AUU(AMINO_ACID.I, false),
    AAA(AMINO_ACID.K, false),
    CAA(AMINO_ACID.Q, false),
    UUC(AMINO_ACID.F, false),
    CUU(AMINO_ACID.L, false),
    AAC(AMINO_ACID.N, false),
    CAC(AMINO_ACID.H, false),
    GAA(AMINO_ACID.E, false),
    UUG(AMINO_ACID.L, false),
    GUU(AMINO_ACID.V, false),
    AAG(AMINO_ACID.K, false),
    GAC(AMINO_ACID.D, false),
    CAG(AMINO_ACID.Q, false),
    GAG(AMINO_ACID.E, false),
    UUU(AMINO_ACID.F, false),
    AAU(AMINO_ACID.N, false),
    UAC(AMINO_ACID.Y, false),
    CAU(AMINO_ACID.H, false),
    GAU(AMINO_ACID.D, false),
    UAU(AMINO_ACID.Y, false),
    AGA(AMINO_ACID.R, false),
    CGA(AMINO_ACID.R, false),
    AGC(AMINO_ACID.S, false),
    CGC(AMINO_ACID.R, false),
    ACA(AMINO_ACID.T, false),
    CCA(AMINO_ACID.P, false),
    GGA(AMINO_ACID.G, false),
    AGG(AMINO_ACID.R, false),
    ACC(AMINO_ACID.T, false),
    CGG(AMINO_ACID.R, false),
    CCC(AMINO_ACID.P, false),
    GGC(AMINO_ACID.G, false),
    GCA(AMINO_ACID.A, false),
    ACG(AMINO_ACID.T, false),
    GCC(AMINO_ACID.A, false),
    CCG(AMINO_ACID.P, false),
    GGG(AMINO_ACID.G, false),
    GCG(AMINO_ACID.A, false),
    AGU(AMINO_ACID.S, false),
    CGU(AMINO_ACID.R, false),
    UGC(AMINO_ACID.C, false),
    UCA(AMINO_ACID.S, false),
    ACU(AMINO_ACID.T, false),
    UCC(AMINO_ACID.S, false),
    UGG(AMINO_ACID.W, false),
    CCU(AMINO_ACID.P, false),
    GGU(AMINO_ACID.G, false),
    GCU(AMINO_ACID.A, false),
    UCG(AMINO_ACID.S, false),
    UAA(null, true),
    UAG(null, true),
    UGA(null, true),
    ;

    private AMINO_ACID aminoAcid;
    private java.lang.Boolean isStopCodon;

    CODON(AMINO_ACID aminoAcid, java.lang.Boolean isStopCodon) {
        this.aminoAcid = aminoAcid;
        this.isStopCodon = isStopCodon;
    }

    public static CODON getCodonBy3AminoAcids(List<AMINO_ACID> aminoAcids) {
        if (aminoAcids == null || aminoAcids.size() != 3) {
            throw new IllegalArgumentException("There should be 3 amino acids in the list");
        }
        StringBuffer aminoAcidsBuffer = new StringBuffer();
        aminoAcids.stream().forEach(a -> aminoAcidsBuffer.append(a.getOneLetterName()));
        return CODON.valueOf(aminoAcidsBuffer.toString());
    }

    public AMINO_ACID getAminoAcid() {
        return this.aminoAcid;
    }

    ;

    public java.lang.Boolean getIsStopCodon() {
        return this.isStopCodon;
    }

    ;

}
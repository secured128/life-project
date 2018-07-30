package life.core.utils;

import org.apache.commons.lang3.StringUtils;
import life.utils.CIRCLE_REFERENCED_ENUM;
import life.utils.EnumGenerator;

import java.io.IOException;
import java.util.*;

/**
 * Created by alexb on 1/17/2018.
 * data source
 * https://en.wikipedia.org/wiki/Amino_acid
 */
public class CreateModelFromDataString {

    public static void main(String[] args) throws IOException {
        String a = "Leucine\tLeu\tL\taliphatic\tnonpolar\tneutral\t3.8\t\t\t131.175\t9.68\n" +
                "Alanine\tAla\tA\taliphatic\tnonpolar\tneutral\t1.8\t\t\t89.094\t8.76\n" +
                "Serine\tSer\tS\thydroxyl-containing\tpolar\tneutral\t−0.8\t\t\t105.093\t7.14\n" +
                "Glycine\tGly\tG\taliphatic\tnonpolar\tneutral\t−0.4\t\t\t75.067\t7.03\n" +
                "Valine\tVal\tV\taliphatic\tnonpolar\tneutral\t4.2\t\t\t117.148\t6.73\n" +
                "Glutamic acid\tGlu\tE\tacid\tacidic polar\tnegative\t−3.5\t\t\t147.131\t6.32\n" +
                "Arginine\tArg\tR\tbasic\tbasic polar\tpositive\t−4.5\t\t\t174.203\t5.78\n" +
                "Threonine\tThr\tT\thydroxyl-containing\tpolar\tneutral\t−0.7\t\t\t119.119\t5.53\n" +
                "Aspartic acid\tAsp\tD\tacid\tacidic polar\tnegative\t−3.5\t\t\t133.104\t5.49\n" +
                "Isoleucine\tIle\tI\taliphatic\tnonpolar\tneutral\t4.5\t\t\t131.175\t5.49\n" +
                "Lysine\tLys\tK\tbasic\tbasic polar\tpositive\t−3.9\t\t\t146.189\t5.19\n" +
                "Proline\tPro\tP\tcyclic\tnonpolar\tneutral\t−1.6\t\t\t115.132\t5.02\n" +
                "Asparagine\tAsn\tN\tamide\tpolar\tneutral\t−3.5\t\t\t132.119\t3.93\n" +
                "Glutamine\tGln\tQ\tamide\tpolar\tneutral\t−3.5\t\t\t146.146\t3.9\n" +
                "Phenylalanine\tPhe\tF\taromatic\tnonpolar\tneutral\t2.8\t257, 206, 188\t0.2, 9.3, 60.0\t165.192\t3.87\n" +
                "Tyrosine\tTyr\tY\taromatic\tpolar\tneutral\t−1.3\t274, 222, 193\t1.4, 8.0, 48.0\t181.191\t2.91\n" +
                "Methionine\tMet\tM\tsulfur-containing\tnonpolar\tneutral\t1.9\t\t\t149.208\t2.32\n" +
                "Histidine\tHis\tH\tbasic aromatic\tbasic polar\tpositive(10%)-neutral(90%)\t−3.2\t211\t5.9\t155.156\t2.26\n" +
                "Cysteine\tCys\tC\tsulfur-containing\tnonpolar\tneutral\t2.5\t250\t0.3\t121.154\t1.38\n" +
                "Tryptophan\tTrp\tW\taromatic\tnonpolar\tneutral\t−0.9\t280, 219\t5.6, 47.0\t204.228\t1.25";

        //        "Selenocysteine\tSec\tU\t\t\t\t\t\t\t168.064\t \n" +
//                "Pyrrolysine\tPyl\tO\t\t\t\t\t\t\t255.313\t ";

        LinkedHashMap<String, Class> dataAndTypesMap = new LinkedHashMap<>();
        dataAndTypesMap.put("name", String.class);
        dataAndTypesMap.put("threeLettersName", String.class);
        dataAndTypesMap.put("oneLetterName", Character.class);
        dataAndTypesMap.put("SIDE_CHAIN_CLASS", CIRCLE_REFERENCED_ENUM.class);
        dataAndTypesMap.put("SIDE_CHAIN_POLARITY", CIRCLE_REFERENCED_ENUM.class);
        dataAndTypesMap.put("SIDE_CHAIN_CHARGE", CIRCLE_REFERENCED_ENUM.class);
        dataAndTypesMap.put("hydropathyIndex", Float.class);
        dataAndTypesMap.put("absorbance", Integer[].class);
        dataAndTypesMap.put("molarAttenuationCoefficient", Float[].class);
        dataAndTypesMap.put("molecularMass", Float.class);
        dataAndTypesMap.put("occurrenceInProteins", Float.class);

        Map<String, List<Object>> keyValues = new HashMap<>();

        Integer[] absorbanceArray = null;
        Float[] molarAttenuationCoefficient = null;

        Set<String> sideChains = new HashSet<>();
        Set<String> chainClassie = new HashSet<>();
        Set<String> chainPolarity = new HashSet<>();
        Set<String> chainChage = new HashSet<>();
        String[] list = a.split("\n");
        for (String line : list) {
            String[] lineVals = line.replace("−", "-").split("\t");
            String newLine = lineVals[2] + "(";
            List<Object> L = new ArrayList<>();
            keyValues.put(lineVals[2].trim(), L);
            for (int i = 0; i < lineVals.length; i++) {
                if (i < 2) {
                    if (i > 0) {
                        newLine = newLine + ",";
                    }
                    String stringValue = lineVals[i].trim();
                    L.add(stringValue);
                    newLine = newLine + "\"" + stringValue + "\"";
                } else if (i == 2) {
                    Character stringValue = lineVals[i].trim().charAt(0);
                    L.add(stringValue);
                    newLine = newLine + "\'" + stringValue + "\'";
                } else if (i > 5) {
                    String numVal = lineVals[i].trim();
                    if (i == 6) {
                        if (StringUtils.isEmpty(numVal)) {
                            newLine = newLine + ",null";
                            L.add(null);
                        } else {
                            String floatValue = Float.toString(Float.parseFloat(numVal));
                            L.add(floatValue);
                            newLine = newLine + "," + floatValue + "f";
                        }
                    } else if (i == 7) {
                        if (StringUtils.isNotEmpty(numVal)) {
                            String[] absorbanceStrArray = numVal.replace("\"", "").split(",");
                            absorbanceArray = new Integer[absorbanceStrArray.length];
                            for (int j = 0; j < absorbanceStrArray.length; j++) {
                                absorbanceArray[j] = Integer.parseInt(absorbanceStrArray[j].trim());
                            }
                            String intArray = "new int[] {" + numVal + "}";
                            L.add(absorbanceArray);
                            newLine = newLine + "," + intArray;
                        } else {
                            newLine = newLine + ",null";
                            L.add(null);
                        }

                    } else if (i == 8) {
                        if (StringUtils.isEmpty(numVal)) {
                            newLine = newLine + ",null";
                            L.add(null);
                        } else {
                            String[] floatsStringsArray = numVal.split(",");
                            molarAttenuationCoefficient = new Float[floatsStringsArray.length];
                            String floatsArrayStrings = "new float[] {";
                            for (int j = 0; j < floatsStringsArray.length; j++) {
                                if (j > 0) {
                                    floatsArrayStrings = floatsArrayStrings + ",";
                                }
                                floatsArrayStrings = floatsArrayStrings + Float.toString(Float.parseFloat(floatsStringsArray[j])) + "f";
                                molarAttenuationCoefficient[j] = Float.parseFloat(floatsStringsArray[j]);
                            }
                            floatsArrayStrings = floatsArrayStrings + "}";
                            L.add(molarAttenuationCoefficient);
                            newLine = newLine + "," + (StringUtils.isEmpty(numVal) ? "null" : floatsArrayStrings);
                        }
                    } else {
                        if (StringUtils.isEmpty(numVal) || "null".equals(numVal)) {
                            newLine = newLine + ",null";
                            L.add(null);
                        } else {
                            newLine = newLine + "," + Float.toString(Float.parseFloat(numVal)) + "f";
                            L.add(numVal);
                        }
                    }

                } else if (i == 3) {
                    String chainClassStr = lineVals[3].toUpperCase().trim().replace("-", "_").replace(" ", "_");
                    if (StringUtils.isNotEmpty(chainClassStr)) {
                        chainClassie.add(chainClassStr);
                        L.add("life.core.model.SIDE_CHAIN_CLASS." + chainClassStr);
                    }
                    String chainPolarityStr = lineVals[4].toUpperCase().trim().replace("-", "_").replace(" ", "_");
                    if (StringUtils.isNotEmpty(chainPolarityStr)) {
                        chainPolarity.add(chainPolarityStr);
                        L.add("life.core.model.SIDE_CHAIN_POLARITY." + chainPolarityStr);
                    }
                    String chainChargeStr = lineVals[5].toUpperCase().trim().replace("-", "_").replace(")", "").replace("(", "").replace("%", "");
                    if (StringUtils.isNotEmpty(chainChargeStr)) {
                        chainChage.add(chainChargeStr);
                        L.add("life.core.model.SIDE_CHAIN_CHARGE." + chainChargeStr);
                    }

                    newLine = newLine + "," + (StringUtils.isNotEmpty(chainClassStr) ? "SIDE_CHAIN_CLASS." + chainClassStr : "null") + "," + (StringUtils.isNotEmpty(chainPolarityStr) ? "SIDE_CHAIN_POLARITY." + chainPolarityStr : "null") + "," + (StringUtils.isNotEmpty(chainChargeStr) ? "SIDE_CHAIN_CHARGE." + chainChargeStr : "null");


                }
            }
            newLine = newLine + "),";
            System.out.println(newLine);
        }
        System.out.println("=============CHAIN CLASS================================");
        LinkedHashMap<String, Class> classDataAndTypesMap = new LinkedHashMap<>();
        Map<String, List<Object>> classKeyValues = new HashMap<>();
        chainClassie.stream().sorted().forEach(n -> {
            System.out.println(n + ",");
            classKeyValues.put(n, null);
        });
        EnumGenerator.generateEnum("life.core.model", "SIDE_CHAIN_CLASS", classDataAndTypesMap, classKeyValues);

        System.out.println("=============CHAIN POLARITY================================");
        LinkedHashMap<String, Class> polarityDataAndTypesMap = new LinkedHashMap<>();
        Map<String, List<Object>> polarityKeyValues = new HashMap<>();
        chainPolarity.stream().sorted().forEach(n -> {
            System.out.println(n + ",");
            polarityKeyValues.put(n, null);
        });
        EnumGenerator.generateEnum("life.core.model", "SIDE_CHAIN_POLARITY", polarityDataAndTypesMap, polarityKeyValues);

        System.out.println("=============CHAIN CHARGE================================");
        LinkedHashMap<String, Class> chargeDataAndTypesMap = new LinkedHashMap<>();
        Map<String, List<Object>> chargeKeyValues = new HashMap<>();
        chainChage.stream().sorted().forEach(n -> {
            System.out.println(n + ",");
            chargeKeyValues.put(n, null);
        });
        EnumGenerator.generateEnum("life.core.model", "SIDE_CHAIN_CHARGE", chargeDataAndTypesMap, chargeKeyValues);

        List<Object> U = new ArrayList<>();
        U.add("Selenocysteine");
        U.add("Sec");
        U.add(new Character('U'));
        U.add(null);
        U.add(null);
        U.add(null);
        U.add(null);
        U.add(null);
        U.add(null);
        U.add("168.064");
        U.add(null);
        keyValues.put("U", U);

        List<Object> O = new ArrayList<>();
        O.add("Pyrrolysine");
        O.add("Ply");
        O.add(new Character('O'));
        O.add(null);
        O.add(null);
        O.add(null);
        O.add(null);
        O.add(null);
        O.add(null);
        O.add("255.313");
        O.add(null);
        keyValues.put("O", O);

        EnumGenerator.generateEnum("life.core.model", "AMINO_ACID", dataAndTypesMap, keyValues);

        dataAndTypesMap = new LinkedHashMap<>();
        dataAndTypesMap.put("life.core.model.AMINO_ACID", CIRCLE_REFERENCED_ENUM.class);
        dataAndTypesMap.put("isStopCodon", Boolean.class);

        keyValues = new HashMap<>();
        List<Object> GCU = new ArrayList<>();
        GCU.add("life.core.model.AMINO_ACID.A");
        GCU.add(new Boolean(false));
        keyValues.put("GCU", GCU);
        List<Object> GCC = new ArrayList<>();
        GCC.add("life.core.model.AMINO_ACID.A");
        GCC.add(new Boolean(false));
        keyValues.put("GCC", GCC);
        List<Object> GCA = new ArrayList<>();
        GCA.add("life.core.model.AMINO_ACID.A");
        GCA.add(new Boolean(false));
        keyValues.put("GCA", GCA);
        List<Object> GCG = new ArrayList<>();
        GCG.add("life.core.model.AMINO_ACID.A");
        GCG.add(new Boolean(false));
        keyValues.put("GCG", GCG);
        List<Object> CGU = new ArrayList<>();
        CGU.add("life.core.model.AMINO_ACID.R");
        CGU.add(new Boolean(false));
        keyValues.put("CGU", CGU);
        List<Object> CGC = new ArrayList<>();
        CGC.add("life.core.model.AMINO_ACID.R");
        CGC.add(new Boolean(false));
        keyValues.put("CGC", CGC);
        List<Object> CGA = new ArrayList<>();
        CGA.add("life.core.model.AMINO_ACID.R");
        CGA.add(new Boolean(false));
        keyValues.put("CGA", CGA);
        List<Object> CGG = new ArrayList<>();
        CGG.add("life.core.model.AMINO_ACID.R");
        CGG.add(new Boolean(false));
        keyValues.put("CGG", CGG);
        List<Object> AGA = new ArrayList<>();
        AGA.add("life.core.model.AMINO_ACID.R");
        AGA.add(new Boolean(false));
        keyValues.put("AGA", AGA);
        List<Object> AGG = new ArrayList<>();
        AGG.add("life.core.model.AMINO_ACID.R");
        AGG.add(new Boolean(false));
        keyValues.put("AGG", AGG);
        List<Object> AAU = new ArrayList<>();
        AAU.add("life.core.model.AMINO_ACID.N");
        AAU.add(new Boolean(false));
        keyValues.put("AAU", AAU);
        List<Object> AAC = new ArrayList<>();
        AAC.add("life.core.model.AMINO_ACID.N");
        AAC.add(new Boolean(false));
        keyValues.put("AAC", AAC);
        List<Object> GAU = new ArrayList<>();
        GAU.add("life.core.model.AMINO_ACID.D");
        GAU.add(new Boolean(false));
        keyValues.put("GAU", GAU);
        List<Object> GAC = new ArrayList<>();
        GAC.add("life.core.model.AMINO_ACID.D");
        GAC.add(new Boolean(false));
        keyValues.put("GAC", GAC);
        List<Object> UGU = new ArrayList<>();
        UGU.add("life.core.model.AMINO_ACID.C");
        UGU.add(new Boolean(false));
        keyValues.put("UGU", UGU);
        List<Object> UGC = new ArrayList<>();
        UGC.add("life.core.model.AMINO_ACID.C");
        UGC.add(new Boolean(false));
        keyValues.put("UGC", UGC);
        List<Object> CAA = new ArrayList<>();
        CAA.add("life.core.model.AMINO_ACID.Q");
        CAA.add(new Boolean(false));
        keyValues.put("CAA", CAA);
        List<Object> CAG = new ArrayList<>();
        CAG.add("life.core.model.AMINO_ACID.Q");
        CAG.add(new Boolean(false));
        keyValues.put("CAG", CAG);
        List<Object> GAA = new ArrayList<>();
        GAA.add("life.core.model.AMINO_ACID.E");
        GAA.add(new Boolean(false));
        keyValues.put("GAA", GAA);
        List<Object> GAG = new ArrayList<>();
        GAG.add("life.core.model.AMINO_ACID.E");
        GAG.add(new Boolean(false));
        keyValues.put("GAG", GAG);
        List<Object> UUU = new ArrayList<>();
        UUU.add("life.core.model.AMINO_ACID.F");
        UUU.add(new Boolean(false));
        keyValues.put("UUU", UUU);
        List<Object> UUC = new ArrayList<>();
        UUC.add("life.core.model.AMINO_ACID.F");
        UUC.add(new Boolean(false));
        keyValues.put("UUC", UUC);
        List<Object> UUA = new ArrayList<>();
        UUA.add("life.core.model.AMINO_ACID.L");
        UUA.add(new Boolean(false));
        keyValues.put("UUA", UUA);
        List<Object> UUG = new ArrayList<>();
        UUG.add("life.core.model.AMINO_ACID.L");
        UUG.add(new Boolean(false));
        keyValues.put("UUG", UUG);
        List<Object> CUU = new ArrayList<>();
        CUU.add("life.core.model.AMINO_ACID.L");
        CUU.add(new Boolean(false));
        keyValues.put("CUU", CUU);
        List<Object> CUC = new ArrayList<>();
        CUC.add("life.core.model.AMINO_ACID.L");
        CUC.add(new Boolean(false));
        keyValues.put("CUC", CUC);
        List<Object> CUA = new ArrayList<>();
        CUA.add("life.core.model.AMINO_ACID.L");
        CUA.add(new Boolean(false));
        keyValues.put("CUA", CUA);
        List<Object> CUG = new ArrayList<>();
        CUG.add("life.core.model.AMINO_ACID.L");
        CUG.add(new Boolean(false));
        keyValues.put("CUG", CUG);
        List<Object> UCU = new ArrayList<>();
        UCU.add("life.core.model.AMINO_ACID.S");
        UCU.add(new Boolean(false));
        keyValues.put("UCU", UCU);
        List<Object> UCC = new ArrayList<>();
        UCC.add("life.core.model.AMINO_ACID.S");
        UCC.add(new Boolean(false));
        keyValues.put("UCC", UCC);
        List<Object> UCA = new ArrayList<>();
        UCA.add("life.core.model.AMINO_ACID.S");
        UCA.add(new Boolean(false));
        keyValues.put("UCA", UCA);
        List<Object> UCG = new ArrayList<>();
        UCG.add("life.core.model.AMINO_ACID.S");
        UCG.add(new Boolean(false));
        keyValues.put("UCG", UCG);
        List<Object> AGU = new ArrayList<>();
        AGU.add("life.core.model.AMINO_ACID.S");
        AGU.add(new Boolean(false));
        keyValues.put("AGU", AGU);
        List<Object> AGC = new ArrayList<>();
        AGC.add("life.core.model.AMINO_ACID.S");
        AGC.add(new Boolean(false));
        keyValues.put("AGC", AGC);
        List<Object> UAU = new ArrayList<>();
        UAU.add("life.core.model.AMINO_ACID.Y");
        UAU.add(new Boolean(false));
        keyValues.put("UAU", UAU);
        List<Object> UAC = new ArrayList<>();
        UAC.add("life.core.model.AMINO_ACID.Y");
        UAC.add(new Boolean(false));
        keyValues.put("UAC", UAC);
        List<Object> UAA = new ArrayList<>();
        UAA.add(null);
        UAA.add(new Boolean(true));
        keyValues.put("UAA", UAA);
        List<Object> UAG = new ArrayList<>();
        UAG.add(null);
        UAG.add(new Boolean(true));
        keyValues.put("UAG", UAG);
        List<Object> UGA = new ArrayList<>();
        UGA.add(null);
        UGA.add(new Boolean(true));
        keyValues.put("UGA", UGA);
        List<Object> UGG = new ArrayList<>();
        UGG.add("life.core.model.AMINO_ACID.W");
        UGG.add(new Boolean(false));
        keyValues.put("UGG", UGG);
        List<Object> CCU = new ArrayList<>();
        CCU.add("life.core.model.AMINO_ACID.P");
        CCU.add(new Boolean(false));
        keyValues.put("CCU", CCU);
        List<Object> CCC = new ArrayList<>();
        CCC.add("life.core.model.AMINO_ACID.P");
        CCC.add(new Boolean(false));
        keyValues.put("CCC", CCC);
        List<Object> CCA = new ArrayList<>();
        CCA.add("life.core.model.AMINO_ACID.P");
        CCA.add(new Boolean(false));
        keyValues.put("CCA", CCA);
        List<Object> CCG = new ArrayList<>();
        CCG.add("life.core.model.AMINO_ACID.P");
        CCG.add(new Boolean(false));
        keyValues.put("CCG", CCG);
        List<Object> CAU = new ArrayList<>();
        CAU.add("life.core.model.AMINO_ACID.H");
        CAU.add(new Boolean(false));
        keyValues.put("CAU", CAU);
        List<Object> CAC = new ArrayList<>();
        CAC.add("life.core.model.AMINO_ACID.H");
        CAC.add(new Boolean(false));
        keyValues.put("CAC", CAC);
        List<Object> AUU = new ArrayList<>();
        AUU.add("life.core.model.AMINO_ACID.I");
        AUU.add(new Boolean(false));
        keyValues.put("AUU", AUU);
        List<Object> AUC = new ArrayList<>();
        AUC.add("life.core.model.AMINO_ACID.I");
        AUC.add(new Boolean(false));
        keyValues.put("AUC", AUC);
        List<Object> AUA = new ArrayList<>();
        AUA.add("life.core.model.AMINO_ACID.I");
        AUA.add(new Boolean(false));
        keyValues.put("AUA", AUA);
        List<Object> AUG = new ArrayList<>();
        AUG.add("life.core.model.AMINO_ACID.M");
        AUG.add(new Boolean(false));
        keyValues.put("AUG", AUG);
        List<Object> ACU = new ArrayList<>();
        ACU.add("life.core.model.AMINO_ACID.T");
        ACU.add(new Boolean(false));
        keyValues.put("ACU", ACU);
        List<Object> ACC = new ArrayList<>();
        ACC.add("life.core.model.AMINO_ACID.T");
        ACC.add(new Boolean(false));
        keyValues.put("ACC", ACC);
        List<Object> ACA = new ArrayList<>();
        ACA.add("life.core.model.AMINO_ACID.T");
        ACA.add(new Boolean(false));
        keyValues.put("ACA", ACA);
        List<Object> ACG = new ArrayList<>();
        ACG.add("life.core.model.AMINO_ACID.T");
        ACG.add(new Boolean(false));
        keyValues.put("ACG", ACG);
        List<Object> AAA = new ArrayList<>();
        AAA.add("life.core.model.AMINO_ACID.K");
        AAA.add(new Boolean(false));
        keyValues.put("AAA", AAA);
        List<Object> AAG = new ArrayList<>();
        AAG.add("life.core.model.AMINO_ACID.K");
        AAG.add(new Boolean(false));
        keyValues.put("AAG", AAG);
        List<Object> GUU = new ArrayList<>();
        GUU.add("life.core.model.AMINO_ACID.V");
        GUU.add(new Boolean(false));
        keyValues.put("GUU", GUU);
        List<Object> GUC = new ArrayList<>();
        GUC.add("life.core.model.AMINO_ACID.V");
        GUC.add(new Boolean(false));
        keyValues.put("GUC", GUC);
        List<Object> GUA = new ArrayList<>();
        GUA.add("life.core.model.AMINO_ACID.V");
        GUA.add(new Boolean(false));
        keyValues.put("GUA", GUA);
        List<Object> GUG = new ArrayList<>();
        GUG.add("life.core.model.AMINO_ACID.V");
        GUG.add(new Boolean(false));
        keyValues.put("GUG", GUG);
        List<Object> GGU = new ArrayList<>();
        GGU.add("life.core.model.AMINO_ACID.G");
        GGU.add(new Boolean(false));
        keyValues.put("GGU", GGU);
        List<Object> GGC = new ArrayList<>();
        GGC.add("life.core.model.AMINO_ACID.G");
        GGC.add(new Boolean(false));
        keyValues.put("GGC", GGC);
        List<Object> GGA = new ArrayList<>();
        GGA.add("life.core.model.AMINO_ACID.G");
        GGA.add(new Boolean(false));
        keyValues.put("GGA", GGA);
        List<Object> GGG = new ArrayList<>();
        GGG.add("life.core.model.AMINO_ACID.G");
        GGG.add(new Boolean(false));
        keyValues.put("GGG", GGG);
        EnumGenerator.generateEnum("life.core", "CODON", dataAndTypesMap, keyValues);

    }
}

package life.utils;

import java.io.IOException;

/**
 * Created by alexb on 1/17/2018.
 */
public class Main {

    public static void main(String[] args) throws IOException {

//        TestBacteria testBacteria = (TestBacteria) OrganismUtil.readOrganism(TestBacteria.class);
//        Gene gene1 = new Gene();
//        gene1.setId("5080");
//        gene1.setName("GXP_4405072");
//        gene1.setStartPosition(35l);
//        gene1.setDescription("taxid=9606|spec=Homo sapiens|chr=11|ctg=NC_000011|str=(-)");
//        gene1.setLength(13l);
//        testBacteria.getGenes().add(gene1);
//        Gene gene2 = new Gene();
//        gene2.setId("5190");
//        gene2.setName("GXP_67895846");
//        gene2.setStartPosition(115l);
//        gene2.setDescription("taxid=9606|spec=Homo sapiens|chr=11|ctg=NC_000011|str=(-)");
//        gene2.setLength(51L);
//        testBacteria.getGenes().add(gene2);
//        OrganismUtil.saveOrganism(testBacteria,new File("D:\\life-project\\src\\test\\resources\\TestOrganismDNA.txt"));

//        File srcDnaTextFile = new File(Main.class.getClassLoader().getResource("TestOrganismDNA.txt").getFile());
//        File srcJsonFile = new File(Main.class.getClassLoader().getResource("TestBacteria.json").getFile());
//        TestBacteria testBacteria =  new ObjectMapper().readValue(srcJsonFile,TestBacteria.class);

//        OrganismUtil.saveOrganism(testBacteria,srcDnaTextFile);
//
//        TestBacteria testBacteria1 = (TestBacteria) OrganismUtil.readOrganism(TestBacteria.class);
//        AssertTrue("",testBacteria.equals(testBacteria1));
//

//        if (dstbyn.exists()) {
//            dstbyn.delete();
//        }
//        dstJson.createNewFile();
//        if (dstJson.exists()) {
//            dstJson.delete();
//        }
//        dstJson.createNewFile();
//        DNAUtils.generateDNABinaryFile(TestBacteria.class, src, dstJson, dstbyn);

//
//        ObjectMapper mapper = new ObjectMapper();
//
//
////        System.out.println(dstbyn.length());
//
//        File dstbyn = new File(FileUtils.getPathToGeneratedFile(TestBacteria.class, "dna"));
//        File dstJson = new File(FileUtils.getPathToGeneratedFile(TestBacteria.class, "json"));
//        RandomAccessFile rFile = new RandomAccessFile(dstbyn, "r");
//        byte[] dna = new byte[(int) dstbyn.length()];
//        rFile.seek(0);
//        for (int i = 0; i < dstbyn.length(); i++) {
//            dna[i] = rFile.readByte();
//            System.out.print(dna[i]);
//            System.out.print(" ");
//        }
//        rFile.close();
//        System.out.println();
//        TestBacteria testBacteria = mapper.readValue(dstJson, TestBacteria.class);
//        System.out.println(testBacteria.toString());
//        System.out.println(DNAUtils.getSequenceFromBytes(dna, testBacteria.getDnaLength()));
//
////        for (int i = 0; i < bytes.length; i++) {
////            System.out.println(Integer.toString(0xFF & bytes[i]));
////        }
//        rFile.seek(dst.length() - Long.BYTES);
//        long dataLength = rFile.readLong();
//        System.out.println(dataLength);
//        return;
//
//        TestBacteria ecoli = new TestBacteria();
//        System.out.println(ecoli.toString());
//        byte[] bytes = ecoli.getDNA().asByteArray();
////        for (int i = 0; i < bytes.length; i++) {
////            System.out.println(Integer.toString(0xFF & bytes[i]));
////        }
//
//
//        DnaSequence testDna = new DnaSequence(bytes, ecoli.getDNA().toString().length());
//        System.out.println(testDna.toString());
//
//
//        OrganismUtil.saveOrganism(ecoli);
//        testDna = OrganismUtil.readOrganism(ecoli);
//        System.out.println(testDna.toString());

//        s
//
//
// hort shortVal = 0b00110000;
//        short bits78A = 0b00000000;
//        short bits78T = 0b01000000;
//        short bits78G = 0b10000000;
//        short bits78C = 0b11000000;
//        System.out.println(shortVal);
//        System.out.println("A : "+(shortVal & bits78A));
//        System.out.println("T : "+(shortVal & bits78T));
//        System.out.println("G : "+(shortVal & bits78G));
//        System.out.println("C : "+(shortVal & bits78C));
//
//        System.out.println("A : "+(shortVal << 2 & bits78A));
//        System.out.println("T : "+(shortVal << 2 & bits78T));
//        System.out.println("G : "+(shortVal << 2 & bits78G));
//        System.out.println("C : "+(shortVal << 2 & bits78C));

//        System.out.println(byte3 >> 1);
//        System.out.println(byte3 << 1);

//        byte[] dnaBp = new byte[]{0b00011011,0b00000011,0b00011000,0b01011011};
//
//        File file = new File(Main.class.getClassLoader().getResource("data.dna").getFile());
//        System.out.println(file.getAbsolutePath());
//        RandomAccessFile file1 = new RandomAccessFile(file, "rw");
//        file1.writeByte(1);
//        file1.writeInt(dnaBp.length);
//        file1.write(dnaBp);
//        System.out.println(dnaBp.length);
//        file1.close();
//        RandomAccessFile file2 = new RandomAccessFile(file, "r");
//        file2.seek(0);
//        byte b = file2.readByte();
//        System.out.println(b);
//        int i = file2.readInt();
//        System.out.println(i);
//        byte[] bytes = new byte[dnaBp.length];
//        file2.read(bytes);
//        System.out.println(bytes[1]);
//        short highest2Bits = 0b11000000;
//        System.out.println(bytes[1] & highest2Bits);
//        System.out.println(bytes[1] << 2 & highest2Bits);
//        System.out.println(bytes[1] << 4 & highest2Bits);
//        System.out.println(bytes[1] << 6 & highest2Bits);
//
//        switch (bytes[0] & highest2Bits){
//            case 0:
//                break;
//            case 64:
//                break;
//            case 128:
//                break;
//            case 192:
//                break;
//
//        }

//
//
//        file2.close();
//
//
//        file1.close();

//        LinkedHashMap<String, Class> classDataAndTypesMap = new LinkedHashMap<>();
//        Map<String, List<Object>> classKeyValues = new HashMap<>();
//        classKeyValues.put("ACID", null);
//        classKeyValues.put("ALIPHATIC", null);
//        EnumGenerator.generateEnum("life.core.utils", "SIDE_CHAIN_CLASS", classDataAndTypesMap, classKeyValues);
//
//
//        LinkedHashMap<String, Class> dataAndTypesMap = new LinkedHashMap<>();
//        dataAndTypesMap.put("name", String.class);
//        dataAndTypesMap.put("threeLettersName", String.class);
//        dataAndTypesMap.put("oneLetterName", String.class);
//        dataAndTypesMap.put("SIDE_CHAIN_CLASS", CIRCLE_REFERENCED_ENUM.class);
//
//        Map<String, List<Object>> keyValues = new HashMap<>();
//        List<Object> L = new ArrayList<>();
//        L.add("Leucine");
//        L.add("Leu");
//        L.add("L");
//        L.add("SIDE_CHAIN_CLASS.ALIPHATIC");
//        keyValues.put("L", L);
//        List<Object> A = new ArrayList<>();
//        A.add("Alanine");
//        A.add("Ala");
//        A.add("A");
//        A.add("SIDE_CHAIN_CLASS.ALIPHATIC");
//        keyValues.put("A", A);
//        EnumGenerator.generateEnum("life.core.utils", "AMINO_ACID", dataAndTypesMap, keyValues);
//
//        TestBacteria ecoli = new TestBacteria();
//        System.out.println(ecoli);

    }
}

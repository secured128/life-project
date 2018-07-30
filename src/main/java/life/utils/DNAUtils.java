package life.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import life.core.model.DnaSequence;
import life.core.model.AMINO_ACID;
import life.organism.Organism;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static life.core.model.AMINO_ACID.*;

/**
 * Created by alexb on 2/11/2018.
 */
public class DNAUtils {

    protected static final Map<Character, AMINO_ACID> DNA_AMINO_ACID = new HashMap<>(4);
    protected static final Map<Short, AMINO_ACID> DNA_AMINO_ACID_BINARY = new HashMap<>(4);
    protected static final Map<AMINO_ACID, Short> DNA_BINARY_AMINO_ACID = new HashMap<>(4);

    private static final short lowest2Bits = 0b00000011;
    private static final short BUFFER_SIZE = 4;

    private static final Short aBinaryCode = Short.valueOf((byte) 0b00);
    private static final Short tBinaryCode = Short.valueOf((byte) 0b01);
    private static final Short gBinaryCode = Short.valueOf((byte) 0b10);
    private static final Short cBinaryCode = Short.valueOf((byte) 0b11);

    static {
        DNA_AMINO_ACID.put(A.getOneLetterName(), A);
        DNA_AMINO_ACID.put(T.getOneLetterName(), T);
        DNA_AMINO_ACID.put(G.getOneLetterName(), G);
        DNA_AMINO_ACID.put(C.getOneLetterName(), C);

        DNA_AMINO_ACID_BINARY.put(aBinaryCode, A);
        DNA_AMINO_ACID_BINARY.put(tBinaryCode, T);
        DNA_AMINO_ACID_BINARY.put(gBinaryCode, G);
        DNA_AMINO_ACID_BINARY.put(cBinaryCode, C);

        DNA_BINARY_AMINO_ACID.put(A, aBinaryCode);
        DNA_BINARY_AMINO_ACID.put(T, tBinaryCode);
        DNA_BINARY_AMINO_ACID.put(G, gBinaryCode);
        DNA_BINARY_AMINO_ACID.put(C, cBinaryCode);
    }

    public static List<AMINO_ACID> getSequenceFromString(String dnaSequence) {
        List<AMINO_ACID> dnaSequenceList = new ArrayList<>();
        dnaSequence.chars().forEach(c -> {
            if (StringUtils.isNotBlank(String.copyValueOf(new char[]{(char) c}))) {
                if (DNA_AMINO_ACID.containsKey((char) Character.toUpperCase(c))) {
                    dnaSequenceList.add(DNA_AMINO_ACID.get((char) Character.toUpperCase(c)));
                } else {
                    throw new Error("Invalid character " + (char) c + " detected");
                }
            }
        });
        return dnaSequenceList;
    }

    public static List<AMINO_ACID> getSequenceFromBytes(byte[] dnaByteSequence, long numberOfBP) {
        List<AMINO_ACID> dnaSequenceList = new ArrayList<>();
        if (dnaByteSequence != null) {
            for (int i = 0; i < dnaByteSequence.length; i++) {
                for (int j = 3; j >= 0 && dnaSequenceList.size() < numberOfBP; j--) {
                    AMINO_ACID aminoAsid = DNA_AMINO_ACID_BINARY.get((short) ((dnaByteSequence[i] >> (j * 2)) & lowest2Bits));
                    if (aminoAsid != null) {
                        dnaSequenceList.add(aminoAsid);
                        if (dnaByteSequence.length >= numberOfBP) {
                            return dnaSequenceList;
                        }
                    } else {
                        throw new Error("Invalid AMINO_ACID " + (dnaByteSequence[i] << j * 2 & lowest2Bits) + " detected");
                    }
                }
            }
        } else {
            throw new Error("Empty sequence detected");
        }
        return dnaSequenceList;
    }

    public static byte[] getBytesFromSequence(List<AMINO_ACID> dnaSequenceList) {
        int numberOfFullBytes = dnaSequenceList.size() / 4;
        int numberOfPairsInLastByte = dnaSequenceList.size() % 4;
        byte[] dnaBpBytes = new byte[numberOfFullBytes + ((numberOfPairsInLastByte == 0) ? 0 : 1)];
        int k = 0;
        for (int i = 0; i < dnaSequenceList.size(); i = i + 4) {
            short newByte = 0b00000000;
            for (int j = 0; j < ((k < numberOfFullBytes) ? 4 : numberOfPairsInLastByte); j++) {
                int shift = (3 - j) * 2;
                switch (dnaSequenceList.get(i + j)) {
                    case A:
                        newByte = (short) (newByte | DNA_BINARY_AMINO_ACID.get(A) << shift);
                        break;
                    case T:
                        newByte = (short) (newByte | DNA_BINARY_AMINO_ACID.get(T) << shift);
                        break;
                    case G:
                        newByte = (short) (newByte | DNA_BINARY_AMINO_ACID.get(G) << shift);
                        break;
                    case C:
                        newByte = (short) (newByte | DNA_BINARY_AMINO_ACID.get(C) << shift);
                        break;
                    default:
                        throw new Error("Invalid character " + dnaSequenceList.get(i + j) + " detected");
                }
            }
            dnaBpBytes[k] = (byte) newByte;
            k++;
        }
        return dnaBpBytes;
    }

    private static byte[] getBytesFromCharArray(Byte[] arrayOfChars) {
        int dataLength = arrayOfChars.length;
        int numberOfFullBytes = dataLength / 4;
        int numberOfPairsInLastByte = dataLength % 4;
        byte[] dnaBpBytes = new byte[numberOfFullBytes + ((numberOfPairsInLastByte == 0) ? 0 : 1)];
        int k = 0;
        for (int i = 0; i < dataLength; i = i + 4) {
            short newByte = 0b00000000;
            for (int j = 0; j < ((k < numberOfFullBytes) ? 4 : numberOfPairsInLastByte); j++) {
                int shift = (3 - j) * 2;
                switch (arrayOfChars[i + j]) {
                    case 65:
                        newByte = (short) (newByte | DNA_BINARY_AMINO_ACID.get(A) << shift);
                        break;
                    case 84:
                        newByte = (short) (newByte | DNA_BINARY_AMINO_ACID.get(T) << shift);
                        break;
                    case 71:
                        newByte = (short) (newByte | DNA_BINARY_AMINO_ACID.get(G) << shift);
                        break;
                    case 67:
                        newByte = (short) (newByte | DNA_BINARY_AMINO_ACID.get(C) << shift);
                        break;
                    default:
                        throw new Error("Invalid character " + (char) (byte) arrayOfChars[i + j] + " detected");
                }
            }
            dnaBpBytes[k] = (byte) newByte;
            k++;
        }
        return dnaBpBytes;
    }

    public static DnaSequence getDnaSequence(Organism organism, File sequenceBinaryFile, long startPosition, int length) throws IOException {
        RandomAccessFile rFile = new RandomAccessFile(sequenceBinaryFile, "r");
        long startByte = startPosition / 4;
        long firstByteOffset = startPosition % 4;
        int bytesToRead = length / 4 + 1;
        rFile.seek(startByte);
        List<AMINO_ACID> dnaSequence = new ArrayList<>();
        byte[] dna = new byte[1];
        for (int i = 0; i <= bytesToRead; i++) {
            dna[0] = rFile.readByte();
            List<AMINO_ACID> dnaSequenceOf4 = getSequenceFromBytes(dna, 4);
            if (i == 0 && firstByteOffset > 0) {
                dnaSequence.addAll(dnaSequenceOf4.subList((int) firstByteOffset, 4));
            } else {
                for (int j = 0; j < 4; j++) {
                    if (dnaSequence.size() < length) {
                        dnaSequence.add(dnaSequenceOf4.get(j));
                    } else {
                        return new DnaSequence(dnaSequence);
                    }
                }

            }
        }
        return new DnaSequence(dnaSequence);
    }

    public static void generateDNABinaryFile(Organism organism, File sourceSequenceTextFile, File destinationGenomeJsonFile, File destinationSequenceBinaryFile) throws IOException {
        InputStream inputStreamData = null;
        OutputStream outputStreamData = null;
        try {
            inputStreamData = new BufferedInputStream(new FileInputStream(sourceSequenceTextFile));
            outputStreamData = new BufferedOutputStream(new FileOutputStream(destinationSequenceBinaryFile));
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            Long totalDnaLength = 0l;
            List<Byte> validBytes = new ArrayList<>();
            while ((length = inputStreamData.read(buffer)) > 0) {
                for (int i = 0; i < length; i++) {
                    switch (buffer[i]) {
                        case 65:
                        case 97:
                            validBytes.add((byte) 'A');
                            break;
                        case 84:
                        case 116:
                            validBytes.add((byte) 'T');
                            break;
                        case 71:
                        case 103:
                            validBytes.add((byte) 'G');
                            break;
                        case 67:
                        case 99:
                            validBytes.add((byte) 'C');
                            break;
                        case 32:
                        case 9:
                        case 10:
                        case 11:
                        case 13:
                            break;
                        default:
                            throw new Error("Invalid character " + (char) buffer[i] + " detected");
                    }
                }
                if (validBytes.size() >= BUFFER_SIZE) {
                    Byte[] byteArray = new Byte[BUFFER_SIZE];
                    validBytes.subList(0, BUFFER_SIZE).toArray(byteArray);
                    validBytes = validBytes.subList(BUFFER_SIZE, validBytes.size());
                    byte[] binaryFormattedDna = getBytesFromCharArray(byteArray);
                    outputStreamData.write(binaryFormattedDna, 0, binaryFormattedDna.length);
                    totalDnaLength = totalDnaLength + BUFFER_SIZE;
                }
            }
            if (validBytes.size() > 0) {
                Byte[] byteArray = new Byte[validBytes.size()];
                validBytes.toArray(byteArray);
                byte[] binaryFormattedDna = getBytesFromCharArray(byteArray);
                outputStreamData.write(binaryFormattedDna, 0, binaryFormattedDna.length);
                totalDnaLength = totalDnaLength + validBytes.size();
            }
            organism.setDnaLength(totalDnaLength);
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(destinationGenomeJsonFile, organism);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStreamData.close();
            inputStreamData.close();
        }
    }

//    public static byte[] longToBytes(long l) {
//        byte[] result = new byte[8];
//        for (int i = 7; i >= 0; i--) {
//            result[i] = (byte) (l & 0xFF);
//            l >>= 8;
//        }
//        return result;
//    }
//
//    public static long bytesToLong(byte[] b) {
//        long result = 0;
//        for (int i = 0; i < 8; i++) {
//            result <<= 8;
//            result |= (b[i] & 0xFF);
//        }
//        return result;
//    }

}

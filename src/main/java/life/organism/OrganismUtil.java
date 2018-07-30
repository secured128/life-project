package life.organism;

import com.fasterxml.jackson.databind.ObjectMapper;
import life.core.model.DnaSequence;
import life.core.model.RnaSequence;
import life.utils.DNAUtils;
import life.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by alexb on 2/8/2018.
 */
public class OrganismUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String ORGANIZM_DNA_BINARY_FILE_EXTENTION = "dna";
    private static final String ORGANIZM_METADATA_JSON_FILE_EXTENTION = "json";

    public static RnaSequence getGeneRNA(Organism organism, Gene gene) throws IOException {
        File dnaBin = new File(FileUtils.getPathToGeneratedFile(organism.getClass(), ORGANIZM_DNA_BINARY_FILE_EXTENTION));
        DnaSequence dnaSequence = DNAUtils.getDnaSequence(organism, dnaBin, gene.getStartPosition().longValue(), (int) gene.getLength().longValue());
        return new RnaSequence(dnaSequence);
    }

    public static void saveOrganism(Organism organism, File dnaBpTestFile) throws IOException {
        File dstBin = new File(FileUtils.getPathToGeneratedFile(organism.getClass(), ORGANIZM_DNA_BINARY_FILE_EXTENTION));
        File dstJson = new File(FileUtils.getPathToGeneratedFile(organism.getClass(), ORGANIZM_METADATA_JSON_FILE_EXTENTION));
        if (dstBin.exists()) {
            dstBin.delete();
        }
        dstJson.createNewFile();
        if (dstJson.exists()) {
            dstJson.delete();
        }
        dstJson.createNewFile();
        DNAUtils.generateDNABinaryFile(organism, dnaBpTestFile, dstJson, dstBin);
    }

    public static Organism readOrganism(Class<? extends Organism> organismClass) throws Exception {
        File dnaBin = new File(FileUtils.getPathToGeneratedFile(organismClass, ORGANIZM_DNA_BINARY_FILE_EXTENTION));
        if (dnaBin.exists()) {
            File dstJson = new File(FileUtils.getPathToGeneratedFile(organismClass, ORGANIZM_METADATA_JSON_FILE_EXTENTION));
            return mapper.readValue(dstJson, organismClass);
        } else {
            throw new Exception("Missing DNA binary file : " + dnaBin.getAbsolutePath());
        }
    }


}

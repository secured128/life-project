package life.organism;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import life.core.model.RnaSequence;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by alexb on 2/5/2018.
 */
@JsonPropertyOrder({"class_name", "name", "dna_length", "genes"})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractOrganism implements Organism {

    private ObjectMapper jsonMapper = new ObjectMapper();

    @JsonProperty("class_name")
    private String className = StringUtils.EMPTY;
    @JsonProperty("name")
    private String name = StringUtils.EMPTY;
    @JsonProperty("dna_length")
    private Long dnaLength = 0l;
    @JsonProperty("genes")
    private List<Gene> genes = new ArrayList<>();


    public AbstractOrganism() {
        super();
    }

    public AbstractOrganism(Class clazz, String name, long dnaLength) {
        super();
        setClassName(clazz.getCanonicalName());
        setName(name);
        setDnaLength(dnaLength);
        jsonMapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDnaLength() {
        return dnaLength;
    }

    public void setDnaLength(Long dnaLength) {
        this.dnaLength = dnaLength;
    }

    public List<Gene> getGenes() {
        return genes;
    }

    public void setGenes(List<Gene> genes) {
        this.genes = genes;
    }

    public Gene getGeneById(String geneId) {
        Optional<Gene> geneOptional = this.genes.stream().filter(g -> g.getId().equals(geneId)).findFirst();
        if (geneOptional.isPresent()) {
            return geneOptional.get();
        } else {
            return null;
        }
    }

    public Gene getGeneByName(String geneName) {
        Optional<Gene> geneOptional = this.genes.stream().filter(g -> g.getName().equals(geneName)).findFirst();
        if (geneOptional.isPresent()) {
            return geneOptional.get();
        } else {
            return null;
        }
    }

    public RnaSequence getGeneRNA(Gene gene) throws IOException {
        return OrganismUtil.getGeneRNA(this, gene);
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        try {
            jsonMapper.writeValue(sw, this);
            return sw.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Organism)) {
            return false;
        }
        Organism organism = (Organism) obj;
        if (this.getClassName().equals(organism.getClassName()) && this.getName().equals(organism.getName()) && this.getDnaLength().longValue() == organism.getDnaLength().longValue() && this.getGenes().size() == organism.getGenes().size()) {
            for (int i = 0; i < this.getGenes().size(); i++) {
                if (!this.getGenes().get(i).equals(organism.getGenes().get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;

    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}

package life.organism;

import java.util.List;

/**
 * Created by alexb on 2/4/2018.
 */
public interface Organism {
    public String getClassName();

    public void setClassName(String className);

    public String getName();

    public void setName(String name);

    public Long getDnaLength();

    public void setDnaLength(Long dnaLength);

    public List<Gene> getGenes();

    public void setGenes(List<Gene> genes);
}

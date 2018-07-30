package life.organism;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by alexb on 2/12/2018.
 */
@JsonPropertyOrder({"id", "name", "description", "start_position", "length"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gene {

    @JsonProperty("id")
    private String id = StringUtils.EMPTY;
    @JsonProperty("name")
    private String name = StringUtils.EMPTY;
    @JsonProperty("description")
    private String description = StringUtils.EMPTY;
    @JsonProperty("start_position")
    private Long startPosition = 0l;
    @JsonProperty("length")
    private Long length = 0l;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Long startPosition) {
        this.startPosition = startPosition;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Gene)) {
            return false;
        }
        Gene gene = (Gene) obj;
        if (this.getId().equals(gene.getId()) && this.getName().equals(gene.getName()) && this.getLength().longValue() == gene.getLength().longValue() && this.getStartPosition().longValue() == gene.getStartPosition().longValue() && this.getDescription().equals(gene.getDescription())) {
            return true;
        }
        return false;

    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}

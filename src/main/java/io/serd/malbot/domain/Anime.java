package io.serd.malbot.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Atte Lassila
 */
@Data
@XmlRootElement
public class Anime {
    @XmlElement(name = "entry")
    private List<Entry> entries;
}

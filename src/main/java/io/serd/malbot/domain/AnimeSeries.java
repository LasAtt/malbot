package io.serd.malbot.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Atte Lassila
 */
@Data
@XmlRootElement(name = "anime")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimeSeries {
    @XmlElement(name = "entry")
    private List<AnimeEntry> entries;
}

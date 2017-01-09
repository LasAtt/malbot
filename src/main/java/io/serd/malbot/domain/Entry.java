package io.serd.malbot.domain;

import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Atte Lassila
 */
@Data
@XmlRootElement
public class Entry {
    @XmlElement
    private long id;
    @XmlElement
    private String title;
    @XmlElement
    private String english;
    @XmlElement
    private String synonyms;
    @XmlElement
    private int episodes;
    @XmlElement
    private String score;
    @XmlElement
    private String type;
    @XmlElement
    private String status;
    @XmlElement
    private Date start_date;
    @XmlElement
    private Date end_date;
    @XmlElement
    private String synopsis;
    @XmlElement
    private URI image;
}

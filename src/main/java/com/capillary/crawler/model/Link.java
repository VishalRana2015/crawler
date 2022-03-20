package com.capillary.crawler.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
public class Link {
    @EmbeddedId
    LinkId linkId;
    Integer level;
}

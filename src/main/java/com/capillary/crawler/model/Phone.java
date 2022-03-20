package com.capillary.crawler.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
public class Phone {
    @EmbeddedId
    PhoneId phoneId;
    Integer depth;
}

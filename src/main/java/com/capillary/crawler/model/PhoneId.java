package com.capillary.crawler.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class PhoneId implements Serializable {
    @ManyToOne // This owns the relationship
    Source source;
    String phoneNumber;
}

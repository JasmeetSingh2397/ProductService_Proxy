package com.example.personal_productserviceproxy.InhertitanceExamples.singleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="sc_Mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private int gradYear;


}

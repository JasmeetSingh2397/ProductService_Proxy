package com.example.personal_productserviceproxy.InhertitanceExamples.mappedSuperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="mps_Mentor")
public class Mentor extends User {
    private int gradYear;
}

package com.example.personal_productserviceproxy.InhertitanceExamples.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "tpc_ta")

public class TA extends User {
    private double rating;
}

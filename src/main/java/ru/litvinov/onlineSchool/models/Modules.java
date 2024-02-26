package ru.litvinov.onlineSchool.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "modules")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Modules {

    @Id
    @Column(name = "module_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "module_name", nullable = false)
    private String moduleName;

    @Column(name = "module_description")
    private String ModuleDescription;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Courses course;

    @OneToMany(mappedBy = "module")
    private List<TheoryMaterials> theoryMaterials;

    @OneToMany(mappedBy = "module")
    private List<QuizQuestions> questions;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private List<PurchasedModules> purchasedModules;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private List<IndividualTestResults> individualTestResults;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private List<ProgressRecords> progressRecords;

    public Modules(String moduleName, String moduleDescription) {
        this.moduleName = moduleName;
        ModuleDescription = moduleDescription;
    }
}

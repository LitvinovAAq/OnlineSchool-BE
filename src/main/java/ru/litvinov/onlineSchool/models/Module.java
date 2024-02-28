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
public class Module {

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
    private Course course;

    @OneToMany(mappedBy = "module")
    private List<TheoryMaterial> theoryMaterials;

    @OneToMany(mappedBy = "module")
    private List<QuizQuestion> questions;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private List<PurchasedModule> purchasedModules;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private List<IndividualTestResult> individualTestResults;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private List<ProgressRecord> progressRecords;

    public Module(String moduleName, String moduleDescription) {
        this.moduleName = moduleName;
        ModuleDescription = moduleDescription;
    }
}

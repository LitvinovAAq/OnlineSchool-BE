package ru.litvinov.onlineSchool.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "individual_test_results")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class IndividualTestResult {

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "app_user_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    private Module module;

}

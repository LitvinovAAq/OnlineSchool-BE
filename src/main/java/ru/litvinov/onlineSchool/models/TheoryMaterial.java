package ru.litvinov.onlineSchool.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "theory_materials")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TheoryMaterial {

    @Id
    @Column(name = "material_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "material_title")
    private String materialTitle;

    @Column(name = "material_text")
    private String materialText;

    @Column(name = "order_number")
    private int orderNumber;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    private Module module;
}

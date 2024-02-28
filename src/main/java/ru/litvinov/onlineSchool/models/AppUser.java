package ru.litvinov.onlineSchool.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "app_users")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "lastname")
    private String lastname;

    @NotEmpty(message = "Email не должен быть пустым")
    @Email(message = "Неверный формат Email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registration_date", nullable = false)
    private Timestamp registrationDate;

    @OneToMany(mappedBy = "appUser")
    private List<UserAnswer> answers;

    @OneToMany(mappedBy = "appUser")
    private List<ProgressRecord> progress;

    @OneToMany(mappedBy = "appUser")
    private List<PurchasedModule> purchasedModules;

    @OneToMany(mappedBy = "appUser")
    private List<IndividualTestResult> individualTestsResults;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private AppUserRole appUserRole;

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = Timestamp.valueOf(registrationDate);
    }
}

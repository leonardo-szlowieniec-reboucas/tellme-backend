package leoreboucas.com.tellme.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String email;
    @OneToMany(mappedBy = "survey", cascade = CascadeType.PERSIST)
    private List<Respondent> respondents;
}

package leoreboucas.com.tellme.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Respondent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private Boolean isDone = false;
    @ManyToOne
    @JoinColumn(name="survey_id", nullable=false)
    private Survey survey;
}

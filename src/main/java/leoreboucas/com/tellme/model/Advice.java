package leoreboucas.com.tellme.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table
public class Advice {
    //TO DO: add dateCreated... length...
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long idAdvised;
    @NotNull
    private String description;
}

package leoreboucas.com.tellme.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Advice {
    //TODO: add dateCreated... validation...
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long idAdvised;
    @NotNull
    private String description;
}

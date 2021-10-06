package leoreboucas.com.tellme.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//Counselor
public class Adviser {
    //TODO: add dateCreated... validation...
    //TODO: not use ManyToOne (bidirectional)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private Boolean isDone = false;
//    @NotNull
//    private Long idAdvised;

//      @ManyToOne
//      @JoinColumn(name="id_advised", nullable=false)
//    private Long idAdvised;
//    @ManyToOne
//    @JoinColumn(name="id_advised", nullable=false)
//    private Advised advised;

    @ManyToOne
    @JoinColumn(name="id_advised", nullable=false)
    private Advised advised;
}

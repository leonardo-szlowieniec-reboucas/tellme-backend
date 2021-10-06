package leoreboucas.com.tellme.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//Who will receive or received advice
public class Advised {
    //TODO: add dateCreated... validation...
    //TODO: not use OneToMany (bidirectional)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String email;

//    @OneToMany(mappedBy = "advised", cascade = CascadeType.PERSIST)
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "idAdvised", referencedColumnName = "id")

    @OneToMany(mappedBy = "advised", cascade = CascadeType.PERSIST)
    private List<Adviser> advisers;
}

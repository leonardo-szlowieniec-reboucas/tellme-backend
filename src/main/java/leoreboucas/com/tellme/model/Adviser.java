package leoreboucas.com.tellme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Adviser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Boolean isDone;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_advised", nullable=false)
    private Advised advised;
}

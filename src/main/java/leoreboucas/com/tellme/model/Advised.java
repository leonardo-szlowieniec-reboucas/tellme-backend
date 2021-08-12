package leoreboucas.com.tellme.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Advised {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String email;

    @OneToMany(mappedBy = "advised", cascade = CascadeType.PERSIST)
    private List<Adviser> advisers;
}

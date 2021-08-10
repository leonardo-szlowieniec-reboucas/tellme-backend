package leoreboucas.com.tellme.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_advised", referencedColumnName = "id")
    private List<Adviser> advisers;
}

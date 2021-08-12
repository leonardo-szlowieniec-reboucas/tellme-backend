package leoreboucas.com.tellme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //@ManyToOne(cascade = CascadeType.ALL, optional = false)
    //@ManyToOne(fetch = FetchType.LAZY, optional = false)

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "weakness_id", referencedColumnName = "id")


//    public Test(String name, Weakness weakness) {
//        this.name = name;
//        this.weakness = weakness;
//        System.out.println(">>>>>>>>>>>>>>>>>>>++++"+this.weakness);
//    }


    //@ManyToOne
    //@JoinColumn(name = "weakness_id", insertable = false, updatable = false)

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="weakness_id", nullable=false)
    private Weakness weakness;
}

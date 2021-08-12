package leoreboucas.com.tellme.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table
public class Weakness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
//    @OneToMany(mappedBy = "weakness", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@OneToMany(cascade = CascadeType.ALL)
    //@OneToMany(mappedBy = "weakness", cascade = CascadeType.ALL)
//    @OneToMany
//    @JoinColumn(name = "weakness_id")
//    private List<Test> tests = new ArrayList<>();

    @OneToMany(mappedBy = "weakness", cascade = CascadeType.PERSIST)
    private List<Test> tests;
}

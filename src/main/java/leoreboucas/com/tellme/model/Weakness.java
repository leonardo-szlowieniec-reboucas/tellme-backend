package leoreboucas.com.tellme.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Weakness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
}

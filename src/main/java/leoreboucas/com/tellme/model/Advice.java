package leoreboucas.com.tellme.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Advice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idAdvised;
    private String description;
}

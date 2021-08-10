package leoreboucas.com.tellme.model;

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

    public Adviser(String email) {
        this.email = email;
    }

    public Adviser() {
    }
}

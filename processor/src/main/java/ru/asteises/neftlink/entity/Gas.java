package ru.asteises.neftlink.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Основная сущность;
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "gas")
public class Gas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "gas_type", nullable = false)
    private String gasType;

    @OneToMany(mappedBy = "gas")
    private Set<Order> orders;

}

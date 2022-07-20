package ru.asteises.neftlink.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

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
    @Column(name = "id")
    private UUID id;

    @Column(name = "gas_type", nullable = false)
    private String gasType;

    @Column(name = "is_visible")
    private boolean visible;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Gas gas = (Gas) o;
        return getId() != null && Objects.equals(getId(), gas.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

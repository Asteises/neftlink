package ru.asteises.neftlink.entity;

import  lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Основная сущность;
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "base")
public class Base implements Serializable {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "is_visible")
    private boolean visible;

    @OneToMany(mappedBy = "base", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Base base = (Base) o;
        return getId() != null && Objects.equals(getId(), base.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

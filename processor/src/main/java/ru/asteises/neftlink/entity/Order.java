package ru.asteises.neftlink.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Основная сущность;
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
@AllArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    private UUID id;

    /**
    Стоимость может быть не указана исходя из некоторых условий. 1 - просто нет цены от поставщика.
    2 - цена долго не обновлялась.
     */
    @Column(name = "cost")
    private Long cost;

    /**
    Для того, чтобы объявить сторону, которая не несет ответственности за отношения, используется атрибут mappedBy
    в сущности Order. Он ссылается на имя свойства связи (order) на стороне владельца.
     */
    //TODO посмотреть как делать ManyToOne
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gas_id", referencedColumnName = "id", nullable = false)
    private Gas gas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_id", referencedColumnName = "id")
    private Base base;

    // Тут нужно выбрать подходящий класс
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

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
        Order order = (Order) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

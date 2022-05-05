package ru.asteises.neftlink.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Основная сущность;
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order")

public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /*
    Стоимость может быть не указана исходя из некоторых условий. 1 - просто нет цены от поставщика.
    2 - цена долго не обновлялась.
     */
    @Column(name = "cost")
    private Long cost;

    /*
    Для того, чтобы объявить сторону, которая не несет ответственности за отношения, используется атрибут mappedBy
    в сущности Order. Он ссылается на имя свойства связи (order) на стороне владельца.
     */
    //TODO посмотреть как делать ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gas_id", referencedColumnName = "id", nullable = false)
    private Gas gas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_id", referencedColumnName = "id")
    private Base base;

    // Тут нужно выбрать подходящий класс
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
}

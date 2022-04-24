package ru.asteises.neftlink.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order implements Serializable {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /*
    Стоимость может быть не указана исходя из некоторых условий. 1 - просто нет цены от поставщика.
    2 - цена долго не обновлялась.
     */
    @Column(name = "cost")
    private Double cost;

    /*
    Для того, чтобы объявить сторону, которая не несет ответственности за отношения, используется атрибут mappedBy
    в сущности Order. Он ссылается на имя свойства связи (order) на стороне владельца.
     */
    @OneToOne(mappedBy = "order")
    private User userOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gas_id", nullable = false)
    private Gas gas;

    @OneToOne(mappedBy = "base")
    private Base baseOwner;

    // Тут нужно выбрать подходящий класс
    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "create_date", nullable = false)
    private Date createDate;
}

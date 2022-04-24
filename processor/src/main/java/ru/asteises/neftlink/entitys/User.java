package ru.asteises.neftlink.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
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

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    // ИНН организации и есть уникальный идентификатор, возможно стоит его и установить как id ?
    @Column(name = "inn", unique = true, nullable = false)
    private Integer inn;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    /*
    Для связи один к одному в обоих классах, к соответствующим полям добавляется аннотация @OneToOne. Параметр optional
    говорит JPA, является ли значение в этом поле обязательным или нет. Связанное поле в User объявлено с помощью
    аннотации @JoinColumn, параметр name которой обозначает поле в БД для создания связи.

    Со стороны владельца к аннотации @OneToOne добавляется параметр cascade. В однонаправленных отношениях одна из
    сторон (и только одна) должна быть владельцем и нести ответственность за обновление связанных полей. В этом случае
    владельцем выступает сущность User. Каскадирование позволяет указать JPA, что необходимо «сделать со связанным
    объектом при выполнении операции с владельцем». То есть, когда удаляется User из базы, JPA самостоятельно определит
    наличие у него Order и удалит вначале Order, потом User.

    Связь в БД между таблицами users и orders осуществляется посредством поля orderId в таблице users.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;
}

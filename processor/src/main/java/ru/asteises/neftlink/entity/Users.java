package ru.asteises.neftlink.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Set<Order> orderList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }


}

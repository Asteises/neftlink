package ru.asteises.neftlink.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asteises.neftlink.entitys.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

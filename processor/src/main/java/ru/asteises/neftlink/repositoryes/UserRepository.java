package ru.asteises.neftlink.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asteises.neftlink.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}

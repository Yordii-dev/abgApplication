package telmex.abg.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import telmex.abg.entidad.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}

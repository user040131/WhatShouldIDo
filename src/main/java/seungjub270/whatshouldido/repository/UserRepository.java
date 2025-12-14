package seungjub270.whatshouldido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seungjub270.whatshouldido.domain.UserVector;

public interface UserRepository extends JpaRepository<UserVector, Long> {
}

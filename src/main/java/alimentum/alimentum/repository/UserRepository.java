package alimentum.alimentum.repository;

import alimentum.alimentum.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
  boolean existsUsersByUsername(String username);
  User findByUsername(String username);
  User getById(Long id);

}

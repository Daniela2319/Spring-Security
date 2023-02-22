package dio.spring.security.repository;

import dio.spring.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/*
* Criado um repositório para interagir com a nossa entidade User.java e realizar as operações de CRUD necessarias.
* Explorando o Spring Data JPA temos uma query override que busca um usuário sua respectiva lista de perfis(roles)
* */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELET e FROM User e JOIN FETCH e.roles WHERE e.username= (:username)")
    public User findByUsername(@Param("username") String username);
}

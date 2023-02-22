package dio.spring.security.configSeguranca;
/*
* Criando essa clase SecurityDatabaseService.java que implementará a userDetailService para retornar um usuário
* para contexto de segurança conforme nosso banco de dados.
* */
import dio.spring.security.model.User;
import dio.spring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SecurityDatabaseService implements UserDetailsService {

    // injeta o repositório para a localização do usuário no banco de dados
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        // converte os perfis do usuário (roles) em Authoritys

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        userEntity.getRoles().forEach(role-> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });
        // retorna um usuário para o contexto Spring Security
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
                userEntity.getPassword(),
                authorities);
    }

    /* em um contexto de banco de dados a classe WebSecutityConfig.java não contém mais responsabilidade de criar
     e localizar os usuários, esta ação é delegada ao UserDetailService.
     */
}

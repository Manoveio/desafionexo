package br.com.desafionexo.repository;

import br.com.desafionexo.domain.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface  UsuariosRepository extends JpaRepository<UsuariosEntity, Integer> {



    boolean existsByUsername(String username);

    UsuariosEntity findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

}

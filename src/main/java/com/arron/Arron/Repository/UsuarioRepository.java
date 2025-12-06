package com.arron.Arron.Repository;

import com.arron.Arron.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNumeroDocumento(Long numeroDocumento);

    boolean existsByNumeroDocumento(Long numeroDocumento);

}

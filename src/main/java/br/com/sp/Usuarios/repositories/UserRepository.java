package br.com.sp.Usuarios.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.Usuarios.domain.usuario.Usuarios;

public interface UserRepository extends JpaRepository<Usuarios, UUID> {

	Page<Usuarios> findByEnabledTrue(Pageable pageable);

	Page<Usuarios> findByEnabledFalse(Pageable pageable);

}

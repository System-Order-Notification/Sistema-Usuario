package br.com.sp.Usuarios.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.Usuarios.domain.usuario.Usuarios;

public interface UserRepository extends JpaRepository<Usuarios, UUID> {

}

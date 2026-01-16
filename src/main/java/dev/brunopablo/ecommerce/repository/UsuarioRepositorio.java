package dev.brunopablo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.brunopablo.ecommerce.entity.UsuarioEntidade;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioEntidade, Long>{}
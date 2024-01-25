package br.com.messias.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.messias.apirest.models.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
	Playlist findByNome(String name);
}

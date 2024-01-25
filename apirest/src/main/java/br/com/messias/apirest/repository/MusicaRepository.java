package br.com.messias.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.messias.apirest.models.Musica;
import br.com.messias.apirest.models.Playlist;

public interface MusicaRepository  extends JpaRepository<Musica, Long>{
	List<Musica> findByPlaylist(Playlist playlist);
	
	Musica findByTitulo(String titulo);
}

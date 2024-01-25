package br.com.messias.apirest.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.messias.apirest.models.Musica;
import br.com.messias.apirest.models.Playlist;
import br.com.messias.apirest.repository.MusicaRepository;
import br.com.messias.apirest.repository.PlaylistRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/musicas")
public class MusicResource {

	@Autowired
	PlaylistRepository playlistRepository;
	@Autowired
	MusicaRepository musicaRepository;
	
	@GetMapping("/lists")
	public List<Musica> listaMusicas(){
		List<Musica> musicas = musicaRepository.findAll();
		
		return musicas;
	}
	
	@GetMapping("/lists/{listName}")
	public	ResponseEntity<Object> listaMusicaUnica(@PathVariable(value="listName") String titulo){
		Musica musica = musicaRepository.findByTitulo(titulo);
		
		if (musica != null) {
	        return ResponseEntity.ok(musica);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista não encontrada.");
	    }
	}
	
	@PostMapping("/lists")
	public ResponseEntity<Object> adicionarMusica(@RequestBody @Validated Musica musica) {
	    if (musica.getTitulo() == null || musica.getArtista() == null || musica.getGenero() == null || musica.getAno() == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
	    }

	    if (musica.getPlaylist() == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A Música deve estar associada a uma Playlist.");
	    }

	    Playlist playlist = playlistRepository.findByNome(musica.getPlaylist().getNome());

	    if (playlist == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Playlist não encontrada.");
	    }

	    musica.setPlaylist(playlist);

	    Musica novaMusica = musicaRepository.save(musica);
	    URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{listName}")
	            .buildAndExpand(novaMusica.getTitulo())
	            .toUri();

	    return ResponseEntity.created(location).body(novaMusica);
	}

	
	@DeleteMapping("/lists/{listName}")
	public ResponseEntity<Object> deletaProduto(@PathVariable(value="listName") String titulo) {
		Musica musica = musicaRepository.findByTitulo(titulo);
		
		if (titulo != null) {
			musicaRepository.delete(musica);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista não encontrada.");
	    }
	}
	
}


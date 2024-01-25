package br.com.messias.apirest.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@RestController
@RequestMapping(value="/playlist")
public class PlaylistResource {

	@Autowired
	PlaylistRepository playlistRepository;
	@Autowired
	MusicaRepository musicaRepository;
	
	@GetMapping("/lists")
	public List<Playlist> listaPlaylist(){
		List<Playlist> playlists = playlistRepository.findAll();
		
		for (Playlist playlist : playlists) {
            List<Musica> musicas = musicaRepository.findByPlaylist(playlist);
            playlist.setMusicas(musicas);
        }
		
		return playlists;
	}
	
	@GetMapping("/lists/{listName}")
	public	ResponseEntity<Object> listaMusicaUnica(@PathVariable(value="listName") String name){
		Playlist playlist = playlistRepository.findByNome(name);
		
		if (playlist != null) {
	        return ResponseEntity.ok(playlist);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista não encontrada.");
	    }
	}
	
	@PostMapping("/lists")
	public ResponseEntity<Object> adicionarPlaylist(@RequestBody @Validated  Playlist playlist) {
		if (playlist.getNome() == null || playlist.getNome().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome da lista inválido.");
	    }
		
		Playlist novaPlaylist = playlistRepository.save(playlist);
		URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{listName}")
	            .buildAndExpand(novaPlaylist.getNome())
	            .toUri();

	    return ResponseEntity.created(location).body(novaPlaylist);
	}
	
	@DeleteMapping("/lists/{listName}")
	public ResponseEntity<Object> deletaProduto(@PathVariable(value="listName") String name) {
		Playlist playlist = playlistRepository.findByNome(name);
		
		if (playlist != null) {
	        playlistRepository.delete(playlist);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista não encontrada.");
	    }
	}
	
}

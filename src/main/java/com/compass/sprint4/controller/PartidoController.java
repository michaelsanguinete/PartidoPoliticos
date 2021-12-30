package com.compass.sprint4.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.compass.sprint4.dto.PartidoDTO;
import com.compass.sprint4.dto.PartidoFormDTO;
import com.compass.sprint4.entity.Associado;
import com.compass.sprint4.entity.IdeologiaEnum;
import com.compass.sprint4.entity.Partido;
import com.compass.sprint4.repository.AssociadoRepository;
import com.compass.sprint4.repository.PartidoRepository;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

	@Autowired
	private PartidoRepository repository;
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	@GetMapping("/{id}/associados")
	public List<Associado> retornaAssociadoEmPartidos(@PathVariable(value = "id") int id){
		
		List<Associado> partido = associadoRepository.findAllByPartido(id);
		
		return partido;
		
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PartidoDTO> savePartido(@RequestBody @Valid PartidoFormDTO body,
			UriComponentsBuilder uriBuilder) {

		Partido partido = body.converter();
		repository.save(partido);

		URI uri = uriBuilder.path("/{id}").buildAndExpand(partido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PartidoDTO(partido));

	}

	@GetMapping
	public List<PartidoDTO> getPartidos(@RequestParam(value = "ideologia", required = false) IdeologiaEnum ideologia) {
		List<Partido> partido;
		if (ideologia != null) {
			partido = this.repository.findByIdeologia(ideologia);
		} else {
			partido = this.repository.findAll();
		}
		return PartidoDTO.converter(partido);

	}

	@GetMapping("/{id}")
	public ResponseEntity<PartidoDTO> searchPartido(@PathVariable(value = "id") int id) {

		Optional<Partido> partido = repository.findById(id);
		if (partido.isPresent()) {
			return ResponseEntity.ok(new PartidoDTO(partido.get()));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletePartido(@PathVariable int id) {
		Optional<Partido> optional = repository.findById(id);

		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PartidoDTO> updatePartido(@PathVariable int id,
			@RequestBody @Valid PartidoFormDTO form) {
		Optional<Partido> optional = repository.findById(id);
		if (optional.isPresent()) {
			Partido partido = form.atualizar(id, repository);
			return ResponseEntity.ok(new PartidoDTO(partido));
		}

		return ResponseEntity.notFound().build();
	}
}
























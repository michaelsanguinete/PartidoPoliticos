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

import com.compass.sprint4.dto.AssociadoDTO;
import com.compass.sprint4.dto.AssociadoFormDTO;
import com.compass.sprint4.dto.AssociadoPartidoForm;
import com.compass.sprint4.entity.Associado;
import com.compass.sprint4.entity.CargoEnum;
import com.compass.sprint4.repository.AssociadoRepository;
import com.compass.sprint4.repository.PartidoRepository;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

	@Autowired
	private AssociadoRepository repository;

	private PartidoRepository partidoRepository;
	
	@DeleteMapping("/{id}/partidos/{id}")
	public ResponseEntity<?> deletaAssociadoDoPartido(@PathVariable int idAssociado, @PathVariable int idPartido ){
		
		Associado associado = repository.getById(idAssociado);
		if (associado != null) {
			if (associado.getPartido().getId() == idPartido) {
				associado.setPartido(null);
				return ResponseEntity.ok().build();
			}
		}
		
		return ResponseEntity.notFound().build();
		
	}
	

	@PostMapping("/partidos")
	public ResponseEntity<AssociadoDTO> adicionaAssociadoPartido(@RequestBody @Valid AssociadoPartidoForm form) {

		Associado associado = repository.getById(form.getIdAssociado());
		associado.setPartido(partidoRepository.getById(form.getIdPartido()));

		repository.save(associado);

		return ResponseEntity.ok().build();

	}

	@PostMapping
	public ResponseEntity<AssociadoDTO> saveAssocioado(@RequestBody @Valid AssociadoFormDTO body,
			UriComponentsBuilder uriBuilder) {

		Associado associado = body.converter();
		repository.save(associado);

		URI uri = uriBuilder.path("/{id}").buildAndExpand(associado.getId()).toUri();
		return ResponseEntity.created(uri).body(new AssociadoDTO(associado));

	}

	@GetMapping
	public List<AssociadoDTO> getAssociados(@RequestParam(value = "cargo", required = false) CargoEnum cargo, @RequestParam(required = false) boolean ordencao) {
		List<Associado> associado;
		if (cargo != null) {
			associado = this.repository.findByCargo(cargo);
		}
		if (ordencao != false) {
			associado = this.repository.findByCargoOrderByNomeAsc(ordencao);
		}
		else {
			associado = this.repository.findAll();
		}
		return AssociadoDTO.converter(associado);

	}

	@GetMapping("/{id}")
	public ResponseEntity<AssociadoDTO> searchAssociado(@PathVariable(value = "id") int id) {

		Optional<Associado> associado = repository.findById(id);
		if (associado.isPresent()) {
			return ResponseEntity.ok(new AssociadoDTO(associado.get()));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteAssociado(@PathVariable int id) {
		Optional<Associado> optional = repository.findById(id);

		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AssociadoDTO> updateAssociado(@PathVariable int id,
			@RequestBody @Valid AssociadoFormDTO form) {
		Optional<Associado> optional = repository.findById(id);
		if (optional.isPresent()) {
			Associado associado = form.atualizar(id, repository);
			return ResponseEntity.ok(new AssociadoDTO(associado));
		}

		return ResponseEntity.notFound().build();
	}

}

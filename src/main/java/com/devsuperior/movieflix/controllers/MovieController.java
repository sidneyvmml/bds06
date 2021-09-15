package com.devsuperior.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")

public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id){
		
		MovieDetailsDTO movieDTO = service.findById(id);
		return ResponseEntity.ok(movieDTO);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieCardDTO>> findById(
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
			Pageable pageable){
		
		Page<MovieCardDTO> page = service.findByGenre(genreId, pageable );
		return ResponseEntity.ok().body(page);
		
	}
	
	/*
	 * @GetMapping public ResponseEntity<Page<MovieCardDTO>> findByGenre(
	 * 
	 * @RequestParam(value = "genreId", defaultValue = "0") Long genreId, Pageable
	 * pageable ){
	 * 
	 * Page<MovieCardDTO> page = service.findByGenre(genreId, pageable); return
	 * ResponseEntity.ok(page); }
	 */

	/*
	 *  public ResponseEntity<Page<MovieMinDTO>> findAll(Pageable
	 * pageable) {
	 * 
	 * Page<MovieMinDTO> page = service.find(pageable);
	 * 
	 * return ResponseEntity.ok().body(page);
	 * 
	 * }
	 * 
	 * @PreAuthorize("hasAnyRole('MEMBER')")
	 * 
	 * @GetMapping(value = "/{id}") public ResponseEntity<MovieDTO>
	 * findById(@PathVariable Long id){
	 * 
	 * MovieDTO dto = service.findById(id); return ResponseEntity.ok().body(dto); }
	 */

}

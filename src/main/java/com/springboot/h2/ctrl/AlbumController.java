package com.springboot.h2.ctrl;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.h2.exception.AlbumNotFoundException;
import com.springboot.h2.exception.BandNotFoundException;
import com.springboot.h2.model.Album;
import com.springboot.h2.model.Band;
import com.springboot.h2.model.MusicRequest;
import com.springboot.h2.model.MusicResponse;
import com.springboot.h2.serv.AlbumService;

@RestController
@RequestMapping("/music")
public class AlbumController {
	
	@Autowired
	private AlbumService albumService;

	//Post for creating the band
	@PostMapping("/bands/{bandId}/albums") 
	public ResponseEntity<MusicResponse> createBand(@PathVariable Long bandId,@Valid @RequestBody MusicRequest musicRequest)
			 
	{
		Album album = albumService.createAlbum(musicRequest, bandId);
		
		if (album == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(album.getAlbumId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/bands/{bandId}/albums/{albumId}")
    public ResponseEntity<Album> getBandById(@PathVariable Long bandId, 
    		@PathVariable Long albumId) throws BandNotFoundException, AlbumNotFoundException {
	 Optional<Album> album = albumService.retrieveAlbumByBandIdandAlbumId(bandId,albumId);
	 
	 if (!album.isPresent()){
		 throw new AlbumNotFoundException("Album not found-" + albumId);
	 }
	 
	 return new ResponseEntity<>(album.get(), HttpStatus.OK);
	     
	  
    }
}

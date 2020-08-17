package com.springboot.h2.ctrl;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.h2.exception.BandNotFoundException;
import com.springboot.h2.model.Band;
import com.springboot.h2.model.MusicRequest;
import com.springboot.h2.serv.BandService;

@RestController
@RequestMapping("/music")
public class BandController {

	@Autowired
	private BandService bandService;

	// Post for creating the band
	@PostMapping("/bands")
	public ResponseEntity<?> createBand(@Valid @RequestBody MusicRequest musicRequest)

	{
		Band band = bandService.createBand(musicRequest);

		if (band == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(band.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	 @GetMapping("/bands/{bandId}")
	    public ResponseEntity<Band> getBandById(@PathVariable Long bandId) throws BandNotFoundException {
		 Optional<Band> band = bandService.retrieveBandById(bandId);
		 
		 if (!band.isPresent()){
			 throw new BandNotFoundException("Band not found-" + bandId);
		 }
		 
		 return new ResponseEntity<>(band.get(), HttpStatus.OK);
		     
		  
	    }

}

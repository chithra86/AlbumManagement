package com.springboot.h2.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.h2.exception.BandNotFoundException;
import com.springboot.h2.model.Band;
import com.springboot.h2.model.MusicRequest;
import com.springboot.h2.repo.BandRepository;

@Service
public class BandService {
	
	@Autowired
	private BandRepository bandRepository;

	public Band createBand(MusicRequest musicRequest) {
		
		Band band = new Band();
		band.setName(musicRequest.getBandName());
		band.setMembers(musicRequest.getMembers());
		
		return bandRepository.save(band);
		
	}

	public Optional<Band> retrieveBandById(Long bandId){
		

		return bandRepository.findById(bandId);
	}
}

package com.springboot.h2.serv;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.h2.exception.BandNotFoundException;
import com.springboot.h2.model.Album;
import com.springboot.h2.model.Band;
import com.springboot.h2.model.MusicRequest;
import com.springboot.h2.repo.AlbumRepository;
import com.springboot.h2.repo.BandRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository  albumRepository;
	
	@Autowired
	private BandRepository bandRepository;
	
	public Album createAlbum(MusicRequest musicRequest, Long id) {
		
		Band band = new Band();
		band.setId(id);
		
		Album album = new Album();
		album.setName(musicRequest.getAlbumName());
		album.setCount(musicRequest.getCount());
		album.setDescription(musicRequest.getDescription());
		album.setReleased(new Date());
		album.setBand(band);
		
		return albumRepository.save(album);
		
	}

	public Optional<Album> retrieveAlbumByBandIdandAlbumId(Long bandId, Long albumId) throws BandNotFoundException{
		
		 if(!bandRepository.existsById(bandId)) {
	            throw new BandNotFoundException("Band " + bandId + " not found");
	        }

	        return albumRepository.findById(albumId);
		
		
	}
}

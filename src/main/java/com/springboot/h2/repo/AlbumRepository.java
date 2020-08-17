package com.springboot.h2.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springboot.h2.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long>{

	Optional<Album> findByAlbumId(Long albumId);

}

package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.SongService;

@Service
public class SongServiceImpl implements SongService{
	
	@Autowired
	SongRepository songRepository;

	@Override
	public void addSong(Song song) {
		// TODO Auto-generated method stub
		songRepository.save(song);
	}

	@Override
	public boolean songExists(String name) {
		// TODO Auto-generated method stub
		Song song = songRepository.findByName(name);
		if(song==null) {
			return false;
		}
		else {
			return true;			
		}
	}

	@Override
	public List<Song> fetchAllSongs() {
		// TODO Auto-generated method stub
		List<Song> songs = songRepository.findAll();
 		return songs;
	}

	@Override
	public void updateSong(Song s) {
		// TODO Auto-generated method stub
		songRepository.save(s);
	}
}

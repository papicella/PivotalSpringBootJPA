package pas.au.pivotal.springboot.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pas.au.pivotal.springboot.demo.domain.Album;
import pas.au.pivotal.springboot.demo.repositories.JpaAlbumRepository;

import java.util.List;

@RestController
public class AlbumRest {
    private static final Logger logger = LoggerFactory.getLogger(AlbumRest.class);
    private JpaAlbumRepository repository;

    @Autowired
    public AlbumRest(JpaAlbumRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/all_albums",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Album> listAlbums() {
        logger.info("REST request to get all Albums");
        List<Album> albums = repository.findAll();

        return albums;
    }

    @RequestMapping(value = "/album/{albumid}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Album findAlbum(@PathVariable String albumid) {
        logger.info(String.format("REST request to get Album : {%s}", albumid));
        Album album = repository.findOne(albumid);

        return album;
    }
}

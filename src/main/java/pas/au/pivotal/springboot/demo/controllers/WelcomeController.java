package pas.au.pivotal.springboot.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pas.au.pivotal.springboot.demo.domain.Album;
import pas.au.pivotal.springboot.demo.repositories.JpaAlbumRepository;
import pas.au.pivotal.springboot.demo.Utils;

import java.util.List;

@Controller
public class WelcomeController
{
    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);
    private JpaAlbumRepository repository;

    @Autowired
    public WelcomeController(JpaAlbumRepository repository)
    {
        this.repository = repository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model)
    {

        List<Album> albums = (List<Album>) repository.findAll();

        model.addAttribute("albums", albums);

        model.addAttribute("count", repository.findAll().size());
        model.addAttribute("appIndex", Utils.applicationIndex());
        model.addAttribute("dbservice", Utils.getDBService());

        return "welcome";
    }

    @RequestMapping(value = "/killme", method = RequestMethod.GET)
    public String properties(Model model) throws Exception
    {
        Runtime.getRuntime().halt(-1);
        return "welcome";
    }

}

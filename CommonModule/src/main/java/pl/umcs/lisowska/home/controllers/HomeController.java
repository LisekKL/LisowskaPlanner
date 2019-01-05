package pl.umcs.lisowska.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"", "/"})
public class HomeController {

    //private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    public HomeController( ) {}

    @GetMapping()
    protected String getHomePage() {
        return "redirect: " + "<html><body><a>http://localhost:8080/users</a></body></html>";
    }
}

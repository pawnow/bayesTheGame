package app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class SomeController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String greeting() {
        return "Hello";
    }

}

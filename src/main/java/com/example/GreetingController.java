package com.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GreetingController {

    private static final String template = "Hello %s!";

    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greet(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
        Greeting greeting = new Greeting(String.format(template, name));

        greeting.add(linkTo(methodOn(GreetingController.class).greet(name)).withSelfRel());

        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }
}

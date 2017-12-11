package io.test.sas.web.controller;

import io.test.sas.common.entity.User;
import io.test.sas.common.service.general.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/dummy")
public class DummyController {

    private UserService service;

    @Autowired
    public DummyController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    @PreAuthorize("@userService.hasPermission('USER', 'VIEW')")
    public ResponseEntity<User> get(@PathVariable("id") long id) {

        Optional<User> userWrapper = service.get(10000);
        return new ResponseEntity<>(userWrapper.orElse(null), HttpStatus.OK);

    }

}
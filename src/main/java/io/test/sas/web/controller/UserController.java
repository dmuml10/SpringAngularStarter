package io.test.sas.web.controller;

import io.test.sas.common.entity.User;
import io.test.sas.common.exception.AppException;
import io.test.sas.common.filter.general.UserFilter;
import io.test.sas.common.service.general.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @PreAuthorize("@userService.hasPermission('USER', 'ADD')")
    public ResponseEntity<User> add(@RequestBody User entity) {
        try {
            return new ResponseEntity<>(service.add(entity), HttpStatus.OK);
        } catch (AppException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @PreAuthorize("@userService.hasPermission('USER', 'EDIT')")
    public ResponseEntity<User> update(@RequestBody User entity) {
        try {
            return new ResponseEntity<>(service.update(entity), HttpStatus.OK);
        } catch (AppException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("@userService.hasPermission('USER', 'DELETE')")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        try {
            service.delete(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AppException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    @PreAuthorize("@userService.hasPermission('USER', 'VIEW')")
    public ResponseEntity<User> get(@PathVariable("id") long id) {
        Optional<User> entityWrapper = service.get(id);

        return new ResponseEntity<>(entityWrapper.orElse(null), HttpStatus.OK);
    }

    @RequestMapping(value = "getCount", method = RequestMethod.POST)
    @PreAuthorize("@userService.hasPermission('USER', 'VIEW')")
    public ResponseEntity<Long> getCount(@RequestBody(required = false) UserFilter filter) {
        return new ResponseEntity<>(service.getCount(filter), HttpStatus.OK);
    }

    @RequestMapping(value = "getList", method = RequestMethod.POST)
    @PreAuthorize("@userService.hasPermission('USER', 'VIEW')")
    public ResponseEntity<List<User>> getList(@RequestBody(required = false) UserFilter filter) {
        return new ResponseEntity<>(service.getList(filter), HttpStatus.OK);
    }
}
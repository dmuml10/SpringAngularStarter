package io.test.sas.web.controller;

import io.test.sas.common.exception.AppException;
import io.test.sas.common.service.general.AuthenticationService;
import io.test.sas.web.jwt.JwtAuthenticationRequest;
import io.test.sas.web.jwt.JwtAuthenticationResponse;
import io.test.sas.web.jwt.JwtTokenUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Log4j2
public class AuthenticationController {

    private Environment environment;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(Environment environment, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                                    UserDetailsService userDetailsService, AuthenticationService authenticationService) {
        this.environment = environment;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        String token = null;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception e) {
            log.error("Authentication failed! ", e);
        }
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<JwtAuthenticationResponse> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(environment.getRequiredProperty("jwt.header"));
        String username = jwtTokenUtil.getUsernameFromToken(token);
        userDetailsService.loadUserByUsername(username);

        if (!jwtTokenUtil.isTokenExpired(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);

            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public ResponseEntity<Void> resetPassword(HttpServletRequest request, @RequestParam("usernameOrEmail") String usernameOrEmail) {
        try {
            authenticationService.resetPassword(usernameOrEmail, request.getContextPath());

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AppException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
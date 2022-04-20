package com.deezzex.security.service;

import com.deezzex.security.entity.CustomerDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements ReactiveUserDetailsService {

    private final RestTemplate restTemplate;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        CustomerDetails customer = restTemplate.getForObject("http://LOGIN-SERVICE/api/v1/login/{username}",
                CustomerDetails.class, username);

       if (!Objects.requireNonNull(customer).isExist()){
           throw new UsernameNotFoundException("User with username: " + username + " doesn't exist");
       }

        return Mono.just(customer);
    }
}

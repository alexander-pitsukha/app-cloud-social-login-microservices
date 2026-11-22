package com.alexander.services.identity.service.impl;

import com.alexander.services.identity.dto.LocalUser;
import com.alexander.services.identity.entity.User;
import com.alexander.services.identity.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocalUserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public LocalUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new UsernameNotFoundException("User " + email + " was not found in the database"));
        return LocalUser.createLocalUser(user);
    }

}

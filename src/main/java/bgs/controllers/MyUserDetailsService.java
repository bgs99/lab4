package bgs.controllers;

import bgs.MyUserPrincipal;
import bgs.model.Logins;
import bgs.repo.LoginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginsRepository loginsRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Logins user = null;
        try {
            user = loginsRepository.findByName(username);
        }catch (Exception e){
            throw new UsernameNotFoundException(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
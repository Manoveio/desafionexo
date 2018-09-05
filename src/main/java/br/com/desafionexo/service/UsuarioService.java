package br.com.desafionexo.service;

import br.com.desafionexo.domain.UsuariosEntity;
import br.com.desafionexo.enumerator.Role;
import br.com.desafionexo.exception.HttpRuntimeException;
import br.com.desafionexo.repository.UsuariosRepository;
import br.com.desafionexo.security.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository userRepository;

    @Autowired
   private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, Role.valueOf(userRepository.findByUsername(username).getRole()));
        } catch (AuthenticationException e) {
            throw new HttpRuntimeException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(UsuariosEntity user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setSenha(passwordEncoder.encode(user.getSenha()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), Role.valueOf(user.getRole()));
        } else {
            throw new HttpRuntimeException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public UsuariosEntity search(String username) {
        UsuariosEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new HttpRuntimeException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public UsuariosEntity whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

}

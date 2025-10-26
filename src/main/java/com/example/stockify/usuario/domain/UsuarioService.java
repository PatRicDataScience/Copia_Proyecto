//package com.example.stockify.usuario.domain;
//
//import com.example.stockify.usuario.infrastructure.UsuarioRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UsuarioService implements UserDetailsService {
//
//    private UsuarioRepository usuarioRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return usuarioRepository
//                .findByEmail(username)
//                .orElseThrow();
//    }
//
//
//
//}

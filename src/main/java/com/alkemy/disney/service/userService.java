package com.alkemy.disney.service;

import com.alkemy.disney.entity.Usuario;
import com.alkemy.disney.enums.Role;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.repository.userRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service("userDetailsService")
public class userService implements UserDetailsService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private mailService mailService;

    //CRUD
    @Transactional
    public Usuario create(Usuario usuario) {
        try {
            validate(usuario);
            Optional<Usuario> usuarioVerificar = userRepository.findByMailUserO(usuario.getId());
            if (usuarioVerificar.isPresent()) {
                throw new webException("El email ya está registrado");
            }

            usuario.setId(usuario.getId());
            usuario.setMail(usuario.getMail());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            usuario.setPassword(encoder.encode(usuario.getPassword()));
            usuario.setRol(Role.USER);

            //ENVIAR MAIL
            mailService.mailSender(usuario.getMail(), "Disney API", "Bienvenido ya perteneces a DisneyAPI");

            return userRepository.save(usuario);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public Usuario modify(Usuario usuario) throws webException {

        Optional<Usuario> optional = userRepository.findById(usuario.getId());
        if (optional.isPresent()) {
            Usuario usuarioModificado = optional.get();
            usuarioModificado.setMail(usuario.getMail());
            usuarioModificado.setPassword(usuario.getPassword());
            return userRepository.save(usuarioModificado);
        } else {
            throw new webException("Ese usuario no existe.");
        }
    }

    @Transactional
    public void delete(String id) throws Exception {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void validate(Usuario usuario) throws webException {
        if (usuario.getMail() == null || usuario.getMail().isEmpty()) {
            throw new webException("El mail no puede estar vacio.");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new webException("Debe ingresar contraseña.");
        }
        if (usuario.getPassword().length() < 6) {
            throw new webException("La contraseña debe contener al menos 6 caracteres");
        }
    }

    //LIST
    public List<Usuario> listAll() {
        return userRepository.findAll();
    }

    public Optional<Usuario> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        try {
            Usuario usuario = userRepository.findByMailUser(mail);

            List<GrantedAuthority> permisos = new ArrayList<>();
            permisos.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            if (usuario.getRol().equals(Role.ADMIN)) {
                permisos.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new User(mail, usuario.getPassword(), permisos);

        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe.");
        }
    }
    //ServletRequestAttributes ratt = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    //HttpSession sesion = ratt.getRequest().getSession(true);
    //sesion.setAttribute("sesion_usuario", usuario);
}

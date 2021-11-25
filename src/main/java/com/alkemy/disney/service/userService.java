package com.alkemy.disney.service;

import com.alkemy.disney.entity.Usuario;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.repository.userRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class userService implements UserDetailsService {

    @Autowired
    private userRepository userRepository;

    //CRUD
    @Transactional
    public Usuario create(String mail, String password, String password2) {
        try {
            validate(mail, password, password2);
            Usuario usuario = new Usuario();
            usuario.setMail(mail);
            //encriptar
            usuario.setPassword(password);
            return userRepository.save(usuario);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public Usuario modify(Usuario usuario) throws webException {

        Optional<Usuario> optional = userRepository.findById(usuario.getUsuario_id());
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
    public void delete(String usuario_id) throws Exception {

        try {
            userRepository.deleteById(usuario_id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void validate(String mail, String password, String password2) throws webException {
        if (mail == null || mail.isEmpty()) {
            throw new webException("El mail no puede estar vacio.");
        }
        if (password == null || password2 == null || password.isEmpty() || password2.isEmpty()) {
            throw new webException("Debe ingresar contraseña.");
        }
        if (!password.equals(password2)) {
        }
        throw new webException("Las contraseñas no coinciden.");
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        try {
            Usuario usuario = userRepository.findByMailUser(mail);
            List<GrantedAuthority> permisos = new ArrayList<>();

            ServletRequestAttributes ratt = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession sesion = ratt.getRequest().getSession(true);
            sesion.setAttribute("sesion_usuario", usuario);
            return new User(mail, usuario.getPassword(), permisos);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existente.");
        }
    }
}

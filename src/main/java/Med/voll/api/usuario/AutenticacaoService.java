package med.voll.api.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import med.voll.api.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
        return repository.findByEmail(username);
    }


    
}

package ing.soft.cocheCompartido.controller;

import ing.soft.cocheCompartido.model.Usuario;
import ing.soft.cocheCompartido.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuariosRESTController {

    private final UsuarioRepository usuarioRepository;

    public UsuariosRESTController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}

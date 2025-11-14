package com.ecommerce.auth.infraestructure.driver_adapters.jpa_repository;

import com.ecommerce.auth.domain.model.Usuario;
import com.ecommerce.auth.domain.model.gateway.UsuarioGateway;
import com.ecommerce.auth.infraestructure.mapper.MapperUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsuarioDataGatewayImpl implements UsuarioGateway {

    private final MapperUsuario mapperUsuario;
    private final UsuarioDataJpaRepository repository;

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioData usuarioData = mapperUsuario.toData(usuario);
        return mapperUsuario.toUsuario(repository.save(usuarioData));
    }

    @Override
    public void eliminarPorId(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception error) {
            throw new RuntimeException(error.getMessage());
        }
    }

    @Override
    public Usuario buscarPorId(Long id) {
//        UsuarioData usuarioData = repository.findById(id).get();
//        return mapperUsuario.toUsuario(usuarioData);
        return repository.findById(id)
                .map(usuarioData -> mapperUsuario.toUsuario(usuarioData))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        UsuarioData usuarioData = mapperUsuario.toData(usuario);

        if(!repository.existsById(usuario.getId())){
            throw new RuntimeException("Usuario con id " + usuario.getId() + " no existe");
        }
        return mapperUsuario.toUsuario(repository.save(usuarioData));
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(usuarioData -> mapperUsuario.toUsuario(usuarioData))
                .orElseThrow(() -> new RuntimeException("Fallo consulta de base de datos"));
    }
}

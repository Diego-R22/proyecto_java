package com.arron.Arron.Service;

import com.arron.Arron.Model.Usuario;
import com.arron.Arron.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ================================
    // BUSCAR POR DOCUMENTO (LOGIN)
    // ================================
    public Usuario buscarPorNumeroDocumento(Long numeroDocumento) {
        return usuarioRepository.findByNumeroDocumento(numeroDocumento).orElse(null);
    }

    // ================================
    // LISTAR
    // ================================
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // ================================
    // OBTENER POR ID
    // ================================
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // ================================
    // GUARDAR (nombre corto simplificado)
    // ================================
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ================================
    // GUARDAR USUARIO (por si otro código lo usa)
    // ================================
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ================================
    // ACTUALIZAR
    // ================================
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ================================
    // ELIMINAR
    // ================================
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // ================================
    // CONTAR USUARIOS (NECESARIO PARA EL DATALOADER)
    // ================================
    public long count() {
        return usuarioRepository.count();
    }

    // ================================
    // VALIDAR SI EXISTE POR DOCUMENTO (REGISTRO)
    // ================================
    public boolean existePorDocumento(Long numeroDocumento) {
        return usuarioRepository.existsByNumeroDocumento(numeroDocumento);
    }

}

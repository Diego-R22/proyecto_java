package com.arron.Arron.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "numero_documento", nullable = false, unique = true) // ✅ AGREGADO unique
    private Long numeroDocumento;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true) // ✅ AGREGADO unique
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    // ENUM nivel_educativo
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_educativo")
    private NivelEducativo nivelEducativo;

    @Column(name = "referencia_personal")
    private String referenciaPersonal;

    @Column(name = "telefono_referencia_personal")
    private String telefonoReferenciaPersonal;

    @Column(name = "eps")
    private String eps;

    // ENUM tipo_usuario
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    // ENUM estado
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Estado estado = Estado.A; // valor por defecto

    // LLAVE FORÁNEA - registrado_por
    @ManyToOne
    @JoinColumn(name = "registrado_por")
    private Usuario registradoPor;

    // ENUMS
    public enum NivelEducativo {
        Bachiller, Tecnico, Tecnologo, Profesional
    }

    public enum TipoUsuario {
        Administrador, Cliente, Sup_bodega, Gerente
    }

    public enum Estado {
        A, I
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(Long numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public NivelEducativo getNivelEducativo() { return nivelEducativo; }
    public void setNivelEducativo(NivelEducativo nivelEducativo) { this.nivelEducativo = nivelEducativo; }

    public String getReferenciaPersonal() { return referenciaPersonal; }
    public void setReferenciaPersonal(String referenciaPersonal) { this.referenciaPersonal = referenciaPersonal; }

    public String getTelefonoReferenciaPersonal() { return telefonoReferenciaPersonal; }
    public void setTelefonoReferenciaPersonal(String telefonoReferenciaPersonal) { this.telefonoReferenciaPersonal = telefonoReferenciaPersonal; }

    public String getEps() { return eps; }
    public void setEps(String eps) { this.eps = eps; }

    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Usuario getRegistradoPor() { return registradoPor; }
    public void setRegistradoPor(Usuario registradoPor) { this.registradoPor = registradoPor; }
}
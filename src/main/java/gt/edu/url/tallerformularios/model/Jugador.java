package gt.edu.url.tallerformularios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import jakarta.persistence.ElementCollection;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    private String apodo;

    @NotNull(message = "La edad es obligatoria.")
    @Min(value = 5, message = "La edad mínima es 5 años.")
    @Max(value = 17, message = "La edad máxima es 17 años.")
    private Integer edad;

    private String email;
    private String telefono;

    @NotBlank(message = "Debe seleccionar una posición.")
    private String posicion;

    @ElementCollection
    private List<String> plataformas;

    private String nivelExperiencia;

    // Constructores
    public Jugador() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApodo() { return apodo; }
    public void setApodo(String apodo) { this.apodo = apodo; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public List<String> getPlataformas() { return plataformas; }
    public void setPlataformas(List<String> plataformas) { this.plataformas = plataformas; }

    public String getNivelExperiencia() { return nivelExperiencia; }
    public void setNivelExperiencia(String nivelExperiencia) { this.nivelExperiencia = nivelExperiencia; }
}
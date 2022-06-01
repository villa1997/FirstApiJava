package com.myfristproject.mvcdemo.modelo;

import javax.persistence.*;

@Entity
@Table(name="empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name ="Nombre", length = 60, nullable = false)
    private String Nombre;
    @Column(name ="Apellido", length = 60, nullable = false)
    private String Apellido;
    @Column(name ="Email", length = 60, nullable = false, unique = true)
    private String Email;

    public Empleado() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Empleado(long id, String nombre, String apellido, String email) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Email = email;
    }
}

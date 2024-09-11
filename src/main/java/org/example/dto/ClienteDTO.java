package org.example.dto;

public class ClienteDTO {
    private String nombre;
    private String email;
    private float facturado;

    public ClienteDTO(String nombre, String email, float facturado) {
        this.nombre = nombre;
        this.email = email;
        this.facturado = facturado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getFacturado() {
        return facturado;
    }

    public void setFacturado(float facturado) {
        this.facturado = facturado;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", facturado=" + facturado +
                '}';
    }
}

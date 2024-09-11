package org.example.dto;

public class ProductoDTO {
    private String nombre;
    private float valor;
    private float recaudado;

    public ProductoDTO(String nombre, float valor, float recaudado) {
        this.nombre = nombre;
        this.valor = valor;
        this.recaudado = recaudado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getRecaudado() {
        return recaudado;
    }

    public void setRecaudado(float recaudado) {
        this.recaudado = recaudado;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", recaudado=" + recaudado +
                '}';
    }
}

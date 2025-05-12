package com.pcprime.pcprime.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Vendedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedoresModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "dni", length = 20)
    private String dni;

    @Column(name = "codigo_vendedor", length = 20)
    private String codigoVendedor;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;

    @Column(name = "fecha_fin_contrato")
    private LocalDate fechaFinContrato;

    @Column(name = "salario", precision = 10, scale = 2)
    private java.math.BigDecimal salario;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, columnDefinition = "ENUM('activo','inactivo','suspendido')")
    private EstadoVendedor estado = EstadoVendedor.activo;

    public enum EstadoVendedor {
        activo, inactivo, suspendido
    }
}
package com.lumen.billing.v1.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "document_x_country_api")
public class DocumentDBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "country")
    private String country;
}

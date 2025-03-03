package com.lumen.api.v1.models.db;


import jakarta.persistence.*;

@Entity
@Table(name = "document_x_country_api")
public class DocumentDBModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "country")
    private String country;
}

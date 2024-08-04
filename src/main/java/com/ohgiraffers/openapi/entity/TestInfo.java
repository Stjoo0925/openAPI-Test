package com.ohgiraffers.openapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class TestInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passEndDt;
    private String description;
    private String examRegStartDt;
    private String examRegEndDt;
    private String examStartDt;
    private String examEndDt;
    private String passStartDt;

}

package com.practice.rosreestr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "information")
@Table(name = "information")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "information_id")
    private Long id;

    @Column(name = "request_timestamp")
    private Date requestTimestamp;

    @Column(name = "url")
    private String URL;

    @Column(name = "type_number")
    private Integer typeNumber;

    @Column(name = "cadastre_number")
    private String cadastreNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "cadastre_cost")
    private BigDecimal cadastreCost;

}

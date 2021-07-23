package com.practice.rosreestr.entity;

import com.practice.rosreestr.model.AccessibilityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "accessibility")
@Table(name = "accessibility")
public class Accessibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accessibility_id")
    private Long id;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "url")
    private String URL;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccessibilityStatus status;

}

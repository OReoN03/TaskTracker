/*
 * Copyright (c) 2008-2020
 * LANIT
 * All rights reserved.
 *
 * This product and related documentation are protected by copyright and
 * distributed under licenses restricting its use, copying, distribution, and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * LANIT and its licensors, if any.
 *
 * $
 */
package com.example.tasktracker.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = -5449326074498337972L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;
}

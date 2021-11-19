package com.work.accounting.models.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(of = { "entityUUID" })
public abstract class BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @Column(name = "entity_uuid")
    protected String entityUUID;

    @Column(name = "create_date")
    protected Date createDate;

    protected void doOnCreate()
    {
        entityUUID = UUID.randomUUID().toString();
        createDate = new Date();
    }
}


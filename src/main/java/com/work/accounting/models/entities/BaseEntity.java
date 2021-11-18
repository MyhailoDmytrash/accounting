package com.work.accounting.models.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@EqualsAndHashCode(of = { "entityUUID" })
@MappedSuperclass
public abstract class BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @Column(name = "entity_uuid")
    protected String entityUUID;

    protected ZonedDateTime createDate;

    @PrePersist
    protected void onCreate()
    {
        entityUUID = UUID.randomUUID().toString();
        createDate = ZonedDateTime.now(ZoneId.of("EET"));
    }
}

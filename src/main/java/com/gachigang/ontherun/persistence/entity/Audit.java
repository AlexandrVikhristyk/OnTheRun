package com.gachigang.ontherun.persistence.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * An abstract base class for entities that require auditing of some actions.
 * Extending this class automatically tracks information.
 *
 * @see AuditingEntityListener
 */
@Audited
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class Audit {

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private long createdDate;

    @LastModifiedBy
    private String modifiedBy;
}

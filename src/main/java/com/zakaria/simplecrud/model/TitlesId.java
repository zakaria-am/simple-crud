package com.zakaria.simplecrud.model;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class TitlesId implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Positive
    @Column(name="emp_no", unique=true, nullable=false)
    private Integer employeeNumber;

    @NotNull
    @NotBlank
    @Column(name="title", nullable=false, length=50)
    private String title;

    @NotNull
    @Column(name="from_date", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date fromDate;

}

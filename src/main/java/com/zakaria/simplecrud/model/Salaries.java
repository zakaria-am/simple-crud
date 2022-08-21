package com.zakaria.simplecrud.model;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "salaries")
@Data
public class Salaries implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SalariesId salariesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", nullable = false, insertable=false, updatable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employees employees;

    @NotNull
    @Positive
    @Column(name="salary", nullable=false)
    private Integer salary;

    @Column(name="to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;

}

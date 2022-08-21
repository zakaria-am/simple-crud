package com.zakaria.simplecrud.model;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dept_manager")
@Data
public class DepartmentManager implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DepartmentManagerId departmentManagerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no", nullable = false, insertable=false, updatable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private Departments departments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", nullable = false, insertable=false, updatable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employees employees;

    @NotNull
    @Column(name="from_date", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @NotNull
    @Column(name="to_date", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date toDate;

}

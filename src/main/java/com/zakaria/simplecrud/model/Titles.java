package com.zakaria.simplecrud.model;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "titles")
@Data
public class Titles implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TitlesId titlesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", nullable = false, insertable=false, updatable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotFound(action = NotFoundAction.IGNORE)
    private Employees employees;

    @Column(name="to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;

}

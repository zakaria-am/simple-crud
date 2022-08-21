package com.zakaria.simplecrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zakaria.simplecrud.enums.Gender;
import com.zakaria.simplecrud.enums.GenderConverter;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class Employees implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Positive
    @Id
    @Column(name="emp_no", unique=true, nullable=false)
    private Integer employeeNumber;

    @NotNull
    @PastOrPresent
    @Column(name="birth_date", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NotNull
    @NotBlank
    @Column(name="first_name", nullable=false, length=14)
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name="last_name", nullable=false, length=16)
    private String lastName;

    @NotNull
    @Column(name="gender", nullable=false)
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @NotNull
    @Column(name="hire_date", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @JsonIgnore
    @OneToMany(mappedBy = "employees")
    private List<DepartmentsEmployees> departmentsEmployeesList;

    @JsonIgnore
    @OneToMany(mappedBy = "employees")
    private List<DepartmentManager> departmentManagerList;

    @JsonIgnore
    @OneToMany(mappedBy = "employees")
    private List<Titles> titlesList;

    @JsonIgnore
    @OneToMany(mappedBy = "employees")
    private List<Salaries> salariesList;

}

package com.zakaria.simplecrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zakaria.simplecrud.enums.Gender;
import com.zakaria.simplecrud.enums.GenderConverter;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
public class Departments implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    @Id
    @Column(name="dept_no", unique=true, nullable=false, length=4)
    private String departmentNumber;

    @NotNull
    @NotBlank
    @Column(name="dept_name", nullable=false, length=40)
    private String departmentName;

    @JsonIgnore
    @OneToMany(mappedBy = "departments")
    private List<DepartmentsEmployees> departmentsEmployeesList;

    @JsonIgnore
    @OneToMany(mappedBy = "departments")
    private List<DepartmentManager> departmentManagerList;

}

package com.seveneleven.employeeapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    
    @Column(nullable = false)
    private String empName;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
    
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Employee> subordinates;
    
    @Column(nullable = false)
    private BigDecimal salary;
    
    @Column(nullable = false)
    private LocalDate hireDate;
    
    @Column(nullable = false)
    private String status;
}

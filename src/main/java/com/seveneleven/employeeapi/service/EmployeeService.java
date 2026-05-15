package com.seveneleven.employeeapi.service;

import com.seveneleven.employeeapi.dto.EmployeeDTO;
import com.seveneleven.employeeapi.model.Employee;
import com.seveneleven.employeeapi.model.Department;
import com.seveneleven.employeeapi.repository.EmployeeRepository;
import com.seveneleven.employeeapi.repository.DepartmentRepository;
import com.seveneleven.employeeapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmpName(employeeDTO.getEmpName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setSalary(employeeDTO.getSalary());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setStatus(employeeDTO.getStatus());
        
        Department department = departmentRepository.findById(employeeDTO.getDeptId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + employeeDTO.getDeptId()));
        employee.setDepartment(department);
        
        if (employeeDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(employeeDTO.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + employeeDTO.getManagerId()));
            employee.setManager(manager);
        }
        
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }
    
    public EmployeeDTO getEmployeeById(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));
        return convertToDTO(employee);
    }
    
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public EmployeeDTO updateEmployee(Long empId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));
        employee.setEmpName(employeeDTO.getEmpName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setSalary(employeeDTO.getSalary());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setStatus(employeeDTO.getStatus());
        
        Department department = departmentRepository.findById(employeeDTO.getDeptId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + employeeDTO.getDeptId()));
        employee.setDepartment(department);
        
        if (employeeDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(employeeDTO.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + employeeDTO.getManagerId()));
            employee.setManager(manager);
        }
        
        Employee updatedEmployee = employeeRepository.save(employee);
        return convertToDTO(updatedEmployee);
    }
    
    @SuppressWarnings("unused")
	public void deleteEmployee(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + empId));
        employeeRepository.deleteById(empId);
    }
    
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpId(employee.getEmpId());
        dto.setEmpName(employee.getEmpName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDeptId(employee.getDepartment().getDeptId());
        if (employee.getManager() != null) {
            dto.setManagerId(employee.getManager().getEmpId());
        }
        dto.setSalary(employee.getSalary());
        dto.setHireDate(employee.getHireDate());
        dto.setStatus(employee.getStatus());
        return dto;
    }
}

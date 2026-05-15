package com.seveneleven.employeeapi.service;

import com.seveneleven.employeeapi.dto.DepartmentDTO;
import com.seveneleven.employeeapi.model.Department;
import com.seveneleven.employeeapi.model.Location;
import com.seveneleven.employeeapi.model.Employee;
import com.seveneleven.employeeapi.repository.DepartmentRepository;
import com.seveneleven.employeeapi.repository.LocationRepository;
import com.seveneleven.employeeapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setDeptName(departmentDTO.getDeptName());
        department.setBudget(departmentDTO.getBudget());
        
        Location location = locationRepository.findById(departmentDTO.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));
        department.setLocation(location);
        
        if (departmentDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(departmentDTO.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
            department.setManager(manager);
        }
        
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }
    
    public DepartmentDTO getDepartmentById(Long deptId) {
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return convertToDTO(department);
    }
    
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public DepartmentDTO updateDepartment(Long deptId, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        department.setDeptName(departmentDTO.getDeptName());
        department.setBudget(departmentDTO.getBudget());
        
        Location location = locationRepository.findById(departmentDTO.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));
        department.setLocation(location);
        
        if (departmentDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(departmentDTO.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
            department.setManager(manager);
        }
        
        Department updatedDepartment = departmentRepository.save(department);
        return convertToDTO(updatedDepartment);
    }
    
    public void deleteDepartment(Long deptId) {
        departmentRepository.deleteById(deptId);
    }
    
    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDeptId(department.getDeptId());
        dto.setDeptName(department.getDeptName());
        dto.setLocationId(department.getLocation().getLocationId());
        dto.setBudget(department.getBudget());
        if (department.getManager() != null) {
            dto.setManagerId(department.getManager().getEmpId());
        }
        return dto;
    }
}

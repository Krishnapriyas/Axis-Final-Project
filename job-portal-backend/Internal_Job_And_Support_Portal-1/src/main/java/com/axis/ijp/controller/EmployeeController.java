package com.axis.ijp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axis.ijp.entity.Employee;
import com.axis.ijp.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;
	@Autowired
	private PasswordEncoder passwordEncoder;

    /**
     * Register Employee Details.
     * Author: Utkarsha Bhosale
     */
    @PostMapping("/registerEmployee")
//    @PreAuthorize("hasAuthority('ROLE_HR')")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    /**
     * View Employee By Id.
     * Author: Utkarsha Bhosale
     */
    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    /**
     * View All Employees.
     * Author: Utkarsha Bhosale
     */
    @GetMapping("/viewAllEmployees")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * Search for employees by name.
     * Author: Utkarsha Bhosale
     */
    @GetMapping("/searchByName")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public List<Employee> searchEmployeesByName(@RequestParam String name) {
        return employeeService.searchEmployeesByName(name);
    }
    
    /**
     * Get Employee Role by Full Name and Password.
     * Author: Pallavi Bolar
     */
    @PostMapping("/getRoleByFullNameAndPassword")
    public String getRoleByFullNameAndPassword(@RequestBody Employee credentials) {
        Employee employee = employeeService.getEmployeeByFullName(credentials.getFullName());
        if (employee != null && passwordEncoder.matches(credentials.getPassword(), employee.getPassword())) {
            return employee.getRole().toString();
        }
        return "Invalid credentials";
    }
    
	@PostMapping("/login")		//verified
	public ResponseEntity<Employee> loginEmployee(@RequestBody Employee employee) {
		
			Employee emplyee = employeeService.loginEmployee(employee.getEmailId(), employee.getPassword());
			
			return ResponseEntity.ok(emplyee);
		
	}
	
	@PutMapping("/update/{employeeId}")
	public ResponseEntity<Void> updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee){
		return employeeService.updateEmployee(employeeId, employee);
		
	}
	
	/**
     * Get Employee ID by Full Name 
     * Author: Krishnapriya S
     */
    @GetMapping("/getIdByFullName/{fullName}")
    public ResponseEntity<Integer> getEmployeeIdByFullName(@PathVariable String fullName) {
        Integer employeeId = employeeService.getEmployeeIdByFullName(fullName);
        if (employeeId != null) {
            return ResponseEntity.ok(employeeId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
}

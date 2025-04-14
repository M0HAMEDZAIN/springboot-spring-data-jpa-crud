package com.spring.employee.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employee.entity.Employee;
import com.spring.employee.service.EmployeeService;

@RestController
@RequestMapping("/spring/api")
public class EmployeeCtrl {

	EmployeeService service;

	public EmployeeCtrl(EmployeeService employeeService) {
		this.service = employeeService;
	}

	@GetMapping("employees")
	public List<Employee> findAll() {
		return service.findAll();
	}

	@GetMapping("employees/{id}")
	public Employee findById(@PathVariable int id) throws Exception {
		return service.findById(id);
	}

	@PostMapping("employees")
	public Employee create(@RequestBody Employee employee) {
		return service.save(employee);
	}

	@PutMapping("employees")
	public Employee update(@RequestBody Employee employee) {
		return service.update(employee);
	}

	@DeleteMapping("employees/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) throws Exception {
		service.delete(id);
		return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
	}

	@PatchMapping("employees/{id}")
	public Employee patch(@PathVariable int id, @RequestBody Map<String, Object> patchPayload) throws Exception {
		return service.patch(id, patchPayload);
	}
}

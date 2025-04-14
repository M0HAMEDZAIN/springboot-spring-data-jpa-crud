package com.spring.employee.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.spring.employee.entity.Employee;
import com.spring.employee.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeRepo repo;
	ObjectMapper mapper;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		this.repo = employeeRepo;
		mapper = new ObjectMapper();
	}

	@Override
	public List<Employee> findAll() {
		return repo.findAll();
	}

	@Override
	public Employee findById(int id) throws Exception {
		Optional<Employee> employee = repo.findById(id);
		if (employee.isEmpty())
			throw new Exception("Employee [" + id + "] Not Found!");
		return employee.get();
	}

	@Override
	public Employee save(Employee employee) {
		employee.setId(0);
		return repo.save(employee);
	}

	@Override
	public Employee update(Employee employee) {
		return repo.save(employee);
	}

	@Override
	public void delete(int id) throws Exception {
		Employee employee = findById(id);
		repo.delete(employee);
	}

	@Override
	public Employee patch(int id, Map<String, Object> patchPayload) throws Exception {
		if (patchPayload.containsKey("id"))
			throw new Exception("Employee id not allowed in request body");

		Employee source = findById(id);
		Employee patched = apply(source, patchPayload);

		return repo.save(patched);
	}

	private Employee apply(Employee source, Map<String, Object> patchPayload) throws Exception {
		ObjectReader updater = mapper.readerForUpdating(source);
		return updater.readValue(mapper.writeValueAsString(patchPayload));
	}

//  another way
//	private Employee apply2(Employee source, Map<String, Object> patchPayload) throws Exception {
//		ObjectNode node1 = mapper.convertValue(source, ObjectNode.class);
//		ObjectNode node2 = mapper.convertValue(patchPayload, ObjectNode.class);
//		node1.setAll(node2);
//		return mapper.convertValue(node1, Employee.class);
//	}

}

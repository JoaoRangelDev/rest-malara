package br.edu.uniara.lpi2.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.edu.uniara.lpi2.rest.controller.EmployeeController;
import br.edu.uniara.lpi2.rest.model.Employee;
import br.edu.uniara.lpi2.rest.model.EmployeeRepository;

@SpringBootTest
class RestApplicationTests {
	
	@Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeController controller;

    
    @Test
    public void testInsertEmployeeSuccess() {
        Employee employee = new Employee();

        when(repository.save(employee)).thenReturn(employee);

        ResponseEntity<Employee> responseEntity = controller.insert(employee);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employee, responseEntity.getBody());
    }
    
	@Test
	public void testFindAllBadRequestPage() {
	    int page = 0;
	    int size = 10;

	    ResponseEntity<?> responseEntity = controller.all(page, size);

	    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	    assertTrue(responseEntity.getBody().toString().contains("page deve ser > 0"));
	}

	@Test
	public void testFindAllBadRequestSize() {
	    int page = 1;
	    int size = 0;

	    ResponseEntity<?> responseEntity = controller.all(page, size);

	    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	    assertTrue(responseEntity.getBody().toString().contains("size deve ser entre 1 e 500"));
	}

	@Test
	void contextLoads() {
	}

}

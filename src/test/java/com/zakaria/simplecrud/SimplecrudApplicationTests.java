package com.zakaria.simplecrud;

import com.zakaria.simplecrud.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
class SimplecrudApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private DepartmentManagerRepo departmentManagerRepo;
	@MockBean
	private DepartmentsEmployeesRepo departmentsEmployeesRepo;
	@MockBean
	private DepartmentsRepo departmentsRepo;
	@MockBean
	private EmployeesRepo employeesRepo;
	@MockBean
	private SalariesRepo salariesRepo;
	@MockBean
	private TitlesRepo titlesRepo;

}

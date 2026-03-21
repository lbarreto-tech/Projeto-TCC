package com.example.gerenciadorAgendamento;

import com.example.gerenciadorAgendamento.repository.AgendamentoRepository;
import com.example.gerenciadorAgendamento.repository.AuditoriaLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.autoconfigure.exclude=" +
				"org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
				"org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration"
})
class GerenciadorAgendamentoApplicationTests {

	@MockBean
	private AgendamentoRepository agendamentoRepository;

	@MockBean
	private AuditoriaLogRepository auditoriaLogRepository;

	@Test
	void contextLoads() {
	}

}

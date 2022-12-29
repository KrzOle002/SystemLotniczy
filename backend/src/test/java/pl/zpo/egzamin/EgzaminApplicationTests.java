package pl.zpo.egzamin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import pl.zpo.egzamin.ModelDTO.PostedReservation;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EgzaminApplicationTests {

	@Test//Najpierw włącz aplikacje potem test
	void contextLoads(){
		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject("http://localhost:8080/test",String.class);
		assertEquals("Test",result);
	}
	@Test
	void testStreamApi(){
		RestTemplate rest = new RestTemplate();
		PostedReservation postedReservation = new PostedReservation("Krzysztof", "Olejniczak", "Bydgoszcz", "London", 3, LocalDate.parse("2022-07-08"),true,false);
		String result = rest.postForObject("http://localhost:8080/reservation",postedReservation,String.class);
		assertEquals("Reserved",result);
	}


}

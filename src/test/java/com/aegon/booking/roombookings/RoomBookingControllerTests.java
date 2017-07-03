/**
 * 
 * @author Nareshkumar
 * Classname: RoomBookingControllerTests
 *
 */
package com.aegon.booking.roombookings;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.aegon.booking.roombookings.model.BookingEntity;
import com.aegon.booking.roombookings.repository.BookingEntityRepository;

/**
 * The Class RoomBookingControllerTests is used to check Integration testing 
 * for the RoomBookingApplication using the following test cases.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
/**
 * The execution order of test cases is maintained for the purpose of 
 * Integration testing.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomBookingControllerTests {
	
	/** The booking entity repository used for DB actions. */
	@Autowired
	private BookingEntityRepository bookingEntityRepository;
	
	/** The test rest template used to fire restful webservice. */
	private TestRestTemplate testRestTemplate = new TestRestTemplate();
	
	/**
	 * This method is used to test the restful webservice used for booking
	 * new room.
	 *
	 */
	@Test
	public void testCase1NewBooking()
	{
		/**
		 * Creating a new booking entity with the required details
		 */
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setRoomId(1);
		bookingEntity.setRoomType("single");
		bookingEntity.setCustId(1);
		bookingEntity.setCustName("Naresh");
		bookingEntity.setFromDate(LocalDate.parse("2017-08-01"));
		bookingEntity.setToDate(LocalDate.parse("2017-08-03"));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BookingEntity> entity = new HttpEntity<BookingEntity>(bookingEntity, headers);

		/**
		 * Triggering POST request for booking new room and getting 
		 * ResponseEntity<BookingEntity> as response.
		 */
		ResponseEntity<BookingEntity> response = testRestTemplate
				.postForEntity("http://localhost:8080/lochside/booking", entity, BookingEntity.class);

		//Retrieving booking id of newly saved booking
		Long savedBookingId = response.getBody().getBookingId();
		
		//Checking bookingId is not null
		assertThat(savedBookingId).isNotNull();
		//Checking bookingId value is 1 (as it is the first booking)
		assertThat(savedBookingId).isEqualTo(new Long(1));
		
		/**
		 * Retrieving booking details of saved booking and checking whether
		 * all the values are saved properly in repository. 
		 */
		BookingEntity savedBooking = bookingEntityRepository.findOne(savedBookingId);
		assertThat(savedBooking.getRoomId()).isEqualTo(bookingEntity.getRoomId());
		assertThat(savedBooking.getRoomType()).isEqualTo(bookingEntity.getRoomType());
		assertThat(savedBooking.getCustId()).isEqualTo(bookingEntity.getCustId());
		assertThat(savedBooking.getCustName()).isEqualTo(bookingEntity.getCustName());
		assertThat(savedBooking.getFromDate()).isEqualTo(bookingEntity.getFromDate());
		assertThat(savedBooking.getToDate()).isEqualTo(bookingEntity.getToDate());
	}
	
	/**
	 * This method is used to test the restful webservice used for updating
	 * the booked room.
	 *
	 */
	@Test
	public void testCase2UpdateBooking()
	{
		/**
		 * Creating a new booking entity with the required details and saving
		 * in repository.
		 */
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setRoomId(2);
		bookingEntity.setRoomType("single");
		bookingEntity.setCustId(2);
		bookingEntity.setCustName("Kumar");
		bookingEntity.setFromDate(LocalDate.parse("2017-08-04"));
		bookingEntity.setToDate(LocalDate.parse("2017-07-06"));

		BookingEntity savedBooking = new BookingEntity();
		savedBooking = bookingEntityRepository.save(bookingEntity);
		
		/**
		 * Preparing the booking entity to be updated.
		 */
		BookingEntity updatedBookingEntity = new BookingEntity();
		updatedBookingEntity.setRoomId(2);
		updatedBookingEntity.setRoomType("single");
		updatedBookingEntity.setFromDate(LocalDate.parse("2017-08-04"));
		updatedBookingEntity.setToDate(LocalDate.parse("2017-08-06"));
		/**
		 * Updating the Customer id and Customer name from the 
		 * previously saved booking.
		 */
		updatedBookingEntity.setCustId(3);
		updatedBookingEntity.setCustName("Vayan");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BookingEntity> entity = new HttpEntity<BookingEntity>(updatedBookingEntity, headers);

		Map<String, String> params = new HashMap<String, String>();
	    params.put("bookingId", savedBooking.getBookingId().toString());

		/**
		 * Triggering PUT request for updating the existing booking and getting 
		 * updated ResponseEntity<BookingEntity> as response.
		 */
		ResponseEntity<BookingEntity> response = testRestTemplate.exchange(
				"http://localhost:8080/lochside/booking/{bookingId}", HttpMethod.PUT, entity, BookingEntity.class,
				params);
		
		//Checking the booking id is not null
		assertThat(response.getBody().getBookingId()).isNotNull();
		
		//Checking whether the customer id and name has been updated.
		assertThat(response.getBody().getCustId()).isEqualTo(updatedBookingEntity.getCustId());
		assertThat(response.getBody().getCustId()).isNotEqualTo(bookingEntity.getCustId());
		assertThat(response.getBody().getCustName()).isEqualTo(updatedBookingEntity.getCustName());
		assertThat(response.getBody().getCustName()).isNotEqualTo(bookingEntity.getCustName());
	}
	
	/**
	 * This method is used to test the restful webservice used for retrieving 
	 * the bookings based on customer id.
	 *
	 */
	@Test
	public void testCase3BookingByCustomerId()
	{
		/**
		 * Creating three booking entities with two, having customer id as 4
		 * and saving in repository. 
		 */
		BookingEntity bookingEntity1 = new BookingEntity();
		bookingEntity1.setRoomId(1);
		bookingEntity1.setRoomType("single");
		bookingEntity1.setCustId(4);
		bookingEntity1.setCustName("Dagul");
		bookingEntity1.setFromDate(LocalDate.parse("2017-08-07"));
		bookingEntity1.setToDate(LocalDate.parse("2017-08-09"));
		bookingEntityRepository.save(bookingEntity1);
		
		BookingEntity bookingEntity2 = new BookingEntity();
		bookingEntity2.setRoomId(2);
		bookingEntity2.setRoomType("single");
		bookingEntity2.setCustId(4);
		bookingEntity2.setCustName("Dagul");
		bookingEntity2.setFromDate(LocalDate.parse("2017-08-11"));
		bookingEntity2.setToDate(LocalDate.parse("2017-08-12"));
		bookingEntityRepository.save(bookingEntity2);
		
		BookingEntity bookingEntity3 = new BookingEntity();
		bookingEntity3.setRoomId(3);
		bookingEntity3.setRoomType("double");
		bookingEntity3.setCustId(2);
		bookingEntity3.setCustName("Kumar");
		bookingEntity3.setFromDate(LocalDate.parse("2017-08-13"));
		bookingEntity3.setToDate(LocalDate.parse("2017-08-15"));
		bookingEntityRepository.save(bookingEntity3);
		
		ParameterizedTypeReference<List<BookingEntity>> paramResp = new ParameterizedTypeReference<List<BookingEntity>>() {};
		Map<String, String> params = new HashMap<String, String>();
	    params.put("custId", "4");
	    
		/**
		 * Triggering GET request for retrieving bookings based on customer id.
		 */
		ResponseEntity<List<BookingEntity>> response = testRestTemplate
				.exchange("http://localhost:8080/lochside/customers/{custId}", HttpMethod.GET, null, paramResp, params); 
		//Checking the response is not null
		assertThat(response.getBody()).isNotNull();
		//Checking whether response contains 2 booking entities for customer id 4
		assertThat(response.getBody().size()).isEqualTo(2);

		//Checking whether the bookings belongs to customer with id 4
		for (int i = 0; i < response.getBody().size(); i++) {
			assertThat(response.getBody().get(i).getCustId()).isEqualTo(4);
		}
	}
	
	
	/**
	 * This method is used to test the restful webservice used for retrieving 
	 * the bookings based on room id.
	 *
	 */
	@Test
	public void testCase4BookingByRoomId()
	{
		ParameterizedTypeReference<List<BookingEntity>> paramResp = new ParameterizedTypeReference<List<BookingEntity>>() {};
		Map<String, String> params = new HashMap<String, String>();
	    params.put("roomId", "1");

		/**
		 * Triggering GET request for retrieving bookings based on room id.
		 */
	    ResponseEntity<List<BookingEntity>> response = testRestTemplate
				.exchange("http://localhost:8080/lochside/rooms/{roomId}", HttpMethod.GET, null, paramResp, params); 

	    //Checking the response is not null
	    assertThat(response.getBody()).isNotNull();
		
		//Checking whether the bookings belongs to room with id 1
		for (int i = 0; i < response.getBody().size(); i++) {
			assertThat(response.getBody().get(i).getRoomId()).isEqualTo(1);
		}
	}
	
	/**
	 * This method is used to test the restful webservice used for checking 
	 * the availability of rooms for a given date range.
	 *
	 */
	@Test
	public void testCase5AvailabilityOfRoomBasedonDateRange()
	{
		ParameterizedTypeReference<Map<LocalDate, String>> paramResp = new ParameterizedTypeReference<Map<LocalDate, String>>() {};
		Map<String, String> params = new HashMap<String, String>();
		params.put("fromDate", "2017-08-01");
		params.put("toDate", "2017-08-10");
		params.put("roomId", "1");
		/**
		 * Triggering GET request for retrieving availability of specific
		 * room for a given date range.
		 */
		ResponseEntity<Map<LocalDate, String>> response = testRestTemplate.exchange(
				"http://localhost:8080/lochside/availability/from/{fromDate}/to/{toDate}/room/{roomId}", HttpMethod.GET,
				null, paramResp, params);
		//Checking the response is not null
		assertThat(response.getBody()).isNotNull();
		
		//Checking the availability of room id 1 on 2017-08-01.
		assertThat(response.getBody().get(LocalDate.parse("2017-08-01"))).isEqualTo("UNAVAILABLE");
	}

}

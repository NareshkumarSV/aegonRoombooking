/**
 * 
 * @author Nareshkumar
 * Classname: BookingServiceImpl
 *
 */
package com.aegon.booking.roombookings.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aegon.booking.roombookings.exception.RoomBookingException;
import com.aegon.booking.roombookings.model.BookingEntity;
import com.aegon.booking.roombookings.model.RoomEntity;
import com.aegon.booking.roombookings.repository.BookingEntityRepository;
import com.aegon.booking.roombookings.repository.RoomEntityRepository;
import com.aegon.booking.roombookings.service.BookingService;
import com.aegon.booking.roombookings.util.ApplicationConstants;

/**
 * The Class BookingServiceImpl implements all the service methods 
 * of BookingService, that handles the methods required for maintaining
 * room booking application.
 */
@Service
public class BookingServiceImpl implements BookingService {

	/** The bookingEntityRepository used to handle DB operations of BookingEntity. */
	@Autowired
	BookingEntityRepository bookingEntityRepository;

	/** The roomEntityRepository used to handle DB operations of RoomEntity. */
	@Autowired
	RoomEntityRepository roomEntityRepository;

	/**
	 * This method is used for creating a new booking.
	 *
	 * @param bookingEntity : post body of BookingEntity
	 * @return BookingEntity: the new BookingEntity
	 * @throws RoomBookingException : If request validation fails
	 */
	@Override
	public BookingEntity newBooking(BookingEntity bookingEntity) throws RoomBookingException {

		BookingEntity savedBooking = new BookingEntity();
		boolean validateBooking = validateBookingDetails(bookingEntity);

		if (validateBooking) {
			BookingEntity newBooking = bookingEntity;

			LocalDate fromDate = bookingEntity.getFromDate();
			LocalDate toDate = bookingEntity.getToDate();

			newBooking.costCalculation(bookingEntity.getRoomType(), fromDate, toDate);

			Map<LocalDate, String> roomAvailableStatus = new HashMap<>();
			roomAvailableStatus = getRoomAvailability(fromDate, toDate, bookingEntity.getRoomId());

			if (!roomAvailableStatus.containsValue(ApplicationConstants.UNAVAILABLE)) {

				savedBooking = bookingEntityRepository.save(newBooking);

				while ((fromDate.isEqual(toDate)) || (fromDate.isBefore(toDate))) {
					RoomEntity roomEntity = new RoomEntity();
					roomEntity.setBookingId(savedBooking.getBookingId());
					roomEntity.setRoomId(bookingEntity.getRoomId());
					roomEntity.setAvailableDay(fromDate);
					roomEntityRepository.save(roomEntity);
					fromDate = fromDate.plusDays(1);
				}
			}
		}
		return savedBooking;
	}

	/**
	 * This method is used for retrieving the 
	 * bookings based on the room id.
	 *
	 * @param roomId : the room id
	 * @return List<BookingEntity>: the list of booking entity
	 */
	@Override
	public List<BookingEntity> getBookingByRoomId(int roomId) {

		List<BookingEntity> allBookings = getAllBookings();
		List<BookingEntity> roomBookings = new ArrayList<>();

		if ((allBookings != null) && allBookings.size() != 0) {
			for (BookingEntity bookingEntity : allBookings) {
				if (bookingEntity.getRoomId() == roomId) {
					roomBookings.add(bookingEntity);
				}
			}
		}
		return roomBookings;
	}

	/**
	 * This method is used for retrieving the 
	 * bookings based on the customer id.
	 *
	 * @param custId : the customer id
	 * @return List<BookingEntity>: the list of booking entity
	 */
	@Override
	public List<BookingEntity> getBookingByCustId(int custId) {

		List<BookingEntity> allBookings = getAllBookings();
		List<BookingEntity> customerBookings = new ArrayList<>();

		if ((allBookings != null) && allBookings.size() != 0) {
			for (BookingEntity bookingEntity : allBookings) {
				if (bookingEntity.getCustId() == custId) {
					customerBookings.add(bookingEntity);
				}
			}
		}
		return customerBookings;
	}

	/**
	 * This method is used for updating the existing 
	 * booking.
	 *
	 * @param bookingId : the booking to be updated
	 * @param bookingEntity : post body of BookingEntity
	 * @return BookingEntity: the updated BookingEntity
	 * @throws RoomBookingException : If request validation fails
	 */
	@Override
	public BookingEntity updateBooking(Long bookingId, BookingEntity bookingEntity) throws RoomBookingException {

		BookingEntity savedBooking = new BookingEntity();
		boolean validateBooking = validateBookingDetails(bookingEntity);

		if (validateBooking) {
			BookingEntity existingBooking = bookingEntityRepository.findOne(bookingId);

			if ((existingBooking != null) && bookingId.equals(existingBooking.getBookingId())) {

				if ((existingBooking.getFromDate().equals(bookingEntity.getFromDate()))
						&& (existingBooking.getToDate().equals(bookingEntity.getToDate()))) {

					existingBooking = bookingEntity;
					existingBooking.setBookingId(bookingId);
					existingBooking.costCalculation(bookingEntity.getRoomType(), bookingEntity.getFromDate(),
							bookingEntity.getToDate());
					savedBooking = bookingEntityRepository.save(existingBooking);
				} else {
					List<RoomEntity> allRoomsAvailability = roomEntityRepository.findAll();
					List<LocalDate> availDate = new ArrayList<>();

					if ((allRoomsAvailability != null) && allRoomsAvailability.size() != 0) {

						for (RoomEntity roomEntity : allRoomsAvailability) {
							if ((roomEntity.getRoomId() == bookingEntity.getRoomId())
									&& (roomEntity.getBookingId() != bookingId)) {
								availDate.add(roomEntity.getAvailableDay());
							}
						}
						LocalDate fromDate = bookingEntity.getFromDate();
						LocalDate toDate = bookingEntity.getToDate();

						while ((fromDate.isEqual(toDate)) || (fromDate.isBefore(toDate))) {
							if (availDate.contains(fromDate)) {
								throw new RoomBookingException(ApplicationConstants.DATES_UNAVAILABLE);
							}
							fromDate = fromDate.plusDays(1);
						}

						for (RoomEntity roomEntity : allRoomsAvailability) {
							if ((roomEntity.getRoomId() == bookingEntity.getRoomId())
									&& (roomEntity.getBookingId() == bookingId)) {
								roomEntityRepository.delete(roomEntity);
							}
						}

						existingBooking = bookingEntity;
						existingBooking.setBookingId(bookingId);
						existingBooking.costCalculation(bookingEntity.getRoomType(), bookingEntity.getFromDate(),
								bookingEntity.getToDate());
						savedBooking = bookingEntityRepository.save(existingBooking);

						LocalDate savedFromDate = savedBooking.getFromDate();
						LocalDate savedToDate = savedBooking.getToDate();

						while ((savedFromDate.isEqual(savedToDate)) || (savedFromDate.isBefore(savedToDate))) {
							RoomEntity roomEntity = new RoomEntity();
							roomEntity.setBookingId(savedBooking.getBookingId());
							roomEntity.setRoomId(savedBooking.getRoomId());
							roomEntity.setAvailableDay(savedFromDate);
							roomEntityRepository.save(roomEntity);
							savedFromDate = savedFromDate.plusDays(1);
						}
					}
				}
			}
		}

		return savedBooking;
	}

	/**
	 * This method is used for retrieving the 
	 * availability of specific room within the provided range of period.
	 *
	 * @param fromDate : fromdate
	 * @param toDate : todate
	 * @param roomId : Id of room
	 * @return Map : availability of room
	 */
	@Override
	public Map<LocalDate, String> getRoomAvailability(LocalDate fromDate, LocalDate toDate, int roomId) {

		Map<LocalDate, String> roomAvailableStatus = new HashMap<>();
		LocalDate fromDateRange = fromDate;
		LocalDate toDateRange = toDate;

		while ((fromDateRange.isEqual(toDateRange)) || (fromDateRange.isBefore(toDateRange))) {
			roomAvailableStatus.put(fromDateRange, ApplicationConstants.AVAILABLE);
			fromDateRange = fromDateRange.plusDays(1);
		}

		List<RoomEntity> allRoomsAvailability = roomEntityRepository.findAll();

		if ((allRoomsAvailability != null) && allRoomsAvailability.size() != 0) {

			for (RoomEntity roomEntity : allRoomsAvailability) {
				if (roomEntity.getRoomId() == roomId) {

					if (roomAvailableStatus.containsKey(roomEntity.getAvailableDay())) {
						roomAvailableStatus.put(roomEntity.getAvailableDay(), ApplicationConstants.UNAVAILABLE);
					}
				}
			}
		}
		return new TreeMap<LocalDate, String>(roomAvailableStatus);
	}

	/**
	 * This method is used to retrieve all the available bookings from the repository
	 *
	 * @return List<BookingEntity> : list of all the bookings
	 */
	private List<BookingEntity> getAllBookings() {
		List<BookingEntity> allBookings = bookingEntityRepository.findAll();
		return allBookings;
	}

	/**
	 * This method is used to validate the input details provided for booking.
	 *
	 * @param bookingEntity the booking entity
	 * @return true, if successful
	 * @throws RoomBookingException : the room booking exception if validation fails
	 */
	private boolean validateBookingDetails(BookingEntity bookingEntity) throws RoomBookingException {
		if ((bookingEntity != null) && (bookingEntity.getRoomId() != 0) && (bookingEntity.getRoomType() != null)
				&& (bookingEntity.getCustId() != 0) && (bookingEntity.getCustName() != null)
				&& (bookingEntity.getFromDate() != null) && (bookingEntity.getToDate() != null)) {

			if ((bookingEntity.getRoomId() > 0) && (bookingEntity.getRoomId() < 6)) {

				if (((bookingEntity.getRoomId() < 3) && bookingEntity.getRoomType().equals(ApplicationConstants.SINGLE))
						|| ((bookingEntity.getRoomId() > 2)
								&& bookingEntity.getRoomType().equals(ApplicationConstants.DOUBLE))) {

					if ((bookingEntity.getFromDate().equals(bookingEntity.getToDate()))
							|| (bookingEntity.getFromDate().isBefore(bookingEntity.getToDate()))) {
						return true;
					} else {
						throw new RoomBookingException(ApplicationConstants.INVALID_DATE_RANGE);
					}
				} else {
					throw new RoomBookingException(ApplicationConstants.INVALID_ROOM_TYPE);
				}
			} else {
				throw new RoomBookingException(ApplicationConstants.INVALID_ROOM_ID);
			}
		} else {
			throw new RoomBookingException(ApplicationConstants.DETAILS_MISSING);
		}

	}
}

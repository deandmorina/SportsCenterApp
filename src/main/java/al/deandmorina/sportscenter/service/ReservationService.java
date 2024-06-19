package al.deandmorina.sportscenter.service;

import al.deandmorina.sportscenter.entity.Event;
import al.deandmorina.sportscenter.entity.Reservation;
import al.deandmorina.sportscenter.entity.User;
import al.deandmorina.sportscenter.payload.responseDTO.ReservationResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.ReservationSaveDTO;
import al.deandmorina.sportscenter.repository.EventRepository;
import al.deandmorina.sportscenter.repository.ReservationRepository;
import al.deandmorina.sportscenter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ModelMapper modelMapper, UserRepository userRepository, EventRepository eventRepository) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public ReservationResponseDTO makeReservation(ReservationSaveDTO reservationSaveDTO, Principal principal) {
        Reservation reservation = modelMapper.map(reservationSaveDTO, Reservation.class);
        User user = this.userRepository.findByEmail(principal.getName());
        reservation.setUser(user);
        Event event = this.eventRepository.findById(reservationSaveDTO.getEventId()).orElseThrow(() -> new RuntimeException("Event not found"));
        reservation.setEvent(event);
        reservation.setNormalSeatPrice(event.getNormalSeatPrice());
        reservation.setVIPSeatPrice(event.getVIPSeatPrice());
        reservation.setUltraSeatPrice(event.getUltraSeatPrice());
        Reservation saved = this.reservationRepository.save(reservation);
        ReservationResponseDTO response = modelMapper.map(saved, ReservationResponseDTO.class);
        double total = response.getNormalSeats() * response.getNormalSeatPrice() + response.getVIPSeats() * response.getVIPSeatPrice() + response.getUltraSeats() * response.getUltraSeatPrice();
        response.setTotal(total);
        return response;
    }
}

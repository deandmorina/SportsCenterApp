package al.deandmorina.sportscenter.service;

import al.deandmorina.sportscenter.entity.Event;
import al.deandmorina.sportscenter.entity.Hall;
import al.deandmorina.sportscenter.entity.Reservation;
import al.deandmorina.sportscenter.entity.User;
import al.deandmorina.sportscenter.payload.responseDTO.ReservationResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.ReservationSaveDTO;
import al.deandmorina.sportscenter.repository.EventRepository;
import al.deandmorina.sportscenter.repository.HallRepository;
import al.deandmorina.sportscenter.repository.ReservationRepository;
import al.deandmorina.sportscenter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ReservationService {
    private final HallRepository hallRepository;
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public ReservationService(HallRepository hallRepository, ReservationRepository reservationRepository, ModelMapper modelMapper, UserRepository userRepository, EventRepository eventRepository) {
        this.hallRepository = hallRepository;
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public ReservationResponseDTO makeReservation(ReservationSaveDTO reservationSaveDTO, Principal principal) throws Exception {
        Event event = this.eventRepository.findById(reservationSaveDTO.getEventId()).orElseThrow(() -> new RuntimeException("Event not found"));
        List<Reservation> reservations = this.reservationRepository.findByEvent(event);
        int reservedNormal = 0, reservedVIP = 0, reservedUltra = 0;
        for (Reservation reservation : reservations) {
            reservedNormal += reservation.getNormalSeats();
            reservedVIP += reservation.getVIPSeats();
            reservedUltra += reservation.getUltraSeats();
        }
        Hall hall = this.hallRepository.findById(event.getHall().getId()).orElseThrow(() -> new RuntimeException("Hall not found"));
        if (reservationSaveDTO.getNormalSeats() > (hall.getNormalSeats() - reservedNormal)
                && reservationSaveDTO.getVIPSeats() > (hall.getVIPSeats() - reservedVIP)
                && reservationSaveDTO.getUltraSeats() > (hall.getUltraSeats() - reservedUltra)) {
            throw new Exception("You cannot make this reservation!");
        }

        Reservation reservation = modelMapper.map(reservationSaveDTO, Reservation.class);
        User user = this.userRepository.findByEmail(principal.getName());
        reservation.setUser(user);
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

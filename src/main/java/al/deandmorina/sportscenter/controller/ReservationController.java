package al.deandmorina.sportscenter.controller;

import al.deandmorina.sportscenter.payload.responseDTO.ReservationResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.ReservationSaveDTO;
import al.deandmorina.sportscenter.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/make")
    public ReservationResponseDTO makeReservation(@Valid @RequestBody ReservationSaveDTO reservationSaveDTO, Principal principal) throws Exception {
        return this.reservationService.makeReservation(reservationSaveDTO, principal);
    }
}

package al.deandmorina.sportscenter.service;

import al.deandmorina.sportscenter.entity.Feedback;
import al.deandmorina.sportscenter.entity.Reservation;
import al.deandmorina.sportscenter.payload.responseDTO.FeedbackResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.FeedbackSaveDTO;
import al.deandmorina.sportscenter.repository.FeedbackRepository;
import al.deandmorina.sportscenter.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;
    private final ReservationRepository reservationRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, ModelMapper modelMapper, ReservationRepository reservationRepository) {
        this.feedbackRepository = feedbackRepository;
        this.modelMapper = modelMapper;
        this.reservationRepository = reservationRepository;
    }

    public FeedbackResponseDTO leaveFeedback(FeedbackSaveDTO feedbackSaveDTO, Principal principal) throws Exception {
        Reservation reservation = this.reservationRepository.findById(feedbackSaveDTO.getReservationId()).orElseThrow(() -> new RuntimeException("Reservation not found"));
        if (!reservation.getUser().getEmail().equalsIgnoreCase(principal.getName())) {
            throw new Exception("You are not allowed to leave this feedback!");
        }
        Feedback existingFeedback = this.feedbackRepository.findByReservation(reservation);
        if (existingFeedback != null) {
            throw new Exception("You have already left a feedback for this reservation!");
        }
        Feedback feedback = modelMapper.map(feedbackSaveDTO, Feedback.class);
        feedback.setReservation(reservation);
        Feedback savedFeedback = this.feedbackRepository.save(feedback);
        return this.modelMapper.map(savedFeedback, FeedbackResponseDTO.class);
    }
}

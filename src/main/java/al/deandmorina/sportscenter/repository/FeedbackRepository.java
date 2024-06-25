package al.deandmorina.sportscenter.repository;

import al.deandmorina.sportscenter.entity.Feedback;
import al.deandmorina.sportscenter.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findByReservation(Reservation reservation);
}

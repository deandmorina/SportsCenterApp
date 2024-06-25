package al.deandmorina.sportscenter.repository;

import al.deandmorina.sportscenter.entity.Event;
import al.deandmorina.sportscenter.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByEvent(Event event);
}

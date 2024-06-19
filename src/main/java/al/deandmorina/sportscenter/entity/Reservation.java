package al.deandmorina.sportscenter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "crated_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne
    @Column(name = "user", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @Column(name = "event", nullable = false)
    @JsonBackReference
    private Event event;

    @Column(name = "normal_seats", nullable = false)
    private int normalSeats;

    @Column(name = "vip_seats", nullable = false)
    private int VIPSeats;

    @Column(name = "ultra_seats", nullable = false)
    private int ultraSeats;

    @Column(name = "normal_seat_price", nullable = false)
    private double normalSeatPrice;

    @Column(name = "vip_seat_price", nullable = false)
    private double VIPSeatPrice;

    @Column(name = "ultra_seat_price", nullable = false)
    private double ultraSeatPrice;
}


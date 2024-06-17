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
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne
    @Column(name = "hall", nullable = false)
    @JsonBackReference
    private Hall hall;

    @Column(name = "normal_seat_price", nullable = false)
    private double normalSeatPrice;

    @Column(name = "vip_seat_price", nullable = false)
    private double VIPSeatPrice;

    @Column(name = "ultra_seat_price", nullable = false)
    private double UltraSeatPrice;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(name = "first_team", nullable = false)
    private String firstTeam;

    @Column(name = "second_team", nullable = false)
    private String secondTeam;
}

package al.deandmorina.sportscenter.entity;

import al.deandmorina.sportscenter.enums.HallType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Hall {
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hall_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private HallType hallType;

    @Column(name = "normal_seats", nullable = false)
    private int normalSeats;

    @Column(name = "vip_seats", nullable = false)
    private int VIPSeats;

    @Column(name = "ultra_seats", nullable = false)
    private int ultraSeats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;
}

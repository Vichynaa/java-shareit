package ru.practicum.shareit.booking;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    @JoinColumn(name="item_id")
    Item item;
    @ManyToOne()
    @JoinColumn(name="booker_id")
    User booker;
    @Column(name = "start_time", nullable = false)
    LocalDateTime start;
    @Column(name = "end_time", nullable = false)
    LocalDateTime end;
    @Enumerated(EnumType.STRING)
    BookingStatus status;
}

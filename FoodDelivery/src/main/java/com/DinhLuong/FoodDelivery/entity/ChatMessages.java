package com.DinhLuong.FoodDelivery.entity;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "ChatMessages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users usersSender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Users usersReceiver;
    @Column(name = "message")
    private String message;
    @Column(name = "sent_at")
    private Date sent_at;
    
}

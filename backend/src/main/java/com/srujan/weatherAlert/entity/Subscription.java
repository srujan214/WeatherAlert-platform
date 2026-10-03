package com.srujan.weatherAlert.entity;

import jakarta.persistence.*;            //USER
import lombok.AllArgsConstructor;        //     Srujan
import lombok.Data;                      //   email: srujan@gmail.com
import lombok.NoArgsConstructor;         //          has
import java.time.LocalDateTime;          //  subscription like
import java.util.ArrayList;               //       bengaluru -alert if temperature >35C
import java.util.List;                    //        mysure - alert if temperature >38C
                                          // Alert History
@Entity                                   //         alert sent on oct 1
@Table(name = "subscriptions")            //         alert sent on oct 2
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)//this stores where the user wants weather alerts and city name con't be nullable
    private String city;

    @Column(nullable = false)//add temp like 35 ,36 its a threshold temperature
    private Double temperatureThreshold;

    @Column(nullable = false)//it tells the system that subscription is currently active or not
    private Boolean isActive = true;

    @Column(nullable = false, updatable = false)//its save created time which cant be nullable and updatable
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime lastAlertSentAt; //

    @ManyToOne(fetch = FetchType.LAZY)//it show the relation between user and subscription
    //many subscription belong to one user so its called many to one
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //one subscription can have many alert history records
    private List<AlertHistory> alertHistories = new ArrayList<>();
}
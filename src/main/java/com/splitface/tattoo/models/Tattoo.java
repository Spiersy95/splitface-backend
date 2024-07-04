package com.splitface.tattoo.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table
public class Tattoo {

    @Id
    @GeneratedValue
    private Long id;
    private String design;
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    @JsonIgnore
    private Artist artist;


    @Column(name = "hours_worked")
    private String hoursWorked;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "style_tattoo_mapping", joinColumns = @JoinColumn(name = "tattoo_id"),
            inverseJoinColumns = @JoinColumn(name = "style_id"))
    private List<Style> styles;

    @Column(name = "time_posted")
    private Instant timePosted;


}

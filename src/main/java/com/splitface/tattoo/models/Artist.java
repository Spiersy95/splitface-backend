package com.splitface.tattoo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table
public class Artist {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;

    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    private List<Tattoo> tattoos;

    private String profileURL;
}


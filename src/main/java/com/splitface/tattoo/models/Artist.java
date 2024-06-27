package com.splitface.tattoo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;

    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Tattoo> tattoos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Tattoo> getTattoos() {
        return tattoos;
    }

    public void setTattoos(List<Tattoo> tattoos) {
        this.tattoos = tattoos;
    }
}


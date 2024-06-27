package com.splitface.tattoo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Style {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "style_tattoo_mapping", joinColumns = @JoinColumn(name = "style_id"),
            inverseJoinColumns = @JoinColumn(name = "tattoo_id"))
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

    public List<Tattoo> getTattoos() {
        return tattoos;
    }

    public void setTattoos(List<Tattoo> tattoos) {
        this.tattoos = tattoos;
    }
}

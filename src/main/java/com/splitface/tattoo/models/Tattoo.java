package com.splitface.tattoo.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tattoo {

    @Id
    @GeneratedValue
    private Long id;
    private String design;
    private String price;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "style_tattoo_mapping", joinColumns = @JoinColumn(name = "tattoo_id"),
            inverseJoinColumns = @JoinColumn(name = "style_id"))
    private List<Style> styles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }
}

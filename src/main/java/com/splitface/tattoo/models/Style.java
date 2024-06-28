package com.splitface.tattoo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Style {

    public enum StyleEnum {
        REALISM,
        WATERCOLOUR,
        WILDCARD
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private StyleEnum styleName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "style_tattoo_mapping", joinColumns = @JoinColumn(name = "style_id"),
            inverseJoinColumns = @JoinColumn(name = "tattoo_id"))
    @JsonBackReference
    private List<Tattoo> tattoos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StyleEnum getName() {
        return styleName;
    }

    public void setName(StyleEnum styleName) {
        this.styleName = styleName;
    }

    public List<Tattoo> getTattoos() {
        return tattoos;
    }

    public void setTattoos(List<Tattoo> tattoos) {
        this.tattoos = tattoos;
    }
}

package com.xmen.meli.hhfm.entetity;


import com.xmen.meli.hhfm.enumeration.KindPerson;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DNAEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column
    private String dna;
    @Column
    private KindPerson kindPerson;

    public DNAEntity(int id, String dna, KindPerson kindPerson) {
        this.id = id;
        this.dna = dna;
        this.kindPerson = kindPerson;
    }

    public DNAEntity() { }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public KindPerson getKindPerson() {
        return kindPerson;
    }

    public void setKindPerson(KindPerson kindPerson) {
        this.kindPerson = kindPerson;
    }
}


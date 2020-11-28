package hu.unideb.fupn26.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class MatchStatId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "mid")
    private MatchEntity match;

    @ManyToOne
    @JoinColumn(name = "pid")
    private PlayerEntity player;
}

package hu.unideb.fupn26.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class MatchStatId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "mid")
    private MatchEntity match;

    @ManyToOne
    @JoinColumn(name = "pid")
    private PlayerEntity player;
}

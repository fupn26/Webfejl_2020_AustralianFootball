package hu.unideb.fupn26.model;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Team {

    Integer id;
    @NonNull
    String teamName;
}

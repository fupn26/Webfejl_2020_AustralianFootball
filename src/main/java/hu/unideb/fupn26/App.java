package hu.unideb.fupn26;

import hu.unideb.fupn26.dao.MatchDao;
import hu.unideb.fupn26.dao.MatchStatDao;
import hu.unideb.fupn26.dao.PlayerDao;
import hu.unideb.fupn26.dao.TeamDao;
import hu.unideb.fupn26.model.Match;
import hu.unideb.fupn26.model.MatchStat;
import hu.unideb.fupn26.model.Player;
import hu.unideb.fupn26.model.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Run -> Configuration -> Environment Variables
 *  - DB_HOST localhost /127.0.0.1
 *  - DB_PORT 3306
 *  - DB_NAME AustralianFootball
 *  - DB_USER root
 *  - DB_PASS secret
 *
 *  Example configuration
 * @see {project.basedir}/src/main/resources/sql/football.sh
 */
@Slf4j
@SpringBootApplication
//@RequiredArgsConstructor
public class App /*implements CommandLineRunner*/
{
//    private final MatchDao matchDao;
//    private final MatchStatDao matchStatDao;
//    private final TeamDao teamDao;
//    private final PlayerDao playerDao;

//    @Autowired
//    ApplicationContext context;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Hello World!");
//
//        Match match = new Match(
//                2020,
//                "EF",
//                "Adelaide",
//                "Carlton",
//                "h",
//                "a",
//                "Carlton",
//                120,
//                "Adelaide",
//                34,
//                "Adelaide",
//                "Carlton"
//        );
//
//        MatchStat matchStat = new MatchStat(
//                "Aaron",
//                "Black",
//                "Carlton",
//                "Adelaide",
//                2020,
//                "EF",
//                "a"
//        );
//
//        Team team = new Team("HelloTeam");
//
//        Player player = new Player(2020, 10, 9, "Peter", "Furjes", 190, 90);
//        try {
//            playerDao.createPlayer(player);
//            playerDao.deletePlayer(player);
//            playerDao.readAll().stream()
//                    .forEach(System.out::println);
//            teamDao.createTeam(team);
//            teamDao.deleteTeam(team);
//            teamDao.readAll().stream()
//                    .forEach(System.out::println);
//            matchDao.createMatch(match);
//            matchStatDao.createMatchStat(matchStat);
//            matchStatDao.deleteMatchStat(matchStat);
//            matchDao.deleteMatch(match);
//            matchDao.readAll().stream()
//                    .forEach(System.out::println);
////            matchStatDao.readAll().stream()
////                    .filter(model -> model.getSeason() == 2020)
////                    .forEach(System.out::println);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            e.printStackTrace();
//        }
//    }
}
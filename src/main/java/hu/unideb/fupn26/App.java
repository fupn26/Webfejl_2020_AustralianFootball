package hu.unideb.fupn26;

import hu.unideb.fupn26.dao.MatchDao;
import hu.unideb.fupn26.model.Match;
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
@RequiredArgsConstructor
public class App implements CommandLineRunner
{
    private final MatchDao matchDao;

    @Autowired
    ApplicationContext context;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World!");

        Match match = Match.builder()
                .season(2020)
                .round("EF")
                .team1("Adelaide")
                .team2("Carlton")
                .winnerTeam("Carlton")
                .loserTeam("Adelaide")
                .homeTeam("Adelaide")
                .awayTeam("Carlton")
                .build();

        try {
            matchDao.createMatch(match);
            matchDao.deleteMatch(match);
            matchDao.readAll().stream()
                    .forEach(System.out::println);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}

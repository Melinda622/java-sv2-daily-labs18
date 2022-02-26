package day02;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

public class BooksMain {

    public static void main(String[] args) {

        MariaDbDataSource dataSource=new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/books?useUnicode=true");
            dataSource.setUserName("activitytracker");
            dataSource.setPassword("activitytracker");
        } catch (
                SQLException sqle) {
            throw new IllegalStateException("Cannot insert",sqle);
        }


        Flyway flyway=Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        BooksRepository booksRepository=new BooksRepository(dataSource);

        booksRepository.insertBook("Fekete Istvan","Vuk",3400,3);
        booksRepository.insertBook("Fekete Istvan","Tuskevar",2000,1);
        booksRepository.insertBook("Mora Ferenc","A Pal utcai fiuk",1500,5);

        System.out.println(booksRepository.findBooksByWriter("Fekete Istvan"));

        booksRepository.updatePieces(1,2);
    }
}

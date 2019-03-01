import com.bimdog.mainclasses.DatabaseManager;
import org.junit.*;

import java.sql.SQLException;

public class DataBaseConnectionTest {


    private DatabaseManager databaseManager = new DatabaseManager();

    String HOST_MYSQL = "jdbc:mysql://localhost:3306/users_db"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    String USERNAME_MYSQL = "root";
    String PASSWORD_MYSQL = "root";

    @Test
    public void test_connection_to_db_with_all_correct_input_parameters() {
        try {
            Assert.assertTrue(databaseManager.connect(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

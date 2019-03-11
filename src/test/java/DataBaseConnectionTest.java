import com.bimdog.mainclasses.DatabaseManager;

import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnectionTest {

//    private DatabaseManager databaseManager;
//    private Connection connection;
//
//    String HOST_MYSQL = "jdbc:mysql://localhost:3306/users_db"+
//            "?verifyServerCertificate=false"+
//            "&useSSL=false"+
//            "&requireSSL=false"+
//            "&useLegacyDatetimeCode=false"+
//            "&amp"+
//            "&serverTimezone=UTC";
//    String USERNAME_MYSQL = "root";
//    String PASSWORD_MYSQL = "root";
//
//    @Before
//    public void setup() {
//        databaseManager = new DatabaseManager();
//        System.out.println("before");
//    }
//
//
//    @After
//    public void closeConnection(){
//        try {
//            databaseManager.disconnectDb();
//            System.out.println("конект закрито");
//        } catch (NullPointerException e) {
//            System.out.println("конект не був відкритий - " + e);
//        }
//    }
//
//    @Test
//    public void test_connection_to_db_with_all_correct_input_parameters() {
//        try {
//           connection = databaseManager.connectDB(HOST_MYSQL, USERNAME_MYSQL, PASSWORD_MYSQL);
//            Assert.assertTrue(!connection.isClosed());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("OK. Base test");
//    }
//
//    @Test
//    public void test_connection_to_db_with_Host_null() {
//        try {
//            databaseManager.connectDB(null, USERNAME_MYSQL, PASSWORD_MYSQL);
//            Assert.fail();
//        } catch (SQLException e) {
//            System.out.println("OK");
//        }
//    }
//
//    @Test
//    public void test_connection_to_db_with_username_null() {
//        try {
//            databaseManager.connectDB(HOST_MYSQL, null, PASSWORD_MYSQL);
//            Assert.fail();
//        } catch (SQLException e) {
//            System.out.println("OK");
//        }
//    }
//
//    @Test
//    public void test_connection_to_db_with_password_null() {
//        try {
//            databaseManager.connectDB(HOST_MYSQL, USERNAME_MYSQL, null);
//            Assert.fail();
//        } catch (SQLException e) {
//            System.out.println("OK");
//        }
//    }
//
//    @Test
//    public void test_connection_to_db_with_all_input_parameters_null() {
//        try {
//            databaseManager.connectDB(null, null, null);
//            Assert.fail();
//        } catch (SQLException e) {
//            System.out.println("OK");
//        }
//    }
}

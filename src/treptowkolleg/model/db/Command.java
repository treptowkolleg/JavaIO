package treptowkolleg.model.db;

public interface Command {
    String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";
    String PRIMARY_KEY = " PRIMARY KEY";
    String AUTO_INCREMENT = " AUTO_INCREMENT";
    String NOT_NULL = " NOT NULL";
    String INSERT_INTO = "INSERT INTO ";
    String VALUES = " VALUES ";
    String SELECT_ALL_FROM = "SELECT * FROM ";
    String WHERE = " WHERE ";
    String OPEN_PAREN = " (";
    String CLOSE_PAREN = ")";
    String SEMICOLON = ";";
}

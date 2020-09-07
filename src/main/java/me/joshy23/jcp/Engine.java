package me.joshy23.jcp;

/**
 * @author joshy23
 * @since 6/9/2020
 **/
public enum Engine {
    APACHE_DERBY("Derby","org.apache.derby.jdbc.ClientDataSource"),
    FIREBIRD("Jaybird","org.firebirdsql.ds.FBSimpleDataSource"),
    H2("H2","org.h2.jdbcx.JdbcDataSource"),
    HSQLDB("HSQLDB","org.hsqldb.jdbc.JDBCDataSource"),
    IBM_DB2("IBM JCC","com.ibm.db2.jcc.DB2SimpleDataSource"),
    IBM_INFORMIX("IBM Informix","com.informix.jdbcx.IfxDataSource"),
    MS_SQL_SERVER("Microsoft","com.microsoft.sqlserver.jdbc.SQLServerDataSource"),
    MYSQL("Connector/J","com.mysql.jdbc.jdbc2.optional.MysqlDataSource"),
    MARIADB("MariaDB","org.mariadb.jdbc.MariaDbDataSource"),
    ORACLE("Oracle","oracle.jdbc.pool.OracleDataSource"),
    ORIENTDB("OrientDB","com.orientechnologies.orient.jdbc.OrientDataSource"),
    POSTGRESQL_PG("pgjdbc-ng","com.impossibl.postgres.jdbc.PGDataSource"),
    POSTGRESQL("PostgreSQL","org.postgresql.ds.PGSimpleDataSource"),
    SAP_MAXDB("SAP","com.sap.dbtech.jdbc.DriverSapDB"),
    SQLITE("xerial","org.sqlite.SQLiteDataSource"),
    SYBASE("jConnect","com.sybase.jdbc4.jdbc.SybDataSource");

    private String driver;
    private String dataSource;

    Engine(String driver, String dataSource){
        this.driver = driver;
        this.dataSource = dataSource;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getDriver() {
        return driver;
    }
}

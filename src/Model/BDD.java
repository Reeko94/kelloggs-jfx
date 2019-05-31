package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD {


    private String pwd,server,user,bdd;

    private Connection connection;

    /**
     *
     * @param server
     * @param user
     * @param pwd
     * @param bdd
     */
    public BDD(String server, String user, String pwd, String bdd)
    {
        this.server = server;
        this.user = user;
        this.pwd = pwd;
        this.bdd = bdd;
    }


    /**
     *
     */
    public void loadLibrary()
    {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("Absence du pilote connector JDBC");
        }
    }

    /**
     *
     */
    public void login()
    {
        this.loadLibrary();
        String url = "jdbc:mariadb://" + this.server + "/" + this.bdd;
        try {
            this.connection = DriverManager.getConnection(url,this.user,this.pwd);
        } catch (SQLException e) {
            System.out.println("Connection impossible à " + url);
        }
    }

    /**
     *
     */
    public void logout()
    {
        try {
            if(this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Imposible de fermer la connection");
        }

    }

    /**
     *
     * @param table
     * @param fieldValue
     * @param where
     * @return
     */
    public static String getQueryUpdateTable(String table, String[][] fieldValue, String[][] where)
    {

        /*
         *         String table = "table"
         *         String[][]  fieldValue = {
         *                 {"FIELD","ANOTHER"},
         *                 {"TT","1"},
         *                 {"true","false"}
         *         };
         *         String[][] where = {
         *                 {"id","email"},
         *                 {"=","LIKE"},
         *                 {"1","AZ"},
         *                 {"false","true"}
         *         };
         *
         *         RETURN QUERY
         *         UPDATE table SET FIELD = 'TT', ANOTHER = 1 WHERE id = 1 AND email LIKE 'AZ';
         */

        String query = "UPDATE " + table + " SET ";
        for(int i = 0;i< fieldValue[0].length;i++){
            query += "`" + fieldValue[0][i] + "` =";
            if(fieldValue[2][i] == "true"){
                query += "'" + fieldValue[1][i]+"'";
            } else {
                query += "" + fieldValue[1][i]+"";
            }
            if (i+1 != fieldValue[0].length) {
                query += " , ";
            }
        }

        for(int i = 0;i<where[0].length;i++){
            if(i==0){
                query = query + " WHERE `" + where[0][i] + "` ";
                if(where[3][i] == "true"){
                    query += where[1][i] + " '" + where[2][i] + "'";
                } else {
                    query += where[1][i] + " " + where[2][i] + "";
                }


            } else {
                query += " AND `" + where[0][i] + "` " + where[1][i] + " '" + where[2][i]+ "'";
            }
        }
        query += ";";
        return query;
    }

    public static String getQueryInsertTable(String table, String[][] fieldValue) {
        String query = "INSERT INTO " + table + " (";

        for(int i = 0; i<fieldValue[0].length;i++) {
            query += ("`"+fieldValue[0][i]+"` ");
            if(i != fieldValue[0].length - 1) {
                query += ",";
            }
        }

        query += ") VALUES (";

        // INSERT INTO TABLE (field1,field2) VALUES (
        for(int i=0;i<fieldValue[1].length;i++) {
            if(fieldValue[2][i].equals("true")) {
                query += "'"+fieldValue[1][i] +"'";
            } else {
                query += fieldValue[1][i];
            }
            if(i != fieldValue[1].length - 1) {
                query += ",";
            }
        }

        query +=");";
        return query;
    }


    public Connection getConnection() {
        return this.connection;
    }
}

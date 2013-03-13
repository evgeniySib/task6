package com.it.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");
        PreparedStatement pst;
        Statement st;
        List<Record> listR;
        st = connection.createStatement();
        Calendar calendar = GregorianCalendar.getInstance();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

        st.executeUpdate("CREATE TABLE POST(ID INTEGER NOT NULL AUTO_INCREMENT, postDate DATE, message VARCHAR(200));");
        pst = connection.prepareStatement("INSERT INTO POST VALUES(DEFAULT,?,?);");
        DbController dbc = new DbController(connection, pst);

        while (true) {
            String comm = br.readLine().trim();
            if (comm.length() == 0) {
                continue;
            }
            String[] str = comm.split(" ");

            if (str[0].toLowerCase().equals("add")) {
                comm = comm.substring(str[0].length()).trim();
                if (comm.length() > 0) {
                    dbc.addRecord(comm);
                } else {
                    System.out.println("Сообщение не введено...");
                }
            } else if (str[0].toLowerCase().equals("list")) {
                listR = dbc.getRecords();
                for (Record r : listR) {
                    System.out.println("ID: " + r.getId() + " Date:" + r.getPostDate() + "\nMessage: " + r.getMessage());
                }
            } else if (str[0].toLowerCase().equals("exit")) {
                break;
            } else {
                System.out.println("Неверный формат команды...");
            }
        }
        pst.close();
        st.close();
        connection.close();
    }
}

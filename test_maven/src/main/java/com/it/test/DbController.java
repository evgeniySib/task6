package com.it.test;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DbController implements GuestBookController {
    private int id = 0;
    private List<Record> listR = new ArrayList();
    private Connection conn;
    private PreparedStatement pst;

    private Calendar calendar = GregorianCalendar.getInstance();

    public DbController(Connection conn, PreparedStatement pst) {
        this.conn = conn;
        this.pst = pst;
    }

    @Override
    public void addRecord(String message) {
        try {
            pst.setDate(1, new Date(calendar.getTime().getTime()));
            pst.setString(2, message);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> getRecords() throws SQLException {
        ResultSet rs;
        Statement st = conn.createStatement();
        rs = st.executeQuery("SELECT ID, postDate, message FROM POST WHERE ID>" + id);
        while (rs.next()) {
            Record rec = new Record();
            rec.setId(rs.getInt("ID"));
            rec.setPostDate(rs.getDate("postDate"));
            rec.setMessage(rs.getString("message"));
            listR.add(rec);
            id++;
        }
        rs.close();


        return listR;
    }
}

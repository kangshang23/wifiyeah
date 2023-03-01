

package kang.zero.wifiyeah.service;

import kang.zero.wifiyeah.config.MySqlConfig;
import kang.zero.wifiyeah.dto.request.RequestHistory;
import kang.zero.wifiyeah.dto.request.RequestWifi;

import java.sql.*;

public class Service {
    final static MySqlConfig dbConn = new MySqlConfig();

    public void saveWifi(RequestWifi requestWifi) {
        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = dbConn.getConn();

            String sql = "insert into wifi(manage_num, region, wifi_name, road_address, detailed_address, floor, installaion_type, organizations, claasified_service, network_type, year_of_install, inorout, conn_environment, lat, lnt, work_time)" +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, requestWifi.getManageNum());
            pstmt.setString(2, requestWifi.getRegion());
            pstmt.setString(3, requestWifi.getWifiName());
            pstmt.setString(4, requestWifi.getRoadAddress());
            pstmt.setString(5, requestWifi.getDetailedAddress());
            pstmt.setString(6, requestWifi.getFloor());
            pstmt.setString(7, requestWifi.getInstallationType());
            pstmt.setString(8, requestWifi.getOrganization()); // s
            pstmt.setString(9, requestWifi.getClassifiedService());
            pstmt.setString(10, requestWifi.getNetworkType());
            pstmt.setInt(11, requestWifi.getYearOfInstall());
            pstmt.setString(12, requestWifi.getInOrOut());
            pstmt.setString(13, requestWifi.getConnEnvironment());
            pstmt.setFloat(14, requestWifi.getLAT());
            pstmt.setFloat(15, requestWifi.getLNT());
            pstmt.setTimestamp(16, requestWifi.getWorkTime());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[SQL Error : " + e.getMessage() + "]");
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteAllWifi() throws SQLException, ClassNotFoundException {
        Connection conn = dbConn.getConn();

        String sql = "truncate wifi;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate(sql);

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public void saveHistory(RequestHistory requestHistory) {
        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = dbConn.getConn();

            String sql = "insert into history(x, y, created_time) " +
                    "values (?, ?, ?); ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, requestHistory.getX());
            pstmt.setFloat(2, requestHistory.getY());
            pstmt.setTimestamp(3, requestHistory.getCreatedTime());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[SQL Error : " + e.getMessage() + "]");
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}


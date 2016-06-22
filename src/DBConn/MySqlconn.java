package DBConn;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import Data.singleRec;

public class MySqlconn {

	@SuppressWarnings("null")
	public List<singleRec> select() throws Exception {
		List<singleRec> result = null;
		Connection conn = null;

		String sql;

		String url = "jdbc:mysql://localhost:3306/highmath?"

		+ "user=root&password=11111111&useUnicode=true&characterEncoding=UTF8";

		try {
			Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����

			System.out.println("�ɹ�����MySQL��������");

			conn = DriverManager.getConnection(url);

			Statement stmt = conn.createStatement();

			sql = "select * from student";

			ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ

			System.out.println("ѧ��\t����");

			while (rs.next()) {
				singleRec sr = new singleRec();
				sr.setWd(rs.getDouble(1));
				sr.setShidu(rs.getString(2));
				sr.setY1(rs.getDouble(3));
				sr.setFx(rs.getDouble(4));
				sr.setFxname(rs.getString(5));
				sr.setFxcode(rs.getString(6));
				sr.setFsname(rs.getString(7));
				sr.setFs(rs.getInt(8));
				sr.setSj(rs.getString(9));
				result.add(sr);
				System.out

				.println(rs.getDouble(1) + "\t" + rs.getString(2));

			}

		} catch (SQLException e) {

			System.out.println("MySQL��������");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return result;
	}

	public void insert(singleRec data) throws Exception {
		String param = data.getWd() + "," + data.getShidu() + "," + data.getY1() + "," + data.getFx() + ","
				+ data.getFxname() + "," + data.getFxcode() + "," + data.getFsname() + "," + data.getFs() + ","
				+ data.getSj();
		System.out.println(param);
		Connection conn = null;

		String sql;
		String url = "jdbc:mysql://localhost:3306/highmath?"

		+ "user=root&password=11111111&useUnicode=true&characterEncoding=UTF8&useSSL=true";
		try {
			Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
			System.out.println("�ɹ�����MySQL��������");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			sql = "insert into weather(wd,shidu,y1,fx,fxname,fxcode,fsname,fs,sj,cur_time) " + "values(" + data.getWd()
					+ ",'" + data.getShidu() + "'," + data.getY1() + "," + data.getFx() + ",'" + data.getFxname()
					+ "','" + data.getFxcode() + "','" + data.getFsname() + "'," + data.getFs() + ",'" + data.getSj()
					+ "',now())";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("MySQL��������");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

}

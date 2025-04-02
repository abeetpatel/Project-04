package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.html.ListView;

import in.co.rays.bean.ItemInformationBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.util.JDBCDataSource;

public class ItemInformationModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_iteminformation");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				pk = (int) rs.getLong(1);

			}

			System.out.println("Max id => " + pk);

		} catch (Exception e) {
			throw new DatabaseException("Exception: Exception in nextPk " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return pk + 1;

	}

	public void add(ItemInformationBean bean) throws Exception {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_iteminformation values(?,?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setString(2, bean.getTittle());
			pstmt.setString(3, bean.getOverview());
			pstmt.setInt(4, bean.getCost());
			pstmt.setDate(5, new Date(bean.getPurchaseDate().getTime()));
			pstmt.setString(6, bean.getCategory());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully => " + i);

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception: Exception in rollback " + e1);
			}
			throw new ApplicationException("Exception: Exception in add " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);

		}

	}

	public void update(ItemInformationBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_iteminformation set tittle = ?, overview = ?,cost = ?, purchaseDate = ?, category = ? where id = ?");

			pstmt.setString(1, bean.getTittle());
			pstmt.setString(2, bean.getOverview());
			pstmt.setInt(3, bean.getCost());
			if (bean.getPurchaseDate() != null) {
				pstmt.setDate(4, new Date(bean.getPurchaseDate().getTime()));
			} else {
				pstmt.setNull(4, java.sql.Types.DATE);
			}
			pstmt.setString(5, bean.getCategory());
			pstmt.setLong(6, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data updated successfully => " + i);
		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception: Exception in rollback " + e1);
			}
			throw new ApplicationException("Exception: Exception in update " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void delete(int pk) throws ApplicationException {

		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_iteminformation where id = ?");

			pstmt.setInt(1, pk);

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("Data deleted successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception: Exception in rokllback " + e1);
			}

			throw new ApplicationException("Exception: Exception in delete " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public ItemInformationBean finedByPk(long pk) throws ApplicationException {

		Connection conn = null;

		ItemInformationBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_iteminformation where id = ?");

			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new ItemInformationBean();

				bean.setId(rs.getLong(1));
				bean.setTittle(rs.getString(2));
				bean.setOverview(rs.getString(3));
				bean.setCost(rs.getInt(4));
				bean.setPurchaseDate(rs.getDate(5));
				bean.setCategory(rs.getString(6));

			}

		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in delete " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	public List search(ItemInformationBean bean, int pageNo, int pageSize) throws ApplicationException {

		System.out.println(" pageNo => " + pageNo);
		System.out.println(" pageSize => " + pageSize);

		Connection conn = null;

		List list = null;

		ItemInformationModel model = null;

		try {

			conn = JDBCDataSource.getConnection();

			StringBuffer sql = new StringBuffer("select * from st_iteminformation where 1 = 1");

			if (bean != null) {

				if (bean.getTittle() != null && bean.getTittle().length() > 0) {

					sql.append(" and tittle like '" + bean.getTittle() + "%'");

				}

			}

			if (pageNo > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + "," + pageSize);
			}

			System.out.println("sql ====>>> " + sql.toString());

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			list = new ArrayList();

			while (rs.next()) {

				bean = new ItemInformationBean();

				bean.setId(rs.getLong(1));
				bean.setTittle(rs.getString(2));
				bean.setOverview(rs.getString(3));
				bean.setCost(rs.getInt(4));
				bean.setPurchaseDate(rs.getDate(5));
				bean.setCategory(rs.getString(6));

				list.add(bean);

			}

		} catch (Exception e) {
			throw new ApplicationException("Exception: Exception in search " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

	public static void main(String[] args) throws Exception {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFinedByPk();
		 testSearch();

	}

	private static void testSearch() throws ApplicationException {

		int pageNo = 1;
		int pageSize = 5;
		ItemInformationBean bean = new ItemInformationBean();
		ItemInformationModel model = new ItemInformationModel();
		List list = new ArrayList();

		bean.setTittle("laptop");

		list = model.search(bean, pageNo, pageSize);

		Iterator it = list.iterator();

		while (it.hasNext()) {

			bean = (ItemInformationBean) it.next();

			System.out.print("Id => " + bean.getId());
			System.out.print("\t" + "\t" + "Tittle => " + bean.getTittle());
			System.out.print("\t" + "\t" + "Overview => " + bean.getOverview());
			System.out.print("\t" + "\t" + "Cost => " + bean.getCost());
			System.out.print("\t" + "\t" + "PurchaseDate => " + bean.getPurchaseDate());
			System.out.println("\t" + "\t" + "Category => " + bean.getCategory());

		}

	}

	private static void testFinedByPk() throws ApplicationException {

		ItemInformationModel model = new ItemInformationModel();

		ItemInformationBean bean = model.finedByPk(1);

		System.out.println("Tittle => " + bean.getTittle());

	}

	private static void testDelete() throws ApplicationException {

		ItemInformationModel model = new ItemInformationModel();

		model.delete(1);

	}

	private static void testUpdate() throws Exception {

		ItemInformationBean bean = new ItemInformationBean();
		ItemInformationModel model = new ItemInformationModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setId(1);
		bean.setTittle("demo");
		bean.setOverview("demo1");
		bean.setCost(1000);
		bean.setPurchaseDate(sdf.parse("2025-01-25"));
		bean.setCategory("demo1");

		model.update(bean);

	}

	private static void testAdd() throws Exception {

		ItemInformationBean bean = new ItemInformationBean();
		ItemInformationModel model = new ItemInformationModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		bean.setTittle("pc");
		bean.setOverview("demo");
		bean.setCost(100);
		bean.setPurchaseDate(sdf.parse("2025-01-02"));
		bean.setCategory("demo");

		model.add(bean);

	}

}

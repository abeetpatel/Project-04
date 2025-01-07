package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.StockPurchaseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.util.JDBCDataSource;

public class StockPurchaseModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_stockpurchase");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				pk = rs.getLong(1);

				System.out.println("max id => " + pk);

			}

		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in nextPk " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return pk + 1;

	}

	public void add(StockPurchaseBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_stockpurchase values(?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setInt(2, bean.getQuantity());
			pstmt.setLong(3, bean.getPurchasePrice());
			pstmt.setDate(4, new Date(bean.getPurchaseDate().getTime()));
			pstmt.setString(5, bean.getOrderType());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully => " + i);

		} catch (Exception e) {

			try {

				conn.rollback();

			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback " + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in add " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void update(StockPurchaseBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_stockpurchase set quantity = ?, purchasePrice  = ?, purchaseDate = ?, orderType = ? where id = ?");

			pstmt.setInt(1, bean.getQuantity());
			pstmt.setLong(2, bean.getPurchasePrice());
			pstmt.setDate(3, new Date(bean.getPurchaseDate().getTime()));
			pstmt.setString(4, bean.getOrderType());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data updated successfully => " + i);

		} catch (Exception e) {

			try {

				conn.rollback();

			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback " + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in update " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void delete(long id) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_stockpurchase where id = ?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data deleted successfully => " + i);

		} catch (Exception e) {

			try {

				conn.rollback();

			} catch (Exception e1) {

				throw new ApplicationException("Exception : Exception in rollback " + e1.getMessage());

			}

			throw new ApplicationException("Exception : Exception in delete " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public StockPurchaseBean finedByPk(long id) throws ApplicationException {

		Connection conn = null;

		StockPurchaseBean bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_stockpurchase where id = ?");

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new StockPurchaseBean();

				bean.setId(rs.getLong(1));
				bean.setQuantity(rs.getInt(2));
				bean.setPurchasePrice(rs.getLong(3));
				bean.setPurchaseDate(rs.getDate(4));
				bean.setOrderType(rs.getString(5));

			}

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in finedByPk " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return bean;

	}

	public List list() throws Exception {
		return search(null, 0, 0);
	}

	public List search(StockPurchaseBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_stockpurchase where 1 = 1");

		if (bean != null) {

			if (bean.getId() > 0) {

				sql.append(" and id = " + bean.getId());

			}

			if (bean.getQuantity() > 0) {

				sql.append(" and quantity like '" + bean.getQuantity() + "%'");
			}

			if (bean.getPurchasePrice() > 0) {

				sql.append(" and purchasePrice like '" + bean.getPurchasePrice() + "%'");

			}

			if (bean.getPurchaseDate() != null && bean.getPurchaseDate().getTime() > 0) {

				Date d = new Date(bean.getPurchaseDate().getTime());

				sql.append(" and purchaseDate like '" + d + "%'");

			}

			if (bean.getOrderType() != null && bean.getOrderType().length() > 0) {

				sql.append(" and orderType like '" + bean.getOrderType() + "%'");

			}

		}
		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + "," + pageSize);

		}

		System.out.println("sql ====> " + sql.toString());

		Connection conn = null;

		List list = new ArrayList();

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new StockPurchaseBean();

				bean.setId(rs.getLong(1));
				bean.setQuantity(rs.getInt(2));
				bean.setPurchasePrice(rs.getLong(3));
				bean.setPurchaseDate(rs.getDate(4));
				bean.setOrderType(rs.getString(5));
				list.add(bean);

			}

		} catch (Exception e) {

			throw new ApplicationException("Exception : Exception in Search " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return list;

	}

}

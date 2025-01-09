package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.PurchaseOrderBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.util.JDBCDataSource;

public class PurchaseOrderModel {

	public long nextPk() throws DatabaseException {

		long pk = 0;

		PurchaseOrderBean bean = null;

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_purchase_order");

			pstmt.setLong(1, bean.getId());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new PurchaseOrderBean();

				pk = rs.getLong(1);

				System.out.println("max id => " + pk);

			}

		} catch (Exception e) {

			throw new DatabaseException("Exception in nextPk => " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return pk + 1;

	}

	public void add(PurchaseOrderBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_purchase_order values(?,?,?,?,?)");

			pstmt.setLong(1, nextPk());
			pstmt.setInt(2, bean.getTotalQuantity());
			pstmt.setString(3, bean.getProduct());
			pstmt.setDate(4, new Date(bean.getOrderDate().getTime()));
			pstmt.setInt(5, bean.getTotalCost());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception in add/rollback => " + e1);

			}

			throw new ApplicationException("Exception in add => " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void update(PurchaseOrderBean bean) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_purchase_order set totalQuantity = ?, product = ?, orderDate = ?, totalCost = ? where id = ?");

			pstmt.setInt(1, bean.getTotalQuantity());
			pstmt.setString(2, bean.getProduct());
			pstmt.setDate(3, new Date(bean.getOrderDate().getTime()));
			pstmt.setInt(4, bean.getTotalCost());
			pstmt.setLong(5, bean.getId());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data updated successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {

				throw new ApplicationException("Exception in update/rollback => " + e1);

			}
			throw new ApplicationException("Exception in update => " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public void delete(long id) throws ApplicationException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_purchase_order where id = ?");

			pstmt.setLong(1, id);

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data deleted successfully => " + i);

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new ApplicationException("Exception in delete/rollback => " + e1);
			}

			throw new ApplicationException("Exception in delete => " + e);

		} finally {

			JDBCDataSource.closeConnection(conn);

		}

	}

	public PurchaseOrderBean finedByPk(long pk) throws ApplicationException {

		PurchaseOrderBean bean = null;

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_purchase_order where id = ?");

			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new PurchaseOrderBean();

				bean.setId(rs.getLong(1));
				bean.setTotalQuantity(rs.getInt(2));
				bean.setProduct(rs.getString(3));
				bean.setOrderDate(rs.getDate(4));
				bean.setTotalCost(rs.getInt(5));

			}
		} catch (Exception e) {
			throw new ApplicationException("Exception in finedByPk => " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return bean;

	}

	public List list() throws Exception {
		return search(null, 0, 0);

	}

	public List search(PurchaseOrderBean bean, int pageNo, int pageSize) throws ApplicationException {

		StringBuffer sql = new StringBuffer("select * from st_purchase_order where 1 = 1");

		if (bean != null) {

			if (bean.getId() > 0) {

				sql.append(" and id is like '" + bean.getId() + "%'");

			}

			if (bean.getProduct() != null && bean.getProduct().length() > 0) {

				sql.append(" and product is like '" + bean.getProduct() + "%'");

			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + "," + pageSize);

		}

		System.out.println("Sql ===> " + sql.toString());

		Connection conn = null;

		List list = new ArrayList();

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new PurchaseOrderBean();

				bean.setId(rs.getLong(1));
				bean.setTotalQuantity(rs.getInt(2));
				bean.setProduct(rs.getString(3));
				bean.setOrderDate(rs.getDate(4));
				bean.setTotalCost(rs.getInt(5));

				list.add(bean);

			}

		} catch (Exception e) {
			throw new ApplicationException("Exception in search => " + e);
		} finally {

			JDBCDataSource.closeConnection(conn);

		}

		return list;

	}

}

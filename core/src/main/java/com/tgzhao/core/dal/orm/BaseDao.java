package com.tgzhao.core.dal.orm;

import com.tgzhao.core.dal.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by tgzhao on 16/7/17.
 */
public abstract class BaseDao {

    protected static <T> T execute(Operation<T> operation) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setReadOnly(true);
            conn.createStatement().execute("SET NAMES 'utf8mb4'");
            return operation.doInConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }
    }

    protected static <T> T execute(TransactionalOperation<T> transactionalOperation) {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            connection.createStatement().execute("SET NAMES 'utf8mb4'");
            T result = transactionalOperation.doInConnection(connection);
            connection.commit();
            return result;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

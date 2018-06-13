package com.ppblock.mybatis.spring.plus.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 用于转换参数中日期
 * 格式: yyyy-MM-dd
 *
 * @author chenzhaoju
 */
public class DateTypeHandler extends BaseTypeHandler<Date> {



    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    Date parameter, JdbcType jdbcType) throws SQLException {
        DateTime dateTime = new DateTime(parameter);
        ps.setString(i, dateTime.toString("yyyy-MM-dd"));
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return null;
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return null;
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return null;
    }

}

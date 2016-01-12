package com.qihua.common;

import java.beans.PropertyDescriptor;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since 2013-12-31
 * @version 1.0
 * @see
 */
public class CustomBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {

    public CustomBeanPropertyRowMapper(Class<T> clazz) {
        super(clazz);
    }

    @Override
    protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
        Class<?> requiredType = pd.getPropertyType();
        if (int.class.equals(requiredType) || Integer.class.equals(requiredType)) {
            String val = rs.getString(index);
            if (null == val) {
                return 0;
            }
        } else if (long.class.equals(requiredType) || Long.class.equals(requiredType)) {
            String val = rs.getString(index);
            if (null == val) {
                return 0;
            }
        } else if (double.class.equals(requiredType) || Double.class.equals(requiredType)) {
            String val = rs.getString(index);
            if (null == val) {
                return 0;
            }
        } else if (Date.class.equals(requiredType)) {
            Date val = rs.getDate(index);
            if (null == val) {
                return null;
            }
        } else if (Timestamp.class.equals(requiredType)) {
            Date val = rs.getDate(index);
            if (null == val) {
                return null;
            }
        } else if (String.class.equals(requiredType)) {
            String val = rs.getString(index);
            if (null == val) {
                return "";
            }
        }

        return super.getColumnValue(rs, index, pd);
    }

}

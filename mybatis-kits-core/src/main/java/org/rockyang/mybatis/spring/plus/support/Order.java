package org.rockyang.mybatis.spring.plus.support;

import java.io.Serializable;

/**
 *
 * @author chenzhaoju
 */
public class Order implements Serializable {
    private boolean ascending;
    private String propertyName;

    protected Order(String propertyName, boolean ascending) {
        this.propertyName = propertyName;
        this.ascending = ascending;
    }

    public static Order asc(String propertyName) {
        return new Order(propertyName, true);
    }

    public static Order desc(String propertyName) {
        return new Order( propertyName, false);
    }

    public String getPropertyName() {
        return propertyName;
    }

    public boolean isAscending() {
        return ascending;
    }

    public String toSqlString() {
        StringBuilder fragment = new StringBuilder();
        fragment.append(this.propertyName).append(( ascending ? " ASC " : " DESC " ));
        return fragment.toString();
    }

    @Override
    public String toString() {
        return propertyName + ' '
                + ( ascending ? "ASC" : "DESC" );
    }
}

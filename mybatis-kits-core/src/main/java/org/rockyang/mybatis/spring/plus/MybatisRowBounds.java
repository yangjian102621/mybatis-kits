package org.rockyang.mybatis.spring.plus;

import org.rockyang.mybatis.spring.plus.plugins.page.Page;
import org.apache.ibatis.session.RowBounds;

/**
 * @author chenzhaoju
 */
public class MybatisRowBounds extends RowBounds {

    private Page<?> page;

    public MybatisRowBounds(Page<?> page) {
        this.page = page;
    }

    public Page<?> getPage() {
        return page;
    }
}

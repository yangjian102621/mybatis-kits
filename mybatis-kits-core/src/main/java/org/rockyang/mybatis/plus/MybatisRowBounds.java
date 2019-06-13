package org.rockyang.mybatis.plus;

import org.rockyang.mybatis.plus.plugins.page.Page;
import org.apache.ibatis.session.RowBounds;

/**
 * @author chenzhaoju
 * @author yangjian
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

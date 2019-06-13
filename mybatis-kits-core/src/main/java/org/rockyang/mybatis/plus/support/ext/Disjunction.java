package org.rockyang.mybatis.plus.support.ext;

import org.rockyang.mybatis.plus.support.Criterion;

/**
 * @author chenzhaoju
 */
public class Disjunction extends Junction {

    protected Disjunction() {
        super( Nature.OR );
    }

    protected Disjunction(Criterion[] conditions) {
        super( Nature.OR, conditions );
    }
}

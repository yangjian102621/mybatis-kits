package com.dayi.mybatis.spring.plus.support.ext;

import com.dayi.mybatis.spring.plus.support.Criterion;

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

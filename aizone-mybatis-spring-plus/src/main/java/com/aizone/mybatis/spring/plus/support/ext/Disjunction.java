package com.aizone.mybatis.spring.plus.support.ext;

import com.aizone.mybatis.spring.plus.support.Criterion;

/**
 * @author yangjian
 */
public class Disjunction extends Junction {

    protected Disjunction() {
        super( Nature.OR );
    }

    protected Disjunction(Criterion[] conditions) {
        super( Nature.OR, conditions );
    }
}

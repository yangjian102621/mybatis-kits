package org.rockyang.mybatis.plus.support.ext;

import org.rockyang.mybatis.plus.support.Criterion;

import java.util.Collection;

/**
 * @author chenzhaoju
 * @author yangjian
 */
public class Conjunction extends Junction {

    public Conjunction() {
        super( Nature.AND );
    }

    protected Conjunction(Criterion... criterion) {
        super( Nature.AND, criterion );
    }

    public Conjunction(Collection<Criterion> criterions) {
        super(Nature.AND, criterions);
    }

}

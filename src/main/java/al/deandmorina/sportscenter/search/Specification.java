package al.deandmorina.sportscenter.search;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Specification<T> implements org.springframework.data.jpa.domain.Specification<T> {
    private final SearchCriteria criteria;

    @Autowired
    public Specification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (this.criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(this.criteria.getKey()), this.criteria.getValue().toString());
        } else if (this.criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(this.criteria.getKey()), this.criteria.getValue().toString());
        } else if (this.criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(this.criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(root.get(this.criteria.getKey()), "%" + this.criteria.getValue().toString() + "%");
            } else {
                return criteriaBuilder.equal(root.get(this.criteria.getKey()), this.criteria.getValue().toString());
            }
        }
        return null;
    }
}

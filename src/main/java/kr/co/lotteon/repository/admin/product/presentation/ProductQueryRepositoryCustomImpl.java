package kr.co.lotteon.repository.admin.product.presentation;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.lotteon.response.admin.product.ProductAdminListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static kr.co.lotteon.entity.member.QMember.member;
import static kr.co.lotteon.entity.product.QProductEntity.productEntity;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@RequiredArgsConstructor
public class ProductQueryRepositoryCustomImpl implements ProductQueryRepositoryCustom {
    private final JPAQueryFactory query;
    @Override
    public Page<ProductAdminListResponse> searchWithPageAndCond(ProductSearchCond searchCond, Pageable pageable) {
        List<ProductAdminListResponse> content = query.select(Projections.constructor(ProductAdminListResponse.class,
                        productEntity.thumb1.storedFileName,
                        productEntity.prodNo,
                        productEntity.prodName,
                        productEntity.price,
                        productEntity.discount,
                        productEntity.point,
                        productEntity.stock,
                        productEntity.seller.uid,
                        productEntity.hit)
                )
                .from(productEntity)
                .leftJoin(productEntity.seller, member)
                .where(
                        companyNameLike(searchCond.getCompany()),
                        prodNameLike(searchCond.getProdName()),
                        sellerNameLike(searchCond.getSellerName()),
                        prodNoEq(searchCond.getProdNo()),
                        descriptLike(searchCond.getDescript())
                )
                .offset(pageable.getOffset())
                .limit(searchCond.getPageSize())
                .orderBy(getSortField(pageable))
                .fetch();

        JPAQuery<ProductAdminListResponse> preCnt = query.select(Projections.constructor(ProductAdminListResponse.class,
                        productEntity)
                )
                .from(productEntity)
                .leftJoin(productEntity.seller, member)
                .where(
                        companyNameLike(searchCond.getCompany()),
                        prodNameLike(searchCond.getProdName()),
                        sellerNameLike(searchCond.getSellerName()),
                        prodNoEq(searchCond.getProdNo()),
                        descriptLike(searchCond.getDescript())
                )
                .offset(pageable.getOffset())
                .limit(searchCond.getPageSize())
                .orderBy(getSortField(pageable));

        return PageableExecutionUtils.getPage(content,pageable,preCnt::fetchCount);
    }

    /**
     * OrderSpecifier로 정렬 기준과 순서를 맞춘다.
     */
    private OrderSpecifier<?> getSortField(Pageable page) {
        if (!page.getSort().isEmpty()) {
            for (Sort.Order order : page.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "sold":
                        return new OrderSpecifier<>(direction, productEntity.sold);
                    case "price":
                        return new OrderSpecifier<>(direction, productEntity.price);
                    case "score":
                        return new OrderSpecifier<>(direction, productEntity.score);
                    case "review":
                        return new OrderSpecifier<>(direction, productEntity.review);
                    case "rdate":
                        return new OrderSpecifier<>(direction, productEntity.rdate);
                }
            }
        }
        // 기본적으로 rdate 내림차순으로 정렬한다.
        return new OrderSpecifier<>(Order.DESC, productEntity.rdate);
    }

    private BooleanExpression descriptLike(String descript) {
        return hasText(descript) ? productEntity.descript.contains(descript) : null;
    }

    private BooleanExpression prodNoEq(Integer prodNo) {
        return prodNo == null ? null : productEntity.prodNo.eq(prodNo);
    }

    private BooleanExpression sellerNameLike(String sellerName) {
        return hasText(sellerName) ? productEntity.seller.uid.contains(sellerName) : null;
    }

    private BooleanExpression prodNameLike(String prodName) {
        return hasText(prodName) ? productEntity.prodName.contains(prodName) : null;
    }

    private BooleanExpression companyNameLike(String company) {
        return hasText(company) ? productEntity.company.like(company) : null;
    }
}

package kr.co.lotteon.repository.admin.product.presentation;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.lotteon.response.admin.product.ProductAdminListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static kr.co.lotteon.entity.member.QMember.member;
import static kr.co.lotteon.entity.product.QProductEntity.productEntity;
import static org.springframework.util.StringUtils.hasText;

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
                        prodNoEq(searchCond.getProdNo())
                )
                .offset(pageable.getOffset())
                .limit(searchCond.getPageSize())
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
                        prodNoEq(searchCond.getProdNo())

                )
                .offset(pageable.getOffset())
                .limit(searchCond.getPageSize());

        return PageableExecutionUtils.getPage(content,pageable,preCnt::fetchCount);
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

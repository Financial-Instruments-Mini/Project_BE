//package com.financial.product.repository;
//
//import com.financial.product.dto.ProductListDto;
//import com.financial.product.entity.Product;
//import com.querydsl.core.types.NullExpression;
//import com.querydsl.core.types.Order;
//import com.querydsl.core.types.OrderSpecifier;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//import org.springframework.data.domain.SliceImpl;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.financial.product.entity.QProduct.product;
//
//
//@Repository
//@RequiredArgsConstructor
//public class CustomProductRepositoryImpl implements CustomProductRepository {
//
//    private final JPAQueryFactory query;
//
//    @Override
//    public Slice<ProductListDto> allProductPage(Pageable pageable, String sort) {
//        List<Product> fetch = query
//                .selectFrom(product)
//                .where()
//                .orderBy(orderByMakeProductDay(sort))
//                .orderBy(orderByInterest(sort))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize() + 1)
//                .fetch();
//
//        List<ProductListDto> content = fetch.stream().map(p -> new ProductListDto(p))
//                .collect(Collectors.toList());
//
//        boolean hasNext = false;
//        if (content.size() > pageable.getPageSize()) {
//            hasNext = true;
//            content.remove(pageable.getPageSize());
//        }
//
//        return new SliceImpl<>(content, pageable, hasNext);
//    }
//
//
//    private OrderSpecifier orderByInterest(String sort) {
//
//        return sort.equals("interest") ? product.productMakeDay.asc() : OrderByNull.DEFAULT;
//
//    }
//
//    private OrderSpecifier orderByMakeProductDay(String sort) {
//        return sort.equals("last") ? product.productMakeDay.desc() : OrderByNull.DEFAULT;
//    }
//
//    static class OrderByNull extends OrderSpecifier {
//        public static final OrderByNull DEFAULT = new OrderByNull();
//
//        private OrderByNull() {
//            super(Order.DESC, NullExpression.DEFAULT, NullHandling.Default);
//        }
//    }
//}

package kr.co.lotteon.service.product;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductCartEntity;
import kr.co.lotteon.entity.product.ProductEntity;
import kr.co.lotteon.repository.admin.product.ProductRepository;
import kr.co.lotteon.repository.member.MemberRepository;
import kr.co.lotteon.repository.product.ProductCartRepository;
import kr.co.lotteon.repository.product.ProductListRepository;
import kr.co.lotteon.repository.product.ProductViewRepository;
import kr.co.lotteon.request.product.ProductCartRequest;
import kr.co.lotteon.response.product.ProductListResponse;
import kr.co.lotteon.response.product.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductListRepository productListRepository;
    private final ProductViewRepository productViewRepository;
    private final ProductCartRepository productCartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    /* Product List */
    public List<ProductListResponse> findProductsByCate2(Integer prodCate2Id){


        List<ProductListResponse> productList = productListRepository.findByCate2(prodCate2Id).stream()
                .map(ProductListResponse::new)
                .collect(Collectors.toList());

        log.info("productList : " + productList);
        return productList;
    }

    /* Product View */
    public ProductViewResponse findView(Integer prodNo){
        ProductEntity productEntity = productViewRepository.findById(prodNo).orElseThrow();
        return new ProductViewResponse(productEntity);
    }

    /* Product Cart */
    @Transactional
    public void insertCart(ProductCartRequest productCartRequest) throws IOException {
        //TODO: prodNO에 해당하는 cart를 findMember가 이미 가지고 있으면
        //TODO: distinct로 member-cart join된 결과 덩치 줄이기(최적화)
        //TODO: dirtychecking, EntityManager , dto와  entity의 필드명은 웬만하면 같게,
        ProductEntity product = productRepository.findById(productCartRequest.getProdNo()).orElseThrow();
        log.info("hh");
        Member findMember = memberRepository.findMemberByIdWithCarts(productCartRequest.getUid()).orElseThrow();
        log.info("hh2");
        log.info("ProductService findMember : " + findMember);
        //prodNo에 해당하는 cart가 있는지 확인
        //prodNo에 해당하는 cart가 있으면 업데이트 해준다.
        List<ProductCartEntity> carts = findMember.getCarts();
        log.info("[CART SERVICE INSERTCART CALLED] user {}'s cart size = {}", findMember.getUid(), carts.size());

        int checkProdNo = productCartRequest.getProdNo();
        for (ProductCartEntity cart: carts) {
            ProductEntity curProdNo = cart.getProduct();
            if (curProdNo.getProdNo() == checkProdNo) {
                cart.setCount(cart.getCount()+productCartRequest.getCount());
                return; // 업데이트 완료
            }
        }
        //prodNo에 해당하는 cart가 없으면 새로 만들어준다.
        ProductCartEntity newCart = productCartRequest.toEntity(product, findMember);
        findMember.addCart(newCart);
    }
}


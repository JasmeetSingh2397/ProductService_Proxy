package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.DTOs.SortParamsDto;
import com.example.personal_productserviceproxy.DTOs.SortType;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.ElasticSearchRepositories.ProductElasticSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductElasticSearchService {
    private ProductElasticSearchRepository productElasticSearchRepository;


    public ProductElasticSearchService(ProductElasticSearchRepository productElasticSearchRepository) {
        this.productElasticSearchRepository = productElasticSearchRepository;
    }

    public Page<Product> searchProducts(String query, int offset, int sizeOfPage,
                                        List<SortParamsDto> sortParamList) {
        Sort sort;
        if(sortParamList.get(0).getSortType().equals(SortType.DESCENDING)){
            sort= Sort.by(sortParamList.get(0).getParamName()).descending();
        }
        else{
            sort= Sort.by(sortParamList.get(0).getParamName()).ascending();
        }

        int sizeOfSortParamList= sortParamList.size();

        for(int i=1; i<sizeOfSortParamList;i++){
            SortParamsDto sortParamsDto= sortParamList.get(i);
            if(sortParamsDto.getSortType().equals(SortType.DESCENDING)){
                sort= sort.and(Sort.by(sortParamsDto.getParamName()).descending());
            }
            else if(sortParamsDto.getSortType().equals(SortType.ASCENDING)){
                sort= sort.and(Sort.by(sortParamsDto.getParamName()).ascending());
            }

        }

        Page<Product> products= productElasticSearchRepository.findAllContaining(query,
                PageRequest.of(offset/sizeOfPage,sizeOfPage,sort));
        return products;
    }
//        Sort sort = Sort.by("title").descending()
}

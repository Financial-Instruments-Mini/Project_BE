package com.financial.product.service;

import com.financial.product.dto.KeywordDTO;
import com.financial.product.entity.enums.Keyword;

import java.util.List;

public interface KeywordService {

    public List<KeywordDTO> searchKeyword(Keyword keyword);
}

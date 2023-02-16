package com.financial.product.service.serviceImpl;

import com.financial.product.dto.KeywordDTO;
import com.financial.product.entity.Deposit;
import com.financial.product.entity.Saving;
import com.financial.product.entity.enums.Keyword;
import com.financial.product.repository.DepositRepository;

import com.financial.product.repository.SavingRepository;
import com.financial.product.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class KeywordServiceImpl implements KeywordService {

    private final DepositRepository depositRepository;
    private final SavingRepository savingRepository;

    @Override
    public List<KeywordDTO> searchKeyword(Keyword keyword){
        List<Deposit> depositList = depositRepository.findAll();
        List<KeywordDTO> depositDTOList = depositList.stream().map(deposit->new KeywordDTO(deposit)).collect(Collectors.toList());
        List<Saving> savingList = savingRepository.findAll();
        List<KeywordDTO> savingDTOList = savingList.stream().map(saving -> new KeywordDTO(saving)).collect(Collectors.toList());
        return Stream.concat(depositDTOList.stream(), savingDTOList.stream()).collect(Collectors.toList());
    }


}

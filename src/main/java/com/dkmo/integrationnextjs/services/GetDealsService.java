package com.dkmo.integrationnextjs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.ResponseDealDto;
import com.dkmo.integrationnextjs.interfaces.IGetAllDeals;
import com.dkmo.integrationnextjs.models.Deal;
import com.dkmo.integrationnextjs.repository.DealsRepository;

@Service
public class GetDealsService  implements IGetAllDeals{
   @Autowired
   private DealsRepository dealsRepository;
    @Override
    public List<Deal> getAllDeals() {
        List<Deal> deals = dealsRepository.findAll();
        return deals;
    }
    @Override
    public ResponseDealDto searchCodigo(Long codigo) {
       Deal deal = dealsRepository.findByCodigo(codigo);
       ResponseDealDto responseDealDto = new ResponseDealDto(deal.getTitulo(), deal.getNome(),deal.getCaminho().replaceAll(",$", "").split(","), deal.getValor());
       return responseDealDto;
    }
}

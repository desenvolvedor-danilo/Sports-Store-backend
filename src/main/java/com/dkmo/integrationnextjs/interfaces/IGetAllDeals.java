package com.dkmo.integrationnextjs.interfaces;
import java.util.List;

import com.dkmo.integrationnextjs.dto.ResponseDealDto;
import com.dkmo.integrationnextjs.models.Deal;

public interface IGetAllDeals {
    public List<Deal> getAllDeals();
    public ResponseDealDto searchCodigo(Long codigo);

}

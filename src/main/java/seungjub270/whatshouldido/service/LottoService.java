package seungjub270.whatshouldido.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class LottoService {
    public Set lottoNum(){
        Set<Integer> lotto = new LinkedHashSet<>();
        while(lotto.size() < 7){
            lotto.add(randomNumber());
        }
        return lotto;
    }

    private Integer randomNumber(){
        return (int)((Math.random()*45)+1);
    }
}

package tn.esprit.rh.achat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {


    @InjectMocks
    private StockServiceImpl stockServiceImpl;
    @Mock
    private StockRepository stockRepository;
    @Test
    public void retrieveAllStocks() {
        when(stockRepository.findAll()).thenReturn(Stream.of(new Stock(),new Stock(),new Stock()).collect(Collectors.toList())) ;
        assertEquals(3,stockServiceImpl.retrieveAllStocks().size());
    }

    @Test
    public void addStock() {
        Stock stock=new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);
        assertEquals(stock, stockServiceImpl.addStock(stock));
    }

    @Test
    public void updateStock() {
        Stock stock=new Stock();
        when(stockRepository.save(stock)).thenReturn(stock);
        assertEquals(stock, stockServiceImpl.updateStock(stock));
    }

    @Test
    public void retrieveStock(){
        Stock stock = new Stock();
        when(stockRepository.findById(stock.getIdStock())).thenReturn(java.util.Optional.of(stock));
        assertEquals(stock.getIdStock(),stockServiceImpl.retrieveStock(stock.getIdStock()).getIdStock());
    }
}
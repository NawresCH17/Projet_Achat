package tn.esprit.rh.achat;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;




@ExtendWith(MockitoExtension.class)
class CategorieServiceImplTest {

 
	@InjectMocks
	private CategorieProduitServiceImpl CategorieServiceImpl;
	@Mock
	private CategorieProduitRepository categorieRepository;
	@Test
	public void retrieveAllCategorieProduitsTest() {
		 when(categorieRepository.findAll()).thenReturn(Stream.of(new CategorieProduit(),new CategorieProduit(),new CategorieProduit()).collect(Collectors.toList())) ; 
		 assertEquals(3,CategorieServiceImpl.retrieveAllCategorieProduits().size());
	}

	@Test
	public void addCategorieProduitTest() {
		CategorieProduit cat=new CategorieProduit();
		when(categorieRepository.save(cat)).thenReturn(cat);
		assertEquals(cat, CategorieServiceImpl.addCategorieProduit(cat));
	}


	   @Test
	    public void retrieveCategorieProduitTest(){
		   CategorieProduit cat = new CategorieProduit();
	        when(categorieRepository.findById(cat.getIdCategorieProduit())).thenReturn(java.util.Optional.of(cat));
	        assertEquals(cat.getIdCategorieProduit(),CategorieServiceImpl.retrieveCategorieProduit(cat.getIdCategorieProduit()).getIdCategorieProduit());
	    }
	   
	   /*@Test
	   public void deleteCategorieProduitTest() {
		CategorieProduit cat1 = new CategorieProduit();
		CategorieProduit cat2=new CategorieProduit();
		CategorieServiceImpl.deleteCategorieProduit(cat1.getIdCategorieProduit());
		CategorieServiceImpl.deleteCategorieProduit(cat2.getIdCategorieProduit());
		verify(categorieRepository).deleteById(cat1.getIdCategorieProduit());
		System.out.println(cat1);
        */
		
	}
	


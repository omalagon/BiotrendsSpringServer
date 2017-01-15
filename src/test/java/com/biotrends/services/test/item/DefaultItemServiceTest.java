package com.biotrends.services.test.item;

import com.biotrends.entities.item.Item;
import com.biotrends.entities.item.ItemBase;
import com.biotrends.repositories.item.ItemRepository;
import com.biotrends.services.item.ItemService;
import com.biotrends.services.item.impl.DefaultItemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.when;


/**
 * @author Oscar Malagon
 * @since 22/12/2016.
 */
public class DefaultItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    private ItemService itemService;


    @Before
    public void setUp() {
        initMocks(this);
    }


    @Test
    public void findById() {
        String idItem = "AS-001";
        ItemBase expectedItem = ItemBase.builder()
            .inventario("MB")
            .descripcion("Bolsa roja tamaño industrial")
            .presentacion("Unidad.")
            .cantidad(0.0)
            .precio(0.0)
            .cCalidad(true)
            .cEsp(true)
            .build();

        Item item  = Item.builder()
            .itemBase(expectedItem)
            .build();

        when(itemRepository.findOne(idItem)).thenReturn(item);
        itemService = new DefaultItemService(itemRepository);
        Optional<Item> actualItem = itemService.findById(idItem);

        assertTrue(actualItem.isPresent());
        assertEquals(item, actualItem.get());

    }

}

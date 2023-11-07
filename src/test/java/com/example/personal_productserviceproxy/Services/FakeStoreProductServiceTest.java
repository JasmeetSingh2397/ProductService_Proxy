package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FakeStoreProductServiceTest {
    @MockBean
    private FakeStoreClient fakeStoreClient;

    private FakeStoreProductService fakeStoreProductService;

    @Test
    public void Test_FakeStoreClient() {

        List<FakeStoreProductDTO> fakeStoreProductDTOList= fakeStoreClient.getAllProducts();
        assertNotNull(fakeStoreProductDTOList);
    }

}

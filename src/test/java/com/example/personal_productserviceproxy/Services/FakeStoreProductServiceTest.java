package com.example.personal_productserviceproxy.Services;

import com.example.personal_productserviceproxy.Clients.FakeStore.Client.FakeStoreClient;
import com.example.personal_productserviceproxy.Clients.FakeStore.DTO.FakeStoreProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FakeStoreProductServiceTest {
    @Autowired
    private FakeStoreClient fakeStoreClient;


    @Test
    public void Test_FakeStoreClient() {

        List<FakeStoreProductDTO> fakeStoreProductDTOList= fakeStoreClient.getAllProducts();
        assertNotNull(fakeStoreProductDTOList);
    }

}

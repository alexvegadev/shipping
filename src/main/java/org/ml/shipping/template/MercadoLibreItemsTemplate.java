package org.ml.shipping.template;


import org.ml.shipping.dto.ItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.ml.shipping.shared.RestConstants.ML_API_URL;

@Component
public class MercadoLibreItemsTemplate {

    private UriComponents getItemURIResponse(String itemId){
        final UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(ML_API_URL)
                .path(String.format("items/%s", itemId))
                .build();
        return uri;
    }

    public ItemDTO getItemResponse(String itemId) {
        final RestTemplate rt = new RestTemplate();
        final UriComponents uri = getItemURIResponse(itemId);
        ResponseEntity<ItemDTO> itemRes = rt.getForEntity(uri.toString(), ItemDTO.class);
        return itemRes.getBody();
    }

}

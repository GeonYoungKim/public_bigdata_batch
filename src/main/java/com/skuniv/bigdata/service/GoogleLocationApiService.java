package com.skuniv.bigdata.service;

import com.skuniv.bigdata.domain.dto.YamlDto;
import com.skuniv.bigdata.domain.dto.open_api.GoogleLocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Service
@RequiredArgsConstructor
public class GoogleLocationApiService {
    private final YamlDto yamlDto;

    private final String keyParam = ",+CA&key=";
    private final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/xml?address=";

    public GoogleLocationDto googleLocationApiCall(String address) {
        StringBuilder url = new StringBuilder();
        url.append(baseUrl).append(address).append(keyParam).append(yamlDto.getGoogleKey());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(url.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("location");

        Node nNode = nList.item(0);

        GoogleLocationDto googleLocationDto = null;
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            googleLocationDto = GoogleLocationDto.builder()
                    .latitude(getTagValue("lat", eElement))
                    .longitude(getTagValue("lng", eElement))
                    .build();
        }
        return googleLocationDto;
    }

    private String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null) {
            return null;
        }
        return nValue.getNodeValue();
    }
}

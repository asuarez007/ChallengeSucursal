package com.api.sucursal.util;

import com.api.sucursal.model.Sucursal;
import com.api.sucursal.model.Distancia;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CalculadorDistancia {

    /**
     * @param lat1
     * @param lon1
     * @param list
     * @return
     */
    public static Distancia calcularDistancia(double lat1, double lon1, List<Sucursal> list) {
        Map<Integer, Double> distanceAll = new HashMap();
        Distancia distancia = new Distancia();
        getInfoDistance(lat1, lon1, list, distanceAll);
        Map<Integer, Double> sortedByCount = distanceAll.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        getDistanceKilometo(list, distancia, sortedByCount);
        return distancia;
    }


    /**
     * @param lat1
     * @param lon1
     * @param list
     * @param distanceAll
     */
    private static void getInfoDistance(double lat1, double lon1, List<Sucursal> list, Map<Integer, Double> distanceAll) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + "--" + list.get(i).getDireccion() + "--" + list.get(i).getLatitude()
                    + "--" + list.get(i).getLongitude());
            distanceAll.put(i, distancia(lat1, lon1, list.get(i).getLatitude(), list.get(i).getLongitude(), "K"));
        }
    }

    /**
     * @param list
     * @param distancia
     * @param sortedByCount
     */
    private static void getDistanceKilometo(List<Sucursal> list, Distancia distancia, Map<Integer, Double> sortedByCount) {
        for (Map.Entry<Integer, Double> entry : sortedByCount.entrySet()) {
            distancia
                    .setInformacionSucursal("La sucursal mas cercana en Kilometros es :" + list.get(entry.getKey()).getDireccion());
            distancia.setDistancia(sortedByCount.get(entry.getKey()));
            break;
        }
    }

    /**
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @param unit
     * @return
     */
    private static double distancia(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double dist = getDist(lat1, lon1, lat2, lon2);
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

    /**
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    private static double getDist(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        return dist;
    }

}

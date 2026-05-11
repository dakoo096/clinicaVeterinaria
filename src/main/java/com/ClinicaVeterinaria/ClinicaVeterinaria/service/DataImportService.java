package com.ClinicaVeterinaria.ClinicaVeterinaria.service;

import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Especie;
import com.ClinicaVeterinaria.ClinicaVeterinaria.entity.Raza;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.EspecieRepository;
import com.ClinicaVeterinaria.ClinicaVeterinaria.repository.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class DataImportService {
    @Autowired
    private RazaRepository razaRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Value("${api.ninjas.key}")
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate();

    public void importarTodo() {
        Especie perro = obtenerOCrearEspecie("Perro");
        Especie gato = obtenerOCrearEspecie("Gato");

        importarRazas("https://api.api-ninjas.com/v1/dogs", perro);
        importarRazas("https://api.api-ninjas.com/v1/cats", gato);
    }

    private Especie obtenerOCrearEspecie(String nombre) {
        return especieRepository.findByNombre(nombre)
                .orElseGet(() -> {
                    Especie e = new Especie();
                    e.setNombre(nombre);
                    return especieRepository.save(e);
                });
    }

    private void importarRazas(String baseUrl, Especie especie) {
        int offset = 0;
        boolean seguir = true;

        while (seguir) {
            String url = baseUrl + "?name=a&offset=" + offset;

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Api-Key", apiKey);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<List> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    List.class
            );

            List<Map<String, Object>> data = response.getBody();

            if (data == null || data.isEmpty()) {
                seguir = false;
                break;
            }

            for (Map<String, Object> item : data) {
                String nombre = (String) item.get("name");

                if (!razaRepository.existsByNombre(nombre)) {
                    Raza raza = new Raza();
                    raza.setNombre(nombre);
                    raza.setEspecie(especie);

                    razaRepository.save(raza);
                }
            }

            offset += 20;
        }
    }
}

package com.ygor.cadastromestre.service;

import com.ygor.cadastromestre.model.CountryFilter;
import com.ygor.cadastromestre.model.EscalationContact;
import com.ygor.cadastromestre.model.PersonContact;
import com.ygor.cadastromestre.model.ServiceContact;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CadastroMestreService {

    private static final List<String> FIRST_NAMES = List.of(
            "Carlos", "Ana", "Bruno", "Mariana", "Lucas", "Fernanda",
            "Roberto", "Paula", "Renata", "Diego", "Juliana", "Mateus"
    );

    private static final List<String> LAST_NAMES = List.of(
            "Silva", "Souza", "Oliveira", "Pereira", "Costa", "Almeida",
            "Rodrigues", "Santos", "Martins", "Ribeiro", "Araujo", "Mendes"
    );

    private static final List<String> FASTCOMNS_GROUPS = List.of(
            "FAST-BR-01", "FAST-BR-02", "FAST-LATAM-01", "FAST-OPS-04", "FAST-APP-03"
    );

    private static final List<String> ASSIGNMENT_TOWERS = List.of(
            "Torre Aplicacao", "Torre Infra", "Torre DBA", "Torre Cloud", "Torre Middleware"
    );

    private static final List<String> NOTES = List.of(
            "Atendimento 24x7 para incidentes criticos.",
            "Escalada prioritaria para chamados P1 e P2.",
            "Validar janela de mudanca antes de acionar o especialista.",
            "Fluxo de atendimento compartilhado com operacao.",
            "Contato direto em horario comercial."
    );

    private final Map<CountryFilter, Map<String, EscalationContact>> ccsDataByCountry;
    private final Map<CountryFilter, Map<String, EscalationContact>> gimDataByCountry;
    private final Map<CountryFilter, Map<String, EscalationContact>> supportDataByCountry;
    private final Map<CountryFilter, Map<String, EscalationContact>> applicationDataByCountry;
    private final Map<CountryFilter, Map<String, EscalationContact>> engineeringDataByCountry;
    private final Map<CountryFilter, Map<String, ServiceContact>> serviceDataByCountry;

    public CadastroMestreService() {
        ccsDataByCountry = buildCountryEscalationData(List.of("CCS"));

        List<String> supportAndEngineeringOptions = List.of(
                "Produtos",
                "Transacional",
                "Base de Dados",
                "Sistemas Operacionais",
                "Telecom",
                "Public Cloud",
                "Observabilidade"
        );

        gimDataByCountry = buildCountryEscalationData(List.of("GIM BR", "GIM CHL", "GIM ARG", "GIM MEX"));
        supportDataByCountry = buildCountryEscalationData(supportAndEngineeringOptions);
        engineeringDataByCountry = buildCountryEscalationData(supportAndEngineeringOptions);
        applicationDataByCountry = buildCountryEscalationData(List.of(
                "MBB", "IBE", "SPI", "CRM", "ERP", "BFF", "PIX", "API", "OMS"
        ));
        serviceDataByCountry = buildCountryServiceData(List.of(
                "Hyper", "Tibco", "PaaS", "CaaS", "DB2", "WebSphere", "Linux", "Windows", "Kafka"
        ));
    }

    public List<String> getCcsOptions(CountryFilter country) {
        return orderedKeys(ccsDataByCountry.getOrDefault(country, Map.of()));
    }

    public List<String> getGimOptions(CountryFilter country) {
        return orderedKeys(gimDataByCountry.getOrDefault(country, Map.of()));
    }

    public List<String> getSupportOptions(CountryFilter country) {
        return orderedKeys(supportDataByCountry.getOrDefault(country, Map.of()));
    }

    public List<String> getApplicationOptions(CountryFilter country) {
        return orderedKeys(applicationDataByCountry.getOrDefault(country, Map.of()));
    }

    public List<String> getEngineeringOptions(CountryFilter country) {
        return orderedKeys(engineeringDataByCountry.getOrDefault(country, Map.of()));
    }

    public List<String> getServiceOptions(CountryFilter country) {
        return orderedKeys(serviceDataByCountry.getOrDefault(country, Map.of()));
    }

    public EscalationContact findCcsEscalation(CountryFilter country, String key) {
        return ccsDataByCountry.getOrDefault(country, Map.of()).get(key);
    }

    public EscalationContact findGimEscalation(CountryFilter country, String key) {
        return gimDataByCountry.getOrDefault(country, Map.of()).get(key);
    }

    public EscalationContact findSupportEscalation(CountryFilter country, String key) {
        return supportDataByCountry.getOrDefault(country, Map.of()).get(key);
    }

    public EscalationContact findApplicationEscalation(CountryFilter country, String key) {
        return applicationDataByCountry.getOrDefault(country, Map.of()).get(key);
    }

    public EscalationContact findEngineeringEscalation(CountryFilter country, String key) {
        return engineeringDataByCountry.getOrDefault(country, Map.of()).get(key);
    }

    public ServiceContact findServiceEscalation(CountryFilter country, String key) {
        return serviceDataByCountry.getOrDefault(country, Map.of()).get(key);
    }

    private List<String> orderedKeys(Map<String, ?> source) {
        return new ArrayList<>(source.keySet());
    }

    private Map<CountryFilter, Map<String, EscalationContact>> buildCountryEscalationData(List<String> options) {
        Map<CountryFilter, Map<String, EscalationContact>> byCountry = new EnumMap<>(CountryFilter.class);
        for (CountryFilter country : CountryFilter.values()) {
            Random random = new Random(20260318L + country.ordinal() * 99L + options.hashCode());
            Map<String, EscalationContact> data = new LinkedHashMap<>();
            for (String option : options) {
                data.put(option, randomEscalationContact(option + "-" + country.code(), country, random));
            }
            byCountry.put(country, data);
        }
        return byCountry;
    }

    private Map<CountryFilter, Map<String, ServiceContact>> buildCountryServiceData(List<String> services) {
        Map<CountryFilter, Map<String, ServiceContact>> byCountry = new EnumMap<>(CountryFilter.class);
        for (CountryFilter country : CountryFilter.values()) {
            Random random = new Random(20260318L + country.ordinal() * 121L + services.hashCode());
            Map<String, ServiceContact> data = new LinkedHashMap<>();
            for (String service : services) {
                data.put(service, randomServiceContact(service, country, random));
            }
            byCountry.put(country, data);
        }
        return byCountry;
    }

    private EscalationContact randomEscalationContact(String context, CountryFilter country, Random random) {
        return new EscalationContact(
                randomPerson(country, random),
                randomPerson(country, random),
                randomPerson(country, random),
                context + "-" + randomFrom(FASTCOMNS_GROUPS, random),
                randomFrom(ASSIGNMENT_TOWERS, random)
        );
    }

    private ServiceContact randomServiceContact(String service, CountryFilter country, Random random) {
        return new ServiceContact(
                service,
                randomPerson(country, random),
                randomFrom(FASTCOMNS_GROUPS, random),
                randomFrom(ASSIGNMENT_TOWERS, random),
                randomFrom(NOTES, random)
        );
    }

    private String randomName(Random random) {
        return randomFrom(FIRST_NAMES, random) + " " + randomFrom(LAST_NAMES, random);
    }

    private PersonContact randomPerson(CountryFilter country, Random random) {
        String name = randomName(random);
        String id = String.format("%s-%04d", country.code(), 1000 + random.nextInt(9000));
        String phone = randomPhone(country, random);
        return new PersonContact(name, id, phone);
    }

    private String randomPhone(CountryFilter country, Random random) {
        int block1 = 1000 + random.nextInt(9000);
        int block2 = 1000 + random.nextInt(9000);
        return switch (country) {
            case BR -> String.format("+55 11 9%d-%04d", block1, block2);
            case ARG -> String.format("+54 11 15-%04d-%04d", block1, block2);
            case CHL -> String.format("+56 9 %04d %04d", block1, block2);
        };
    }

    private String randomFrom(List<String> values, Random random) {
        return values.get(random.nextInt(values.size()));
    }
}

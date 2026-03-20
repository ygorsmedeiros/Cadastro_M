package com.ygor.cadastromestre.Controller;

import com.ygor.cadastromestre.model.CountryFilter;
import com.ygor.cadastromestre.model.EscalationContact;
import com.ygor.cadastromestre.model.PersonContact;
import com.ygor.cadastromestre.model.ServiceContact;
import com.ygor.cadastromestre.service.CadastroMestreService;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class CadastroMestreController implements Initializable {

    private final CadastroMestreService service = new CadastroMestreService();
    private final ToggleGroup countryToggleGroup = new ToggleGroup();
    private final List<SelectorConfig<?>> selectors = new ArrayList<>();
    private CountryFilter activeCountry = CountryFilter.BR;
    private SelectorConfig<?> openSelector;
    private SelectorConfig<String> flowCategorySelector;
    private SelectorConfig<String> flowContextSelector;
    private FlowCategory selectedFlowCategory = FlowCategory.GIM;
    private EscalationLevel flowLevel = EscalationLevel.PONTO_FOCAL;
    private PersonContact currentFlowPerson;
    private EscalationContact currentFlowEscalationContact;
    private ServiceContact currentFlowServiceContact;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox sidebar;

    @FXML
    private ToggleButton countryBrButton;

    @FXML
    private ToggleButton countryArgButton;

    @FXML
    private ToggleButton countryChlButton;

    @FXML
    private Label countryActiveLabel;

    @FXML
    private Button flowCategorySelectButton;

    @FXML
    private VBox flowCategorySelectPanel;

    @FXML
    private TextField flowCategorySearchField;

    @FXML
    private ListView<String> flowCategoryListView;

    @FXML
    private Button flowContextSelectButton;

    @FXML
    private VBox flowContextSelectPanel;

    @FXML
    private TextField flowContextSearchField;

    @FXML
    private ListView<String> flowContextListView;

    @FXML
    private Label flowCurrentRoleLabel;

    @FXML
    private Label flowResponsibleLabel;

    @FXML
    private Label flowStatusLabel;

    @FXML
    private Button flowEscalateButton;

    @FXML
    private Button flowResetButton;

    @FXML
    private Button flowCopyContactButton;

    @FXML
    private Button flowCopyPhoneButton;

    @FXML
    private Button ccsSelectButton;

    @FXML
    private VBox ccsSelectPanel;

    @FXML
    private TextField ccsSearchField;

    @FXML
    private ListView<String> ccsListView;

    @FXML
    private Button gimSelectButton;

    @FXML
    private VBox gimSelectPanel;

    @FXML
    private TextField gimSearchField;

    @FXML
    private ListView<String> gimListView;

    @FXML
    private Button supportSelectButton;

    @FXML
    private VBox supportSelectPanel;

    @FXML
    private TextField supportSearchField;

    @FXML
    private ListView<String> supportListView;

    @FXML
    private Button applicationSelectButton;

    @FXML
    private VBox applicationSelectPanel;

    @FXML
    private TextField applicationSearchField;

    @FXML
    private ListView<String> applicationListView;

    @FXML
    private Button engineeringSelectButton;

    @FXML
    private VBox engineeringSelectPanel;

    @FXML
    private TextField engineeringSearchField;

    @FXML
    private ListView<String> engineeringListView;

    @FXML
    private Button serviceSelectButton;

    @FXML
    private VBox serviceSelectPanel;

    @FXML
    private TextField serviceSearchField;

    @FXML
    private ListView<String> serviceListView;

    @FXML
    private Label ccsHeadLabel;

    @FXML
    private Label ccsLeaderLabel;

    @FXML
    private Label ccsFocalLabel;

    @FXML
    private Label ccsFastcomnsLabel;

    @FXML
    private Label ccsTowerLabel;

    @FXML
    private Label gimHeadLabel;

    @FXML
    private Label gimLeaderLabel;

    @FXML
    private Label gimFocalLabel;

    @FXML
    private Label gimFastcomnsLabel;

    @FXML
    private Label gimTowerLabel;

    @FXML
    private Label supportHeadLabel;

    @FXML
    private Label supportLeaderLabel;

    @FXML
    private Label supportFocalLabel;

    @FXML
    private Label supportFastcomnsLabel;

    @FXML
    private Label supportTowerLabel;

    @FXML
    private Label applicationHeadLabel;

    @FXML
    private Label applicationLeaderLabel;

    @FXML
    private Label applicationFocalLabel;

    @FXML
    private Label applicationFastcomnsLabel;

    @FXML
    private Label applicationTowerLabel;

    @FXML
    private Label engineeringHeadLabel;

    @FXML
    private Label engineeringLeaderLabel;

    @FXML
    private Label engineeringFocalLabel;

    @FXML
    private Label engineeringFastcomnsLabel;

    @FXML
    private Label engineeringTowerLabel;

    @FXML
    private Label serviceNameLabel;

    @FXML
    private Label serviceSpecialistLabel;

    @FXML
    private Label serviceFastcomnsLabel;

    @FXML
    private Label serviceTowerLabel;

    @FXML
    private Label serviceNotesLabel;

    @FXML
    private BorderPane Bord_root_pane;

    @FXML
    public void DescSigla() {
        try {
            FXMLLoader loader = new FXMLLoader(DescriptorSigla.class.getResource("/com/ygor/cadastromestre/DescriptorSigla.fxml"));
            Parent sidebarDireita = loader.load();

            Bord_root_pane.setRight(sidebarDireita);

            sidebarDireita.setTranslateX(400);
            TranslateTransition transition = new TranslateTransition(Duration.millis(300), sidebarDireita);
            transition.setToX(0);
            transition.setInterpolator(javafx.animation.Interpolator.EASE_BOTH);
            transition.play();

        } catch (IOException e) {
            System.err.println("Erro ao carregar a Sidebar: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private boolean sidebarExpanded = true;

    private enum FlowCategory {
        CCS("Equipe CCS"),
        GIM("Equipe GIM"),
        SUPORTE("Suporte Tecnico"),
        APLICACAO("Aplicacao"),
        ENGENHARIA("Engenharia"),
        SERVICOS("Busca por Servicos");

        private final String label;

        FlowCategory(String label) {
            this.label = label;
        }

        public String label() {
            return label;
        }

        public static FlowCategory fromLabel(String label) {
            for (FlowCategory value : values()) {
                if (value.label.equals(label)) {
                    return value;
                }
            }
            return GIM;
        }
    }

    private enum EscalationLevel {
        PONTO_FOCAL("Ponto Focal"),
        LIDER("Lider"),
        HEAD("Head"),
        ESPECIALISTA("Especialista");

        private final String label;

        EscalationLevel(String label) {
            this.label = label;
        }

        public String label() {
            return label;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureCountryFilter();
        configureSelectors();
        configureFlowAssistant();
        applyCountryFilter(CountryFilter.BR);
        rootPane.addEventFilter(MouseEvent.MOUSE_PRESSED, this::closeSelectorWhenClickingOutside);

    }

    @FXML
    private void toggleSidebar() {
        double targetWidth = sidebarExpanded ? 70.0 : 200.0;

        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(220), new KeyValue(sidebar.prefWidthProperty(), targetWidth))
        );
        animation.play();
        sidebarExpanded = !sidebarExpanded;
    }

    @FXML
    private void fecharSidebar() {
        if (sidebarExpanded) {
            toggleSidebar();
        }
    }

    private void configureCountryFilter() {
        setupCountryButton(countryBrButton, CountryFilter.BR);
        setupCountryButton(countryArgButton, CountryFilter.ARG);
        setupCountryButton(countryChlButton, CountryFilter.CHL);

        countryToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == null) {
                if (oldToggle != null) {
                    oldToggle.setSelected(true);
                }
                return;
            }

            CountryFilter selectedCountry = (CountryFilter) newToggle.getUserData();
            applyCountryFilter(selectedCountry);
        });

        countryBrButton.setSelected(true);
    }

    private void setupCountryButton(ToggleButton button, CountryFilter countryFilter) {
        button.setToggleGroup(countryToggleGroup);
        button.setUserData(countryFilter);
    }

    private void configureSelectors() {
        selectors.add(buildEscalationSelector(
                ccsSelectButton, ccsSelectPanel, ccsSearchField, ccsListView,
                service::getCcsOptions,
                service::findCcsEscalation,
                contact -> updateEscalationLabels(contact, ccsHeadLabel, ccsLeaderLabel, ccsFocalLabel, ccsFastcomnsLabel, ccsTowerLabel),
                true
        ));

        selectors.add(buildEscalationSelector(
                gimSelectButton, gimSelectPanel, gimSearchField, gimListView,
                service::getGimOptions,
                service::findGimEscalation,
                contact -> updateEscalationLabels(contact, gimHeadLabel, gimLeaderLabel, gimFocalLabel, gimFastcomnsLabel, gimTowerLabel),
                false
        ));

        selectors.add(buildEscalationSelector(
                supportSelectButton, supportSelectPanel, supportSearchField, supportListView,
                service::getSupportOptions,
                service::findSupportEscalation,
                contact -> updateEscalationLabels(contact, supportHeadLabel, supportLeaderLabel, supportFocalLabel, supportFastcomnsLabel, supportTowerLabel),
                false
        ));

        selectors.add(buildEscalationSelector(
                applicationSelectButton, applicationSelectPanel, applicationSearchField, applicationListView,
                service::getApplicationOptions,
                service::findApplicationEscalation,
                contact -> updateEscalationLabels(contact, applicationHeadLabel, applicationLeaderLabel, applicationFocalLabel, applicationFastcomnsLabel, applicationTowerLabel),
                false
        ));

        selectors.add(buildEscalationSelector(
                engineeringSelectButton, engineeringSelectPanel, engineeringSearchField, engineeringListView,
                service::getEngineeringOptions,
                service::findEngineeringEscalation,
                contact -> updateEscalationLabels(contact, engineeringHeadLabel, engineeringLeaderLabel, engineeringFocalLabel, engineeringFastcomnsLabel, engineeringTowerLabel),
                false
        ));

        selectors.add(buildServiceSelector(
                serviceSelectButton, serviceSelectPanel, serviceSearchField, serviceListView,
                service::getServiceOptions,
                service::findServiceEscalation,
                this::updateServiceLabels
        ));

        selectors.forEach(this::setupSelectorBehavior);
    }

    private void configureFlowAssistant() {
        flowCategorySelector = new SelectorConfig<>(
                flowCategorySelectButton,
                flowCategorySelectPanel,
                flowCategorySearchField,
                flowCategoryListView,
                country -> List.of(
                        FlowCategory.CCS.label(),
                        FlowCategory.GIM.label(),
                        FlowCategory.SUPORTE.label(),
                        FlowCategory.APLICACAO.label(),
                        FlowCategory.ENGENHARIA.label(),
                        FlowCategory.SERVICOS.label()
                ),
                (country, selected) -> selected,
                this::handleFlowCategorySelection,
                false
        );

        flowContextSelector = new SelectorConfig<>(
                flowContextSelectButton,
                flowContextSelectPanel,
                flowContextSearchField,
                flowContextListView,
                country -> getFlowContextOptions(selectedFlowCategory, country),
                (country, selected) -> selected,
                this::handleFlowContextSelection,
                false
        );

        selectors.add(flowCategorySelector);
        selectors.add(flowContextSelector);
        setupSelectorBehavior(flowCategorySelector);
        setupSelectorBehavior(flowContextSelector);
    }

    private SelectorConfig<EscalationContact> buildEscalationSelector(
            Button triggerButton,
            VBox panel,
            TextField searchField,
            ListView<String> listView,
            Function<CountryFilter, List<String>> optionsProvider,
            BiFunction<CountryFilter, String, EscalationContact> valueProvider,
            Consumer<EscalationContact> renderer,
            boolean fixed
    ) {
        return new SelectorConfig<>(
                triggerButton,
                panel,
                searchField,
                listView,
                optionsProvider,
                valueProvider,
                renderer,
                fixed
        );
    }

    private SelectorConfig<ServiceContact> buildServiceSelector(
            Button triggerButton,
            VBox panel,
            TextField searchField,
            ListView<String> listView,
            Function<CountryFilter, List<String>> optionsProvider,
            BiFunction<CountryFilter, String, ServiceContact> valueProvider,
            Consumer<ServiceContact> renderer
    ) {
        return new SelectorConfig<>(
                triggerButton,
                panel,
                searchField,
                listView,
                optionsProvider,
                valueProvider,
                renderer,
                false
        );
    }

    private <T> void setupSelectorBehavior(SelectorConfig<T> config) {
        config.panel.setVisible(false);
        config.panel.setManaged(false);
        config.listView.setFixedCellSize(34);
        if (config.fixed) {
            config.triggerButton.setDisable(true);
            config.triggerButton.getStyleClass().add("selector-button-fixed");
        }

        config.listView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    return;
                }

                String selectedForCountry = config.selectedByCountry.get(activeCountry);
                String indicator = Objects.equals(item, selectedForCountry) ? "● " : "○ ";
                setText(indicator + item);
                if (!getStyleClass().contains("selector-item")) {
                    getStyleClass().add("selector-item");
                }
            }
        });

        config.searchField.textProperty().addListener((observable, oldValue, newValue) -> refreshList(config));

        config.triggerButton.setOnAction(event -> {
            if (config.fixed) {
                return;
            }
            toggleSelector(config);
        });

        config.listView.setOnMouseClicked(event -> {
            String selected = config.listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selectOption(config, selected);
            }
        });

        config.searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DOWN) {
                config.listView.requestFocus();
                config.listView.getSelectionModel().selectFirst();
            } else if (event.getCode() == KeyCode.ENTER) {
                String selected = config.listView.getSelectionModel().getSelectedItem();
                if (selected == null && !config.listView.getItems().isEmpty()) {
                    selected = config.listView.getItems().get(0);
                }
                if (selected != null) {
                    selectOption(config, selected);
                }
            } else if (event.getCode() == KeyCode.ESCAPE) {
                closeSelector(config);
            }
        });

        config.listView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String selected = config.listView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selectOption(config, selected);
                }
            } else if (event.getCode() == KeyCode.ESCAPE) {
                closeSelector(config);
            }
        });
    }

    private void applyCountryFilter(CountryFilter country) {
        activeCountry = country;
        countryActiveLabel.setText("Filtro ativo: " + country.displayName());
        closeOpenSelector();

        for (SelectorConfig<?> selector : selectors) {
            renderSelectorForCountry(selector);
        }
    }

    private <T> void renderSelectorForCountry(SelectorConfig<T> config) {
        List<String> options = config.optionsProvider.apply(activeCountry);

        if (options.isEmpty()) {
            config.selectedByCountry.remove(activeCountry);
            config.triggerButton.setText("Selecionar...");
            config.renderer.accept(null);
            refreshList(config);
            return;
        }

        String selected = config.selectedByCountry.get(activeCountry);
        if (selected == null || !options.contains(selected)) {
            selected = options.get(0);
            config.selectedByCountry.put(activeCountry, selected);
        }

        config.triggerButton.setText("● " + selected);
        config.renderer.accept(config.valueProvider.apply(activeCountry, selected));
        refreshList(config);
    }

    private List<String> getFlowContextOptions(FlowCategory category, CountryFilter country) {
        return switch (category) {
            case CCS -> service.getCcsOptions(country);
            case GIM -> service.getGimOptions(country);
            case SUPORTE -> service.getSupportOptions(country);
            case APLICACAO -> service.getApplicationOptions(country);
            case ENGENHARIA -> service.getEngineeringOptions(country);
            case SERVICOS -> service.getServiceOptions(country);
        };
    }

    private void handleFlowCategorySelection(String selectedCategoryLabel) {
        if (selectedCategoryLabel == null || selectedCategoryLabel.isBlank()) {
            return;
        }

        selectedFlowCategory = FlowCategory.fromLabel(selectedCategoryLabel);
        flowStatusLabel.setText("Selecione o contexto para continuar.");
        renderSelectorForCountry(flowContextSelector);
    }

    private void handleFlowContextSelection(String selectedContext) {
        if (selectedContext == null || selectedContext.isBlank()) {
            return;
        }

        if (selectedFlowCategory == FlowCategory.SERVICOS) {
            currentFlowServiceContact = service.findServiceEscalation(activeCountry, selectedContext);
            currentFlowEscalationContact = null;
            flowLevel = EscalationLevel.ESPECIALISTA;
            currentFlowPerson = currentFlowServiceContact != null ? currentFlowServiceContact.specialist() : null;
        } else {
            currentFlowEscalationContact = resolveEscalationContact(selectedFlowCategory, activeCountry, selectedContext);
            currentFlowServiceContact = null;
            flowLevel = EscalationLevel.PONTO_FOCAL;
            currentFlowPerson = currentFlowEscalationContact != null ? currentFlowEscalationContact.pointOfContact() : null;
        }

        if (currentFlowPerson == null) {
            flowStatusLabel.setText("Nenhum responsavel encontrado para o contexto.");
        } else {
            flowStatusLabel.setText("Contato carregado. Use Escalar ou Copiar.");
        }
        updateFlowPanel();
    }

    private EscalationContact resolveEscalationContact(FlowCategory category, CountryFilter country, String context) {
        return switch (category) {
            case CCS -> service.findCcsEscalation(country, context);
            case GIM -> service.findGimEscalation(country, context);
            case SUPORTE -> service.findSupportEscalation(country, context);
            case APLICACAO -> service.findApplicationEscalation(country, context);
            case ENGENHARIA -> service.findEngineeringEscalation(country, context);
            case SERVICOS -> null;
        };
    }

    @FXML
    private void escalateFlowOnce() {
        if (currentFlowEscalationContact == null) {
            flowStatusLabel.setText("Sem escalonamento para o contexto atual.");
            updateFlowPanel();
            return;
        }

        if (flowLevel == EscalationLevel.PONTO_FOCAL) {
            flowLevel = EscalationLevel.LIDER;
            currentFlowPerson = currentFlowEscalationContact.leader();
            flowStatusLabel.setText("Escala atualizada para Lider.");
        } else if (flowLevel == EscalationLevel.LIDER) {
            flowLevel = EscalationLevel.HEAD;
            currentFlowPerson = currentFlowEscalationContact.head();
            flowStatusLabel.setText("Escala atualizada para Head.");
        } else {
            flowStatusLabel.setText("Ja esta no topo da escala.");
        }

        updateFlowPanel();
    }

    @FXML
    private void resetFlowEscalation() {
        if (currentFlowEscalationContact != null) {
            flowLevel = EscalationLevel.PONTO_FOCAL;
            currentFlowPerson = currentFlowEscalationContact.pointOfContact();
            flowStatusLabel.setText("Escala resetada para Ponto Focal.");
        } else if (currentFlowServiceContact != null) {
            flowLevel = EscalationLevel.ESPECIALISTA;
            currentFlowPerson = currentFlowServiceContact.specialist();
            flowStatusLabel.setText("Especialista definido como contato atual.");
        } else {
            flowStatusLabel.setText("Selecione categoria e contexto.");
        }
        updateFlowPanel();
    }

    @FXML
    private void copyCurrentContact() {
        if (currentFlowPerson == null) {
            flowStatusLabel.setText("Nenhum contato para copiar.");
            return;
        }

        String text = String.format(
                "%s | ID: %s | Tel: %s",
                currentFlowPerson.name(),
                currentFlowPerson.employeeId(),
                currentFlowPerson.phone()
        );
        copyToClipboard(text);
        flowStatusLabel.setText("Contato copiado.");
    }

    @FXML
    private void copyCurrentPhone() {
        if (currentFlowPerson == null) {
            flowStatusLabel.setText("Nenhum telefone para copiar.");
            return;
        }

        copyToClipboard(currentFlowPerson.phone());
        flowStatusLabel.setText("Telefone copiado.");
    }

    private void updateFlowPanel() {
        if (currentFlowPerson == null) {
            flowCurrentRoleLabel.setText("Papel atual: -");
            flowResponsibleLabel.setText("Responsavel: -");
            flowEscalateButton.setDisable(true);
            flowResetButton.setDisable(true);
            flowCopyContactButton.setDisable(true);
            flowCopyPhoneButton.setDisable(true);
            return;
        }

        flowCurrentRoleLabel.setText("Papel atual: " + flowLevel.label());
        flowResponsibleLabel.setText(formatPersonLine("Responsavel", currentFlowPerson));

        boolean canEscalate = currentFlowEscalationContact != null && flowLevel != EscalationLevel.HEAD;
        flowEscalateButton.setDisable(!canEscalate);
        flowResetButton.setDisable(false);
        flowCopyContactButton.setDisable(false);
        flowCopyPhoneButton.setDisable(false);

        if (flowLevel == EscalationLevel.PONTO_FOCAL) {
            flowEscalateButton.setText("Escalar para Lider");
        } else if (flowLevel == EscalationLevel.LIDER) {
            flowEscalateButton.setText("Escalar para Head");
        } else {
            flowEscalateButton.setText("No topo da escala");
        }
    }

    private void copyToClipboard(String text) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

    private <T> void toggleSelector(SelectorConfig<T> config) {
        if (config.panel.isVisible()) {
            closeSelector(config);
            return;
        }

        if (openSelector != null && openSelector != config) {
            closeSelector(openSelector);
        }

        config.panel.setManaged(true);
        config.panel.setVisible(true);
        config.searchField.clear();
        refreshList(config);
        openSelector = config;

        Platform.runLater(() -> {
            config.searchField.requestFocus();
            config.searchField.selectAll();
        });
    }

    private void closeSelector(SelectorConfig<?> config) {
        config.panel.setVisible(false);
        config.panel.setManaged(false);
        config.searchField.clear();
        if (openSelector == config) {
            openSelector = null;
        }
    }

    private void closeOpenSelector() {
        if (openSelector != null) {
            closeSelector(openSelector);
        }
    }

    private <T> void refreshList(SelectorConfig<T> config) {
        List<String> options = config.optionsProvider.apply(activeCountry);
        String searchText = config.searchField.getText() == null
                ? ""
                : config.searchField.getText().toLowerCase(Locale.ROOT).trim();

        List<String> filtered = options.stream()
                .filter(item -> item.toLowerCase(Locale.ROOT).contains(searchText))
                .toList();

        config.listView.setItems(FXCollections.observableArrayList(filtered));

        String selected = config.selectedByCountry.get(activeCountry);
        if (selected != null && filtered.contains(selected)) {
            config.listView.getSelectionModel().select(selected);
        }
        config.listView.refresh();
    }

    private <T> void selectOption(SelectorConfig<T> config, String selectedOption) {
        config.selectedByCountry.put(activeCountry, selectedOption);
        config.triggerButton.setText("● " + selectedOption);
        config.renderer.accept(config.valueProvider.apply(activeCountry, selectedOption));
        closeSelector(config);
    }

    private void closeSelectorWhenClickingOutside(MouseEvent event) {
        if (openSelector == null) {
            return;
        }

        Object targetObj = event.getTarget();
        if (!(targetObj instanceof Node target)) {
            return;
        }

        if (isInside(target, openSelector.panel) || isInside(target, openSelector.triggerButton)) {
            return;
        }

        closeOpenSelector();
    }

    private boolean isInside(Node node, Node potentialAncestor) {
        Node current = node;
        while (current != null) {
            if (current == potentialAncestor) {
                return true;
            }
            current = current.getParent();
        }
        return false;
    }

    private void updateEscalationLabels(
            EscalationContact contact,
            Label headLabel,
            Label leaderLabel,
            Label focalLabel,
            Label fastcomnsLabel,
            Label towerLabel
    ) {
        if (contact == null) {
            headLabel.setText("Head: -");
            leaderLabel.setText("Lider: -");
            focalLabel.setText("Ponto focal: -");
            fastcomnsLabel.setText("Grupo Fastcomns: -");
            towerLabel.setText("Torre de atribuicao: -");
            return;
        }

        headLabel.setText(formatPersonLine("Head", contact.head()));
        leaderLabel.setText(formatPersonLine("Lider", contact.leader()));
        focalLabel.setText(formatPersonLine("Ponto focal", contact.pointOfContact()));
        fastcomnsLabel.setText("Grupo Fastcomns: " + contact.fastcomnsGroup());
        towerLabel.setText("Torre de atribuicao: " + contact.assignmentTower());
    }

    private void updateServiceLabels(ServiceContact contact) {
        if (contact == null) {
            serviceNameLabel.setText("Servico: -");
            serviceSpecialistLabel.setText("Especialista: -");
            serviceFastcomnsLabel.setText("Grupo Fastcomns: -");
            serviceTowerLabel.setText("Torre de atribuicao: -");
            serviceNotesLabel.setText("Observacao: -");
            return;
        }

        serviceNameLabel.setText("Servico: " + contact.service());
        serviceSpecialistLabel.setText(formatPersonLine("Especialista", contact.specialist()));
        serviceFastcomnsLabel.setText("Grupo Fastcomns: " + contact.fastcomnsGroup());
        serviceTowerLabel.setText("Torre de atribuicao: " + contact.assignmentTower());
        serviceNotesLabel.setText("Observacao: " + contact.notes());
    }

    private String formatPersonLine(String role, PersonContact person) {
        if (person == null) {
            return role + ": -";
        }

        return String.format(
                "%s: %s  |  ID: %s  |  Tel: %s",
                role,
                person.name(),
                person.employeeId(),
                person.phone()
        );
    }

    private static final class SelectorConfig<T> {
        private final Button triggerButton;
        private final VBox panel;
        private final TextField searchField;
        private final ListView<String> listView;
        private final Function<CountryFilter, List<String>> optionsProvider;
        private final BiFunction<CountryFilter, String, T> valueProvider;
        private final Consumer<T> renderer;
        private final boolean fixed;
        private final EnumMap<CountryFilter, String> selectedByCountry = new EnumMap<>(CountryFilter.class);

        private SelectorConfig(
                Button triggerButton,
                VBox panel,
                TextField searchField,
                ListView<String> listView,
                Function<CountryFilter, List<String>> optionsProvider,
                BiFunction<CountryFilter, String, T> valueProvider,
                Consumer<T> renderer,
                boolean fixed
        ) {
            this.triggerButton = triggerButton;
            this.panel = panel;
            this.searchField = searchField;
            this.listView = listView;
            this.optionsProvider = optionsProvider;
            this.valueProvider = valueProvider;
            this.renderer = renderer;
            this.fixed = fixed;
        }
    }
}

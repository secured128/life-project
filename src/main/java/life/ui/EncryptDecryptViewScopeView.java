package life.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import openu.cryptograthy.CRYPTOSYSTEM_TYPE;
import openu.cryptograthy.CryptosystemApi;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static openu.cryptograthy.CryptosystemApi.CHARECTERS_NUMBER;

@SpringView(name = EncryptDecryptViewScopeView.EN_DE_VIEW_NAME)
public class EncryptDecryptViewScopeView extends VerticalLayout implements View {
    public static final String EN_DE_VIEW_NAME = "EncryptDecryptView";
    private static final long serialVersionUID = 5784972560238064106L;

    private static final String substitutionSypherDefaultKey = "abcdefghijklmnopqrstuvwxyz";

    ComboBox cryptoSystemCombo;
    ComboBox shiftCryptosystemKeyLength;
    TextField substitutionCypherKey;
    CryptosystemApi cryptosystem;

    // A new instance will be created for every view instance created
    @Autowired
    private DemoViewScopeBean viewBean;
    // The same instance will be used by all views of the UI
    @Autowired
    private DemoUIScopeBean uiBean;

    @PostConstruct
    void init() {
        cryptoSystemCombo = new ComboBox("CryptosystemApi");
        shiftCryptosystemKeyLength = new ComboBox("Shift CryptosystemApi Key Length");
        List<Integer> integerList = new ArrayList<Integer>();
        for (int i = 0; i <= CHARECTERS_NUMBER; i++) {
            integerList.add(i);
        }
        shiftCryptosystemKeyLength.setItems(integerList);
        shiftCryptosystemKeyLength.setSelectedItem(3);
        cryptosystem = CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem("3");
        shiftCryptosystemKeyLength.setEmptySelectionAllowed(false);

        substitutionCypherKey = new TextField("Substitution CryptosystemApi Key");
        substitutionCypherKey.setValue(substitutionSypherDefaultKey);
        substitutionCypherKey.setMaxLength(CHARECTERS_NUMBER);
        substitutionCypherKey.setRequiredIndicatorVisible(true);

        cryptoSystemCombo.setItems(CRYPTOSYSTEM_TYPE.SHIFT.name(), CRYPTOSYSTEM_TYPE.SUBSTITUTION.name());
        cryptoSystemCombo.setSelectedItem(CRYPTOSYSTEM_TYPE.SHIFT.name());
        cryptoSystemCombo.setEmptySelectionAllowed(false);

        TextArea planeText = new TextArea();
        Button encryptButton = new Button("Encrypt");
        TextArea encryptedText = new TextArea();
        Button decryptButton = new Button("Decrypt");
        addComponent(cryptoSystemCombo);
        addComponent(shiftCryptosystemKeyLength);
        addComponent(planeText);
        addComponent(encryptButton);
        addComponent(encryptedText);
        addComponent(decryptButton);

        cryptoSystemCombo.addValueChangeListener(event -> {
            CRYPTOSYSTEM_TYPE type = CRYPTOSYSTEM_TYPE.valueOf(cryptoSystemCombo.getSelectedItem().get().toString());
            if (type == CRYPTOSYSTEM_TYPE.SHIFT) {
                removeComponent(substitutionCypherKey);
                addComponent(shiftCryptosystemKeyLength, 1);
            } else {
                removeComponent(shiftCryptosystemKeyLength);
                addComponent(substitutionCypherKey, 1);
            }
        });

        encryptButton.addClickListener(clickEvent -> {
            Notification.show("Encrypting ...");
            encryptedText.setValue(getCryptoSystem().encrypt(planeText.getValue()));
        });
        decryptButton.addClickListener(clickEvent -> {
            Notification.show("Decrypting ...");
            planeText.setValue(getCryptoSystem().decrypt(encryptedText.getValue()));

        });
    }


    private CryptosystemApi getCryptoSystem() {
        CRYPTOSYSTEM_TYPE type = CRYPTOSYSTEM_TYPE.valueOf(cryptoSystemCombo.getSelectedItem().get().toString());
        if (type == CRYPTOSYSTEM_TYPE.SHIFT) {
            String key = shiftCryptosystemKeyLength.getSelectedItem().get().toString();
            return CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem(key);
        } else {
            String key = substitutionCypherKey.getValue();
            return cryptosystem = CRYPTOSYSTEM_TYPE.SUBSTITUTION.getCryptosystem(key);
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {
        System.out.print("public void enter(ViewChangeEvent event)");
    }
}

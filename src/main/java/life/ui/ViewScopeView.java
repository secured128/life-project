package life.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = ViewScopeView.VIEW_NAME)
public class ViewScopeView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "view";
    private static final long serialVersionUID = 5784972560238064106L;
    // A new instance will be created for every view instance created
    @Autowired
    private DemoViewScopeBean viewBean;
    // The same instance will be used by all views of the UI
    @Autowired
    private DemoUIScopeBean uiBean;

    @PostConstruct
    void init() {
        addComponent(new Label("This is a view scoped view"));
        addComponent(new Label(uiBean.getData()));
        addComponent(new Label(viewBean.getData()));
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}

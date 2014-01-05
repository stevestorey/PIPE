package pipe.views.builder;

import pipe.models.component.Token;
import pipe.views.TokenView;

public class TokenViewBuilder {

    private final Token model;

    public TokenViewBuilder(Token model) {
        this.model = model;
    }

    public TokenView build() {
        return new TokenView(model);
    }

}

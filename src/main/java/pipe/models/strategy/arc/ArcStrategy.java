package pipe.models.strategy.arc;

import pipe.models.component.Arc;
import pipe.models.component.ArcType;
import pipe.models.component.Connectable;

/**
 * Arc strategy used to determine arc behaviour
 */
public interface ArcStrategy<S extends Connectable<S>, T extends Connectable<T>> {
    public boolean canFire(Arc<S, T> arc);

    ArcType getType();
}

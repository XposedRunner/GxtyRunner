package rx.observers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Notification;
import rx.Observer;
import rx.exceptions.CompositeException;

@Deprecated
public class TestObserver<T> implements Observer<T> {
    private static final Observer<Object> INERT = new 1();
    private final Observer<T> delegate;
    private final List<Notification<T>> onCompletedEvents;
    private final List<Throwable> onErrorEvents;
    private final List<T> onNextEvents;

    public TestObserver(Observer<T> observer) {
        this.onNextEvents = new ArrayList();
        this.onErrorEvents = new ArrayList();
        this.onCompletedEvents = new ArrayList();
        this.delegate = observer;
    }

    public TestObserver() {
        this.onNextEvents = new ArrayList();
        this.onErrorEvents = new ArrayList();
        this.onCompletedEvents = new ArrayList();
        this.delegate = INERT;
    }

    public void onCompleted() {
        this.onCompletedEvents.add(Notification.createOnCompleted());
        this.delegate.onCompleted();
    }

    public List<Notification<T>> getOnCompletedEvents() {
        return Collections.unmodifiableList(this.onCompletedEvents);
    }

    public void onError(Throwable th) {
        this.onErrorEvents.add(th);
        this.delegate.onError(th);
    }

    public List<Throwable> getOnErrorEvents() {
        return Collections.unmodifiableList(this.onErrorEvents);
    }

    public void onNext(T t) {
        this.onNextEvents.add(t);
        this.delegate.onNext(t);
    }

    public List<T> getOnNextEvents() {
        return Collections.unmodifiableList(this.onNextEvents);
    }

    public List<Object> getEvents() {
        List arrayList = new ArrayList();
        arrayList.add(this.onNextEvents);
        arrayList.add(this.onErrorEvents);
        arrayList.add(this.onCompletedEvents);
        return Collections.unmodifiableList(arrayList);
    }

    public void assertReceivedOnNext(List<T> list) {
        if (this.onNextEvents.size() != list.size()) {
            assertionError("Number of items does not match. Provided: " + list.size() + "  Actual: " + this.onNextEvents.size() + ".\n" + "Provided values: " + list + "\n" + "Actual values: " + this.onNextEvents + "\n");
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            Object obj2 = this.onNextEvents.get(i);
            if (obj == null) {
                if (obj2 != null) {
                    assertionError("Value at index: " + i + " expected to be [null] but was: [" + obj2 + "]\n");
                }
            } else if (!obj.equals(obj2)) {
                assertionError("Value at index: " + i + " expected to be [" + obj + "] (" + obj.getClass().getSimpleName() + ") but was: [" + obj2 + "] (" + (obj2 != null ? obj2.getClass().getSimpleName() : "null") + ")\n");
            }
        }
    }

    public void assertTerminalEvent() {
        if (this.onErrorEvents.size() > 1) {
            assertionError("Too many onError events: " + this.onErrorEvents.size());
        }
        if (this.onCompletedEvents.size() > 1) {
            assertionError("Too many onCompleted events: " + this.onCompletedEvents.size());
        }
        if (this.onCompletedEvents.size() == 1 && this.onErrorEvents.size() == 1) {
            assertionError("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (this.onCompletedEvents.isEmpty() && this.onErrorEvents.isEmpty()) {
            assertionError("No terminal events received.");
        }
    }

    final void assertionError(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length() + 32);
        stringBuilder.append(str).append(" (");
        int size = this.onCompletedEvents.size();
        stringBuilder.append(size).append(" completion");
        if (size != 1) {
            stringBuilder.append('s');
        }
        stringBuilder.append(')');
        if (!this.onErrorEvents.isEmpty()) {
            size = this.onErrorEvents.size();
            stringBuilder.append(" (+").append(size).append(" error");
            if (size != 1) {
                stringBuilder.append('s');
            }
            stringBuilder.append(')');
        }
        AssertionError assertionError = new AssertionError(stringBuilder.toString());
        if (!this.onErrorEvents.isEmpty()) {
            if (this.onErrorEvents.size() == 1) {
                assertionError.initCause((Throwable) this.onErrorEvents.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.onErrorEvents));
            }
        }
        throw assertionError;
    }
}

package com.nostra13.universalimageloader.core.assist.deque;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedBlockingDeque<E> extends AbstractQueue<E> implements a<E>, Serializable {
    private static final long serialVersionUID = -387911632671998426L;
    private transient int a;
    private final int capacity;
    transient d<E> first;
    transient d<E> last;
    final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    private abstract class a implements Iterator<E> {
        d<E> a;
        E b;
        final /* synthetic */ LinkedBlockingDeque c;
        private d<E> d;

        abstract d<E> a();

        abstract d<E> a(d<E> dVar);

        a(LinkedBlockingDeque linkedBlockingDeque) {
            this.c = linkedBlockingDeque;
            ReentrantLock reentrantLock = linkedBlockingDeque.lock;
            reentrantLock.lock();
            try {
                this.a = a();
                this.b = this.a == null ? null : this.a.a;
            } finally {
                reentrantLock.unlock();
            }
        }

        private d<E> b(d<E> dVar) {
            while (true) {
                d<E> a = a(dVar);
                if (a == null) {
                    return null;
                }
                if (a.a != null) {
                    return a;
                }
                if (a == dVar) {
                    return a();
                }
                dVar = a;
            }
        }

        void b() {
            ReentrantLock reentrantLock = this.c.lock;
            reentrantLock.lock();
            try {
                this.a = b(this.a);
                this.b = this.a == null ? null : this.a.a;
            } finally {
                reentrantLock.unlock();
            }
        }

        public boolean hasNext() {
            return this.a != null;
        }

        public E next() {
            if (this.a == null) {
                throw new NoSuchElementException();
            }
            this.d = this.a;
            E e = this.b;
            b();
            return e;
        }

        public void remove() {
            d dVar = this.d;
            if (dVar == null) {
                throw new IllegalStateException();
            }
            this.d = null;
            ReentrantLock reentrantLock = this.c.lock;
            reentrantLock.lock();
            try {
                if (dVar.a != null) {
                    this.c.unlink(dVar);
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                reentrantLock.unlock();
            }
        }
    }

    private class b extends a {
        final /* synthetic */ LinkedBlockingDeque d;

        private b(LinkedBlockingDeque linkedBlockingDeque) {
            this.d = linkedBlockingDeque;
            super(linkedBlockingDeque);
        }

        d<E> a() {
            return this.d.last;
        }

        d<E> a(d<E> dVar) {
            return dVar.b;
        }
    }

    private class c extends a {
        final /* synthetic */ LinkedBlockingDeque d;

        private c(LinkedBlockingDeque linkedBlockingDeque) {
            this.d = linkedBlockingDeque;
            super(linkedBlockingDeque);
        }

        d<E> a() {
            return this.d.first;
        }

        d<E> a(d<E> dVar) {
            return dVar.c;
        }
    }

    static final class d<E> {
        E a;
        d<E> b;
        d<E> c;

        d(E e) {
            this.a = e;
        }
    }

    public LinkedBlockingDeque() {
        this(Integer.MAX_VALUE);
    }

    public LinkedBlockingDeque(int i) {
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = i;
    }

    public LinkedBlockingDeque(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (Object next : collection) {
                if (next == null) {
                    throw new NullPointerException();
                } else if (!b(new d(next))) {
                    throw new IllegalStateException("Deque full");
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    private boolean a(d<E> dVar) {
        if (this.a >= this.capacity) {
            return false;
        }
        d dVar2 = this.first;
        dVar.c = dVar2;
        this.first = dVar;
        if (this.last == null) {
            this.last = dVar;
        } else {
            dVar2.b = dVar;
        }
        this.a++;
        this.notEmpty.signal();
        return true;
    }

    private boolean b(d<E> dVar) {
        if (this.a >= this.capacity) {
            return false;
        }
        d dVar2 = this.last;
        dVar.b = dVar2;
        this.last = dVar;
        if (this.first == null) {
            this.first = dVar;
        } else {
            dVar2.c = dVar;
        }
        this.a++;
        this.notEmpty.signal();
        return true;
    }

    private E a() {
        d dVar = this.first;
        if (dVar == null) {
            return null;
        }
        d dVar2 = dVar.c;
        E e = dVar.a;
        dVar.a = null;
        dVar.c = dVar;
        this.first = dVar2;
        if (dVar2 == null) {
            this.last = null;
        } else {
            dVar2.b = null;
        }
        this.a--;
        this.notFull.signal();
        return e;
    }

    private E b() {
        d dVar = this.last;
        if (dVar == null) {
            return null;
        }
        d dVar2 = dVar.b;
        E e = dVar.a;
        dVar.a = null;
        dVar.b = dVar;
        this.last = dVar2;
        if (dVar2 == null) {
            this.first = null;
        } else {
            dVar2.c = null;
        }
        this.a--;
        this.notFull.signal();
        return e;
    }

    void unlink(d<E> dVar) {
        d dVar2 = dVar.b;
        d dVar3 = dVar.c;
        if (dVar2 == null) {
            a();
        } else if (dVar3 == null) {
            b();
        } else {
            dVar2.c = dVar3;
            dVar3.b = dVar2;
            dVar.a = null;
            this.a--;
            this.notFull.signal();
        }
    }

    public void addFirst(E e) {
        if (!offerFirst(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public void addLast(E e) {
        if (!offerLast(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public boolean offerFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean a = a(dVar);
            return a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean offerLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean b = b(dVar);
            return b;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void putFirst(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (!a(dVar)) {
            try {
                this.notFull.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void putLast(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (!b(dVar)) {
            try {
                this.notFull.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public boolean offerFirst(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        while (!a(dVar)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.notFull.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public boolean offerLast(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        d dVar = new d(e);
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        while (!b(dVar)) {
            try {
                if (toNanos <= 0) {
                    return false;
                }
                toNanos = this.notFull.awaitNanos(toNanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        reentrantLock.unlock();
        return true;
    }

    public E removeFirst() {
        E pollFirst = pollFirst();
        if (pollFirst != null) {
            return pollFirst;
        }
        throw new NoSuchElementException();
    }

    public E removeLast() {
        E pollLast = pollLast();
        if (pollLast != null) {
            return pollLast;
        }
        throw new NoSuchElementException();
    }

    public E pollFirst() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E a = a();
            return a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E pollLast() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E b = b();
            return b;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E takeFirst() throws InterruptedException {
        E a;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (true) {
            try {
                a = a();
                if (a != null) {
                    break;
                }
                this.notEmpty.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return a;
    }

    public E takeLast() throws InterruptedException {
        E b;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        while (true) {
            try {
                b = b();
                if (b != null) {
                    break;
                }
                this.notEmpty.await();
            } finally {
                reentrantLock.unlock();
            }
        }
        return b;
    }

    public E pollFirst(long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E a = a();
                if (a != null) {
                    reentrantLock.unlock();
                    return a;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.notEmpty.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E pollLast(long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        long j2 = toNanos;
        while (true) {
            try {
                E b = b();
                if (b != null) {
                    reentrantLock.unlock();
                    return b;
                } else if (j2 <= 0) {
                    break;
                } else {
                    j2 = this.notEmpty.awaitNanos(j2);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public E getFirst() {
        E peekFirst = peekFirst();
        if (peekFirst != null) {
            return peekFirst;
        }
        throw new NoSuchElementException();
    }

    public E getLast() {
        E peekLast = peekLast();
        if (peekLast != null) {
            return peekLast;
        }
        throw new NoSuchElementException();
    }

    public E peekFirst() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E e = this.first == null ? null : this.first.a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public E peekLast() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            E e = this.last == null ? null : this.last.a;
            reentrantLock.unlock();
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public boolean removeFirstOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (d dVar = this.first; dVar != null; dVar = dVar.c) {
                if (obj.equals(dVar.a)) {
                    unlink(dVar);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean removeLastOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (d dVar = this.last; dVar != null; dVar = dVar.b) {
                if (obj.equals(dVar.a)) {
                    unlink(dVar);
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public boolean offer(E e) {
        return offerLast(e);
    }

    public void put(E e) throws InterruptedException {
        putLast(e);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        return offerLast(e, j, timeUnit);
    }

    public E remove() {
        return removeFirst();
    }

    public E poll() {
        return pollFirst();
    }

    public E take() throws InterruptedException {
        return takeFirst();
    }

    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        return pollFirst(j, timeUnit);
    }

    public E element() {
        return getFirst();
    }

    public E peek() {
        return peekFirst();
    }

    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.capacity - this.a;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection == this) {
            throw new IllegalArgumentException();
        } else {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.a);
                for (int i2 = 0; i2 < min; i2++) {
                    collection.add(this.first.a);
                    a();
                }
                return min;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void push(E e) {
        addFirst(e);
    }

    public E pop() {
        return removeFirst();
    }

    public boolean remove(Object obj) {
        return removeFirstOccurrence(obj);
    }

    public int size() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.a;
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            for (d dVar = this.first; dVar != null; dVar = dVar.c) {
                if (obj.equals(dVar.a)) {
                    return true;
                }
            }
            reentrantLock.unlock();
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Object[] toArray() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.a];
            int i = 0;
            d dVar = this.first;
            while (dVar != null) {
                int i2 = i + 1;
                objArr[i] = dVar.a;
                dVar = dVar.c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (tArr.length < this.a) {
                tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.a);
            }
            int i = 0;
            d dVar = this.first;
            while (dVar != null) {
                int i2 = i + 1;
                tArr[i] = dVar.a;
                dVar = dVar.c;
                i = i2;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            reentrantLock.unlock();
            return tArr;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public String toString() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            String str;
            d dVar = this.first;
            if (dVar == null) {
                str = "[]";
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                d dVar2 = dVar;
                while (true) {
                    Object obj = dVar2.a;
                    if (obj == this) {
                        obj = "(this Collection)";
                    }
                    stringBuilder.append(obj);
                    dVar = dVar2.c;
                    if (dVar == null) {
                        break;
                    }
                    stringBuilder.append(',').append(' ');
                    dVar2 = dVar;
                }
                str = stringBuilder.append(']').toString();
                reentrantLock.unlock();
            }
            return str;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            d dVar = this.first;
            while (dVar != null) {
                dVar.a = null;
                d dVar2 = dVar.c;
                dVar.b = null;
                dVar.c = null;
                dVar = dVar2;
            }
            this.last = null;
            this.first = null;
            this.a = 0;
            this.notFull.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> iterator() {
        return new c();
    }

    public Iterator<E> descendingIterator() {
        return new b();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            objectOutputStream.defaultWriteObject();
            for (d dVar = this.first; dVar != null; dVar = dVar.c) {
                objectOutputStream.writeObject(dVar.a);
            }
            objectOutputStream.writeObject(null);
        } finally {
            reentrantLock.unlock();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.a = 0;
        this.first = null;
        this.last = null;
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }
}

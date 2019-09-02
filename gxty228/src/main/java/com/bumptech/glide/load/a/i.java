package com.bumptech.glide.load.a;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: StreamLocalUriFetcher */
public class i extends g<InputStream> {
    private static final UriMatcher a = new UriMatcher(-1);

    protected /* synthetic */ Object b(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        return a(uri, contentResolver);
    }

    static {
        a.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        a.addURI("com.android.contacts", "contacts/lookup/*", 1);
        a.addURI("com.android.contacts", "contacts/#/photo", 2);
        a.addURI("com.android.contacts", "contacts/#", 3);
        a.addURI("com.android.contacts", "contacts/#/display_photo", 4);
    }

    public i(Context context, Uri uri) {
        super(context, uri);
    }

    protected InputStream a(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        return a(uri, contentResolver, a.match(uri));
    }

    protected void a(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    private InputStream a(Uri uri, ContentResolver contentResolver, int i) throws FileNotFoundException {
        switch (i) {
            case 1:
            case 3:
                if (i == 1) {
                    uri = Contacts.lookupContact(contentResolver, uri);
                    if (uri == null) {
                        throw new FileNotFoundException("Contact cannot be found");
                    }
                }
                return a(contentResolver, uri);
            default:
                return contentResolver.openInputStream(uri);
        }
    }

    @TargetApi(14)
    private InputStream a(ContentResolver contentResolver, Uri uri) {
        if (VERSION.SDK_INT < 14) {
            return Contacts.openContactPhotoInputStream(contentResolver, uri);
        }
        return Contacts.openContactPhotoInputStream(contentResolver, uri, true);
    }
}
